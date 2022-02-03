package be.feastorders.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v2/order")
public class OrderREST {


    @GetMapping
    public String hello(){
        return "Hello World";
    }
}
