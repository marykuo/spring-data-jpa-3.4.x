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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "person_address",
            joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "address_id", referencedColumnName = "id")})
    private Address address;

    protected Person() {
    }

    public Person(String name, String emailAddress, Address address) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.address = address;
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
    }
}
