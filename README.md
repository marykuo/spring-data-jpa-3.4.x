# JPA 繼承策略：Single Table

SINGLE_TABLE 是 JPA 三種繼承映射策略中最簡潔的一種。所有類別的屬性資料統一儲存在一張資料表中，並透過 discriminator column（辨識欄位）區分資料的實際類型。

## 概念說明

- 設計原則：將整個繼承樹的資料映射至單一資料表，使用 @DiscriminatorColumn 來區分各子類型。
- 優點：
  - 簡單易用，實作容易。
  - 查詢效能優異，不需進行資料表 join。
- 缺點：
  - 不同子類別的欄位全部出現在同一張表，造成許多欄位為 NULL，違反正規化原則。 
  - 子類別無法定義 NOT NULL 欄位約束。 
  - 若繼承層級或資料量龐大，表格寬度與資料密度會影響效能與可讀性。

## 實作範例

### JPA Entity

```java

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("null")
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
@DiscriminatorValue("Employee")
public class Employee extends Person {
    private String company;

    // Constructor, getters and setters
}

@Entity
@DiscriminatorValue("Interviewee")
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
    person_id      bigint      not null
        primary key,
    name           varchar(255),
    person_type    varchar(31) not null,
    company        varchar(255),
    interview_date date
);
```

- 單一資料表包含所有子類別的欄位。
- person_type 欄位自動填入 discriminator 值，用於區分實體類型。

### 資料展示

<img src="images/records.png" alt="records" />

## 使用情境與建議

- 適用場景 
  - 子類別欄位數量少，重疊度高。 
  - 資料筆數不大，且查詢效能優先於結構正規化。 
  - 不會強烈依賴資料層級間的約束限制。
- 不適用場景 
  - 各子類屬性差異大，導致資料表欄位過於稀疏。 
  - 須對特定欄位加上 NOT NULL 限制。 
  - 系統需依照物件結構演進頻繁修改欄位，可能導致資料表膨脹與風險。

## 總結

SINGLE_TABLE 策略是實作上最簡便且查詢效能最佳的 JPA 繼承對應方式，但隨著系統規模擴大與物件模型複雜化，易造成結構膨脹與維護困難。

建議僅在資料模型簡單或對效能要求極高的場景中使用。
