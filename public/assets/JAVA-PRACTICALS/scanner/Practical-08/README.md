# Practical-08 (Scanner Edition) - Packages and Access Modifiers

## Aim
Develop Java programs demonstrating package structure (`mypack`) and access modifier visibility (`public`, `private`, `protected`, default) using user input via `java.util.Scanner`.

## Files
- `mypack/Account.java`: Class inside package `mypack` defining members with different access levels.
- `Main.java`: Interactive driver program.

## Compilation and Execution

```bash
javac mypack/Account.java Main.java
java Main
```

## Sample Terminal Run
```text
=== Packages & Access Modifiers (User Input) ===
Enter Account Holder Name: Omkar
Enter Initial Balance: 25000
Enter Security Code: SEC-9841

[Public Member] Account Holder: Omkar
[Private via Getter] Current Balance: Rs.25000.0

Enter amount to deposit: 5000
Rs.5000.0 deposited successfully.
[Private via Getter] Updated Balance: Rs.30000.0

Account Summary from inside package:
Account Type (default package-private): Savings
Security Code (protected member): SEC-9841
```
