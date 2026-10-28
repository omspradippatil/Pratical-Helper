# Practical-13 (Scanner Edition) - Generic Classes and Generic Methods

## Aim
Implement generic classes (`Box<T>`) and generic methods (`<E> void printArray(E[])`) accepting user-defined data via `java.util.Scanner`.

## Files
- `GenericsDemo.java`: Interactive Java program.

## Compilation and Execution

```bash
javac GenericsDemo.java
java GenericsDemo
```

## Sample Terminal Run
```text
=== Generic Classes & Generic Methods (User Input) ===
Enter an integer value to store in Box<Integer>: 1234
Box<Integer> contains: 1234

Enter a text value to store in Box<String>: Generics in Java
Box<String> contains: Generics in Java

How many words in your String array? 3
Enter 3 words:
Java Python C++

Printing array via generic method <E> printArray: Java Python C++ 
```
