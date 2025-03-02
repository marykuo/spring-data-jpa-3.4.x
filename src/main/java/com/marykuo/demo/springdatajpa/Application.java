package com.marykuo.demo.springdatajpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository, AddressRepository addressRepository) {
        return args -> {
            Address address = new Address("中正區中山北路１段", "臺北市");
            Person andy = new Person(
                    "Andy",
                    "andy@example.com",
                    address
            );
            personRepository.saveAndFlush(andy);

            assertThat(personRepository.findAll().size() == 1);
            assertThat(addressRepository.findAll().size() == 1);

            // 只會刪除關聯，不會刪除該筆 address 資料
            andy.setAddress(null);
            personRepository.saveAndFlush(andy);

            assertThat(personRepository.findAll().size() == 1);
            assertThat(addressRepository.findAll().size() == 1);

            // 建立一筆新的 address
            andy.setAddress(new Address("中正區中山北路１段", "臺北市"));
            personRepository.saveAndFlush(andy);

            assertThat(personRepository.findAll().size() == 1);
            assertThat(addressRepository.findAll().size() == 2);

            // 刪除成功
            addressRepository.delete(address);
            addressRepository.flush();

            assertThat(personRepository.findAll().size() == 1);
            assertThat(addressRepository.findAll().size() == 1);

            // 因為關聯關係存在，所以會刪除失敗
            addressRepository.delete(andy.getAddress());
            addressRepository.flush();

            assertThat(personRepository.findAll().size() == 1);
            assertThat(addressRepository.findAll().size() == 1);

            // create a person without address
            Person mary = new Person("Mary", "mary@example.com", null);
            personRepository.saveAndFlush(mary);

            assertThat(personRepository.findAll().size() == 2);
            assertThat(addressRepository.findAll().size() == 1);
        };
    }

    private void assertThat(boolean condition) {
        if (!condition) {
            throw new RuntimeException("Assertion failed");
        }
    }
}
