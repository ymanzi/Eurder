package com.switchfully.eurder.order.service;

import com.switchfully.eurder.customer.domain.Address;
import com.switchfully.eurder.customer.domain.CustomerRepository;
import com.switchfully.eurder.order.domain.ItemGroup;
import com.switchfully.eurder.order.domain.Order;
import com.switchfully.eurder.order.service.dto.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
public class OrderMapper {
    /*public OrderDto toDto(Order order){
        return new OrderDto(order.getOrderId(), order.getListOfItemsGroup(), order.getCustomerId());
    }*/

    public ReportItemGroupDto toDto(ItemGroup itemGroup){
        String itemName = itemGroup.getItem().getName();
        int amount = itemGroup.getAmount();
        double price = itemGroup.getPrice();
        return new ReportItemGroupDto(itemName, amount, price);
    }

    public List<ReportItemGroupDto> toDto(List<ItemGroup> listOfItemGroup){
        return listOfItemGroup
                .stream()
                .map(this::toDto)
                .toList();
    }

    public ReportOrderDto toDto(Order order){
        UUID id = order.getOrderId();
        List<ReportItemGroupDto> listOfItemGroupDto = this.toDto(order.getListOfItemsGroup());
        double price = order
                        .getListOfItemsGroup()
                        .stream()
                        .map(itemGroup -> itemGroup.getPrice())
                        .reduce(0.0, Double::sum);

        return new ReportOrderDto(id, listOfItemGroupDto, price);
    }

}
