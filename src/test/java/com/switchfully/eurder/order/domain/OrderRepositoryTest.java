package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.item.domain.Item;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private OrderRepository orderRepository = new OrderRepository();

    @Test
    void save_whenSavingOrder_thenReturnGivenOrder() {
        UUID customerId = UUID.randomUUID();
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 3));

        Order order = new Order(itemGroupList, customerId);
        Order savedOrder = orderRepository.save(order);
        assertEquals(order, savedOrder);
    }
}