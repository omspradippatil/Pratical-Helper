import java.util.Scanner;

public class MathProblems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Mathematical Problems Solver (User Input) ===");
        System.out.print("Enter an integer: ");
        int n = sc.nextInt();

        // 1. Decision-making (if-else)
        if (n % 2 == 0) {
            System.out.println(n + " is even.");
        } else {
            System.out.println(n + " is odd.");
        }

        // 2. Factorial calculation using for loop
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        System.out.println("Factorial of " + n + ": " + fact);

        // 3. Prime checking using loop and break statement
        boolean isPrime = true;
        if (n <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        System.out.println("Is " + n + " a prime number? " + isPrime);

        // 4. Fibonacci sequence using while loop
        System.out.print("First " + n + " Fibonacci numbers: ");
        int a = 0, b = 1, count = 0;
        while (count < n) {
            System.out.print(a + " ");
            int sum = a + b;
            a = b;
            b = sum;
            count++;
        }
        System.out.println();

        // 5. Branching statement (continue) demo
        System.out.print("Enter upper limit to print odd numbers (skipping evens with continue): ");
        int limit = sc.nextInt();
        System.out.print("Odd numbers up to " + limit + ": ");
        for (int i = 1; i <= limit; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();

        sc.close();
    }
}
