package com.switchfully.eurder.service.dtos;

import com.switchfully.eurder.domain.classes.Address;
import com.switchfully.eurder.domain.classes.Contact;
import com.switchfully.eurder.domain.classes.Name;

import java.util.UUID;

public record CustomerDto(int id, Name name, Contact contact, Address address) {
}
