# spring-data-jpa-3.4.x

本專案聚焦於 JPA 在 Spring Data 3.4.x 中的進階用法，涵蓋繼承策略、多種關聯對應方法與實作細節，並提供完整範例與說明，方便快速理解與應用。

## 目的

- 精準掌握 Spring Data JPA 3.4.x 的映射策略與特性。
- 結合實務場景說明各種設計抉擇的優缺點。
- 透過分支示範不同策略的實作方式與資料行為。

## 分支分類

### 值類型（Value Types）

- [value-types/embedded](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/value-types/embedded)
- [value-types/element-collection](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/value-types/element-collection)
- [value-types/embedded-id](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/value-types/embedded-id)
- [value-types/attribute-overrides](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/value-types/attribute-overrides)

### 繼承策略（Inheritance Strategies）

在設計物件導向實體與關聯式資料庫映射時，JPA 提供三種標準的繼承策略（`@Inheritance`）來對應物件導向的 class hierarchy：

- [inheritance-strategy/single-table](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/inheritance-strategy/single-table)
- [inheritance-strategy/table-per-class](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/inheritance-strategy/table-per-class)
- [inheritance-strategy/mapped-superclass](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/inheritance-strategy/mapped-superclass)
- [inheritance-strategy/joined-table](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/inheritance-strategy/joined-table)

<details>
<summary>繼承策略概述</summary>

#### Summary

- SINGLE_TABLE：父類和子類的所有欄位儲存在**同一張資料表**，使用 discriminator 欄位區分不同的子類。
- TABLE_PER_CLASS：父類和每個子類使用各自的資料表，共通欄位會同時存在於父類和子類的資料表中。
    - 若無多型查詢需求，可改用 `@MappedSuperclass`。
- JOINED：父類（共通欄位）存在一個資料表中，子類的欄位在各自的資料表中，並且使用外鍵關聯。

#### 比較

| Strategy | SINGLE_TABLE                  | TABLE_PER_CLASS  | JOINED          |
|----------|-------------------------------|------------------|-----------------|
| 優點       | 查詢效能高、結構簡單                    | 多型查詢效能高、易於擴充     | 儲存效能高、正規化高、易於擴充 |
| 缺點       | 欄位稀疏／儲存空間浪費、無法加子類 Not null    | 多型查詢效能低、主鍵生成策略受限 | 查詢需 JOIN、效能較差   |
| 適用場景     | 子類欄位數量少、結構簡單、多型查詢頻繁、查詢單一子類機會低 | 子類欄位數量多、多型查詢機會低  | 子類欄位數量多、多型查詢機會高 |

| Strategy | SINGLE_TABLE | TABLE_PER_CLASS | JOINED      |
|----------|--------------|-----------------|-------------|
| 結構正規化    | 中            | 低               | ✅           |
| 查詢子類效能   | 低（欄位稀疏）      | ✅               | 低（需 JOIN）   |
| 多型查詢效能   | ✅            | 低（需 UNION）      | ✅           |
| 儲存空間效率   | 中            | 中（欄位重複）         | ✅           |
| 實作難度     | ✅            | 中（主鍵生成策略受限）     | 難（資料一致性風險高） |

#### 考量關鍵

- 多型查詢頻繁度
- 子類欄位數量
- 子類資料量
- 子類查詢頻繁度
- 正規化需求

</details>

### 表格關聯（Entity Relationships）

#### One-to-One

- [relationships/one-to-one/join-table](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/one-to-one/join-table)
- [relationships/one-to-one/shared-primary-key](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/one-to-one/shared-primary-key)
- [relationships/one-to-one/foreign-key](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/one-to-one/foreign-key)

#### One-to-Many

- [relationships/one-to-many/mapped-by](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/one-to-many/mapped-by)
- [relationships/one-to-many/owning-side](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/one-to-many/owning-side)
- [relationships/one-to-many/foreign-key](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/one-to-many/foreign-key)
- [relationships/one-to-many/join-table](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/one-to-many/join-table)

#### Many-to-One

- [relationships/many-to-one/mapped-by](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-one/mapped-by)
- [relationships/many-to-one/owning-side](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-one/owning-side)
- [relationships/many-to-one/foreign-key](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-one/foreign-key)
- [relationships/many-to-one/join-table](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-one/join-table)

#### Many-to-Many

- [relationships/many-to-many/join-table](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-many/join-table)
- [relationships/many-to-many/owning-side](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-many/owning-side)
- [relationships/many-to-many/inverse-side](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-many/inverse-side)
- [relationships/many-to-many/shared-primary-key](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-many/shared-primary-key)
- [relationships/many-to-many/foreign-key](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/relationships/many-to-many/foreign-key)

## 適用對象

本專案適合以下角色深入研究與應用：

- 資深軟體工程師：需根據需求選擇適當映射策略並實作最佳效能方案。
- 系統架構師（SA）：設計資料結構與實體模型，並統整效能與正規化需求。
- 資料庫工程師（DBA）：了解 ORM 生成 SQL 模式以配合指標、交易與效能調校。

## Tech Stack

- Java 17
- Spring Boot 3.4.x
- Spring Data JPA 3.4.x
- Hibernate 6.x
- PostgreSQL 15.x
