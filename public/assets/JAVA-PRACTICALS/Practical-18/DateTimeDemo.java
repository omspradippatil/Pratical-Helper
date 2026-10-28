import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateTimeDemo {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        System.out.println("Current Date: " + today);
        System.out.println("Current Time: " + currentTime);
        System.out.println("Current Date and Time: " + currentDateTime);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = currentDateTime.format(formatter);
        System.out.println("Formatted Date Time: " + formattedDate);

        LocalDate birthDate = LocalDate.of(2004, 5, 15);
        Period age = Period.between(birthDate, today);
        System.out.println("Period from 2004-05-15 to today: " + age.getYears() + " years, " + age.getMonths() + " months, " + age.getDays() + " days.");
    }
}
