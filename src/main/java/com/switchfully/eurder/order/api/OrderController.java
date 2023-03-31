package com.switchfully.eurder.order.api;

import com.switchfully.eurder.order.service.OrderService;
import com.switchfully.eurder.order.service.dto.CreateOrderDto;
import com.switchfully.eurder.order.service.dto.OrderTodayDto;
import com.switchfully.eurder.order.service.dto.ReportOrdersOfCustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public double create(@RequestBody CreateOrderDto createOrderDto, @RequestHeader UUID customerId){
        return orderService.save(createOrderDto, customerId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/{orderId}", produces = "application/json")
    public double orderAgain(@PathVariable UUID orderId, @RequestHeader UUID customerId){
        return orderService.orderAgain(orderId, customerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/", produces = "application/json")
    public ReportOrdersOfCustomerDto getByCustomer(@RequestHeader UUID customerId){
        return orderService.getByCustomerId(customerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "/today", produces = "application/json")
    public List<OrderTodayDto> getTodayOrders(@RequestHeader String adminId){
        return orderService.getTodayOrders(adminId);
    }
}
