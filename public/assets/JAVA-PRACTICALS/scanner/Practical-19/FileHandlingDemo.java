import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileHandlingDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String byteFile = "user_byte_test.dat";
        String charFile = "user_char_test.txt";
        String bufferedFile = "user_buffered_test.txt";

        System.out.println("=== File Handling Streams (User Input) ===");

        try {
            // 1. Byte Stream (FileOutputStream / FileInputStream)
            System.out.println("\n--- 1. Byte Stream Demo ---");
            System.out.print("Enter short text to write using Byte Stream: ");
            String byteInput = sc.nextLine();

            FileOutputStream fos = new FileOutputStream(byteFile);
            fos.write(byteInput.getBytes());
            fos.close();
            System.out.println("Written to " + byteFile + " using FileOutputStream.");

            FileInputStream fis = new FileInputStream(byteFile);
            System.out.print("Reading back via FileInputStream: ");
            int b;
            while ((b = fis.read()) != -1) {
                System.out.print((char) b);
            }
            System.out.println();
            fis.close();

            // 2. Character Stream (FileWriter / FileReader)
            System.out.println("\n--- 2. Character Stream Demo ---");
            System.out.print("Enter text to write using Character Stream: ");
            String charInput = sc.nextLine();

            FileWriter fw = new FileWriter(charFile);
            fw.write(charInput);
            fw.close();
            System.out.println("Written to " + charFile + " using FileWriter.");

            FileReader fr = new FileReader(charFile);
            System.out.print("Reading back via FileReader: ");
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
            fr.close();

            // 3. Buffered Stream (BufferedWriter / BufferedReader)
            System.out.println("\n--- 3. Buffered Stream Demo ---");
            System.out.print("Enter Line 1 for Buffered Writer: ");
            String line1 = sc.nextLine();
            System.out.print("Enter Line 2 for Buffered Writer: ");
            String line2 = sc.nextLine();

            BufferedWriter bw = new BufferedWriter(new FileWriter(bufferedFile));
            bw.write(line1);
            bw.newLine();
            bw.write(line2);
            bw.close();
            System.out.println("Written lines to " + bufferedFile + " using BufferedWriter.");

            BufferedReader br = new BufferedReader(new FileReader(bufferedFile));
            System.out.println("Reading back line-by-line via BufferedReader:");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  > " + line);
            }
            br.close();

        } catch (IOException e) {
            System.err.println("File I/O Error: " + e.getMessage());
        } finally {
            new File(byteFile).delete();
            new File(charFile).delete();
            new File(bufferedFile).delete();
            System.out.println("\nTemporary demo files cleaned up successfully.");
        }

        sc.close();
    }
}
