package com.marykuo.demo.springdatajpa;

import jakarta.persistence.*;

@Entity
@Table
public class Person {
    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @Column(updatable = false)
    private Long personId;
    private String name;
    private String emailAddress;
    @Embedded
    private Address address;

    protected Person() {
    }

    public Person(String name, String emailAddress, Address address) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
    }

    public Long getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Address getAddress() {
        return address;
    }
}
