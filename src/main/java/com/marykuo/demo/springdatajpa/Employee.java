package com.marykuo.demo.springdatajpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Employee")
public class Employee extends Person {
    private String company;

    protected Employee() {
    }

    public Employee(long personId, String name, String company) {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}