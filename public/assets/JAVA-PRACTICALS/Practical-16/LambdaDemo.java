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
        MathOperation addition = (a, b) -> a + b;
        MathOperation multiplication = (a, b) -> a * b;

        System.out.println("10 + 5 = " + addition.operate(10, 5));
        System.out.println("10 * 5 = " + multiplication.operate(10, 5));

        GreetingService greet = msg -> System.out.println("Hello, " + msg);
        greet.sayMessage("Learner");
    }
}
