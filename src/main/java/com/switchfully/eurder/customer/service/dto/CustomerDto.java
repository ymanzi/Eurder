package com.switchfully.eurder.customer.service.dto;

import com.switchfully.eurder.customer.domain.Address;
import com.switchfully.eurder.customer.domain.Contact;
import com.switchfully.eurder.customer.domain.Name;

import java.util.UUID;

public record CustomerDto(UUID id, Name name, Contact contact, Address address) {
}
