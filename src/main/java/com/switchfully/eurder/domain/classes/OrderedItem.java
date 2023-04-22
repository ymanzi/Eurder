package com.switchfully.eurder.domain.classes;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "ordered_items")
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordered_item")
    @SequenceGenerator(sequenceName = "seq_ordered_item", allocationSize = 1, name = "seq_ordered_item")
    private int id;

    private int item_id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private int amount;
    @Column(name = "price")
    private double price;
    @Column(name = "shipping_date", columnDefinition = "DATE")
    private LocalDate shippingDate;

    public OrderedItem() {
    }

    public OrderedItem(Item item, int amount) {
        this.item_id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.amount = amount;
        this.price = item.getPrice() * amount;
        this.shippingDate = setShippingDate(amount, item.getStockAmount());
    }

    private LocalDate setShippingDate(int amount, int stock){
        if (amount <= stock) {
            return LocalDate.now().plusDays(1);
        }
        return LocalDate.now().plusDays(7);
    }

    public long getId() {
        return id;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItem that = (OrderedItem) o;
        return id == that.id && amount == that.amount && Double.compare(that.price, price) == 0 && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(shippingDate, that.shippingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, amount, price, shippingDate);
    }
}
