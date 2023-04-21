package com.switchfully.eurder.service;

import com.switchfully.eurder.domain.AddressRepository;
import com.switchfully.eurder.domain.UserRepository;
import com.switchfully.eurder.domain.classes.Address;
import com.switchfully.eurder.domain.classes.Customer;
import com.switchfully.eurder.domain.CustomerRepository;
import com.switchfully.eurder.domain.classes.User;
import com.switchfully.eurder.service.dtos.CreateCustomerDto;
import com.switchfully.eurder.service.dtos.CustomerDto;
import com.switchfully.eurder.infrastructure.exceptions.NonExistentItemException;
import com.switchfully.eurder.service.mappers.CustomerMapper;
import com.switchfully.eurder.infrastructure.Utils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CustomerService {
    private CustomerMapper customerMapper;
    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;
    private UserRepository userRepository;

    @Autowired
    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository, AddressRepository addressRepository, UserRepository userRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }


    public CustomerDto save(CreateCustomerDto createCustomerDto) {
        Customer newCustomer = customerMapper.fromDto(createCustomerDto);
        //User user = userRepository.save(newCustomer.getUser());
        //Address address =addressRepository.save(newCustomer.getAddress());

        Customer createdCustomer = customerRepository.save(newCustomer);
        return customerMapper.toDto(createdCustomer);
    }

    public List<CustomerDto> getAll(String adminId) {
        Utils.adminAccess(adminId);
        List<Customer> listOfCustomers = customerRepository.findAll();
        return customerMapper.toDto(listOfCustomers);
    }

    public CustomerDto getById(int userId, String adminId) {
        Utils.adminAccess(adminId);
        Optional<Customer> customer = customerRepository.findById(userId);

        if (customer.isEmpty())
            throw new NonExistentItemException("customer");

        return customerMapper.toDto(customer.get());
    }
}
