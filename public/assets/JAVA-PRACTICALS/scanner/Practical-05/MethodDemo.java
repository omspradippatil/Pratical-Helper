import java.util.Scanner;

public class MethodDemo {
    // Static member tracking total method invocations
    static int callCount = 0;

    // Overloaded method: adds two integers
    public static int add(int a, int b) {
        callCount++;
        return a + b;
    }

    // Overloaded method: adds two doubles
    public static double add(double a, double b) {
        callCount++;
        return a + b;
    }

    // Variable-length arguments method (varargs)
    public static int sum(int... numbers) {
        callCount++;
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    public static void printCallCount() {
        System.out.println("Total static method calls made: " + callCount);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Method Overloading, Static Members & Varargs (User Input) ===");

        // 1. Overloaded method with integers
        System.out.print("Enter two integers to add: ");
        int int1 = sc.nextInt();
        int int2 = sc.nextInt();
        System.out.println("Result add(" + int1 + ", " + int2 + "): " + add(int1, int2));

        // 2. Overloaded method with doubles
        System.out.print("\nEnter two floating point numbers to add: ");
        double d1 = sc.nextDouble();
        double d2 = sc.nextDouble();
        System.out.println("Result add(" + d1 + ", " + d2 + "): " + add(d1, d2));

        // 3. Variable-length arguments (varargs)
        System.out.print("\nHow many numbers do you want to pass to varargs sum()? ");
        int count = sc.nextInt();
        int[] userNums = new int[count];
        System.out.println("Enter " + count + " numbers:");
        for (int i = 0; i < count; i++) {
            userNums[i] = sc.nextInt();
        }
        System.out.println("Result of varargs sum(): " + sum(userNums));

        // 4. Static member verification
        System.out.println();
        printCallCount();

        sc.close();
    }
}
