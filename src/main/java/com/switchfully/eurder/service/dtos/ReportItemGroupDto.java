package com.switchfully.eurder.service.dtos;

import java.util.Objects;

public class ReportItemGroupDto {
    private String name;
    private int amount;
    private double price;

    public ReportItemGroupDto(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ReportItemGroupDto) obj;
        return Objects.equals(this.name, that.name) &&
                this.amount == that.amount &&
                Double.doubleToLongBits(this.price) == Double.doubleToLongBits(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, price);
    }

    @Override
    public String toString() {
        return "ReportItemGroupDto[" +
                "name=" + name + ", " +
                "amount=" + amount + ", " +
                "price=" + price + ']';
    }

}
