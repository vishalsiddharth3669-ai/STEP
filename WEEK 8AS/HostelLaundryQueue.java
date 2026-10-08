interface WashType {
    String getName();

    int getDuration();

    double getCharge();
}

class QuickWash implements WashType {
    public String getName() {
        return "Quick";
    }

    public int getDuration() {
        return 30;
    }

    public double getCharge() {
        return 20.00;
    }
}

class NormalWash implements WashType {
    public String getName() {
        return "Normal";
    }

    public int getDuration() {
        return 45;
    }

    public double getCharge() {
        return 30.00;
    }
}

class HeavyWash implements WashType {
    public String getName() {
        return "Heavy";
    }

    public int getDuration() {
        return 60;
    }

    public double getCharge() {
        return 45.00;
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashingMachine {
    private String machineId;
    private boolean free = true;
    private WashCycle currentCycle;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isFree() {
        return free;
    }

    public void startCycle(WashCycle cycle) {
        free = false;
        currentCycle = cycle;
    }

    public void completeCycle() {
        if (!free) {
            System.out.println("M1 cycle completed.");
            free = true;
            currentCycle = null;
            System.out.println(machineId + " is now free.");
        }
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public void displayDetails() {
        System.out.printf(
                "%s wash started on %s for %s (%d min). Charge: ₹%.2f%n",
                washType.getName(),
                machine.getMachineId(),
                student.getName(),
                washType.getDuration(),
                washType.getCharge());
    }
}

public class HostelLaundryQueue {

    public static void startWash(
            Student student,
            WashingMachine machine,
            WashType washType) {

        if (!machine.isFree()) {
            System.out.println(
                    "Machine " + machine.getMachineId() + " is currently busy.");
            return;
        }

        WashCycle cycle = new WashCycle(student, machine, washType);
        machine.startCycle(cycle);
        cycle.displayDetails();
    }

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        startWash(asha, m1, new QuickWash());

        startWash(ravi, m1, new HeavyWash());

        startWash(ravi, m2, new HeavyWash());

        m1.completeCycle();

        startWash(neha, m1, new NormalWash());
    }
}