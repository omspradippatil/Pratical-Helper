import java.util.Scanner;

public class DataTypesDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Primitive Data Types & Casting (User Input) ===");
        System.out.print("Enter a byte value (-128 to 127): ");
        byte b = sc.nextByte();

        System.out.print("Enter a short value: ");
        short s = sc.nextShort();

        System.out.print("Enter an integer value: ");
        int i = sc.nextInt();

        System.out.print("Enter a long value: ");
        long l = sc.nextLong();

        System.out.print("Enter a float value: ");
        float f = sc.nextFloat();

        System.out.print("Enter a double value: ");
        double d = sc.nextDouble();

        System.out.print("Enter a single character: ");
        char c = sc.next().charAt(0);

        System.out.print("Enter a boolean value (true/false): ");
        boolean bool = sc.nextBoolean();

        System.out.println("\n--- Entered Values ---");
        System.out.println("Byte: " + b + ", Short: " + s + ", Int: " + i + ", Long: " + l);
        System.out.println("Float: " + f + ", Double: " + d + ", Char: " + c + ", Boolean: " + bool);

        System.out.println("\n--- Type Conversion (Implicit / Widening) ---");
        int implicitInt = b;
        double implicitDouble = i;
        System.out.println("byte (" + b + ") widened to int: " + implicitInt);
        System.out.println("int (" + i + ") widened to double: " + implicitDouble);

        System.out.println("\n--- Type Casting (Explicit / Narrowing) ---");
        System.out.print("Enter a floating point number to cast to int: ");
        double doubleVal = sc.nextDouble();
        int castedInt = (int) doubleVal;
        System.out.println("double (" + doubleVal + ") cast to int: " + castedInt);

        System.out.print("Enter an int value (> 127) to cast to byte (overflow demo): ");
        int largeInt = sc.nextInt();
        byte castedByte = (byte) largeInt;
        System.out.println("int (" + largeInt + ") cast to byte: " + castedByte);

        sc.close();
    }
}
