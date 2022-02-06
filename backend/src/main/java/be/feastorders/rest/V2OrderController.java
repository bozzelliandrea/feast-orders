package be.feastorders.rest;

import be.feastorders.core.exception.errors.OrderNotFoundException;
import be.feastorders.core.exception.errors.OrderUpdateException;
import be.feastorders.order.dto.OrderStatus;
import be.feastorders.order.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import static be.feastorders.rest.OrderContent.Category.SUB;

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

        order.setContent(List.of(OrderContent.emptyBuilder()
                .itemId("panino1")
                .category(SUB)
                .qty(1)
                .additions("senape")
                .additions("ketchup")
                .less("salad")
                .price(SUB.getPrice())
                .build()));

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

            if (status.equals(OrderStatus.DONE)) {
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
