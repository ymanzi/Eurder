package com.switchfully.eurder.domain;

import com.switchfully.eurder.infrastructure.exceptions.CustomerWithThatEmailAlreadyExist;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CustomerRepository {

    private final HashMap<String, Customer> customerByEmail;

    public CustomerRepository() {
        customerByEmail = new HashMap<>();
    }

    public Customer save(Customer newCustomer) {
        String email = newCustomer.getContact().email();
        checkIfEmailAlreadyExist(newCustomer);
        customerByEmail.put(email, newCustomer);
        return customerByEmail.get(email);
    }

    public List<Customer> getAll() {
        return customerByEmail
                .values()
                .stream()
                .toList();
    }

    public Optional<Customer> getById(UUID id) {
        return customerByEmail
                .values()
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst();
    }

    public void checkIfEmailAlreadyExist(Customer customer){
        String emailToCheck = customer.getContact().email();
        List<String> listOfCustomer = customerByEmail
                .keySet()
                .stream()
                .filter(email -> email.equals(emailToCheck))
                .toList();
        if (!listOfCustomer.isEmpty()){
            throw new CustomerWithThatEmailAlreadyExist();
        }
    }
}
