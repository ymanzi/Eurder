package com.switchfully.eurder.customer.api;

import com.switchfully.eurder.customer.service.CustomerService;
import com.switchfully.eurder.customer.service.dto.CreateCustomerDto;
import com.switchfully.eurder.customer.service.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //=======================================  GET  ==================================================

    @GetMapping(produces = "application/json")
    public List<CustomerDto> getAll(@RequestHeader String adminId){
        return customerService.getAll(adminId);
    }

    @GetMapping(path = "/email}", produces = "application/json")
    public CustomerDto get(@RequestHeader String email, @RequestHeader String adminId){
        return customerService.getByEmail(email, adminId);
    }

    //=======================================  POST ==================================================
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public CustomerDto create(@RequestBody CreateCustomerDto createCustomerDto){
        return customerService.save(createCustomerDto);
    }
}
