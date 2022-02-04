package be.feastorders.rest;

import be.feastorders.core.exception.errors.OrderNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v2/order")
public class OrderREST {


    @GetMapping
    public String hello(){
        throw new OrderNotFoundException("Hello World");
    }
}
