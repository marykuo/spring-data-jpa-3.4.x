package com.marykuo.demo.springdatajpa;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Person {
    private String company;

    protected Employee() {
        super();
    }

    public Employee(String name, String company) {
        super(name);
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
