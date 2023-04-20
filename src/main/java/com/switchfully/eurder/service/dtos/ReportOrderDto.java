package com.switchfully.eurder.service.dtos;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class ReportOrderDto {
    private final UUID orderId;
    private final List<ReportItemGroupDto> listOfItemsGroup;
    private final double price;

    public ReportOrderDto(UUID orderId, List<ReportItemGroupDto> listOfItemsGroup, double price) {
        this.orderId = orderId;
        this.listOfItemsGroup = listOfItemsGroup;
        this.price = price;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<ReportItemGroupDto> getListOfItemsGroup() {
        return listOfItemsGroup;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ReportOrderDto) obj;
        return Objects.equals(this.orderId, that.orderId) &&
                Objects.equals(this.listOfItemsGroup, that.listOfItemsGroup) &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, listOfItemsGroup, price);
    }

    @Override
    public String toString() {
        return "ReportOrderDto[" +
                "orderId=" + orderId + ", " +
                "listOfItemsGroup=" + listOfItemsGroup + ", " +
                "price=" + price + ']';
    }

}
