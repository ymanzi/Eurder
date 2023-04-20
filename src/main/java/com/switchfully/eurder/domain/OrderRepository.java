package com.switchfully.eurder.domain;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {
    private final HashMap<UUID, Order> ordersById;

    public OrderRepository() {
        ordersById = new HashMap<>();
    }

    public double save(Order order) {
        UUID orderId = order.getOrderId();
        ordersById.put(orderId, order);
        return ordersById.get(orderId).getPrice() ;
    }

    public Order getById(UUID orderId){
        return ordersById.get(orderId);
    }

    public List<Order> getByCustomerId(UUID customerId){
        return ordersById
                .values()
                .stream()
                .filter(order -> order.getCustomerId().equals(customerId))
                .toList();
    }

    public List<Order> getAll(){
        return ordersById
                .values()
                .stream()
                .toList();
    }

    public List<Order> getOrdersWithTodayDelivery(){
        return getAll()
                .stream()
                .filter(this::containsTodayDelivery)
                .toList();
    }

    public boolean containsTodayDelivery(Order order){
        return !order
                .getListOfItemsGroup()
                .stream()
                .filter(itemGroup -> itemGroup.getShippingDate().equals(LocalDate.now()))
                .toList()
                .isEmpty();
    }
}
