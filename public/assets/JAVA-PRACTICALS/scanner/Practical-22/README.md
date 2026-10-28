# Practical-22 (Scanner Edition) - JDBC Transactions and CallableStatement on MySQL

## Aim
Implement JDBC transaction management (`commit`, `rollback`, `setAutoCommit`) and invoke a MySQL stored procedure using `CallableStatement` from interactive user input via `java.util.Scanner`.

## Files
- `JdbcTransactionDemo.java`: Interactive Java program with MySQL connection.

## Prerequisites
- Local MySQL server running on port `3306`.
- `javadb` database.

## Compilation and Execution

```bash
# Compile
javac -cp "../../lib/mysql-connector-j-8.3.0.jar:../../lib/h2.jar:." JdbcTransactionDemo.java

# Run
java -cp "../../lib/mysql-connector-j-8.3.0.jar:../../lib/h2.jar:." JdbcTransactionDemo
```

## Sample Terminal Run
```text
=== JDBC Transaction Management & CallableStatement (User Input) ===
Connected to local MySQL database successfully.
Database Engine: MySQL 26.10.0
MySQL Stored Procedure 'GetAccountBalance' created successfully.

Initial Account Balances: Alice (101) = Rs.1000.0 | Bob (102) = Rs.500.0
Enter amount to transfer from Alice (101) to Bob (102): Rs.300

--- Executing Atomic Transaction ---
Transaction committed successfully: Transferred Rs.300.0.

--- Invoke Stored Procedure via CallableStatement ---
Enter account number to fetch updated balance (101 or 102): 101
Procedure Result -> Account 101 New Balance: Rs.700.0
```
