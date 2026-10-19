
import java.util.*;

public class CampusVehiclePass {
    interface Chargeable {
        void charge();
    }

    static abstract class Vehicle {
        String passNumber;
        String owner;

        Vehicle(String passNumber, String owner) {
            this.passNumber = passNumber;
            this.owner = owner;
        }

        abstract int getFee();

        abstract String getType();

        void display() {
            System.out.println(passNumber + " (" + getType() + ") pass fee " + getFee());
        }
    }

    static class Bike extends Vehicle {
        Bike(String pass, String owner) {
            super(pass, owner);
        }

        int getFee() {
            return 300;
        }

        String getType() {
            return "Bike";
        }
    }

    static class Car extends Vehicle {
        Car(String pass, String owner) {
            super(pass, owner);
        }

        int getFee() {
            return 1000;
        }

        String getType() {
            return "Car";
        }
    }

    static class EBike extends Vehicle implements Chargeable {
        EBike(String pass, String owner) {
            super(pass, owner);
        }

        int getFee() {
            return 300;
        }

        String getType() {
            return "EBike";
        }

        public void charge() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    static class ECar extends Vehicle implements Chargeable {
        ECar(String pass, String owner) {
            super(pass, owner);
        }

        int getFee() {
            return 1000;
        }

        String getType() {
            return "ECar";
        }

        public void charge() {
            System.out.println(passNumber + " charging bay allotted");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new LinkedHashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("PASS")) {
                String type = sc.next();
                String pass = sc.next();
                String owner = sc.next();

                Vehicle v = null;

                switch (type) {
                    case "Bike":
                        v = new Bike(pass, owner);
                        break;
                    case "Car":
                        v = new Car(pass, owner);
                        break;
                    case "EBike":
                        v = new EBike(pass, owner);
                        break;
                    case "ECar":
                        v = new ECar(pass, owner);
                        break;
                }

                if (v != null) {
                    vehicles.put(pass, v);
                    v.display();
                }
            } else if (operation.equals("CHARGE")) {
                String pass = sc.next();
                Vehicle v = vehicles.get(pass);

                if (v == null) {
                    System.out.println("Vehicle not found");
                } else if (v instanceof Chargeable) {
                    ((Chargeable) v).charge();
                } else {
                    System.out.println(pass + " rejected: charging unsupported");
                }
            }
        }
        sc.close();
    }
}
