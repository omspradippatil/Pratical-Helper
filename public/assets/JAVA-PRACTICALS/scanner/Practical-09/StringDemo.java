import java.util.Scanner;

public class StringDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== String & StringBuilder Operations (User Input) ===");

        // String Methods
        System.out.print("Enter a string (e.g., 'Hello Java World'): ");
        String str = sc.nextLine();

        System.out.println("\n--- String Class Methods ---");
        System.out.println("Original String: " + str);
        System.out.println("Length: " + str.length());

        if (str.length() > 0) {
            System.out.print("Enter index to fetch character (0 to " + (str.length() - 1) + "): ");
            int idx = sc.nextInt();
            if (idx >= 0 && idx < str.length()) {
                System.out.println("Character at index " + idx + ": " + str.charAt(idx));
            }
        }
        sc.nextLine(); // consume newline

        System.out.println("To Uppercase: " + str.toUpperCase());

        System.out.print("Enter a word to check if string contains it: ");
        String searchWord = sc.nextLine();
        System.out.println("Contains '" + searchWord + "': " + str.contains(searchWord));

        System.out.print("Enter target word to replace: ");
        String targetWord = sc.nextLine();
        System.out.print("Enter replacement word: ");
        String replaceWord = sc.nextLine();
        System.out.println("Replaced Result: " + str.replace(targetWord, replaceWord));

        // StringBuilder Methods
        System.out.println("\n--- StringBuilder Methods ---");
        System.out.print("Enter an initial string for StringBuilder (e.g. 'Programming'): ");
        String initialSb = sc.nextLine();
        StringBuilder sb = new StringBuilder(initialSb);

        System.out.print("Enter text to append: ");
        String appendText = sc.nextLine();
        sb.append(" ").append(appendText);
        System.out.println("After append: " + sb);

        System.out.println("Reversed: " + sb.reverse());
        sb.reverse(); // restore back
        System.out.println("Restored back: " + sb);

        sc.close();
    }
}
