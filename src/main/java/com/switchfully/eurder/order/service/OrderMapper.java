package com.switchfully.eurder.order.service;

import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.order.service.dto.CreateOrderDto;
import com.switchfully.eurder.order.service.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(Order order){
        return new OrderDto(order.getOrderId(), order.getListOfItemsGroup(), order.getCustomerId());
    }

    public Order fromDto(CreateOrderDto createOrderDto){
        return new Order(createOrderDto.listOfItemsGroup(), createOrderDto.customerId());
    }
}
