import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailPattern);

        String[] testEmails = {
            "student@vcet.edu.in",
            "john.doe@company.org",
            "invalid-email@",
            "user@domain..com"
        };

        System.out.println("Email Validation Results:");
        for (String email : testEmails) {
            Matcher matcher = pattern.matcher(email);
            System.out.println(email + " -> " + (matcher.matches() ? "Valid" : "Invalid"));
        }

        String text = "Contact us at 9876543210 or 8765432109 for admissions.";
        Pattern phonePattern = Pattern.compile("\\b[6-9]\\d{9}\\b");
        Matcher phoneMatcher = phonePattern.matcher(text);

        System.out.println("\nExtracted Phone Numbers:");
        while (phoneMatcher.find()) {
            System.out.println("Found phone number: " + phoneMatcher.group());
        }
    }
}
