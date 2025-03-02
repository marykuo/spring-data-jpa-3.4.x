package com.marykuo.demo.springdatajpa;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public void someUserMethod(Person person) {
        System.out.println("UserRepositoryImpl method called");
    }
}
