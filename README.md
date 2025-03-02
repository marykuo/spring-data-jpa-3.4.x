# spring-data-jpa-3.4.x

## embedded entity

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
