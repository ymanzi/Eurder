package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.domain.classes.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void getPrice_WhenGivenAnOrder_thenReturnTheOrderPrice() {
        Customer customer = new Customer(
                new User(
                        new Name("first", "last"),
                        new Contact("email", "phone")
                ),
                new Address("street", "number", "zip", "city"));
        Item item = new Item("name", "description", 5.0, 25);
        OrderedItem items1 = new OrderedItem(item, 3);

        List<OrderedItem> itemGroupList = List.of(items1,items1);

        Order order = new Order(itemGroupList, customer);

        assertEquals(30, order.getPrice());
    }
}