package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.classes.ItemGroup;
import com.switchfully.eurder.domain.classes.Order;
import com.switchfully.eurder.domain.classes.OrderedItem;
import com.switchfully.eurder.service.dtos.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderMapper {

    public ReportItemGroupDto toDto(OrderedItem orderedItem){
        String itemName = orderedItem.getName();
        int amount = orderedItem.getAmount();
        double price = orderedItem.getPrice();
        return new ReportItemGroupDto(itemName, amount, price);
    }

    public List<ReportItemGroupDto> toDto(List<OrderedItem> listOfItemGroup){
        return listOfItemGroup
                .stream()
                .map(this::toDto)
                .toList();
    }

    public ReportOrderDto toDto(Order order){
        int id = order.getId();
        List<ReportItemGroupDto> listOfItemGroupDto = this.toDto(order.getItems());
        double price = order
                        .getItems()
                        .stream()
                        .map(itemGroup -> itemGroup.getPrice())
                        .reduce(0.0, Double::sum);

        return new ReportOrderDto()
                    .withOrderId(id)
                    .withItemsGroups(listOfItemGroupDto)
                    .withPrice(price);
    }
}
