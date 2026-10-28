import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Scanner;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PracticalInfo {
    String author() default "Omkar";
    int practicalNumber() default 15;
    String topic() default "Java Annotations";
}

class PracticalModule {
    @PracticalInfo(author = "Omkar", practicalNumber = 15, topic = "Java Annotations")
    public void executeTask() {
        System.out.println("Executing task inside PracticalModule.");
    }
}

public class CustomAnnotationDemo {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Custom Annotation & Reflection (User Input) ===");
        System.out.print("Enter your name as student reviewer: ");
        String reviewer = sc.nextLine();

        PracticalModule module = new PracticalModule();
        module.executeTask();

        Method method = module.getClass().getMethod("executeTask");
        if (method.isAnnotationPresent(PracticalInfo.class)) {
            PracticalInfo info = method.getAnnotation(PracticalInfo.class);
            System.out.println("\n--- Extracted Annotation Metadata via Reflection ---");
            System.out.println("Annotation Declared Author: " + info.author());
            System.out.println("Annotation Practical Number: " + info.practicalNumber());
            System.out.println("Annotation Topic: " + info.topic());
            System.out.println("Session Verified By: " + reviewer);
        }

        sc.close();
    }
}
