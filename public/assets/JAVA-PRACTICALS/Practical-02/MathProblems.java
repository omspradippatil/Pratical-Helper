public class MathProblems {
    public static void main(String[] args) {
        int n = 7;
        System.out.println("Number: " + n);

        if (n % 2 == 0) {
            System.out.println(n + " is even.");
        } else {
            System.out.println(n + " is odd.");
        }

        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        System.out.println("Factorial of " + n + ": " + fact);

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
        System.out.println("Is " + n + " prime? " + isPrime);

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

        System.out.print("Odd numbers up to 10 (continue demo): ");
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
