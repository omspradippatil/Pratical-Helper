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
        Employee emp = new Employee("Raj", 40000);
        Manager mgr = new Manager("Sneha", 60000, 15000);

        emp.display();
        System.out.println();
        mgr.display();
    }
}
