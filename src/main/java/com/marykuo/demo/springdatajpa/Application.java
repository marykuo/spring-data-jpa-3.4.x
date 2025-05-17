package com.marykuo.demo.springdatajpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(
            PersonRepository personRepository,
            EmployeeRepository employeeRepository,
            IntervieweeRepository intervieweeRepository
    ) {
        return args -> {
            Person person = new Person();
            person.setName("Mary");
            personRepository.save(person);

            Employee employee = new Employee();
            employee.setName("John");
            employee.setCompany("Google");
            employeeRepository.save(employee);

            Interviewee interviewee = new Interviewee();
            interviewee.setName("Alice");
            interviewee.setInterviewDate(LocalDate.now());
            intervieweeRepository.save(interviewee);
        };
    }
}
