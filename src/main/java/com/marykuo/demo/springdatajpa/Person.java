package com.marykuo.demo.springdatajpa;

import jakarta.persistence.*;

@Entity
@Table
public class Person {
    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @Column(updatable = false)
    private Long id;
    private String name;
    private String emailAddress;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;

    protected Person() {
    }

    public Person(String name, String emailAddress, Address address) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.setAddress(address);
    }

    public Long getId() {
        return id;
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

    public void setAddress(Address address) {
        this.address = address;
        if (address != null) {
            address.setPerson(this);
        }
    }
}
