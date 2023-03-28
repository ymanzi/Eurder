package com.switchfully.eurder.order.api;

import com.switchfully.eurder.order.service.OrderService;
import com.switchfully.eurder.order.service.dto.CreateOrderDto;
import com.switchfully.eurder.order.service.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public OrderDto create(@RequestBody CreateOrderDto createOrderDto, @RequestHeader String customerEmail){
        return orderService.save(createOrderDto, customerEmail);
    }

}
