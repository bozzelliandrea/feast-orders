package business.service;

import arch.service.BaseCRUDService;
import atomic.entity.V2Order;
import atomic.enums.OrderStatus;
import atomic.repository.V2OrderRepository;
import business.converter.OrderConverter;
import business.dto.OrderDTO;
import business.dto.PagedOrderDTO;
import business.exception.OrderNotFoundException;
import business.exception.OrderUpdateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class V2OrderService extends BaseCRUDService<V2Order, Long> {

    private final OrderHistoryService orderHistoryService;
    private final V2OrderRepository repository;
    private final OrderConverter converter;

    public V2OrderService(V2OrderRepository repository,
                          OrderHistoryService orderHistoryService,
                          OrderConverter converter) {
        super(repository);
        this.repository = repository;
        this.orderHistoryService = orderHistoryService;
        this.converter = converter;
    }

    public void createOrder(OrderDTO request) {


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
}
