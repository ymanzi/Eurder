package com.switchfully.eurder.domain.classes;

import com.switchfully.eurder.infrastructure.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Embeddable
public class Contact {
    @Column(name = "email", unique = true)
    @Email(message = "Please provide a valid email!")
    private String email;
    @Size(min = 1, message = "the phone number is mandatory!")
    @Column(name = "phone", unique = true)
    private String phone;

    public Contact() {
    }

    public Contact(String email, String phone) {
        Utils.fieldIsPresent(email, "email");
        Utils.fieldIsPresent(phone, "phone number");

        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Contact) obj;
        return Objects.equals(this.email, that.email) &&
                Objects.equals(this.phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone);
    }

    @Override
    public String toString() {
        return "Contact[" +
                "email=" + email + ", " +
                "phone=" + phone + ']';
    }

}
