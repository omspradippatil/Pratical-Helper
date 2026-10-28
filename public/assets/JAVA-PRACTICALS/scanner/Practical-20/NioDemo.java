import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class NioDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Java NIO API Operations (User Input) ===");

        System.out.print("Enter directory name to create (e.g. user_nio_dir): ");
        String dirName = sc.nextLine();
        Path dirPath = Paths.get(dirName);

        System.out.print("Enter file name to create inside directory (e.g. note.txt): ");
        String fileName = sc.nextLine();
        Path filePath = dirPath.resolve(fileName);

        System.out.print("Enter text message to write via Files.writeString: ");
        String content = sc.nextLine() + System.lineSeparator();

        try {
            // 1. Create directory using Files.createDirectory
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
                System.out.println("Directory created: " + dirPath.toAbsolutePath());
            }

            // 2. Write content using Files.writeString
            Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println("File written successfully: " + filePath.toAbsolutePath());

            // 3. File attributes
            System.out.println("\n--- File Attributes ---");
            System.out.println("File exists: " + Files.exists(filePath));
            System.out.println("File size: " + Files.size(filePath) + " bytes");

            // 4. Read back using Files.readAllLines
            System.out.println("\nReading all lines via Files.readAllLines:");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                System.out.println("  [Content] " + line);
            }

            // 5. Cleanup
            Files.deleteIfExists(filePath);
            Files.deleteIfExists(dirPath);
            System.out.println("\nTemporary file and directory cleaned up successfully.");

        } catch (IOException e) {
            System.err.println("NIO Error: " + e.getMessage());
        }

        sc.close();
    }
}
