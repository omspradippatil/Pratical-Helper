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
    public ElectricCar(String brand) {
        super(brand);
    }

    @Override
    public void start() {
        System.out.println(brand + " electric car started silently.");
    }

    @Override
    public void stop() {
        System.out.println(brand + " electric car stopped.");
    }

    @Override
    public void fuelType() {
        System.out.println(brand + " runs on Lithium-ion battery pack.");
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        Vehicle myVehicle = new ElectricCar("Tata Nexon EV");
        myVehicle.start();
        myVehicle.honk();

        MotorVehicle motor = (MotorVehicle) myVehicle;
        motor.fuelType();
        myVehicle.stop();
    }
}
