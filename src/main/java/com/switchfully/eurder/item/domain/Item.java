package com.switchfully.eurder.item.domain;

import com.switchfully.eurder.exceptions.NegativeNumberInputException;
import com.switchfully.eurder.utils.Utils;

import java.util.UUID;

public class Item {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private int stock;

    public Item(String name, String description, double price, int stock) {
        Utils.fieldIsPresent(name, "name");
        if (price < 0){
            throw new NegativeNumberInputException("price");
        }
        if (stock < 0){
            throw new NegativeNumberInputException("stock");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

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

    public int getStock() {
        return stock;
    }
}
