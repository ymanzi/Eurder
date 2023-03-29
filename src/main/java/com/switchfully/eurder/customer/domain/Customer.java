package com.switchfully.eurder.customer.domain;

import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private Name name;
    private Contact contact;
    private Address address;

    public Customer(Name name, Contact contact, Address address) {
        this.id = UUID.randomUUID();
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

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return contact.equals(customer.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact);
    }
}
