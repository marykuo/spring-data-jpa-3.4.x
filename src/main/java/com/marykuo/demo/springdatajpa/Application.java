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
            Person andy = new Person(
                    "Andy",
                    "andy@example.com",
                    new Address("宜蘭市中山路一段", "宜蘭縣", new ZipCode("26060"))
            );
            Person mary = new Person(
                    "Mary",
                    "mary@example.com",
                    new Address("中正區中山北路１段", "臺北市", new ZipCode("10041"))
            );
            personRepository.saveAll(List.of(andy, mary));

            personRepository.findByAddress_ZipCode(new ZipCode("10041"))
                    .forEach(System.out::println);
        };
    }
}
