# Practical-22 - JDBC Transaction Management and Stored Procedure Execution Using CallableStatement

## Aim
Implement JDBC transaction management (`commit`, `rollback`, `setAutoCommit`) and invoke a database stored procedure using `CallableStatement` on MySQL.

## Files
- `JdbcTransactionDemo.java`: Source code implementation managing account transactions and calling the `GetAccountBalance` stored procedure.

## Prerequisites
- Local MySQL server running on port `3306`.
- Database `javadb`.

## Compilation and Execution

```bash
# Compile with MySQL JDBC Connector
javac -cp "../lib/mysql-connector-j-8.3.0.jar:../lib/h2.jar:." JdbcTransactionDemo.java

# Run
java -cp "../lib/mysql-connector-j-8.3.0.jar:../lib/h2.jar:." JdbcTransactionDemo
```

## Output
```text
Connected to local MySQL database successfully.
Database Engine: MySQL 26.10.0
MySQL Stored Procedure 'GetAccountBalance' created successfully.

Executing Transaction: Transferring 300 from Alice (101) to Bob (102)
Transaction committed successfully.

Calling Stored Procedure using CallableStatement:
Procedure Result - Alice New Balance: 700.0
Procedure Result - Bob New Balance: 800.0
```
