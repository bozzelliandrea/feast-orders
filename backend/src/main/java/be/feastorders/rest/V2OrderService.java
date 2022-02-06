package be.feastorders.rest;

import be.feastorders.core.service.BaseCRUDService;
import be.feastorders.order.dto.OrderDTO;

public class V2OrderService extends BaseCRUDService<V2Order, Long> {

    public V2OrderService(V2OrderRepository repository) {
        super(repository);
    }

    public void createOrder(OrderDTO request) {


    }
}
