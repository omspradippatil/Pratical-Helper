import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Pattern Matching with Regular Expressions (User Input) ===");

        // 1. Email Pattern Matching
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        System.out.print("Enter an email address to validate: ");
        String userEmail = sc.nextLine();

        Matcher emailMatcher = emailPattern.matcher(userEmail);
        if (emailMatcher.matches()) {
            System.out.println("Result: '" + userEmail + "' is a VALID email address.");
        } else {
            System.out.println("Result: '" + userEmail + "' is an INVALID email address.");
        }

        // 2. Phone Number Extraction from freeform text
        System.out.println("\nEnter a sentence containing 10-digit Indian mobile numbers (e.g., 'Call me at 9876543210 or 8765432109'):");
        String userText = sc.nextLine();

        Pattern phonePattern = Pattern.compile("\\b[6-9]\\d{9}\\b");
        Matcher phoneMatcher = phonePattern.matcher(userText);

        System.out.println("\nExtracted Phone Numbers:");
        int foundCount = 0;
        while (phoneMatcher.find()) {
            foundCount++;
            System.out.println("  Match #" + foundCount + ": " + phoneMatcher.group());
        }

        if (foundCount == 0) {
            System.out.println("  No 10-digit phone numbers detected.");
        }

        sc.close();
    }
}
