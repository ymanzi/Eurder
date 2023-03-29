package com.switchfully.eurder.customer.domain;

import com.switchfully.eurder.exceptions.CustomerWithThatEmailAlreadyExist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


class CustomerRepositoryTest {

    private final CustomerRepository customerRepository = new CustomerRepository();
    private final Customer customer = new Customer(new Name("first", "last"),
            new Contact("email", "phone"),
            new Address("street", "number", "zip", "city"));


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
        //Given
        customerRepository.save(customer);

        //Then
        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> customerRepository.save(customer));
    }
}