package com.marykuo.demo.springdatajpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {
            Person user = new Person("Andy", "andy@example.com");
            personRepository.someUserMethod(user);

            Person contact = new Person("Mary", "mary@example.com");
            personRepository.someContactMethod(contact);

            personRepository.saveAll(List.of(contact, user));

            personRepository.findByEmailAddress("mary@example.com")
                    .forEach(System.out::println);
        };
    }
}
