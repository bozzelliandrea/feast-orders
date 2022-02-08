package be.feastorders.rest;

import be.feastorders.order.dto.OrderDTO;
import org.springframework.stereotype.Service;
import service.BaseCRUDService;

@Service
public class V2OrderService extends BaseCRUDService<V2Order, Long> {

    public V2OrderService(V2OrderRepository repository) {
        super(repository);
    }

    public void createOrder(OrderDTO request) {


    }
}
