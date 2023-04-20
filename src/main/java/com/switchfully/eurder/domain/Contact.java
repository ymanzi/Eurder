package com.switchfully.eurder.domain;

import com.switchfully.eurder.infrastructure.Utils;


public record Contact(String email, String phone) {

    public Contact(String email, String phone) {
        Utils.fieldIsPresent(email, "email");
        Utils.fieldIsPresent(phone, "phone number");

        this.email = email;
        this.phone = phone;
    }
}
