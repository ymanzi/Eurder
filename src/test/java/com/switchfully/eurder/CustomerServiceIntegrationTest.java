package com.switchfully.eurder;

import com.switchfully.eurder.domain.classes.*;
import com.switchfully.eurder.service.mappers.CustomerMapper;
import com.switchfully.eurder.service.CustomerService;
import com.switchfully.eurder.service.dtos.CreateCustomerDto;
import com.switchfully.eurder.service.dtos.CustomerDto;
import com.switchfully.eurder.domain.*;
import com.switchfully.eurder.infrastructure.exceptions.NonExistentItemException;
import com.switchfully.eurder.infrastructure.exceptions.UnauthorizedException;
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

@SpringBootTest(classes = {CustomerRepository.class, CustomerService.class, CustomerMapper.class})
@EnableAutoConfiguration
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerServiceIntegrationTest {
    private CustomerMapper customerMapper = new CustomerMapper();
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    private final Customer customer = new Customer(
            new User(
                new Name("first", "last"),
                new Contact("email", "phone")
            ),
            new Address("street", "number", "zip", "city"));

    private final CustomerDto customerDto = customerMapper.toDto(customer);
    private final CreateCustomerDto createCustomerDto = new CreateCustomerDto("first", "last",
                                                                        "email", "phone",
                                                    "street", "number", "zip", "city");

    @Test
    void save_givenACreateCustomerDto_returnCorrespondingCustomerDto() {
        //When
        CustomerDto savedCustomerDto = customerService.save(createCustomerDto);

        //Then
        assertEquals(customerDto.contact() , savedCustomerDto.contact());
        assertEquals(customerDto.name(), savedCustomerDto.name());
    }

    @Test
    void getAll_givenAnAdminAccess_returnAllCustomers() {
        //Given
        CustomerDto savedCustomerDto = customerService.save(createCustomerDto);

        //When
         List<CustomerDto> listOfSavedCustomerDto = customerService.getAll("admin");

        //Then
        Assertions.assertThat(listOfSavedCustomerDto).hasSize(1).containsExactly(savedCustomerDto);
    }

    @Test
    void getAll_givenNotAdminAccess_thenThrowUnauthorizedException() {

        //Then
        assertThrows(UnauthorizedException.class, ()-> customerService.getAll("123"));
    }

    @Test
    void getByEmail_givenAValidEmailAndAdminAccess_returnCorrespondingCustomerDto() {
        //Given
        CustomerDto savedCustomer = customerService.save(createCustomerDto);

        //When
        CustomerDto savedCustomerDto = customerService.getById(savedCustomer.id(),"admin");

        //Then
        assertEquals(savedCustomer, savedCustomerDto);
    }

    @Test
    void getById_givenAnUnknownEmailAndAdminAccess_thenThrowNonExistentItemException() {

        //Then
        assertThrows(NonExistentItemException.class, () -> customerService.getById(123,  "admin"));
    }

    @Test
    void getByEmail_givenANotAdminAccess_thenThrowUnauthorizedException() {

        //Then
        assertThrows(UnauthorizedException.class, ()-> customerService.getById(1, "123"));
    }
}