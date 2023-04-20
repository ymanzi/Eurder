package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getPrice_WhenGivenAnOrder_thenReturnTheOrderPrice() {
        UUID customerId = UUID.randomUUID();
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 3));

        Order order = new Order(itemGroupList, customerId);

        assertEquals(30, order.getPrice());
    }
}