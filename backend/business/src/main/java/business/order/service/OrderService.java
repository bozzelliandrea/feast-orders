package business.order.service;

import arch.component.PaginationUtils;
import arch.search.QueryOperator;
import arch.service.BaseCRUDService;
import atomic.bean.KeyMap;
import atomic.bean.OrderContent;
import atomic.entity.Order;
import atomic.entity.PrinterCfg;
import atomic.enums.CategoryProcessingZone;
import atomic.enums.DiscountType;
import atomic.enums.OrderStatus;
import atomic.repository.OrderRepository;
import business.category.dto.CategoryDTO;
import business.category.service.CategoryService;
import business.discount.dto.DiscountDTO;
import business.discount.service.DiscountService;
import business.order.OrderSpecificationBuilder;
import business.order.converter.OrderConverter;
import business.order.dto.DetailedOrderDTO;
import business.order.dto.PagedOrderDTO;
import business.order.exception.OrderNotFoundException;
import business.order.exception.OrderUpdateException;
import business.printer.service.PrinterAsyncService;
import business.stats.StatsService;
import business.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class OrderService extends BaseCRUDService<Order, Long> {

    private final static Logger _LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderHistoryService orderHistoryService;
    private final OrderRepository repository;
    private final OrderConverter converter;
    private final CategoryService categoryService;
    private final PrinterAsyncService printerAsyncService;
    private final StockService stockService;
    private final DiscountService discountService;

    public OrderService(OrderRepository repository,
                        OrderHistoryService orderHistoryService,
                        OrderConverter converter,
                        CategoryService categoryService,
                        PrinterAsyncService printerAsyncService,
                        StockService stockService,
                        DiscountService discountService) {
        super(repository);
        this.repository = repository;
        this.orderHistoryService = orderHistoryService;
        this.converter = converter;
        this.categoryService = categoryService;
        this.printerAsyncService = printerAsyncService;
        this.stockService = stockService;
        this.discountService = discountService;
    }

    public DetailedOrderDTO getDetailedOrder(Long id) {
        return converter.convertEntityDetailed(super.read(id));
    }

    public DetailedOrderDTO createOrder(DetailedOrderDTO request) {
        Order entity = converter.convertDTO(request);
        entity.setStatsId(StatsService.getSessionStatsId());
        _setOrderProcessingZone(request, entity);
        _manageStockOnOrder(request);
        _evaluateDiscount(request, entity);
        //TODO se flag print = true, mandare in stampa l'ordine
        Order result = super.create(entity);
        return converter.convertEntityDetailed(result);
    }

    //TODO: capire come il client passa le info,
    // se prevedere campi null da non aggiornare oppure la request contiene solo campi da aggiornare
    public DetailedOrderDTO updateOrder(DetailedOrderDTO request) {
        Order savedEntity = super.read(request.getId());
        _setOrderProcessingZone(request, savedEntity);
        _manageStockOnOrder(request);
        _evaluateDiscount(request, savedEntity);
        savedEntity.setNote(request.getNote());
        savedEntity.setContent(request.getContent());
        savedEntity.setClient(request.getClient());
        savedEntity.setPlaceSettingNumber(request.getPlaceSettingNumber().shortValue());
        savedEntity.setTableNumber(request.getTableNumber().shortValue());
        savedEntity.setTotal(request.getTotal());

        Order result = super.update(savedEntity);
        return converter.convertEntityDetailed(result);
    }

    public String patchStatus(Long id, String newStatus) {
        OrderStatus status = OrderStatus.valueOf(newStatus);
        Optional<Order> orderOpt = repository.findById(id);

        if (orderOpt.isPresent()) {
            Order order = orderOpt.get();

            if (order.getStatus() == status) {
                throw new OrderUpdateException("The selected order already have this status.");
            }

            if (status.isClosed()) {
                boolean result = orderHistoryService.create(order);
                if (result)
                    repository.deleteById(id);
            } else {
                order.setStatus(status);
                repository.saveAndFlush(order);
            }

            return "Order saved with status: " + status.name();

        } else {
            throw new OrderNotFoundException("The selected order does not exist in the database.");
        }
    }

    public PagedOrderDTO findAllWithPaginationAndQuery(int page, int size, String query) {
        if (query != null) {
            Pageable paging = PageRequest.of(page, size);

            OrderSpecificationBuilder builder = new OrderSpecificationBuilder();
            String operationSetExper = String.join("|", QueryOperator.SIMPLE_OPERATION_SET);
            Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
            Matcher matcher = pattern.matcher(query + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
            }
            Specification<Order> spec = builder.build();

            Page<Order> orderPage = repository.findAll(spec, paging);
            return _buildPagedOrderDTO(orderPage);
        } else {
            Pageable paging = PageRequest.of(page, size);
            Page<Order> orderPage = repository.findAll(paging);
            return _buildPagedOrderDTO(orderPage);
        }
    }

    public boolean printOrder(Long id) {
        Order order = super.read(id);

        // print orchestration post creation, asynchronous
        Map<PrinterCfg, Order> printerCfgOrderMap = printerAsyncService.splitOrder(order);
        printerAsyncService.executePrintTasks(printerCfgOrderMap);

        return true;
    }

    private void _manageStockOnOrder(DetailedOrderDTO order) {
        for (OrderContent content : order.getContent()) {
            stockService.reduceByMenuItemId(Long.valueOf(content.getItemId()), content.getQty());
        }
    }

    private void _setOrderProcessingZone(DetailedOrderDTO order, Order entity) {

        for (OrderContent content : order.getContent()) {
            CategoryDTO category = categoryService.get(content.getCategoryId());
            _flagProcessingZone(entity, category.getProcessingZone());
        }
    }

    private void _evaluateDiscount(DetailedOrderDTO order, Order entity) {
        if (order.getDiscountIds() == null) {
            return;
        }

        List<KeyMap> appliedDiscounts = new ArrayList<>();

        for (Long discountId : order.getDiscountIds()) {
            DiscountDTO discount = discountService.get(discountId);

            if (discount.getCategoryIds() == null
                    || discount.getCategoryIds().size() == 0) {

                entity.setPaid(_priceDiscount(discount, order.getTotal()));
                appliedDiscounts.add(new KeyMap(discount.getId(), discount.getValue()));
            } else {
                Double originalPrice = order.getTotal();
                for (Long categoryId : discount.getCategoryIds()) {

                    List<OrderContent> items = order.getContent()
                            .stream()
                            .filter(p -> p.getCategoryId().equals(categoryId))
                            .collect(Collectors.toList());

                    for (OrderContent item : items) {
                        double singlePrice = _priceDiscount(discount, item.getPrice());
                        originalPrice = originalPrice - singlePrice;
                    }
                }
                entity.setPaid(originalPrice);
                appliedDiscounts.add(new KeyMap(discount.getId(), discount.getValue()));
            }
        }

        entity.setDiscount(appliedDiscounts);
    }

    private double _priceDiscount(DiscountDTO discount, Double price) {
        double result = 0;
        switch (DiscountType.valueOf(discount.getType())) {
            case EUR:
                result = price - discount.getValue();
                break;
            case PERCENTAGE:
                result = (price * discount.getValue()) / 100;
                break;
            default:
                break;
        }

        if (result < 0) {
            _LOGGER.warn("The item cost is less than 0");
            result = 0;
        }

        return result;
    }

    private void _flagProcessingZone(Order entity, String zone) {
        CategoryProcessingZone pz = CategoryProcessingZone.valueOf(zone);

        switch (pz) {
            case BAR:
                entity.setBarArea(true);
                break;
            case PLATE:
                entity.setPlateArea(true);
                break;
            case KITCHEN:
                entity.setKitchenArea(true);
                break;
            default:
                _LOGGER.warn("Selected processing zone does not exists!");
                break;
        }
    }

    private PagedOrderDTO _buildPagedOrderDTO(Page<Order> orderPage) {
        PagedOrderDTO response = new PagedOrderDTO();
        PaginationUtils.setResponsePagination(orderPage, response);
        response.setData(orderPage.getContent().stream().map(converter::convertEntity).collect(Collectors.toList()));

        return response;
    }
}
