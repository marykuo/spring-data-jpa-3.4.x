# spring-data-jpa-3.4.x

## embedded entity

## JPA

```java

@Entity
@Table
public class Person {
    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @Column(updatable = false)
    private Long personId;

    private String name;

    private String emailAddress;

    @Embedded
    private Address address;

    // Constructors, getters, setters, etc.
}

@Embeddable
public class Address {
    private String street;

    private String city;

    @Embedded
    private ZipCode zipCode;

    // Constructors, getters, setters, etc.
}

@Embeddable
public class ZipCode {
    @Column(name = "zip_code", nullable = false, length = 10)
    private String value;

    // Constructors, getters, setters, etc.
}
```

## Postgres

```sql
create sequence person_sequence;

create table person
(
    person_id     bigint      not null
        primary key,
    name          varchar(255),
    email_address varchar(255),
    street        varchar(255),
    city          varchar(255),
    zip_code      varchar(10) not null
);
```
