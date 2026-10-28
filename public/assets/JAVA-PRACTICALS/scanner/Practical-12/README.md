# Practical-12 (Scanner Edition) - Pattern Matching Using Regular Expressions

## Aim
Develop a Java program to validate patterns (email address) and extract sub-patterns (phone numbers) from user input using `Pattern` and `Matcher` via `java.util.Scanner`.

## Files
- `RegexDemo.java`: Interactive Java program.

## Compilation and Execution

```bash
javac RegexDemo.java
java RegexDemo
```

## Sample Terminal Run
```text
=== Pattern Matching with Regular Expressions (User Input) ===
Enter an email address to validate: student@vcet.edu.in
Result: 'student@vcet.edu.in' is a VALID email address.

Enter a sentence containing 10-digit Indian mobile numbers (e.g., 'Call me at 9876543210 or 8765432109'):
Contact admissions at 9876543210 or alternate 8765432109 today.

Extracted Phone Numbers:
  Match #1: 9876543210
  Match #2: 8765432109
```
