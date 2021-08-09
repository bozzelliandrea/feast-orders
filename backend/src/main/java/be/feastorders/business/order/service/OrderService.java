package be.feastorders.business.order.service;

import be.feastorders.business.order.entity.Order;
import be.feastorders.business.order.repository.OrderRepository;
import be.feastorders.core.service.BaseCRUDService;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseCRUDService<Order, Long> {

    public OrderService(OrderRepository repository) {
        super(repository);
    }
}
