import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.List;

public class NioDemo {
    public static void main(String[] args) {
        Path dirPath = Paths.get("nio_sample_dir");
        Path filePath = dirPath.resolve("demo.txt");

        try {
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
                System.out.println("Directory created: " + dirPath.toAbsolutePath());
            }

            String content = "Hello from Java NIO API!\nSecond line in file.\n";
            Files.writeString(filePath, content, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
            System.out.println("File written successfully: " + filePath);

            System.out.println("File exists: " + Files.exists(filePath));
            System.out.println("File size: " + Files.size(filePath) + " bytes");

            System.out.println("Reading all lines:");
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                System.out.println("  " + line);
            }

            Files.deleteIfExists(filePath);
            Files.deleteIfExists(dirPath);
            System.out.println("Cleaned up directory and file.");

        } catch (IOException e) {
            System.out.println("NIO Error: " + e.getMessage());
        }
    }
}
