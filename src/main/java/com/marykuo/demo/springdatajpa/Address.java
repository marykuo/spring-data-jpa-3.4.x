package com.marykuo.demo.springdatajpa;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @Column(updatable = false)
    private Long id;
    private String street;
    private String city;

    @OneToOne(mappedBy = "address")
    private Person person;

    protected Address() {
    }

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
}

