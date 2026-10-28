import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StreamApiDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Stream API: filter, map, sorted, reduce (User Input) ===");
        System.out.print("How many integers do you want to process in Stream? ");
        int count = sc.nextInt();

        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter " + count + " integers:");
        for (int i = 0; i < count; i++) {
            numbers.add(sc.nextInt());
        }

        System.out.println("\nOriginal Numbers: " + numbers);

        // 1. filter() - keep even numbers
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("1. Filtered (Even Numbers): " + evenNumbers);

        // 2. map() - square each number
        List<Integer> squaredNumbers = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("2. Mapped (Squares): " + squaredNumbers);

        // 3. sorted() - ascending order
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("3. Sorted (Natural Ascending Order): " + sortedNumbers);

        // 4. reduce() - sum of elements
        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("4. Reduced (Total Sum): " + sum);

        sc.close();
    }
}
