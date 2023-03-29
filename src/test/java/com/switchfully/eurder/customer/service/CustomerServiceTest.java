package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.customer.domain.*;
import com.switchfully.eurder.customer.service.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.service.dto.CustomerDto;
import com.switchfully.eurder.exceptions.NonExistentItemException;
import com.switchfully.eurder.exceptions.UnauthorizedException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    private CustomerMapper customerMapper = new CustomerMapper();
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private Customer customer = new Customer(new Name("first", "last"),
            new Contact("email", "phone"),
            new Address("street", "number", "zip", "city"));

    private CustomerDto customerDto = customerMapper.toDto(customer);
    private CreateCustomerDto createCustomerDto = new CreateCustomerDto("first", "last",
                                                                        "email", "phone",
                                                    "street", "number", "zip", "city");

    @BeforeEach
    void setUp() {
        customerMapper = new CustomerMapper();
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerService(customerMapper, customerRepository);


    }

    @Test
    void save_givenACreateCustomerDto_returnCorrespondingCustomerDto() {
        //Given
        Mockito.when(customerRepository.save(org.mockito.ArgumentMatchers.any(Customer.class))).thenReturn(customer);

        //When
        CustomerDto savedCustomerDto = customerService.save(createCustomerDto);

        //Then
        assertEquals(customerDto, savedCustomerDto);
    }

    @Test
    void getAll_givenAnAdminAccess_returnAllCustomers() {
        //Given
        Mockito.when(customerRepository.getAll()).thenReturn(List.of(customer));

        //When
         List<CustomerDto> listOfSavedCustomerDto = customerService.getAll("admin");

        //Then
        Assertions.assertThat(listOfSavedCustomerDto).containsExactly(customerDto);
    }

    @Test
    void getAll_givenNotAdminAccess_thenThrowUnauthorizedException() {
        //Given
        Mockito.when(customerRepository.getAll()).thenReturn(List.of(customer));

        //Then
        assertThrows(UnauthorizedException.class, ()-> customerService.getAll("123"));
    }

    @Test
    void getByEmail_givenAValidEmailAndAdminAccess_returnCorrespondingCustomerDto() {
        //Given
        Mockito.when(customerRepository.getById(org.mockito.ArgumentMatchers.any())).thenReturn(Optional.of(customer));

        //When
        CustomerDto requestedCustomerDto = customerService.getById(customer.getId(),  "admin");

        //Then
        assertEquals(customerDto, requestedCustomerDto);
    }

    @Test
    void getByEmail_givenAnUnknownEmailAndAdminAccess_thenThrowNonExistentItemException() {
        //Given
        Mockito.when(customerRepository.getById(org.mockito.ArgumentMatchers.any())).thenReturn(Optional.ofNullable(null));

        //Then
        assertThrows(NonExistentItemException.class, () -> customerService.getById(UUID.randomUUID(),  "admin"));
    }

    @Test
    void getByEmail_givenANotAdminAccess_thenThrowUnauthorizedException() {
        //Given
        Mockito.when(customerRepository.getById(org.mockito.ArgumentMatchers.any())).thenReturn(Optional.ofNullable(null));

        //Then
        assertThrows(UnauthorizedException.class, ()-> customerService.getById(UUID.randomUUID(), "123"));
    }
}