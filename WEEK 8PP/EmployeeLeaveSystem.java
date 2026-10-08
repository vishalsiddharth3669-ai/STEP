import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class EmployeeLeaveSystem {

    public static void main(String[] args) {

        Employee john = new FullTimeEmployee("John");

        Employee jane = new PartTimeEmployee("Jane");

        LeaveRequest johnRequest = john.submitLeave(
                LocalDate.of(2026, 1, 1),
                LocalDate.of(2026, 1, 5));

        System.out.println(
                johnRequest.getStatus());

        johnRequest.approve("Alice");

        System.out.println(
                johnRequest.getStatus());

        LeaveRequest janeRequest = jane.submitLeave(
                LocalDate.of(2026, 2, 10),
                LocalDate.of(2026, 2, 11));

        janeRequest.reject("Bob");

        System.out.println(
                janeRequest.getStatus());

        johnRequest.changeStatus(
                LeaveStatus.PENDING);
    }
}

enum LeaveStatus {
    PENDING,
    APPROVED,
    REJECTED
}

abstract class Employee {

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getMaximumLeaveDays();

    public LeaveRequest submitLeave(
            LocalDate startDate,
            LocalDate endDate) {

        long days = ChronoUnit.DAYS.between(
                startDate,
                endDate) + 1;

        if (days > getMaximumLeaveDays()) {

            throw new IllegalArgumentException(
                    "Leave exceeds allowed limit");
        }

        LeaveRequest request = new LeaveRequest(
                this,
                startDate,
                endDate);

        System.out.println(
                "Leave request submitted for "
                        + name);

        return request;
    }
}

class FullTimeEmployee extends Employee {

    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public int getMaximumLeaveDays() {
        return 30;
    }
}

class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public int getMaximumLeaveDays() {
        return 15;
    }
}

class Contractor extends Employee {

    public Contractor(String name) {
        super(name);
    }

    @Override
    public int getMaximumLeaveDays() {
        return 10;
    }
}

class LeaveRequest {

    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveStatus status;

    public LeaveRequest(
            Employee employee,
            LocalDate startDate,
            LocalDate endDate) {

        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = LeaveStatus.PENDING;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void approve(String reviewer) {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                    "Cannot approve a request that is already "
                            + status);

            return;
        }

        status = LeaveStatus.APPROVED;

        System.out.println(
                employee.getName()
                        + "'s leave request approved.");
    }

    public void reject(String reviewer) {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                    "Cannot reject a request that is already "
                            + status);

            return;
        }

        status = LeaveStatus.REJECTED;

        System.out.println(
                employee.getName()
                        + "'s leave request rejected.");
    }

    public void changeStatus(
            LeaveStatus newStatus) {

        if (status != LeaveStatus.PENDING) {

            System.out.println(
                    "Cannot change leave request status from "
                            + status
                            + " to "
                            + newStatus);

            return;
        }

        status = newStatus;
    }
}