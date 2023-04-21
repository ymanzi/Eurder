package com.switchfully.eurder.service.mappers;

import com.switchfully.eurder.domain.classes.*;
import com.switchfully.eurder.service.dtos.CreateCustomerDto;
import com.switchfully.eurder.service.dtos.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    public Customer fromDto(CreateCustomerDto createCustomerDto) {
        Name name = new Name(createCustomerDto.getFirstname(), createCustomerDto.getLastname());
        Contact contact = new Contact(createCustomerDto.getEmail(), createCustomerDto.getPhone());
        User user = new User(name, contact);
        Address address = new Address(createCustomerDto.getStreet(),
                                        createCustomerDto.getHouseNumber(),
                                        createCustomerDto.getZipCode(),
                                        createCustomerDto.getCity());
        return new Customer(user, address);
    }

    public CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getUser().getName(), customer.getUser().getContact(), customer.getAddress());
    }

    public List<CustomerDto> toDto(List<Customer> listOfCustomer) {
        return listOfCustomer
                .stream()
                .map(this::toDto)
                .toList();
    }
}
