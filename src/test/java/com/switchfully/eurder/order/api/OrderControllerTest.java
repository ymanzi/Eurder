package com.switchfully.eurder.order.api;

import com.switchfully.eurder.customer.domain.*;
import com.switchfully.eurder.exceptions.UnauthorizedException;
import com.switchfully.eurder.item.domain.Item;
import com.switchfully.eurder.item.domain.ItemRepository;
import com.switchfully.eurder.order.domain.ItemGroup;
import com.switchfully.eurder.order.domain.OrderRepository;
import com.switchfully.eurder.order.service.OrderMapper;
import com.switchfully.eurder.order.service.OrderService;
import com.switchfully.eurder.order.service.dto.CreateOrderDto;
import com.switchfully.eurder.order.service.dto.ReportOrdersOfCustomerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {
    @LocalServerPort
    private int port;


    private OrderController orderController;
    private OrderService orderService;

    private ItemRepository itemRepository;

    private CustomerRepository customerRepository;

    private Item item;
    private Item item2;
    private Customer customer;
    private Customer customer2;

    @BeforeEach
    void setUp() {
        item = new Item("name", "desc", 5.0, 10);
        item2 = new Item("name2", "desc", 3.0, 0);
        itemRepository = new ItemRepository();
        itemRepository.save(item);
        itemRepository.save(item2);

        customer = new Customer(new Name("f", "l"),
                new Contact("email", "phone"),
                new Address("street", "number", "zip", "city")
        );
        customer2 = new Customer(new Name("f2", "l2"),
                new Contact("email2", "phone2"),
                new Address("street", "number", "zip", "city")
        );
        customerRepository = new CustomerRepository();
        customerRepository.save(customer);
        customerRepository.save(customer2);

        orderService = new OrderService(new OrderMapper(), new OrderRepository(), itemRepository, customerRepository);
        orderController = new OrderController(orderService);
    }

    @Test
    void create_whenSavingOrder_thenReturnOrderPrice() {
        //Given
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(item, 3),
                new ItemGroup(item2, 3));

        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);

        double savedPrice = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(orderDto)
                .header("customerId", customer.getId())
                .log().all()
                .when()
                .port(port)
                .post("/orders/")
                .then()
                .contentType(ContentType.JSON)
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(double.class);

        assertEquals(24.0, savedPrice);
    }

    @Test
    void create_whenProvidingUnknownCustomerId_thenReturn401StatusCode() {
        //Given
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(item, 3),
                new ItemGroup(item2, 3));

        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(orderDto)
                .header("customerId", UUID.randomUUID())
                .when()
                .port(port)
                .post("/orders/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    /*@Test
    void getByCustomerId_whenRequestingCustomerOrders_thenReturnCorrespondingOrders() {
        //Given
        List<ItemGroup> itemGroupList = List.of(new ItemGroup(item, 3),
                new ItemGroup(item2, 3));

        List<ItemGroup> itemGroupList2 = List.of(new ItemGroup(item, 3),
                new ItemGroup(item2, 3));

        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList, customer.getId());
        CreateOrderDto orderDto2 = new CreateOrderDto(itemGroupList2, customer2.getId());


        orderController.create(orderDto, )
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
        assertThrows(UnauthorizedException.class, ()-> orderService.getByCustomerId(customer.getId()));
    }*/

}