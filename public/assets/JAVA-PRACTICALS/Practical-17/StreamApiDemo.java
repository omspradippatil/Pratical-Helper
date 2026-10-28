import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamApiDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 12, 8, 3, 19, 24, 7, 2);

        System.out.println("Original Numbers: " + numbers);

        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Filtered (Even Numbers): " + evenNumbers);

        List<Integer> squaredNumbers = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("Mapped (Squares): " + squaredNumbers);

        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted Numbers: " + sortedNumbers);

        int sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("Reduced (Sum of elements): " + sum);
    }
}
