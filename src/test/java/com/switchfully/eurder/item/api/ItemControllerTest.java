package com.switchfully.eurder.item.api;

import com.switchfully.eurder.service.dtos.CreateItemDto;
import com.switchfully.eurder.service.dtos.ItemDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;


@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemControllerTest {

    @LocalServerPort
    private int port;


    @Test
    void create_whenTryingToSaveAsAdmin_thenReturn201StatusCode() {
        CreateItemDto createItemDto = new CreateItemDto("name", "description", 1.0, 5);

        RestAssured
                .given()
                .body(createItemDto)
                .contentType(ContentType.JSON)
                .header("adminId", "admin")
                .log().all()
                .when()
                .port(port)
                .post("/items/")
                .then()
                .contentType(ContentType.JSON)
                .log().all()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void create_whenTryingToSaveAsNonAdmin_thenReturn403StatusCode() {
        CreateItemDto createItemDto = new CreateItemDto("name", "description", 1.0, 5);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createItemDto)
                .header("adminId", "123")
                .when()
                .port(port)
                .post("/items/")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void update_whenUpdatingAnExistingItemAsAdmin_thenReturn200StatusCode() {
        CreateItemDto createItemDto = new CreateItemDto("name", "description", 1.0, 5);

        ItemDto itemDto = RestAssured
                            .given()
                            .contentType(ContentType.JSON)
                            .body(createItemDto)
                            .header("adminId", "admin")
                            .when()
                            .port(port)
                            .post("/items/")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.CREATED.value())
                            .extract()
                            .as(ItemDto.class);


        CreateItemDto updateItemDto = new CreateItemDto("update", "update", 1.0, 5);


        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(updateItemDto)
                .header("adminId", "admin")
                .when()
                .port(port)
                .put("/items/" + itemDto.getId())
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void update_whenUpdatingAnNonExistingItem_thenReturn403StatusCode() {
        CreateItemDto createItemDto = new CreateItemDto("name", "description", 1.0, 5);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createItemDto)
                .header("adminId", "admin")
                .when()
                .port(port)
                .put("/items/123")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void update_whenUpdatingAsANonAdmin_thenReturn401StatusCode() {
        CreateItemDto createItemDto = new CreateItemDto("name", "description", 1.0, 5);

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(createItemDto)
                .header("adminId", "123")
                .when()
                .port(port)
                .put("/items/9d7e1255-a7e5-49df-afe5-4105e4f1d4ba")
                .then()
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void getLow_AsANonAdmin_thenReturn401StatusCode() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("adminId", "123")
                .when()
                .port(port)
                .get("/items/stock/low")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
       }

    @Test
    void getLow_asAdmin_thenReturn200StatusCode() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("adminId", "admin")
                .when()
                .port(port)
                .get("/items/stock/low")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getMedium_AsNonAdmin_thenReturn401StatusCode() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("adminId", "123")
                .when()
                .port(port)
                .get("/items/stock/medium")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void getMedium_asAdmin_thenReturn200StatusCode() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("adminId", "admin")
                .when()
                .port(port)
                .get("/items/stock/medium")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getHigh_AsANonAdmin_thenReturn401StatusCode() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("adminId", "123")
                .when()
                .port(port)
                .get("/items/stock/high")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void getHigh_asAdmin_thenReturn200StatusCode() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("adminId", "admin")
                .when()
                .port(port)
                .get("/items/stock/high")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void getAll_AsANonAdmin_thenReturn401StatusCode() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("adminId", "123")
                .when()
                .port(port)
                .get("/items/")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    void getAll_asAnAdmin_thenReturn200StatusCode() {
        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("adminId", "admin")
                .when()
                .port(port)
                .get("/items/")
                .then()
                .contentType(ContentType.JSON)
                .assertThat()
                .statusCode(HttpStatus.OK.value());
    }
}