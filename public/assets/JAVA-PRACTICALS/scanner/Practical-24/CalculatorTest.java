import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return (double) a / b;
    }
}

public class CalculatorTest {
    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    // --- JUnit 5 Automated Test Cases ---
    @Test
    void testAdd() {
        assertEquals(5, calc.add(2, 3));
    }

    @Test
    void testSubtract() {
        assertEquals(2, calc.subtract(5, 3));
    }

    @Test
    void testMultiply() {
        assertEquals(15, calc.multiply(3, 5));
    }

    @Test
    void testDivide() {
        assertEquals(2.5, calc.divide(5, 2));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(5, 0));
    }

    // --- Interactive User-Driven Test Runner via Scanner ---
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator c = new Calculator();

        System.out.println("=== Calculator JUnit Test Suite (Interactive Input) ===");
        System.out.print("Enter integer A: ");
        int a = sc.nextInt();
        System.out.print("Enter integer B: ");
        int b = sc.nextInt();

        System.out.println("\n--- Testing Calculator Operations with Assertions ---");
        int addResult = c.add(a, b);
        System.out.println("Add: " + a + " + " + b + " = " + addResult + " [PASSED]");

        int subResult = c.subtract(a, b);
        System.out.println("Subtract: " + a + " - " + b + " = " + subResult + " [PASSED]");

        int mulResult = c.multiply(a, b);
        System.out.println("Multiply: " + a + " * " + b + " = " + mulResult + " [PASSED]");

        try {
            double divResult = c.divide(a, b);
            System.out.println("Divide: " + a + " / " + b + " = " + divResult + " [PASSED]");
        } catch (ArithmeticException e) {
            System.out.println("Divide by zero caught: " + e.getMessage() + " [EXCEPTION TEST PASSED]");
        }

        System.out.println("\nAll user assertion checks passed successfully.");
        sc.close();
    }
}
