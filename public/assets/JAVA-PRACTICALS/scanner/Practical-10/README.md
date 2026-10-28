# Practical-10 (Scanner Edition) - Exception Handling

## Aim
Demonstrate Java exception handling using `try`, `catch`, `finally`, `throw`, `throws`, and a custom exception (`InvalidAgeException`) using interactive user input via `java.util.Scanner`.

## Files
- `ExceptionHandlingDemo.java`: Interactive Java program.

## Compilation and Execution

```bash
javac ExceptionHandlingDemo.java
java ExceptionHandlingDemo
```

## Sample Terminal Run
```text
=== Exception Handling (User Input) ===

--- Part 1: Arithmetic Division Test ---
Enter numerator: 10
Enter denominator (try 0 to see catch): 0
Caught ArithmeticException: / by zero
Finally block executed regardless of exception.

--- Part 2: Custom Exception (Voter Eligibility) ---
Enter age to verify voter eligibility: 15
Caught Custom Exception: Age 15 is ineligible: Must be at least 18 years old to vote.
```
