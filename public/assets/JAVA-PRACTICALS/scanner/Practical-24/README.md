# Practical-24 (Scanner Edition) - Testing a Java Program Using JUnit

## Aim
Demonstrate unit testing with JUnit 5 Jupiter assertions (`assertEquals`, `assertThrows`) along with an interactive test runner using `java.util.Scanner`.

## Files
- `CalculatorTest.java`: Source code containing calculator logic, JUnit 5 test methods, and an interactive test runner.

## Prerequisites
- Standalone JUnit jar in `../../lib/junit-platform-console-standalone-1.10.2.jar`.

## Compilation and Execution

```bash
# 1. Compile with JUnit 5 Console Runner in classpath
javac -cp "../../lib/junit-platform-console-standalone-1.10.2.jar:." CalculatorTest.java

# 2. Run Interactive Scanner Test Runner
java -cp "../../lib/junit-platform-console-standalone-1.10.2.jar:." CalculatorTest

# 3. Run Automated JUnit 5 Engine
java -jar ../../lib/junit-platform-console-standalone-1.10.2.jar execute -cp . --select-class CalculatorTest
```

## Sample Interactive Terminal Run
```text
=== Calculator JUnit Test Suite (Interactive Input) ===
Enter integer A: 10
Enter integer B: 5

--- Testing Calculator Operations with Assertions ---
Add: 10 + 5 = 15 [PASSED]
Subtract: 10 - 5 = 5 [PASSED]
Multiply: 10 * 5 = 50 [PASSED]
Divide: 10 / 5 = 2.0 [PASSED]

All user assertion checks passed successfully.
```
