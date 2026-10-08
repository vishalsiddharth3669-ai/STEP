import java.util.ArrayList;
import java.util.List;

public class VehicleRentalSystem {

    public static void main(String[] args) {

        Customer customer1 = new Customer("Customer 1");
        Customer customer2 = new Customer("Customer 2");
        Customer customer3 = new Customer("Customer 3");

        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        RentalSystem system = new RentalSystem();

        system.addVehicle(sedan);
        system.addVehicle(suv);

        system.rentVehicle(customer1, sedan, 3);

        system.rentVehicle(customer2, sedan, 2);

        system.returnVehicle(sedan, customer1);

        system.rentVehicle(customer3, suv, 5);
    }
}

abstract class Vehicle {

    private String name;
    private boolean available;

    public Vehicle(String name) {
        this.name = name;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {

    public Sedan(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 50.0;
    }
}

class SUV extends Vehicle {

    public SUV(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 80.0;
    }
}

class Truck extends Vehicle {

    public Truck(String name) {
        super(name);
    }

    @Override
    public double calculateCharge(int days) {
        return days * 100.0;
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {

    private Vehicle vehicle;
    private Customer customer;
    private int days;

    public Rental(
            Vehicle vehicle,
            Customer customer,
            int days) {

        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getCharge() {
        return vehicle.calculateCharge(days);
    }
}

class RentalSystem {

    private List<Vehicle> vehicles = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public void rentVehicle(
            Customer customer,
            Vehicle vehicle,
            int days) {

        if (!vehicle.isAvailable()) {

            System.out.println(
                    vehicle.getName()
                            + " is currently unavailable.");

            return;
        }

        Rental rental = new Rental(vehicle, customer, days);

        rentals.add(rental);
        vehicle.setAvailable(false);

        System.out.println(
                vehicle.getName()
                        + " rented successfully by "
                        + customer.getName());

        System.out.println(
                "Rental charge: $"
                        + rental.getCharge());
    }

    public void returnVehicle(
            Vehicle vehicle,
            Customer customer) {

        for (int i = 0; i < rentals.size(); i++) {

            Rental rental = rentals.get(i);

            if (rental.getVehicle() == vehicle
                    && rental.getCustomer() == customer) {

                rentals.remove(i);
                vehicle.setAvailable(true);

                System.out.println(
                        vehicle.getName()
                                + " returned by "
                                + customer.getName());

                return;
            }
        }
    }
}