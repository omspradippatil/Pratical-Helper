import java.util.ArrayList;
import java.util.Scanner;

class StudentRecord {
    private int id;
    private String name;
    private String department;
    private double gpa;

    public StudentRecord(int id, String name, String department, double gpa) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.gpa = gpa;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getGpa() { return gpa; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-15s | Dept: %-10s | GPA: %.2f", id, name, department, gpa);
    }
}

class StudentManager {
    private ArrayList<StudentRecord> list = new ArrayList<>();

    public void addStudent(StudentRecord s) {
        list.add(s);
        System.out.println("Student added successfully: " + s.getName());
    }

    public void displayAll() {
        if (list.isEmpty()) {
            System.out.println("No records found in database.");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        for (StudentRecord s : list) {
            System.out.println(s);
        }
        System.out.println("------------------------------------------------------------------");
    }

    public StudentRecord searchById(int id) {
        for (StudentRecord s : list) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteById(int id) {
        StudentRecord s = searchById(id);
        if (s != null) {
            list.remove(s);
            return true;
        }
        return false;
    }
}

public class StudentRecordApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        System.out.println("=================================================");
        System.out.println("   STUDENT RECORD MANAGEMENT SYSTEM (MINI PROJECT)");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Exit");
            System.out.print("Enter choice (1-5): ");

            if (!sc.hasNextInt()) {
                break;
            }
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department (CSE/IT/AIDS/EXTC): ");
                    String dept = sc.nextLine();
                    System.out.print("Enter GPA (0.0 to 10.0): ");
                    double gpa = sc.nextDouble();
                    sc.nextLine();
                    manager.addStudent(new StudentRecord(id, name, dept, gpa));
                    break;

                case 2:
                    System.out.println("\n--- All Student Records ---");
                    manager.displayAll();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    int searchId = sc.nextInt();
                    sc.nextLine();
                    StudentRecord found = manager.searchById(searchId);
                    if (found != null) {
                        System.out.println("Found -> " + found);
                    } else {
                        System.out.println("Student with ID " + searchId + " not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int delId = sc.nextInt();
                    sc.nextLine();
                    if (manager.deleteById(delId)) {
                        System.out.println("Student with ID " + delId + " deleted successfully.");
                    } else {
                        System.out.println("Student with ID " + delId + " not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting Student Record Management System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1 to 5.");
            }
        }

        System.out.println("\nProject Repository: https://github.com/omspradippatil/Vadvan-management");
        sc.close();
    }
}
