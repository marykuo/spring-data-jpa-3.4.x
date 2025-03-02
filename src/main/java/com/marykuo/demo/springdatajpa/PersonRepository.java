package com.marykuo.demo.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByAddress_ZipCode(ZipCode zipCode);
}
