package com.switchfully.eurder.customer.domain;

import com.switchfully.eurder.utils.Utils;

public record Address(String street, String houseNumber, String zipCode, String city) {
    public Address(String street, String houseNumber, String zipCode, String city) {
        Utils.fieldIsPresent(street, "street");
        Utils.fieldIsPresent(houseNumber, "house number");
        Utils.fieldIsPresent(zipCode, "zip code");
        Utils.fieldIsPresent(city, "city");

        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }
}
