import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface PracticalInfo {
    String author() default "Omkar";
    int practicalNumber();
    String topic();
}

class PracticalModule {
    @PracticalInfo(author = "Omkar", practicalNumber = 15, topic = "Java Annotations")
    public void executeTask() {
        System.out.println("Executing task inside PracticalModule.");
    }
}

public class CustomAnnotationDemo {
    public static void main(String[] args) throws Exception {
        PracticalModule module = new PracticalModule();
        module.executeTask();

        Method method = module.getClass().getMethod("executeTask");
        if (method.isAnnotationPresent(PracticalInfo.class)) {
            PracticalInfo info = method.getAnnotation(PracticalInfo.class);
            System.out.println("Annotation Metadata:");
            System.out.println("Author: " + info.author());
            System.out.println("Practical Number: " + info.practicalNumber());
            System.out.println("Topic: " + info.topic());
        }
    }
}
