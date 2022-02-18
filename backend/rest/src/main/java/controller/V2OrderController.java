package controller;

import arch.dto.AbstractPagination;
import atomic.enums.OrderStatus;
import business.order.exception.OrderUpdateException;
import business.order.service.OrderHistoryService;
import business.order.service.V2OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//TODO: CONTROLLER TEMPORANEO PER IL REFACTOR DEGLI ORDINI CON JSONB
@RestController
@RequestMapping(value = "/v2/order", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class V2OrderController {

    @Autowired
    private V2OrderService orderService;

    @Autowired
    private OrderHistoryService orderHistoryService;


    @PostMapping
    public String create() {

        throw new OrderUpdateException();

//        V2Order order = new V2Order();

//        order.setContent(List.of(OrderContent.builder()
//                .itemId("itemId")
//                .categoryId("categoryId")
//                .qty(1)
//                .additions(List.of("senape", "ketchup"))
//                .less(List.of("salad"))
//                .price(20.0)
//                .build()));

//        order.setContent(List.of(
//                new OrderContent("itemId", "categoryId", 1, List.of("senape", "ketchup"), List.of("salad"), "", 20.0)
//        ));
//
//        order.setTotal(20.0);
//        order.setTableNumber((short) 2);
//        order.setPlaceSettingNumber((short) 4);
//        order.setNote("Ordine di prova");
//        repository.saveAndFlush(order);
//        return "Done";
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> patchStatus(@PathVariable Long id, @RequestParam(value = "status") String newStatus) {
        return ResponseEntity.ok(orderService.patchStatus(id, newStatus));
    }

    @GetMapping(params = {"page", "size", "status"})
    public ResponseEntity<AbstractPagination<? extends Serializable>> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                                                              @RequestParam(value = "size", defaultValue = "10", required = false) int size,
                                                                              @RequestParam(value = "status", required = false) String status) {
        if (status != null && OrderStatus.valueOf(status).isClosed()) {
            return ResponseEntity.ok(orderHistoryService.findAllWithPagination(page, size));
        } else {
            return ResponseEntity.ok(orderService.findAllWithPagination(page, size));
        }
    }

    @GetMapping("/search")
    public ResponseEntity<AbstractPagination<? extends Serializable>> search(@RequestParam(value = "query", required = false) String query) {
        return ResponseEntity.ok(orderService.searchOrders(query));
    }
}
