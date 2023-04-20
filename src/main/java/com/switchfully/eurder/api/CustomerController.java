package com.switchfully.eurder.api;

import com.switchfully.eurder.service.CustomerService;
import com.switchfully.eurder.service.dtos.CreateCustomerDto;
import com.switchfully.eurder.service.dtos.CustomerDto;
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

    @GetMapping(path = "/{userId}", produces = "application/json")
    public CustomerDto getById(@PathVariable UUID userId, @RequestHeader String adminId){
        return customerService.getById(userId, adminId);
    }

    //=======================================  POST ==================================================
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "", consumes = "application/json", produces = "application/json")
    public CustomerDto create(@RequestBody CreateCustomerDto createCustomerDto){
        return customerService.save(createCustomerDto);
    }
}
