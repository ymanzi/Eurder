package com.switchfully.eurder.customer.service.dto;

import com.switchfully.eurder.customer.domain.Address;
import com.switchfully.eurder.customer.domain.Contact;
import com.switchfully.eurder.customer.domain.Name;

import java.util.Objects;
import java.util.UUID;

public class CustomerDto {

    private Name name;
    private Contact contact;
    private Address address;

    public CustomerDto(Name name, Contact contact, Address address) {
        this.name = name;
        this.contact = contact;
        this.address = address;
    }


    public Name getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

}
