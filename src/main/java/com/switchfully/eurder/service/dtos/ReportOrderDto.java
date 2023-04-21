package com.switchfully.eurder.service.dtos;

import java.util.List;
import java.util.Objects;

public class ReportOrderDto {
    private int orderId;
    private List<ReportItemGroupDto> itemsGroups;
    private double price;

    public ReportOrderDto() {
    }

    public ReportOrderDto withOrderId(int orderId){
        this.orderId = orderId;
        return this;
    }

    public ReportOrderDto withItemsGroups(List<ReportItemGroupDto> itemsGroups){
        this.itemsGroups = itemsGroups;
        return this;
    }

    public ReportOrderDto withPrice(double price){
        this.price = price;
        return this;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<ReportItemGroupDto> getItemsGroups() {
        return itemsGroups;
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
                Objects.equals(this.itemsGroups, that.itemsGroups) &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemsGroups, price);
    }

    @Override
    public String toString() {
        return "ReportOrderDto[" +
                "orderId=" + orderId + ", " +
                "listOfItemsGroup=" + itemsGroups + ", " +
                "price=" + price + ']';
    }

}
