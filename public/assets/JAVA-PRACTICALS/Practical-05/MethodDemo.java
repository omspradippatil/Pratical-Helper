public class MethodDemo {
    static int callCount = 0;

    public static int add(int a, int b) {
        callCount++;
        return a + b;
    }

    public static double add(double a, double b) {
        callCount++;
        return a + b;
    }

    public static int sum(int... numbers) {
        callCount++;
        int total = 0;
        for (int num : numbers) {
            total += num;
        }
        return total;
    }

    public static void printCallCount() {
        System.out.println("Total method calls made: " + callCount);
    }

    public static void main(String[] args) {
        System.out.println("add(int, int): " + add(10, 20));
        System.out.println("add(double, double): " + add(5.5, 4.5));
        System.out.println("sum(1, 2, 3, 4): " + sum(1, 2, 3, 4));
        System.out.println("sum(10, 20, 30): " + sum(10, 20, 30));
        printCallCount();
    }
}
