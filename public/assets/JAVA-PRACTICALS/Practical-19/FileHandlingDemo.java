import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileHandlingDemo {
    public static void main(String[] args) {
        String byteFile = "byte_test.dat";
        String charFile = "char_test.txt";
        String bufferedFile = "buffered_test.txt";

        try {
            FileOutputStream fos = new FileOutputStream(byteFile);
            fos.write(new byte[]{65, 66, 67, 68, 69});
            fos.close();

            FileInputStream fis = new FileInputStream(byteFile);
            System.out.print("Byte Stream Read: ");
            int b;
            while ((b = fis.read()) != -1) {
                System.out.print((char) b + " ");
            }
            System.out.println();
            fis.close();

            FileWriter fw = new FileWriter(charFile);
            fw.write("Character Stream File Writing");
            fw.close();

            FileReader fr = new FileReader(charFile);
            System.out.print("Character Stream Read: ");
            int c;
            while ((c = fr.read()) != -1) {
                System.out.print((char) c);
            }
            System.out.println();
            fr.close();

            BufferedWriter bw = new BufferedWriter(new FileWriter(bufferedFile));
            bw.write("Line 1: Buffered Stream Entry");
            bw.newLine();
            bw.write("Line 2: High efficiency read write");
            bw.close();

            BufferedReader br = new BufferedReader(new FileReader(bufferedFile));
            System.out.println("Buffered Stream Read:");
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
            br.close();

        } catch (IOException e) {
            System.out.println("File I/O error: " + e.getMessage());
        } finally {
            new File(byteFile).delete();
            new File(charFile).delete();
            new File(bufferedFile).delete();
        }
    }
}
