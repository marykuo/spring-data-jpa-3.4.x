# JPA 繼承策略：MappedSuperClass

`@MappedSuperclass` 是一種屬性繼承策略，它不會對應到資料表本身，而是提供共用欄位給子類別繼承。這種策略較偏向程式設計層級的繼承，而非資料模型的繼承。

## 概念說明

- 設計原則：
  - 將基底類別標註為 `@MappedSuperclass`，子類別會繼承其欄位與對應的 JPA 設定。 
  - 不會在資料庫中為基底類別建立對應的資料表。
- 優點：
  - 可重用共通欄位定義（如 ID、建立時間等），簡化重複程式碼。
  - 每個實體類別有自己的資料表，結構清晰，符合正規化。 
  - 適合應用於 domain-driven design 中的 value object 層。
- 缺點：
  - 不支援 polymorphic query（無法透過基底類別查詢所有子類別）。 
  - 基底類別無法成為實體（無法用 `EntityManager` 直接存取）。

## 實作範例

### JPA Entity

```java

@MappedSuperclass
public abstract class Person {
  @Id
  @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_sequence")
  @Column(updatable = false)
  private long personId;

  private String name;

  private LocalDate createDate;

  private LocalDate modifyDate;

    // constructor, getters, setters
}

@Entity
public class Employee extends Person {
    private String company;

    // constructor, getters, setters
}

@Entity
public class Interviewee extends Person {
    private LocalDate interviewDate;

    // constructor, getters, setters
}
```

- `@MappedSuperclass` 通常搭配抽象類別 `abstract class` 使用，以強化語意並避免被誤用。

### PostgreSQL DDL

```sql
create sequence person_sequence;

create table employee
(
    person_id bigint not null
        primary key,
    name      varchar(255),
    company   varchar(255),
    create_date date,
    modify_date date
)

create table interviewee
(
    person_id      bigint not null
        primary key,
    name           varchar(255),
    interview_date date,
    create_date date,
    modify_date date
)
```

- 子類別 `employee` 與 `interviewee` 各自擁有獨立資料表，皆包含繼承自 Person 的欄位。

### 資料展示

<img src="images/records.png" alt="records" />

## 使用情境與建議

- 適用場景
  - 系統中有多個實體共享相同欄位（如 ID、名稱、建立人等），希望集中管理。
  - 重視資料正規化，每個實體需有獨立資料表。 
  - 不需要 polymorphic query 能力（例如查詢所有 Person 子類實體）。
- 不適用場景
  - 需要依據父類型進行聯合查詢或關聯管理（建議考慮 JOINED 策略）。
  - 想讓父類別可以被單獨儲存或查詢（@MappedSuperclass 不支援這類操作）。


## 總結

`@MappedSuperclass` 更像是一種程式碼共用機制，而非資料模型繼承。它簡潔、實用，特別適合處理大量共通欄位，並保持資料表正規化。

不過，如果你需要跨類型聚合查詢或物件導向查詢特性，應考慮使用其他繼承策略如 `JOINED` 或 `SINGLE_TABLE`。
