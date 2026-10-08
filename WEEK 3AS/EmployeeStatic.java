public class EmployeeStatic {

    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";

    static int employeeCount = 0;

    EmployeeStatic(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println("Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {

        EmployeeStatic employee1 = new EmployeeStatic("Ravi", 50000);

        EmployeeStatic employee2 = new EmployeeStatic("Priya", 60000);

        EmployeeStatic employee3 = new EmployeeStatic("Arjun", 45000);

        EmployeeStatic.printCompanyInfo();
    }
}