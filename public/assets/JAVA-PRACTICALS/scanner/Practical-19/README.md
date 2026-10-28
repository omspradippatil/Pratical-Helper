# Practical-19 (Scanner Edition) - File Handling Streams

## Aim
Implement file handling using byte streams (`FileInputStream`/`FileOutputStream`), character streams (`FileReader`/`FileWriter`), and buffered streams (`BufferedReader`/`BufferedWriter`) with user input via `java.util.Scanner`.

## Files
- `FileHandlingDemo.java`: Interactive Java program.

## Compilation and Execution

```bash
javac FileHandlingDemo.java
java FileHandlingDemo
```

## Sample Terminal Run
```text
=== File Handling Streams (User Input) ===

--- 1. Byte Stream Demo ---
Enter short text to write using Byte Stream: ABCDE
Written to user_byte_test.dat using FileOutputStream.
Reading back via FileInputStream: ABCDE

--- 2. Character Stream Demo ---
Enter text to write using Character Stream: Character Stream File Writing
Written to user_char_test.txt using FileWriter.
Reading back via FileReader: Character Stream File Writing

--- 3. Buffered Stream Demo ---
Enter Line 1 for Buffered Writer: Line 1: Buffered Stream Entry
Enter Line 2 for Buffered Writer: Line 2: High efficiency read write
Written lines to user_buffered_test.txt using BufferedWriter.
Reading back line-by-line via BufferedReader:
  > Line 1: Buffered Stream Entry
  > Line 2: High efficiency read write

Temporary demo files cleaned up successfully.
```
