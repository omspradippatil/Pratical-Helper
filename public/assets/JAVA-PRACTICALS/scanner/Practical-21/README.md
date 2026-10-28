# Practical-21 (Scanner Edition) - JDBC Connection and CRUD Operations on MySQL

## Aim
Develop Java programs using JDBC to connect to local MySQL database and perform interactive CRUD operations using `Statement` and `PreparedStatement` from user input via `java.util.Scanner`.

## Files
- `JdbcCrudDemo.java`: Interactive Java program with MySQL connection.

## Prerequisites
- Local MySQL server running on port `3306`.
- `javadb` database.

## Compilation and Execution

```bash
# Compile
javac -cp "../../lib/mysql-connector-j-8.3.0.jar:../../lib/h2.jar:." JdbcCrudDemo.java

# Run
java -cp "../../lib/mysql-connector-j-8.3.0.jar:../../lib/h2.jar:." JdbcCrudDemo
```

## Sample Terminal Run
```text
=== JDBC MySQL CRUD Operations (User Input) ===
Connected to local MySQL database successfully.
Database Engine: MySQL 26.10.0
Table 'students' created in database.

How many students do you want to insert? 2
Enter details for Student #1:
  ID: 1
  Name: Rahul
  Marks: 88.5
Enter details for Student #2:
  ID: 2
  Name: Priya
  Marks: 92.0
All 2 records inserted successfully using PreparedStatement.

--- Current Records in Database (via Statement) ---
ID: 1 | Name: Rahul | Marks: 88.5
ID: 2 | Name: Priya | Marks: 92.0

--- Update Student Marks ---
Enter Student ID to update marks: 1
Enter New Marks: 95.0
Updated marks for ID 1 to 95.0.

--- Delete Student Record ---
Enter Student ID to delete: 2
Deleted record with ID 2.

--- Final Database Table State ---
ID: 1 | Name: Rahul | Marks: 95.0
```
