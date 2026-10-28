# Practical-23 - Hibernate / ORM Program to Write to Database

## Aim
Demonstrates Object-Relational Mapping (ORM) and Hibernate persistence principles using entity annotations (`@Entity`, `@Table`, `@Id`) and an EntityManager to write and persist object states directly to the local MySQL database.

## Files
- `HibernateOrmSimulation.java`: Source code implementation connecting to MySQL (with fallback to embedded H2 if MySQL is offline).

## Prerequisites
- Local MySQL server running on port `3306`.
- Database `javadb`.

## Compilation and Execution

```bash
# Compile with MySQL JDBC Connector
javac -cp "../lib/mysql-connector-j-8.3.0.jar:../lib/h2.jar:." HibernateOrmSimulation.java

# Run
java -cp "../lib/mysql-connector-j-8.3.0.jar:../lib/h2.jar:." HibernateOrmSimulation
```

## Output
```text
Connected to local MySQL database successfully.
Database Engine: MySQL 26.10.0
Hibernate/ORM Persisted Entity -> Product into table 'products'
Hibernate/ORM Persisted Entity -> Product into table 'products'

Reading back saved records from database table 'products':
Product ID: 101, Name: Mechanical Keyboard, Price: Rs.3499.0
Product ID: 102, Name: Wireless Gaming Mouse, Price: Rs.1999.0
```
