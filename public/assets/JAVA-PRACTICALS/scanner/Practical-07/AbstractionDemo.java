import java.util.Scanner;

interface Vehicle {
    void start();
    void stop();

    default void honk() {
        System.out.println("Default Horn: Beep Beep!");
    }
}

abstract class MotorVehicle implements Vehicle {
    protected String brand;

    public MotorVehicle(String brand) {
        this.brand = brand;
    }

    public abstract void fuelType();
}

class ElectricCar extends MotorVehicle {
    private String batteryPack;

    public ElectricCar(String brand, String batteryPack) {
        super(brand);
        this.batteryPack = batteryPack;
    }

    @Override
    public void start() {
        System.out.println(brand + " electric vehicle powered on silently.");
    }

    @Override
    public void stop() {
        System.out.println(brand + " electric vehicle powered off.");
    }

    @Override
    public void fuelType() {
        System.out.println(brand + " runs on " + batteryPack + " battery pack.");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Abstraction, Interfaces & Default Methods (User Input) ===");
        System.out.print("Enter Electric Vehicle Brand/Model: ");
        String brand = sc.nextLine();

        System.out.print("Enter Battery Specifications (e.g. 40.5 kWh Lithium-ion): ");
        String battery = sc.nextLine();

        Vehicle myVehicle = new ElectricCar(brand, battery);

        System.out.println("\n--- Testing Interface & Abstract Class Methods ---");
        myVehicle.start();
        myVehicle.honk(); // Default method from interface

        MotorVehicle motor = (MotorVehicle) myVehicle;
        motor.fuelType(); // Abstract method implemented

        myVehicle.stop();

        sc.close();
    }
}
