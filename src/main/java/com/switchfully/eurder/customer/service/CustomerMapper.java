package com.switchfully.eurder.customer.service;

import com.switchfully.eurder.customer.domain.Address;
import com.switchfully.eurder.customer.domain.Contact;
import com.switchfully.eurder.customer.domain.Customer;
import com.switchfully.eurder.customer.domain.Name;
import com.switchfully.eurder.customer.service.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.service.dto.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {
    public Customer fromDto(CustomerDto customerDto) {
        return new Customer(customerDto.name(), customerDto.contact(), customerDto.address());
    }

    public Customer fromDto(CreateCustomerDto createCustomerDto) {
        Name name = new Name(createCustomerDto.getFirstname(), createCustomerDto.getLastname());
        Contact contact = new Contact(createCustomerDto.getEmail(), createCustomerDto.getPhone());
        Address address = new Address(createCustomerDto.getStreet(), createCustomerDto.getHouseNumber(), createCustomerDto.getZipCode(), createCustomerDto.getCity());
        return new Customer(name, contact, address);
    }

    public CustomerDto toDto(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getContact(), customer.getAddress());
    }

    public List<CustomerDto> toDto(List<Customer> listOfCustomer) {
        return listOfCustomer
                .stream()
                .map(this::toDto)
                .toList();
    }
}
