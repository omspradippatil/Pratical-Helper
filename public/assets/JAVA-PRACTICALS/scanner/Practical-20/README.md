# Practical-20 (Scanner Edition) - Java NIO API (Path and Files)

## Aim
Implement directory creation, file creation, writing, attribute querying, line reading, and cleanup using `Path`, `Paths`, and `Files` in the Java NIO API from user input via `java.util.Scanner`.

## Files
- `NioDemo.java`: Interactive Java program.

## Compilation and Execution

```bash
javac NioDemo.java
java NioDemo
```

## Sample Terminal Run
```text
=== Java NIO API Operations (User Input) ===
Enter directory name to create (e.g. user_nio_dir): user_nio_dir
Enter file name to create inside directory (e.g. note.txt): note.txt
Enter text message to write via Files.writeString: Hello from Java NIO API with User Input!
Directory created: /path/to/user_nio_dir
File written successfully: /path/to/user_nio_dir/note.txt

--- File Attributes ---
File exists: true
File size: 48 bytes

Reading all lines via Files.readAllLines:
  [Content] Hello from Java NIO API with User Input!

Temporary file and directory cleaned up successfully.
```
