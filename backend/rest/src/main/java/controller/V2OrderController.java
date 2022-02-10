package controller;

import arch.exception.errors.OrderNotFoundException;
import arch.exception.errors.OrderUpdateException;
import atomic.bean.OrderContent;
import atomic.entity.V2Order;
import atomic.enums.OrderStatus;
import atomic.repository.V2OrderRepository;
import business.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

//TODO: CONTROLLER TEMPORANEO PER IL REFACTOR DEGLI ORDINI CON JSONB
@RestController
@RequestMapping(value = "/v2/order")
public class V2OrderController {

    @Autowired
    private V2OrderRepository repository;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @PostMapping
    public String create() {

        V2Order order = new V2Order();

//        order.setContent(List.of(OrderContent.builder()
//                .itemId("itemId")
//                .categoryId("categoryId")
//                .qty(1)
//                .additions(List.of("senape", "ketchup"))
//                .less(List.of("salad"))
//                .price(20.0)
//                .build()));

        order.setContent(List.of(
                new OrderContent("itemId", "categoryId", 1, List.of("senape", "ketchup"), List.of("salad"), "", 20.0)
        ));

        order.setTotal(20.0);
        order.setTableNumber((short) 2);
        order.setPlaceSettingNumber((short) 4);
        order.setNote("Ordine di prova");
        repository.saveAndFlush(order);
        return "Done";
    }

    @PatchMapping("/{id}")
    public String patchStatus(@PathVariable Long id, @RequestBody String newStatus) throws ParseException {
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

    @GetMapping()
    public List<V2Order> findAll() {

        return repository.findAll();
    }
}
