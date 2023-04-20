package com.switchfully.eurder.customer.domain;

import com.switchfully.eurder.domain.*;
import com.switchfully.eurder.infrastructure.exceptions.CustomerWithThatEmailAlreadyExist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CustomerRepositoryTest {

    private CustomerRepository customerRepository = new CustomerRepository();
    private final Customer customer = new Customer(new Name("first", "last"),
            new Contact("email", "phone"),
            new Address("street", "number", "zip", "city"));

    private final Customer customer2 = new Customer(new Name("first", "last"),
            new Contact("email2", "phone"),
            new Address("street", "number", "zip", "city"));

    @BeforeEach
    void setUp() {
        customerRepository = new CustomerRepository();
    }

    @Test
    void save_whenSavingACustomer_returnCorrespondingCustomer() {
        //When
        Customer savedCustomer = customerRepository.save(customer);

        //Then
        Assertions.assertThat(savedCustomer).isEqualTo(customer);
    }

    @Test
    void checkIfEmailAlreadyExist_givenAnExistingCustomer_thenShouldThrowCustomerWithThatEmailAlreadyExist() {
        //Given
        customerRepository.save(customer);

        //Then
        assertThrows(CustomerWithThatEmailAlreadyExist.class, () -> customerRepository.save(customer));
    }

    @Test
    void checkIfEmailAlreadyExist_givenAnNonExistingCustomer_thenShouldNotThrowCustomerWithThatEmailAlreadyExist() {
        //Then
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> customerRepository.save(customer));
    }

    @Test
    void getAll_givenAnEmptyRepository_thenShouldReturnEmptyList() {
        assertTrue(customerRepository.getAll().isEmpty());
    }

    @Test
    void getAll_givenARepositoryWithCustomers_thenShouldReturnListWithAllOfThem() {
        //Given
        customerRepository.save(customer);
        customerRepository.save(customer2);

        //Then
        Assertions.assertThat(customerRepository.getAll()).containsExactlyInAnyOrder(customer, customer2);
    }

    @Test
    void getByEmail_givenARepositoryWithCustomers_thenShouldReturnTheCorrespondingOne() {
        //Given
        customerRepository.save(customer);
        customerRepository.save(customer2);

        //Then
        Assertions.assertThat(customerRepository.getById(customer.getId())).isEqualTo(Optional.of(customer));
    }

    @Test
    void getByEmail_givenARepositoryWithCustomersAndUnknownEmail_thenShouldReturnEmptyOptional() {
        //Given
        customerRepository.save(customer);
        customerRepository.save(customer2);

        //Then
        Assertions.assertThat(customerRepository.getById(UUID.randomUUID())).isEqualTo(Optional.empty());
    }
}