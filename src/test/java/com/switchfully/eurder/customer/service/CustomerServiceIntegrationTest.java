package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.customer.domain.*;
import com.switchfully.eurder.customer.service.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.service.dto.CustomerDto;
import com.switchfully.eurder.exceptions.NonExistentItemException;
import com.switchfully.eurder.exceptions.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceIntegrationTest {
    private final CustomerMapper customerMapper= new CustomerMapper();
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private final Customer customer = new Customer(new Name("first", "last"),
            new Contact("email", "phone"),
            new Address("street", "number", "zip", "city"));

    private final CustomerDto customerDto = customerMapper.toDto(customer);
    private final CreateCustomerDto createCustomerDto = new CreateCustomerDto("first", "last",
                                                                        "email", "phone",
                                                    "street", "number", "zip", "city");

    @BeforeEach
    void setUp() {
        customerRepository = new CustomerRepository();
        customerService = new CustomerService(customerMapper, customerRepository);
    }

    @Test
    void save_givenACreateCustomerDto_returnCorrespondingCustomerDto() {
        //When
        CustomerDto savedCustomerDto = customerService.save(createCustomerDto);

        //Then
        assertEquals(customerDto.contact() , savedCustomerDto.contact());
        assertEquals(customerDto.name(), savedCustomerDto.name());
        assertEquals(customerDto.address(), savedCustomerDto.address());
    }

    @Test
    void getAll_givenAnAdminAccess_returnAllCustomers() {
        //Given
        customerService.save(createCustomerDto);

        //When
         List<CustomerDto> listOfSavedCustomerDto = customerService.getAll("admin");
         CustomerDto savedCustomerDto = listOfSavedCustomerDto.get(0);

        //Then
        assertEquals(1, listOfSavedCustomerDto.size());
        assertEquals(customerDto.contact() , savedCustomerDto.contact());
        assertEquals(customerDto.name(), savedCustomerDto.name());
        assertEquals(customerDto.address(), savedCustomerDto.address());
    }

    @Test
    void getAll_givenNotAdminAccess_thenThrowUnauthorizedException() {

        //Then
        assertThrows(UnauthorizedException.class, ()-> customerService.getAll("123"));
    }

  /*  @Test
    void getByEmail_givenAValidEmailAndAdminAccess_returnCorrespondingCustomerDto() {
        //Given
        customerService.save(createCustomerDto);

        //When
        CustomerDto savedCustomerDto = customerService.getById("email",  "admin");

        //Then
        assertEquals(customerDto.contact() , savedCustomerDto.contact());
        assertEquals(customerDto.name(), savedCustomerDto.name());
        assertEquals(customerDto.address(), savedCustomerDto.address());
    }*/

    @Test
    void getById_givenAnUnknownEmailAndAdminAccess_thenThrowNonExistentItemException() {

        //Then
        assertThrows(NonExistentItemException.class, () -> customerService.getById(UUID.randomUUID(),  "admin"));
    }

    @Test
    void getByEmail_givenANotAdminAccess_thenThrowUnauthorizedException() {

        //Then
        assertThrows(UnauthorizedException.class, ()-> customerService.getById(UUID.randomUUID(), "123"));
    }
}