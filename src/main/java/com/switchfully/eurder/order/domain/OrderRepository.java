package com.switchfully.eurder.order.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
}
