import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class ExceptionHandlingDemo {
    public static void checkEligibility(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age " + age + " is ineligible: Must be at least 18 years old to vote.");
        }
        System.out.println("Age " + age + " is valid. Voter registration granted.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Exception Handling (User Input) ===");

        // 1. Standard Exception Handling with try-catch-finally
        System.out.println("\n--- Part 1: Arithmetic Division Test ---");
        try {
            System.out.print("Enter numerator: ");
            int a = sc.nextInt();
            System.out.print("Enter denominator (try 0 to see catch): ");
            int b = sc.nextInt();

            int result = a / b;
            System.out.println("Division Result: " + a + " / " + b + " = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught ArithmeticException: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed regardless of exception.");
        }

        // 2. Custom Exception Handling with throw and throws
        System.out.println("\n--- Part 2: Custom Exception (Voter Eligibility) ---");
        System.out.print("Enter age to verify voter eligibility: ");
        int age = sc.nextInt();

        try {
            checkEligibility(age);
        } catch (InvalidAgeException e) {
            System.out.println("Caught Custom Exception: " + e.getMessage());
        }

        sc.close();
    }
}
