import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateTimeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Java Date and Time API (User Input) ===");

        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println("System Current Date: " + today);
        System.out.println("System Current Time: " + currentTime);
        System.out.println("System Current DateTime: " + currentDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Formatted Current DateTime: " + currentDateTime.format(formatter));

        // User enters birth date to calculate exact age Period
        System.out.println("\n--- Calculate Age / Period to Today ---");
        System.out.print("Enter birth year (e.g. 2004): ");
        int year = sc.nextInt();
        System.out.print("Enter birth month (1 - 12): ");
        int month = sc.nextInt();
        System.out.print("Enter birth day (1 - 31): ");
        int day = sc.nextInt();

        try {
            LocalDate birthDate = LocalDate.of(year, month, day);
            Period age = Period.between(birthDate, today);
            System.out.println("\nYour Birth Date: " + birthDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            System.out.println("Calculated Age Period: " + age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days.");
        } catch (Exception e) {
            System.out.println("Invalid date entered: " + e.getMessage());
        }

        sc.close();
    }
}
