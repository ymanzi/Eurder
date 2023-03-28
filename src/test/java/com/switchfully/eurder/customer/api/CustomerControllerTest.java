package com.switchfully.eurder.customer.api;

import com.switchfully.eurder.customer.service.CustomerService;
import com.switchfully.eurder.customer.service.dto.CreateCustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {
    private CustomerService customerService;
    private CustomerController customerController;

    private final CreateCustomerDto createCustomerDto = new CreateCustomerDto("first", "last", "email",
            "phone", "street", "16A", "1000", "City");

    @BeforeEach
    void setUp() {
        customerService = Mockito.mock(CustomerService.class);
        customerController = new CustomerController(customerService);
    }

    @Test
    void create_givenCreateCustomerDto_thenCallSave() {
        customerController.create(createCustomerDto);
        Mockito.verify(customerService).save(createCustomerDto);
    }
}