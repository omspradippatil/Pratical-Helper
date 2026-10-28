# Practical-14 (Scanner Edition) - Java Collections Framework

## Aim
Develop Java programs using `ArrayList`, `LinkedList`, `Vector`, and `HashSet` populated with interactive user input via `java.util.Scanner`.

## Files
- `CollectionsDemo.java`: Interactive Java program.

## Compilation and Execution

```bash
javac CollectionsDemo.java
java CollectionsDemo
```

## Sample Terminal Run
```text
=== Java Collections Framework (User Input) ===

--- 1. ArrayList ---
Enter number of programming languages to add to ArrayList: 3
Language 1: Java
Language 2: Python
Language 3: C++
ArrayList contents: [Java, Python, C++]

--- 2. LinkedList ---
Enter middle element for LinkedList: Apple
Enter element to insert at the FRONT (addFirst): Mango
Enter element to insert at the END (addLast): Orange
LinkedList contents: [Mango, Apple, Orange]

--- 3. Vector (Synchronized List) ---
Enter 3 integers for Vector: 10 20 30
Vector contents: [10, 20, 30]

--- 4. HashSet (Unique Elements Demo) ---
Enter 4 colors (feel free to repeat one to verify duplicate elimination):
Color 1: Red
Color 2: Green
Color 3: Blue
Color 4: Red
HashSet contents (duplicates automatically removed): [Red, Blue, Green]
```
