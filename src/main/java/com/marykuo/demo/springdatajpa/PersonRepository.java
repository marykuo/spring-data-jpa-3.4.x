package com.marykuo.demo.springdatajpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long>, UserRepository, ContactRepository {

    List<Person> findByEmailAddress(String emailAddress);
}
