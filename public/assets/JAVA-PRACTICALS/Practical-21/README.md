# Practical-21 - JDBC Connection and CRUD Operations Using Statement and PreparedStatement

## Aim
Develop Java programs using JDBC to connect to local MySQL database and perform CRUD (Create, Read, Update, Delete) operations using `Statement` and `PreparedStatement`.

## Files
- `JdbcCrudDemo.java`: Source code implementation connecting to MySQL (with fallback to embedded H2 if MySQL is offline).

## Prerequisites
- Local MySQL server running on port `3306` (e.g. via `brew services start mysql`).
- Database `javadb` (automatically created if not present).

## Compilation and Execution

```bash
# Compile with MySQL JDBC Connector
javac -cp "../lib/mysql-connector-j-8.3.0.jar:../lib/h2.jar:." JdbcCrudDemo.java

# Run
java -cp "../lib/mysql-connector-j-8.3.0.jar:../lib/h2.jar:." JdbcCrudDemo
```

## Output
```text
Connected to local MySQL database successfully.
Database Engine: MySQL 26.10.0
Table 'students' created successfully.
Records inserted using PreparedStatement.

Reading Records using Statement:
ID: 1, Name: Rahul, Marks: 88.5
ID: 2, Name: Priya, Marks: 92.0

Updated Rahul's marks to 95.0 using PreparedStatement.
Deleted record with ID 2 using PreparedStatement.

Final Records in Database:
ID: 1, Name: Rahul, Marks: 95.0
```
