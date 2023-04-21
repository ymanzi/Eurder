package com.switchfully.eurder.domain.classes;

import com.switchfully.eurder.infrastructure.exceptions.NegativeNumberInputException;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "items")
public class Item {

    //========================== ATTRIBUTES ===================================

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item")
    @SequenceGenerator(sequenceName = "seq_item", allocationSize = 1, name = "seq_item")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "stock_amount")
    private int stockAmount;
    @Column(name = "supply")
    @Enumerated(EnumType.STRING)
    private Supply supply;

    //========================== CONSTRUCTOR ================================

    public Item() {
    }

    public Item(String name, String description, double price, int stockAmount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        setSupply(stockAmount);
    }


    //========================== GETTERS =====================================

    public int getId() {
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

    public int getStockAmount() {
        return stockAmount;
    }

    public Supply getSupply() {
        return supply;
    }


    //========================== SETTERS ====================================


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
        setSupply(stockAmount);
    }

    private void setSupply(int amount) {
        if (amount < 5)
        {
            supply = Supply.STOCK_LOW;
        } else if (amount < 10) {
            supply = Supply.STOCK_MEDIUM;
        }
        else {
            supply = Supply.STOCK_HIGH;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id && Double.compare(item.price, price) == 0 && Objects.equals(name, item.name) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price);
    }
}
