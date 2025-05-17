package com.marykuo.demo.springdatajpa;

import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Person {
    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @Column(updatable = false)
    private long personId;

    private String name;

    private LocalDate createDate;

    private LocalDate modifyDate;

    protected Person() {
        this.createDate = LocalDate.now();
        this.modifyDate = LocalDate.now();
    }

    protected Person(String name) {
        this.name = name;
        this.createDate = LocalDate.now();
        this.modifyDate = LocalDate.now();
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }
}
