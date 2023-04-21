package com.switchfully.eurder.service.dtos;
public class CreateCustomerDto {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;

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
