# spring-data-jpa-3.4.x

## one-to-one

### Implementing With a Foreign Key

- 關聯的兩個 table 都需要宣告 `@OneToOne`，因為是雙向關係。
- 擁有 foreign key column 的 Table 需要宣告 `@JoinColumn`。
  - name 是 foreign key 的欄位名稱。
  - referencedColumnName 是關聯欄位的名稱。

```sql
@JoinColumn(name = "address_id", referencedColumnName = "id")
```

## Entity Relationship Diagram

![image](https://www.baeldung.com/wp-content/uploads/2018/12/1-1_FK.png)

## SQL

```sql
create sequence person_sequence;

create table person
(
    person_id     bigint not null
    primary key,
    address_id    bigint
        unique
        constraint fkk7rgn6djxsv2j2bv1mvuxd4m9
            references address,
    name          varchar(255),
    email_address varchar(255)
);

create sequence address_sequence;

create table address
(
    id     bigint not null
        primary key,
    street varchar(255),
    city   varchar(255)
);
```
