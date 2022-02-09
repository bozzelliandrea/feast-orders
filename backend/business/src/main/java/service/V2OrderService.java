package service;

import dto.OrderDTO;
import entity.V2Order;
import org.springframework.stereotype.Service;
import repository.V2OrderRepository;

@Service
public class V2OrderService extends BaseCRUDService<V2Order, Long> {

    public V2OrderService(V2OrderRepository repository) {
        super(repository);
    }

    public void createOrder(OrderDTO request) {


    }
}
