package business.service;

import atomic.entity.OrderHistory;
import atomic.entity.V2Order;
import atomic.repository.OrderHistoryRepository;
import business.converter.OrderHistoryConverter;
import business.dto.PagedOrderHistoryDTO;
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

    public boolean create(V2Order order) {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setContent(order.getContent());
        orderHistory.setTotal(order.getTotal());
        orderHistory.setDate(order.getCreationTimestamp());

        return create(orderHistory);

    }

    public PagedOrderHistoryDTO findAllWithPagination(int page, int size) {
        Pageable paging = PageRequest.of(page, size);

        Page<OrderHistory> orderPage = repository.findAll(paging);

        PagedOrderHistoryDTO response = new PagedOrderHistoryDTO();
        response.setPageSize(orderPage.getSize());
        response.setPageNumber(orderPage.getNumber());
        response.setTotalPages(orderPage.getTotalPages());
        response.setTotalElements(orderPage.getTotalElements());
        response.setData(orderPage.getContent().stream().map(converter::convertEntity).collect(Collectors.toList()));

        return response;
    }
}
