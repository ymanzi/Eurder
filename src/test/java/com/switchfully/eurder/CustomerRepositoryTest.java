package com.switchfully.eurder;

import com.switchfully.eurder.domain.*;
import com.switchfully.eurder.domain.classes.*;
import com.switchfully.eurder.infrastructure.exceptions.CustomerWithThatEmailAlreadyExist;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = {CustomerRepository.class})
@EnableAutoConfiguration
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class repositoryTest {

    @Autowired
    private CustomerRepository repository;
    private final Customer customer = new Customer(
            new User(
                new Name("first", "last"),
                new Contact("email", "phone")
            ),
            new Address("street", "number", "zip", "city"));

    private final Customer customer2 = new Customer(
            new User(
                new Name("first", "last"),
                new Contact("email2", "phone")
            ),
            new Address("street", "number", "zip", "city"));
    @Test
    void save_whenSavingACustomer_returnCorrespondingCustomer() {
        //When
        Customer savedCustomer = repository.save(customer);

        //Then
        Assertions.assertThat(savedCustomer).isEqualTo(customer);
        Assertions.assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void getAll_givenAnEmptyRepository_thenShouldReturnEmptyList() {
        Assertions.assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void getAll_givenARepositoryWithCustomers_thenShouldReturnListWithAllOfThem() {
        //Given
        repository.save(customer);
        repository.save(customer2);

        //Then
        Assertions.assertThat(repository.findAll()).containsExactlyInAnyOrder(customer, customer2);
    }
}