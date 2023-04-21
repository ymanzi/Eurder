package com.switchfully.eurder.domain.classes;

import jakarta.persistence.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order")
    @SequenceGenerator(sequenceName = "seq_order", allocationSize = 1, name = "seq_order")
    private int id;

    @OneToMany
    @JoinColumn(name = "fk_ordered_items")
    private List<OrderedItem> items;

    @OneToOne
    @JoinColumn(name = "fk_customer_id")
    private Customer customer;

    private double price;

    public Order() {
    }

    public Order(List<OrderedItem> items, Customer customer) {
        this.items = items;
        this.customer = customer;
        this.price = items
                        .stream()
                        .map(group -> group.getPrice())
                        .reduce(0.0, Double::sum);
    }

    public int getId() {
        return id;
    }

    public List<OrderedItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getPrice() {
        return price;
    }
}
