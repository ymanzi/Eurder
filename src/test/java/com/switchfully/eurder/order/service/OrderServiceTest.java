package com.switchfully.eurder.order.service;

import com.switchfully.eurder.domain.*;
import com.switchfully.eurder.domain.classes.*;
import com.switchfully.eurder.infrastructure.exceptions.UnauthorizedException;
import com.switchfully.eurder.domain.ItemRepository;
import com.switchfully.eurder.domain.OrderRepository;
import com.switchfully.eurder.service.mappers.OrderMapper;
import com.switchfully.eurder.service.OrderService;
import com.switchfully.eurder.service.dtos.CreateOrderDto;
import com.switchfully.eurder.service.dtos.OrderedItemDto;
import com.switchfully.eurder.service.dtos.ReportOrdersOfCustomerDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {ItemRepository.class, CustomerRepository.class, OrderRepository.class, OrderService.class})
@EnableAutoConfiguration
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderServiceTest {

    private OrderMapper orderMapper = new OrderMapper();
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderService orderService;

    private Item item;
    private Item item2;
    private Customer customer;
    private Customer customer2;


    @BeforeEach
    void setUp() {
        item = itemRepository.save(new Item("name", "desc", 5.0, 10));
        item2 = itemRepository.save(new Item("name2", "desc", 3.0, 0));

        customer = customerRepository.save(new Customer(
                new User(
                    new Name("f", "l"),
                    new Contact("email", "phone")
                ),
                new Address("street", "number", "zip", "city")
                ));
        customer2 = customerRepository.save(new Customer(
                new User(
                    new Name("f2", "l2"),
                    new Contact("email2", "phone2")
                ),
                new Address("street", "number", "zip", "city")
        ));
    }

    @Test
    void save_whenSavingOrder_thenReturnOrderPrice() {
        //Given
        List<OrderedItemDto> itemGroupList = List.of(new OrderedItemDto(item.getId(), 3),
                new OrderedItemDto(item2.getId(), 3));

        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);

        double savedPrice = orderService.save(orderDto, customer.getId());

        assertEquals(24.0, savedPrice);
    }

    @Test
    void getByCustomerId_whenRequestingCustomerOrders_thenReturnCorrespondingOrders() {
        //Given
        List<OrderedItemDto> itemGroupList = List.of(new OrderedItemDto(item.getId(), 3),
                new OrderedItemDto(item2.getId(), 3));

        List<OrderedItemDto> itemGroupList2 = List.of(new OrderedItemDto(item.getId(), 3),
                new OrderedItemDto(item2.getId(), 3));

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
        assertThrows(UnauthorizedException.class, ()-> orderService.getByCustomerId(123));
    }

}