# Practical-01 (Scanner Edition) - Primitive Data Types, Variables, Type Conversion, Type Casting

## Aim
Demonstrate primitive data types, variables, implicit type conversion, and explicit type casting using interactive user input via `java.util.Scanner`.

## Files
- `DataTypesDemo.java`: Interactive Java program.

## Compilation and Execution

```bash
javac DataTypesDemo.java
java DataTypesDemo
```

## Sample Terminal Run
```text
=== Primitive Data Types & Casting (User Input) ===
Enter a byte value (-128 to 127): 10
Enter a short value: 250
Enter an integer value: 50000
Enter a long value: 15000000000
Enter a float value: 5.75
Enter a double value: 19.99
Enter a single character: A
Enter a boolean value (true/false): true

--- Entered Values ---
Byte: 10, Short: 250, Int: 50000, Long: 15000000000
Float: 5.75, Double: 19.99, Char: A, Boolean: true

--- Type Conversion (Implicit / Widening) ---
byte (10) widened to int: 10
int (50000) widened to double: 50000.0

--- Type Casting (Explicit / Narrowing) ---
Enter a floating point number to cast to int: 3.14159
double (3.14159) cast to int: 3
Enter an int value (> 127) to cast to byte (overflow demo): 130
int (130) cast to byte: -126
```
