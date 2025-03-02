package com.marykuo.demo.springdatajpa;

public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public void someContactMethod(Person person) {
        System.out.println("ContactRepositoryImpl method called");
    }
}
