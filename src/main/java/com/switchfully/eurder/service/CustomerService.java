package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.Customer;
import com.switchfully.eurder.domain.CustomerRepository;
import com.switchfully.eurder.service.dtos.CreateCustomerDto;
import com.switchfully.eurder.service.dtos.CustomerDto;
import com.switchfully.eurder.infrastructure.exceptions.NonExistentItemException;
import com.switchfully.eurder.service.mappers.CustomerMapper;
import com.switchfully.eurder.infrastructure.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerDto save(CreateCustomerDto createCustomerDto) {
        Customer newCustomer = customerMapper.fromDto(createCustomerDto);
        Customer createdCustomer = customerRepository.save(newCustomer);
        return customerMapper.toDto(createdCustomer);
    }

    public List<CustomerDto> getAll(String adminId) {
        Utils.adminAccess(adminId);
        List<Customer> listOfCustomers = customerRepository.getAll();
        return customerMapper.toDto(listOfCustomers);
    }

    public CustomerDto getById(UUID userId, String adminId) {
        Utils.adminAccess(adminId);
        Optional<Customer> customer = customerRepository.getById(userId);

        if (customer.isEmpty())
            throw new NonExistentItemException("customer");

        return customerMapper.toDto(customer.get());
    }
}
