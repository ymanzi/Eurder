package com.switchfully.eurder.order.service.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.switchfully.eurder.order.domain.ItemGroup;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class CreateOrderDto {
    private final List<ItemGroup> listOfItemsGroup;

    public CreateOrderDto(List<ItemGroup> listOfItemsGroup) {
        this.listOfItemsGroup = listOfItemsGroup;
    }

    public List<ItemGroup> getListOfItemsGroup() {
        return listOfItemsGroup;
    }

}
