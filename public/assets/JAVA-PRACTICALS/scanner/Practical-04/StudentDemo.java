import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;

    // Default Constructor (chains to 3-arg constructor using this keyword)
    public Student() {
        this(0, "Unknown", 0);
    }

    // Overloaded Constructor (2 arguments, chains using this)
    public Student(int id, String name) {
        this(id, name, 18);
    }

    // Overloaded Constructor (3 arguments)
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void display() {
        System.out.println("ID: " + this.id + " | Name: " + this.name + " | Age: " + this.age);
    }
}

public class StudentDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Classes, Constructors & 'this' Keyword (User Input) ===");

        // 1. Student using Default Constructor
        System.out.println("\nCreating Student 1 (Default Constructor)...");
        Student s1 = new Student();
        s1.display();

        // 2. Student using 2-argument constructor
        System.out.println("\nEnter details for Student 2 (id and name):");
        System.out.print("ID: ");
        int id2 = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Name: ");
        String name2 = sc.nextLine();
        Student s2 = new Student(id2, name2);
        s2.display();

        // 3. Student using 3-argument constructor
        System.out.println("\nEnter details for Student 3 (id, name, and age):");
        System.out.print("ID: ");
        int id3 = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Name: ");
        String name3 = sc.nextLine();
        System.out.print("Age: ");
        int age3 = sc.nextInt();
        Student s3 = new Student(id3, name3, age3);
        s3.display();

        sc.close();
    }
}
