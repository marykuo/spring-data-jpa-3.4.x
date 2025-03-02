package com.marykuo.demo.springdatajpa;

import jakarta.persistence.*;

@Entity
@Table
public class Address {
    @Id
    private Long personId;
    private String street;
    private String city;

    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    private Person person;

    protected Address() {
    }

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        this.personId = (person != null) ? person.getId() : null;
    }
}
