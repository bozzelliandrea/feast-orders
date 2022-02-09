package service;

import entity.OrderHistory;
import entity.V2Order;
import org.springframework.stereotype.Service;
import repository.OrderHistoryRepository;

import java.text.ParseException;

@Service
public class OrderHistoryService {

    private final OrderHistoryRepository repository;

    public OrderHistoryService(OrderHistoryRepository repository) {
        this.repository = repository;
    }

    public boolean create(OrderHistory orderHistory) {
        repository.saveAndFlush(orderHistory);
        return true;
    }

    public boolean create(V2Order order) throws ParseException {
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setContent(order.getContent());
        orderHistory.setTotal(order.getTotal());
        orderHistory.setDate(order.getCreationTimestamp());

        return create(orderHistory);

    }
}
