package com.switchfully.eurder.service.dtos;

import com.switchfully.eurder.domain.Address;
import com.switchfully.eurder.domain.Contact;
import com.switchfully.eurder.domain.Name;

import java.util.UUID;

public record CustomerDto(UUID id, Name name, Contact contact, Address address) {
}
