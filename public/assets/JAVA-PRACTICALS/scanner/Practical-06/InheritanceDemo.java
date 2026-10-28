import java.util.Scanner;

class Employee {
    protected String name;
    protected double baseSalary;

    public Employee(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public double calculateSalary() {
        return baseSalary;
    }

    public void display() {
        System.out.println("Employee Name: " + name + ", Base Salary: " + baseSalary);
    }
}

class Manager extends Employee {
    private double bonus;

    public Manager(String name, double baseSalary, double bonus) {
        super(name, baseSalary);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + bonus;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Role: Manager, Bonus: " + bonus + ", Total Salary: " + calculateSalary());
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Inheritance, Method Overriding & 'super' (User Input) ===");

        // Employee input
        System.out.println("\nEnter Regular Employee Details:");
        System.out.print("Name: ");
        String empName = sc.nextLine();
        System.out.print("Base Salary: ");
        double empSalary = sc.nextDouble();
        sc.nextLine(); // consume newline

        Employee emp = new Employee(empName, empSalary);

        // Manager input
        System.out.println("\nEnter Manager Details:");
        System.out.print("Name: ");
        String mgrName = sc.nextLine();
        System.out.print("Base Salary: ");
        double mgrSalary = sc.nextDouble();
        System.out.print("Bonus Amount: ");
        double bonus = sc.nextDouble();

        Manager mgr = new Manager(mgrName, mgrSalary, bonus);

        // Display results demonstrating method overriding & super
        System.out.println("\n--- Employee Summary ---");
        emp.display();

        System.out.println("\n--- Manager Summary ---");
        mgr.display();

        sc.close();
    }
}
