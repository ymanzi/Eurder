package com.switchfully.eurder.order.api;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {
//    @LocalServerPort
//    private int port;
//
//
//    private OrderController orderController;
//
//    private Item item;
//    private Item item2;
//    private Customer customer;
//    private Customer customer2;
//
//    Customer saveCustomer(CreateCustomerDto createCustomerDto){
//        return RestAssured
//                .given()
//                .body(createCustomerDto)
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
//                .when()
//                .port(port)
//                .post("/customers")
//                .then()
//                .extract()
//                .as(Customer.class);
//    }
//
//    Item saveItem(CreateItemDto createItemDto){
//        return RestAssured
//                .given()
//                .body(createItemDto)
//                .contentType(ContentType.JSON)
//                .header("adminId", "admin")
//                .when()
//                .port(port)
//                .post("/items/")
//                .then()
//                .extract()
//                .as(Item.class);
//    }
//
//
//    @BeforeEach
//    void setUp() {
//        item =saveItem(new CreateItemDto("name", "desc", 5.0, 10));
//        item2 = saveItem(new CreateItemDto("name2", "desc", 3.0, 0));
//
//        customer = saveCustomer(new CreateCustomerDto("f", "l",
//                "email", "phone",
//                "street", "number", "zip", "city"
//        ));
//        customer2 = saveCustomer(new CreateCustomerDto("f2", "l2",
//                "email2", "phone2",
//                "street", "number", "zip", "city"
//        ));
//    }
//
//    @Test
//    void create_whenSavingOrder_thenReturnOrderPrice() {
//        //Given
//        List<ItemGroupDto> itemGroupList = List.of(new ItemGroupDto(item.getId(), 3),
//                new ItemGroupDto(item2.getId(), 3));
//
//        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);
//
//        double savedPrice = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(orderDto)
//                .header("customerId", customer.getId())
//                .log().all()
//                .when()
//                .port(port)
//                .post("/orders/")
//                .then()
//                .contentType(ContentType.JSON)
//                .log().all()
//                .assertThat()
//                .statusCode(HttpStatus.CREATED.value())
//                .extract()
//                .as(double.class);
//
//        assertEquals(24.0, savedPrice);
//    }
//
//    @Test
//    void create_whenProvidingUnknownCustomerId_thenReturn401StatusCode() {
//        //Given
//        List<ItemGroupDto> itemGroupList = List.of(new ItemGroupDto(item.getId(), 3),
//                new ItemGroupDto(item2.getId(), 3));
//
//        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);
//
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(orderDto)
//                .header("customerId", UUID.randomUUID())
//                .when()
//                .port(port)
//                .post("/orders/")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.UNAUTHORIZED.value());
//    }
//
//    @Test
//    void getByCustomerId_whenRequestingCustomerOrders_thenReturnCorrespondingOrders() {
//        //Given
//        List<ItemGroupDto> itemGroupList = List.of(new ItemGroupDto(item.getId(), 3),
//                new ItemGroupDto(item2.getId(), 3));
//
//        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);
//
//        double savedPrice = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(orderDto)
//                .header("customerId", customer.getId())
//                .log().all()
//                .when()
//                .port(port)
//                .post("/orders/")
//                .then()
//                .contentType(ContentType.JSON)
//                .log().all()
//                .assertThat()
//                .statusCode(HttpStatus.CREATED.value())
//                .extract()
//                .as(double.class);
//
//        //When
//        ReportOrdersOfCustomerDto orders = RestAssured
//                            .given()
//                            .contentType(ContentType.JSON)
//                            .header("customerId", customer.getId())
//                            .when()
//                            .port(port)
//                            .get("/orders/")
//                            .then()
//                            .assertThat()
//                            .statusCode(HttpStatus.OK.value())
//                            .extract()
//                            .as(ReportOrdersOfCustomerDto.class);
//
//        //Then
//        Assertions.assertThat(orders.getPrice()).isEqualTo(24.0);
//    }
//
//    @Test
//    void getByCustomerId_whenCustomerDoesNotExist_thenTrowUnauthorizedException() {
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .header("customerId", UUID.randomUUID())
//                .when()
//                .port(port)
//                .get("/orders/")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.UNAUTHORIZED.value());
//    }
//
//    @Test
//    void orderAgain_whenOrderingSameOrder_thenUpdateTheOldOrder(){
//        //Given
//
//        /// Pass Order
//        List<ItemGroupDto> itemGroupList = List.of(new ItemGroupDto(item.getId(), 3),
//                new ItemGroupDto(item2.getId(), 3));
//
//        CreateOrderDto orderDto = new CreateOrderDto(itemGroupList);
//
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(orderDto)
//                .header("customerId", customer.getId())
//                .log().all()
//                .when()
//                .port(port)
//                .post("/orders/")
//                .then()
//                .contentType(ContentType.JSON)
//                .log().all()
//                .assertThat()
//                .statusCode(HttpStatus.CREATED.value());
//
//
//        /// Get Order Id
//        UUID orderId = RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .header("customerId", customer.getId())
//                .when()
//                .port(port)
//                .get("/orders/")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value())
//                .extract()
//                .as(ReportOrdersOfCustomerDto.class)
//                .getListOfOrderDto()
//                .get(0)
//                .getOrderId();
//
//
//        //Update Item
//        CreateItemDto updateItemDto = new CreateItemDto("update", "update", 10.0, 5);
//
//        RestAssured
//                .given()
//                .contentType(ContentType.JSON)
//                .body(updateItemDto)
//                .header("adminId", "admin")
//                .when()
//                .port(port)
//                .put("/items/" + item.getId())
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.OK.value());
//
//        //When
//        double price = RestAssured
//                        .given()
//                        .contentType(ContentType.JSON)
//                        .body(orderDto)
//                        .header("customerId", customer.getId())
//                        .log().all()
//                        .when()
//                        .port(port)
//                        .post("/orders/" + orderId)
//                        .then()
//                        .contentType(ContentType.JSON)
//                        .log().all()
//                        .assertThat()
//                        .statusCode(HttpStatus.CREATED.value())
//                        .extract()
//                        .as(Double.class);
//
//        //Then
//        assertEquals(39, price);
//    }

}