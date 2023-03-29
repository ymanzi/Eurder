package com.switchfully.eurder.customer.service.dto;
public class CreateCustomerDto {

    private final String firstname;
    private final String lastname;
    private final String email;
    private final String phone;
    private final String street;
    private final String houseNumber;
    private final String zipCode;
    private final String city;

    public CreateCustomerDto(String firstname
                            , String lastname
                            , String email
                            , String phone
                            , String street
                            , String houseNumber
                            , String zipCode
                            , String city)
    {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

}
