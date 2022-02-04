package be.feastorders.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static be.feastorders.rest.OrderContent.Category.SUB;

@RestController
@RequestMapping(value = "/v2/order")
public class OrderREST {

    @Autowired
    private V2OrderRepository repository;

    @GetMapping
    public String hello() {

        V2Order order = new V2Order();

        order.setOrderContent(OrderContent.emptyBuilder()
                .itemId("panino1")
                .category(SUB)
                .qty(1)
                .additions("senape")
                .additions("ketchup")
                .less("salad")
                .price(SUB.getPrice())
                .build());


        repository.saveAndFlush(order);
        return "Done";
    }

    @GetMapping("/read")
    public V2Order read() {

        List<V2Order> result = repository.findAll();
        System.out.println(result.get(0));
        return result.get(0);
    }
}
