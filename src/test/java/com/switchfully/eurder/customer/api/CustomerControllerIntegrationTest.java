package com.switchfully.eurder.customer.api;

import com.switchfully.eurder.customer.domain.Address;
import com.switchfully.eurder.customer.domain.Contact;
import com.switchfully.eurder.customer.domain.Name;
import com.switchfully.eurder.customer.service.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.service.dto.CustomerDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class CustomerControllerIntegrationTest {
    @Autowired
    private CustomerController customerController;

    @LocalServerPort
    private int port;

    private final CreateCustomerDto createCustomerDto = new CreateCustomerDto("first", "last", "email",
            "phone", "street", "16A", "1000", "City");

    private final CreateCustomerDto createCustomerDto2 = new CreateCustomerDto("first", "last", "email2",
            "phone2", "street", "16A", "1000", "City");

    @Test
    void create_givenCreateCustomerDto_thenSaveAndReturnTheCustomer() {
        CustomerDto customerDto =
                RestAssured
                        .given()
                        .body(createCustomerDto)
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when()
                        .port(port)
                        .post("/customers")
                        .then()
                        .extract()
                        .as(CustomerDto.class);


        assertEquals(customerDto.name(), new Name("first", "last"));
        assertEquals(customerDto.contact(), new Contact("email", "phone"));
        assertEquals(customerDto.address(), new Address("street", "16A", "1000", "City"));
    }

    @Test
    void getAll_givenARepositoryWithCustomersWithAdminAccess_thenRetrieveAllCustomers() {
        //Given
        CustomerDto c1 = customerController.create(createCustomerDto);

        //When
        CustomerDto[] customerList =
                RestAssured
                        .given()
                        .headers("adminId", "admin")
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when()
                        .port(port)
                        .get("/customers")
                        .then()
                        .extract()
                        .as(CustomerDto[].class);

        CustomerDto customerDto = customerList[0];

        //Then
        assertEquals(customerDto.name(), new Name("first", "last"));
        assertEquals(customerDto.contact(), new Contact("email", "phone"));
        assertEquals(customerDto.address(), new Address("street", "16A", "1000", "City"));
    }
    @Test
    void getAll_givenARequestWithoutAdminAccess_thenGetUnauthorizedStatusCode() {
        //When
        int statusCode =
                RestAssured
                        .given()
                        .headers("adminId", "123")
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when()
                        .port(port)
                        .get("/customers").getStatusCode();

        //Then
        assertEquals(HttpStatus.UNAUTHORIZED.value(), statusCode);
    }

    @Test
    void getByEmail_givenARepositoryWithCustomers_thenRetrieveCorrespondingCustomer() {
        //Given
        CustomerDto c1 = customerController.create(createCustomerDto);
        CustomerDto c2 = customerController.create(createCustomerDto2);

        //When
        CustomerDto customer =
                RestAssured
                        .given()
                        .header("adminId", "admin")
                        .header("email", "email")
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when()
                        .port(port)
                        .get("/customers/email")
                        .then()
                        .extract()
                        .as(CustomerDto.class);

        //Then
        Assertions.assertThat(customer.contact()).isEqualTo(customer.contact());
    }

    @Test
    void getByEmail_givenARequestWithoutAdminAccess_thenGetUnauthorizedStatusCode() {
        //When
        int statusCode =
                RestAssured
                        .given()
                        .headers("adminId", "123")
                        .header("email", "email")
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when()
                        .port(port)
                        .get("/customers/email").getStatusCode();

        //Then
        assertEquals(HttpStatus.UNAUTHORIZED.value(), statusCode);
    }


}