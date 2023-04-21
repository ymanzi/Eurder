package com.switchfully.eurder.service.dtos;

import com.switchfully.eurder.domain.classes.ItemGroup;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class OrderDto {
    private final UUID orderId;
    private final List<ItemGroup> listOfItemsGroup;
    private final UUID customerId;

    public OrderDto(UUID orderId, List<ItemGroup> listOfItemsGroup, UUID customerId) {
        this.orderId = orderId;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (OrderDto) obj;
        return Objects.equals(this.orderId, that.orderId) &&
                Objects.equals(this.listOfItemsGroup, that.listOfItemsGroup) &&
                Objects.equals(this.customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, listOfItemsGroup, customerId);
    }

    @Override
    public String toString() {
        return "OrderDto[" +
                "orderId=" + orderId + ", " +
                "listOfItemsGroup=" + listOfItemsGroup + ", " +
                "customerId=" + customerId + ']';
    }

}
