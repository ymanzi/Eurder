package com.switchfully.eurder.order.domain;

import com.switchfully.eurder.domain.Item;
import com.switchfully.eurder.domain.ItemGroup;
import com.switchfully.eurder.domain.Order;
import com.switchfully.eurder.domain.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryTest {

    private final OrderRepository orderRepository = new OrderRepository();

    @Test
    void save_whenSavingOrder_thenReturnGivenOrder() {
        UUID customerId = UUID.randomUUID();
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 3));

        Order order = new Order(itemGroupList, customerId);
        double savedPrice = orderRepository.save(order);
        assertEquals(order.getPrice(), savedPrice);
    }

    @Test
    void getByCustomerId_whenRequestingCustomerOrders_thenReturnCorrespondingOrders() {
        //Given
        UUID customerId = UUID.randomUUID();
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 3));

        List<ItemGroup> itemGroupList2 = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 18));

        Order order = new Order(itemGroupList, customerId);
        Order order2 = new Order(itemGroupList2, customerId);
        orderRepository.save(order);
        orderRepository.save(order2);

        //When
        List<Order> listOfOrders = orderRepository.getByCustomerId(customerId);

        //Then
        Assertions.assertThat(listOfOrders).containsExactlyInAnyOrder(order, order2);
    }

    @Test
    void getByCustomerId_whenNoOrderWerePassedByTheCustomer_thenReturnEmptyList() {
        //Given
        UUID customerId = UUID.randomUUID();
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 3));

        List<ItemGroup> itemGroupList2 = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 18));

        Order order = new Order(itemGroupList, customerId);
        Order order2 = new Order(itemGroupList2, customerId);
        orderRepository.save(order);
        orderRepository.save(order2);

        //When
        List<Order> listOfOrders = orderRepository.getByCustomerId(UUID.randomUUID());

        //Then
        Assertions.assertThat(listOfOrders).isEmpty();
    }

    @Test
    void getAll_givenARepositoryWithOrders_thenReturnAllOrders() {
        //Given
        UUID customerId = UUID.randomUUID();
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 3));

        List<ItemGroup> itemGroupList2 = List.of(new ItemGroup(new Item("name", "description", 5.0, 25), 3),
                new ItemGroup( new Item("name", "description", 5.0, 25), 18));

        Order order = new Order(itemGroupList, customerId);
        Order order2 = new Order(itemGroupList2, customerId);
        orderRepository.save(order);
        orderRepository.save(order2);

        //When
        List<Order> listOfOrders = orderRepository.getAll();

        //Then
        Assertions.assertThat(listOfOrders).containsExactlyInAnyOrder(order, order2);
    }
}