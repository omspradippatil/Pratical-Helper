import java.util.Scanner;

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

@FunctionalInterface
interface GreetingService {
    void sayMessage(String message);
}

public class LambdaDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Lambda Expressions & Functional Interfaces (User Input) ===");
        System.out.print("Enter first number (a): ");
        int a = sc.nextInt();
        System.out.print("Enter second number (b): ");
        int b = sc.nextInt();
        sc.nextLine(); // consume newline

        // Lambda expressions implementing MathOperation
        MathOperation addition = (x, y) -> x + y;
        MathOperation multiplication = (x, y) -> x * y;
        MathOperation subtraction = (x, y) -> x - y;

        System.out.println("\n--- Arithmetic Operations via Lambdas ---");
        System.out.println(a + " + " + b + " = " + addition.operate(a, b));
        System.out.println(a + " - " + b + " = " + subtraction.operate(a, b));
        System.out.println(a + " * " + b + " = " + multiplication.operate(a, b));

        // Lambda expression implementing GreetingService
        System.out.print("\nEnter your name for personalized greeting lambda: ");
        String userName = sc.nextLine();

        GreetingService greet = name -> System.out.println("Hello, " + name + "! Welcome to Java Lambda Expressions.");
        greet.sayMessage(userName);

        sc.close();
    }
}
