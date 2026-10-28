# Practical-17 (Scanner Edition) - Stream API (filter, map, sorted, reduce)

## Aim
Implement data processing using the Java Stream API with `filter()`, `map()`, `sorted()`, and `reduce()` pipelines on user-provided collections via `java.util.Scanner`.

## Files
- `StreamApiDemo.java`: Interactive Java program.

## Compilation and Execution

```bash
javac StreamApiDemo.java
java StreamApiDemo
```

## Sample Terminal Run
```text
=== Stream API: filter, map, sorted, reduce (User Input) ===
How many integers do you want to process in Stream? 8
Enter 8 integers:
5 12 8 3 19 24 7 2

Original Numbers: [5, 12, 8, 3, 19, 24, 7, 2]
1. Filtered (Even Numbers): [12, 8, 24, 2]
2. Mapped (Squares): [25, 144, 64, 9, 361, 576, 49, 4]
3. Sorted (Natural Ascending Order): [2, 3, 5, 7, 8, 12, 19, 24]
4. Reduced (Total Sum): 80
```
