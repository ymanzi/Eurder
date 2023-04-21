package com.switchfully.eurder.service.dtos;

import com.switchfully.eurder.domain.classes.Address;
import com.switchfully.eurder.domain.classes.ItemGroup;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class OrderTodayDto {
    private final UUID orderId;
    private final List<ItemGroup> listOfItemsGroup;
    private final Address address;

    public OrderTodayDto(UUID orderId, List<ItemGroup> listOfItemsGroup, Address address) {
        this.orderId = orderId;
        this.listOfItemsGroup = listOfItemsGroup;
        this.address = address;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<ItemGroup> getListOfItemsGroup() {
        return listOfItemsGroup;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (OrderTodayDto) obj;
        return Objects.equals(this.orderId, that.orderId) &&
                Objects.equals(this.listOfItemsGroup, that.listOfItemsGroup) &&
                Objects.equals(this.address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, listOfItemsGroup, address);
    }

    @Override
    public String toString() {
        return "OrderTodayDto[" +
                "orderId=" + orderId + ", " +
                "listOfItemsGroup=" + listOfItemsGroup + ", " +
                "address=" + address + ']';
    }

}
