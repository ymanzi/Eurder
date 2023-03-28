package com.switchfully.eurder.order.domain;

import java.util.List;
import java.util.UUID;

public class Order {
    private UUID orderId;
    private List<ItemGroup> listOfItemsGroup;
    private UUID customerId;

    public Order(List<ItemGroup> listOfItemsGroup, UUID customerId) {
        this.orderId = UUID.randomUUID();
        this.listOfItemsGroup = listOfItemsGroup;
        this.customerId = customerId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<ItemGroup> getListOfItemsGroup() {
        return listOfItemsGroup;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public double getPrice() {
        return listOfItemsGroup
                .stream()
                .map(group -> group.getPrice())
                .reduce(0.0, Double::sum);
    }
}
