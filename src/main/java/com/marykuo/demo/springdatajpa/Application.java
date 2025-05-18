package com.marykuo.demo.springdatajpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

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
            person.setPersonId(1);
            person.setName("Mary");
            personRepository.save(person);

            Employee employee = new Employee();
            employee.setPersonId(2);
            employee.setName("Amy");
            employee.setCompany("Google");
            employeeRepository.save(employee);

            Interviewee interviewee = new Interviewee();
            interviewee.setPersonId(3);
            interviewee.setName("John");
            interviewee.setInterviewDate(LocalDate.now());
            intervieweeRepository.save(interviewee);

            List<Person> personList = personRepository.findAll();
            System.out.println("Person: " + personList.size());  // 3 Records

            List<Employee> employeeList = employeeRepository.findAll();
            System.out.println("Employee: " + employeeList.size());  // 1 Record
        };
    }
}
