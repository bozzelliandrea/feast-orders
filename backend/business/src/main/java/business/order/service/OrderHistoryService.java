package business.order.service;

import arch.component.PaginationUtils;
import arch.dto.AbstractPagination;
import arch.security.dto.UserDTO;
import atomic.entity.Order;
import atomic.entity.OrderHistory;
import atomic.repository.OrderHistoryRepository;
import business.order.converter.OrderHistoryConverter;
import business.order.dto.OrderHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository repository;
    private final OrderHistoryConverter converter;

    public OrderHistoryService(OrderHistoryRepository repository,
                               OrderHistoryConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public boolean create(OrderHistory orderHistory) {
        repository.saveAndFlush(orderHistory);
        return true;
    }

    public boolean create(Order order) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setContent(order.getContent());
        orderHistory.setTotal(order.getTotal());
        orderHistory.setDate(order.getCreationTimestamp());
        orderHistory.setStatsId(order.getStatsId());
        orderHistory.setLoaded(order.isLoaded());
        orderHistory.setCreationUser(order.getCreationUser());
        return create(orderHistory);

    }

    public AbstractPagination<OrderHistoryDTO> findAllWithPagination(int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        Page<OrderHistory> orderPage = repository.findAll(paging);

        AbstractPagination<OrderHistoryDTO> response = new AbstractPagination<>();
        PaginationUtils.setResponsePagination(orderPage, response);
        response.setData(orderPage.getContent().stream().map(converter::convertEntity).collect(Collectors.toList()));

        return response;
    }
}
