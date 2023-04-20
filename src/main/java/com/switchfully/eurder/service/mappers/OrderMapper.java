package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.service.dtos.*;
import org.springframework.stereotype.Component;

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
