package com.switchfully.eurder.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.switchfully.eurder.infrastructure.exceptions.NegativeNumberInputException;
import com.switchfully.eurder.infrastructure.Utils;

import java.util.Objects;
import java.util.UUID;

public class Item {

    //========================== ATTRIBUTES ===================================

    private final UUID id;
    private String name;
    private String description;
    private double price;
    private Stock stock;

    //========================== CONSTRUCTOR ================================

    @JsonCreator
    public Item(UUID id, String name, String description, double price, Stock stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Item(UUID id, String name, String description, double price, int stockAmount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = new Stock(stockAmount);
    }

    public Item(String name, String description, double price, int stockAmount) {
        Utils.fieldIsPresent(name, "name");
        if (price < 0){
            throw new NegativeNumberInputException("price");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = new Stock(stockAmount);
    }

    //========================== GETTERS =====================================

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Stock getStock() {
        return stock;
    }

    public int getStockAmount() {
        return stock.getAmount();
    }

    public Supply getStockSupply() {
        return stock.getSupply();
    }

    //========================== SETTERS ====================================

//    public void setName(String name) {
//        this.name = name;
//    }

//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public void setStock(int stockAmount) {
//        this.stock = new Stock(stockAmount);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 && Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(description, item.description) && Objects.equals(stock, item.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, stock);
    }
}
