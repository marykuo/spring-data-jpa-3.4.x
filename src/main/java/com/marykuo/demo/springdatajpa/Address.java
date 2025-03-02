package com.marykuo.demo.springdatajpa;

import jakarta.persistence.*;

@Embeddable
public class Address {
    private String street;
    private String city;
    @Embedded
    private ZipCode zipCode;

    protected Address() { }

    public Address(String street, String city, ZipCode zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }
}

