# Practical-23 (Scanner Edition) - Hibernate / ORM Persistence to MySQL Database

## Aim
Demonstrate Object-Relational Mapping (ORM) and Hibernate persistence principles using entity annotations (`@Entity`, `@Table`, `@Id`) and an EntityManager to persist user-supplied entities directly to the local MySQL database via `java.util.Scanner`.

## Files
- `HibernateOrmSimulation.java`: Interactive Java program with MySQL connection.

## Prerequisites
- Local MySQL server running on port `3306`.
- `javadb` database.

## Compilation and Execution

```bash
# Compile
javac -cp "../../lib/mysql-connector-j-8.3.0.jar:../../lib/h2.jar:." HibernateOrmSimulation.java

# Run
java -cp "../../lib/mysql-connector-j-8.3.0.jar:../../lib/h2.jar:." HibernateOrmSimulation
```

## Sample Terminal Run
```text
=== Hibernate / ORM Database Persistence (User Input) ===
Connected to local MySQL database successfully.
Database Engine: MySQL 26.10.0

How many products do you want to persist into database? 2

Enter Product #1 details:
  Product ID: 101
  Product Name: Mechanical Keyboard
  Product Price: Rs.3499.0
Hibernate/ORM Persisted Entity -> Product into MySQL table 'products'

Enter Product #2 details:
  Product ID: 102
  Product Name: Wireless Gaming Mouse
  Product Price: Rs.1999.0
Hibernate/ORM Persisted Entity -> Product into MySQL table 'products'

--- Querying Saved Entities from MySQL Database ---
Product ID: 101 | Name: Mechanical Keyboard | Price: Rs.3499.0
Product ID: 102 | Name: Wireless Gaming Mouse | Price: Rs.1999.0
```
