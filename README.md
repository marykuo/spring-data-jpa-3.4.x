# spring-data-jpa-3.4.x

## SingleTable

### JPA

```java

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
    @Column(updatable = false)
    private long personId;

    private String name;

    // Constructor, getters and setters
}

@Entity
@PrimaryKeyJoinColumn(name = "personId")
public class Employee extends Person {
    private String company;

    // Constructor, getters and setters
}

@Entity
@PrimaryKeyJoinColumn(name = "personId")
public class Interviewee extends Person {
    private LocalDate interviewDate;

    // Constructor, getters and setters
}
```

### Postgres

<img src="images/records.png" width="500" />

```sql
create table person
(
    person_id bigint not null
        primary key,
    name      varchar(255)
);

create table employee
(
    person_id bigint not null
        primary key
        constraint fkfm68kmqett1iydj8xgfb6two8
            references person,
    company   varchar(255)
);

create table interviewee
(
    person_id      bigint not null
        primary key
        constraint fkb3s5rcxxgrqg2qn5afjmyq6ij
            references person,
    interview_date date
);
```
