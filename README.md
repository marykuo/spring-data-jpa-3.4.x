# Implementing one-to-one relationship With a Shared Primary Key

## Getting Started

### Entity

- 關聯的兩個 table 都需要宣告 @OneToOne，因為是雙向關係。
- 擁有 foreign key column 的 Table 需要宣告：
  - @MapsId：從 `Person.java` 複製 primary key 的值。
  - @JoinColumn：指定要作為 primary key 的欄位名稱

### Repository

- 若只會透過 Person 管理 Address，則只需要 PersonRepository。
- 若會獨立查詢、更新、刪除 Address，就應該建立 AddressRepository。

### Notice

<details>
  <summary>需要特別注意雙向關係的維護</summary>

  ```java
  @Entity
  @Table
  public class Person {

      // fields, constructor, getters and setters

      public void setAddress(Address address) {
          this.address = address;
          if (address != null) {
              address.setPerson(this);
          }
      }
  }
  ```

  ```java
  @Entity
  @Table
  public class Address {

      // fields, constructor, getters and setters

      public void setPerson(Person person) {
          this.person = person;
          this.personId = (person != null) ? person.getId() : null;
      }
  }
  ```
</details>

## Entity Relationship Diagram

<img src="https://www.baeldung.com/wp-content/uploads/2018/12/1-1-SK.png" width="400" />

## SQL

<img src="images/records.png" width="500" />


```sql
create sequence person_sequence;

create table person
(
    id            bigint not null
        primary key,
    name          varchar(255),
    email_address varchar(255)
);

create table address
(
  person_id bigint not null
    primary key
    constraint fk81ihijcn1kdfwffke0c0sjqeb
      references person,
  street    varchar(255),
  city      varchar(255)
);
```
