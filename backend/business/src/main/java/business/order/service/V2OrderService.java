package business.order.service;

import arch.search.QueryOperator;
import arch.service.BaseCRUDService;
import atomic.bean.OrderContent;
import atomic.entity.V2Order;
import atomic.enums.CategoryProcessingZone;
import atomic.enums.OrderStatus;
import atomic.repository.V2OrderRepository;
import business.category.dto.CategoryDTO;
import business.category.service.CategoryService;
import business.order.OrderSpecificationBuilder;
import business.order.converter.OrderConverter;
import business.order.dto.DetailedOrderDTO;
import business.order.dto.PagedOrderDTO;
import business.order.exception.OrderNotFoundException;
import business.order.exception.OrderUpdateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class V2OrderService extends BaseCRUDService<V2Order, Long> {

    private final static Logger _LOGGER = LoggerFactory.getLogger(V2OrderService.class);

    private final OrderHistoryService orderHistoryService;
    private final V2OrderRepository repository;
    private final OrderConverter converter;
    private final CategoryService categoryService;
    private final Map<Long, CategoryDTO> cachedCategory = new HashMap<>();

    public V2OrderService(V2OrderRepository repository,
                          OrderHistoryService orderHistoryService,
                          OrderConverter converter,
                          CategoryService categoryService) {
        super(repository);
        this.repository = repository;
        this.orderHistoryService = orderHistoryService;
        this.converter = converter;
        this.categoryService = categoryService;
    }

    public DetailedOrderDTO createOrder(DetailedOrderDTO request) {
        V2Order entity = converter.convertDTO(request);
        _setOrderProcessingZone(request, entity);
        //TODO se flag print = true, mandare in stampa l'ordine
        V2Order result = super.create(entity);
        return converter.convertEntityDetailed(result);
    }

    public DetailedOrderDTO updateOrder(DetailedOrderDTO request) {
        V2Order savedEntity = super.read(request.getId());
        _setOrderProcessingZone(request, savedEntity);

        savedEntity.setNote(request.getNote());
        savedEntity.setContent(request.getContent());
        savedEntity.setClient(request.getClient());
        savedEntity.setPlaceSettingNumber(request.getPlaceSettingNumber().shortValue());
        savedEntity.setTableNumber(request.getTableNumber().shortValue());
        savedEntity.setTotal(request.getTotal());
        savedEntity.setDiscount(request.getDiscount());

        V2Order result = super.update(savedEntity);
        return converter.convertEntityDetailed(result);
    }

    public String patchStatus(Long id, String newStatus) {
        OrderStatus status = OrderStatus.valueOf(newStatus);
        Optional<V2Order> orderOpt = repository.findById(id);

        if (orderOpt.isPresent()) {
            V2Order order = orderOpt.get();

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

    public PagedOrderDTO findAllWithPagination(int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        Page<V2Order> orderPage = repository.findAll(paging);

        PagedOrderDTO response = new PagedOrderDTO();
        response.setPageSize(orderPage.getSize());
        response.setPageNumber(orderPage.getNumber());
        response.setTotalPages(orderPage.getTotalPages());
        response.setTotalElements(orderPage.getTotalElements());
        response.setData(orderPage.getContent().stream().map(converter::convertEntity).collect(Collectors.toList()));

        return response;
    }

    public PagedOrderDTO searchOrders(String query) {
        if (query != null) {
            OrderSpecificationBuilder builder = new OrderSpecificationBuilder();

            String operationSetExper = String.join("|", QueryOperator.SIMPLE_OPERATION_SET);
            Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
            Matcher matcher = pattern.matcher(query + ",");
            Pageable paging = PageRequest.of(0, 10);
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(5), matcher.group(4), matcher.group(6));
            }
            Specification<V2Order> spec = builder.build();

            //TODO UNIRE PAGINAZIONE E RICERCA CON UN SERVIZIO UNICO
            Page<V2Order> orderPage = repository.findAll(spec, paging);
            PagedOrderDTO response = new PagedOrderDTO();
            response.setPageSize(orderPage.getSize());
            response.setPageNumber(orderPage.getNumber());
            response.setTotalPages(orderPage.getTotalPages());
            response.setTotalElements(orderPage.getTotalElements());
            response.setData(orderPage.getContent().stream().map(converter::convertEntity).collect(Collectors.toList()));

            return response;
        } else {
            return findAllWithPagination(0, 10);
        }

    }

    private void _setOrderProcessingZone(DetailedOrderDTO order, V2Order entity) {

        for (OrderContent content : order.getContent()) {
            if (cachedCategory.containsKey(content.getCategoryId())) {
                _flagProcessingZone(entity, cachedCategory.get(content.getCategoryId()).getProcessingZone());
            } else {
                CategoryDTO category = categoryService.get(content.getCategoryId());
                cachedCategory.put(category.getId(), category);
                _flagProcessingZone(entity, category.getProcessingZone());
            }
        }
    }

    private void _flagProcessingZone(V2Order entity, String zone) {
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

}
