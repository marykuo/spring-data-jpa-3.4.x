# spring-data-jpa-3.4.x

本專案聚焦於 JPA 在 Spring Data 3.4.x 中的進階用法，涵蓋繼承策略、多種關聯對應方法與實作細節，並提供完整範例與說明，方便快速理解與應用。

## 目的

- 精準掌握 Spring Data JPA 3.4.x 的映射策略與特性。
- 結合實務場景說明各種設計抉擇的優缺點。
- 透過分支示範不同策略的實作方式與資料行為。

## 分支分類

### 繼承策略（Inheritance Strategies）

在設計物件導向實體與關聯式資料庫映射時，JPA 提供三種標準的繼承策略（`@Inheritance`）來對應物件導向的 class hierarchy：

- [inheritance-strategy/single-table](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/inheritance-strategy/single-table)
- [inheritance-strategy/table-per-class](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/inheritance-strategy/table-per-class)
- [inheritance-strategy/mapped-superclass](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/inheritance-strategy/mapped-superclass)
- [inheritance-strategy/joined-table](https://github.com/marykuo/spring-data-jpa-3.4.x/tree/inheritance-strategy/joined-table)

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
