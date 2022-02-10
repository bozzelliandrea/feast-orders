package business.service;

import arch.service.BaseCRUDService;
import atomic.entity.V2Order;
import atomic.repository.V2OrderRepository;
import business.dto.OrderDTO;
import org.springframework.stereotype.Service;

@Service
public class V2OrderService extends BaseCRUDService<V2Order, Long> {

    public V2OrderService(V2OrderRepository repository) {
        super(repository);
    }

    public void createOrder(OrderDTO request) {


    }
}
