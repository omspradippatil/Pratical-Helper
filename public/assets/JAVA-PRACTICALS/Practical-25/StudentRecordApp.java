import java.util.ArrayList;

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
            System.out.println("No records found.");
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
        StudentManager manager = new StudentManager();

        System.out.println("=== Student Record Management System Demo ===");
        manager.addStudent(new StudentRecord(101, "Aarav Sharma", "CSE", 8.9));
        manager.addStudent(new StudentRecord(102, "Sneha Patel", "IT", 9.2));
        manager.addStudent(new StudentRecord(103, "Rohan Verma", "AIDS", 8.4));

        System.out.println("\n--- Displaying All Records ---");
        manager.displayAll();

        System.out.println("\n--- Searching for Student with ID 102 ---");
        StudentRecord found = manager.searchById(102);
        if (found != null) {
            System.out.println("Found: " + found);
        } else {
            System.out.println("Student not found.");
        }

        System.out.println("\n--- Deleting Student with ID 103 ---");
        if (manager.deleteById(103)) {
            System.out.println("Deleted record with ID 103.");
        }

        System.out.println("\n--- Updated Student Records ---");
        manager.displayAll();

        System.out.println("\nProject Repository: https://github.com/omspradippatil/Vadvan-management");
    }
}
