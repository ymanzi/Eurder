package com.switchfully.eurder.domain.classes;

import com.switchfully.eurder.infrastructure.Utils;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "addresses")
public  class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_address")
    @SequenceGenerator(sequenceName = "seq_address", allocationSize = 1, name = "seq_address")
    private int id;

    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "zip_code")
    private String zipCode;
    @Column(name = "city")
    private String city;

    public Address() {
    }

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

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(street, address.street) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(zipCode, address.zipCode) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, houseNumber, zipCode, city);
    }

    @Override
    public String toString() {
        return "Address[" +
                "street=" + street + ", " +
                "houseNumber=" + houseNumber + ", " +
                "zipCode=" + zipCode + ", " +
                "city=" + city + ']';
    }


}
