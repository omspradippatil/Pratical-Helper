class Student {
    private int id;
    private String name;
    private int age;

    public Student() {
        this(0, "Unknown", 0);
    }

    public Student(int id, String name) {
        this(id, name, 18);
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println("ID: " + this.id + ", Name: " + this.name + ", Age: " + this.age);
    }
}

public class StudentDemo {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student(101, "Aarav");
        Student s3 = new Student(102, "Neha", 21);

        s1.display();
        s2.display();
        s3.display();
    }
}
