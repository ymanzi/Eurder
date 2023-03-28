package com.switchfully.eurder.customer.domain;

import com.switchfully.eurder.exceptions.CustomerWithThatEmailAlreadyExist;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {

    private final HashMap<String, Customer> customerByEmail;

    public CustomerRepository() {
        customerByEmail = new HashMap<>();
    }

    public Customer save(Customer newCustomer) {
        String email = newCustomer.getContact().getEmail();
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

    public Optional<Customer> getByEmail(String email) {
        return Optional.ofNullable(customerByEmail.get(email));
    }

    public void checkIfEmailAlreadyExist(Customer customer){
        String emailToCheck = customer.getContact().getEmail();
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
