public class DataTypesDemo {
    public static void main(String[] args) {
        byte b = 10;
        short s = 200;
        int i = 50000;
        long l = 15000000000L;
        float f = 5.75f;
        double d = 19.99d;
        char c = 'A';
        boolean bool = true;

        System.out.println("Byte value: " + b);
        System.out.println("Short value: " + s);
        System.out.println("Integer value: " + i);
        System.out.println("Long value: " + l);
        System.out.println("Float value: " + f);
        System.out.println("Double value: " + d);
        System.out.println("Char value: " + c);
        System.out.println("Boolean value: " + bool);

        int implicitInt = b;
        double implicitDouble = i;
        System.out.println("Implicit Conversion (byte to int): " + implicitInt);
        System.out.println("Implicit Conversion (int to double): " + implicitDouble);

        double pi = 3.14159;
        int explicitInt = (int) pi;
        int bigVal = 130;
        byte explicitByte = (byte) bigVal;
        System.out.println("Explicit Casting (double to int): " + explicitInt);
        System.out.println("Explicit Casting (int 130 to byte): " + explicitByte);
    }
}
