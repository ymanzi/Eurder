package com.switchfully.eurder.service.dtos;

import com.switchfully.eurder.domain.classes.Address;
import com.switchfully.eurder.domain.classes.ItemGroup;
import com.switchfully.eurder.domain.classes.OrderedItem;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class OrderTodayDto {
    private final int orderId;
    private final List<OrderedItem> listOfItemsGroup;
    private final Address address;

    public OrderTodayDto(int orderId, List<OrderedItem> listOfItemsGroup, Address address) {
        this.orderId = orderId;
        this.listOfItemsGroup = listOfItemsGroup;
        this.address = address;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<OrderedItem> getListOfItemsGroup() {
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
