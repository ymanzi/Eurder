package com.switchfully.eurder.order.service;

import com.switchfully.eurder.customer.domain.*;
import com.switchfully.eurder.exceptions.UnauthorizedException;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.order.domain.ItemGroup;
import com.switchfully.eurder.order.domain.OrderRepository;
import com.switchfully.eurder.order.service.dto.CreateOrderDto;
import com.switchfully.eurder.order.service.dto.ItemGroupDto;
import com.switchfully.eurder.order.service.dto.ReportOrdersOfCustomerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderMapper orderMapper;
    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private CustomerRepository customerRepository;
    private OrderService orderService;

    private Item item;
    private Item item2;
    private Customer customer;
    private Customer customer2;


    @BeforeEach
    void setUp() {
        orderMapper = new OrderMapper();
        orderRepository = new OrderRepository();
        itemRepository = new ItemRepository();
        customerRepository = new CustomerRepository();
        orderService = new OrderService(orderMapper, orderRepository, itemRepository, customerRepository);

        item = itemRepository.save(new Item("name", "desc", 5.0, 10));
        item2 = itemRepository.save(new Item("name2", "desc", 3.0, 0));

        customer = customerRepository.save(new Customer(new Name("f", "l"),
                new Contact("email", "phone"),
                new Address("street", "number", "zip", "city")
                ));
        customer2 = customerRepository.save(new Customer(new Name("f2", "l2"),
                new Contact("email2", "phone2"),
                new Address("street", "number", "zip", "city")
        ));
    }

    @Test
    void save_whenSavingOrder_thenReturnOrderPrice() {
        //Given
        List<ItemGroupDto> itemGroupList = List.of(new ItemGroupDto(item.getId(), 3),
                new ItemGroupDto(item2.getId(), 3));

        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);

        double savedPrice = orderService.save(orderDto, customer.getId());

        assertEquals(24.0, savedPrice);
    }

    @Test
    void getByCustomerId_whenRequestingCustomerOrders_thenReturnCorrespondingOrders() {
        //Given
        List<ItemGroupDto> itemGroupList = List.of(new ItemGroupDto(item.getId(), 3),
                new ItemGroupDto(item2.getId(), 3));

        List<ItemGroupDto> itemGroupList2 = List.of(new ItemGroupDto(item.getId(), 3),
                new ItemGroupDto(item2.getId(), 3));

        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);
        CreateOrderDto orderDto2 = new CreateOrderDto(itemGroupList2);

        orderService.save(orderDto, customer.getId());
        orderService.save(orderDto, customer.getId());
        orderService.save(orderDto2, customer2.getId());

        //When
        ReportOrdersOfCustomerDto report = orderService.getByCustomerId(customer.getId());

        //Then
        Assertions.assertThat(report.getListOfOrderDto().size()).isEqualTo(2);
    }

    @Test
    void getByCustomerId_whenCustomerDoesNotExist_thenTrowUnauthorizedException() {
        //Then
        assertThrows(UnauthorizedException.class, ()-> orderService.getByCustomerId(UUID.randomUUID()));
    }

}