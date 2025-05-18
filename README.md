# JPA 繼承策略：Joined Table

在物件導向的領域中，繼承是一種常見的設計手法。JPA 提供了三種繼承對應策略（Inheritance Mapping Strategies）來將類別階層對應至資料庫表格：SINGLE_TABLE、TABLE_PER_CLASS、以及 JOINED。本篇將聚焦於 JOINED 策略的實作與分析。

## 概念說明

- 設計原則：每個 Entity 類別對應一張資料表，子類別透過主鍵與父類別表進行 join。
- 優點：
  - 資料結構正規化（Normalization），避免欄位重複。 
  - 易於擴充與維護，符合 OOP 精神。
- 缺點：
  - 查詢時需進行資料表 join，可能對效能造成影響。 
  - 實作複雜度高於其他策略。

## 實作範例

### JPA Entity

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

### PostgreSQL DDL

```sql
create sequence person_sequence;

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

### 資料展示

<img src="images/records.png" alt="records" />

## 使用情境與建議

- 適用於類別階層清晰，關聯結構穩定的領域模型。
- 若應用系統對查詢效能要求較高，應謹慎評估是否使用此策略，或透過 View、Cache 等手段優化查詢效能。
- 當資料結構複雜且需要擴充時（例如：可預期子類別結構變化頻繁），JOINED 策略能更容易維護資料一致性與表結構清晰度。

## 總結

Joined Table Inheritance 是一種符合物件導向設計原則、適合正規化資料結構的策略，但需考量查詢效能及維運成本。

在設計階段，建議搭配實體模型與查詢需求進行整體評估。
