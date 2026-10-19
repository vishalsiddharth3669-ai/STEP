
import java.util.*;

public class PayrollRegister {
    static class Payslip {
        String name;
        String type;
        long pay;

        Payslip(String name, String type, long pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }

        public String toString() {
            return "Payslip[name=" + name + ", type=" + type + ", pay=" + pay + "]";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Payslip> payslips = new ArrayList<>();

        while (sc.hasNext()) {
            String type = sc.next();
            String name = sc.next();
            long pay;

            switch (type) {
                case "FullTime":
                    pay = sc.nextLong();
                    break;
                case "PartTime":
                    long hours = sc.nextLong();
                    long rate = sc.nextLong();
                    pay = hours * rate;
                    break;
                case "Intern":
                    pay = sc.nextLong();
                    break;
                default:
                    continue;
            }

            payslips.add(new Payslip(name, type, pay));
        }

        long total = 0;
        Payslip topper = null;

        for (Payslip p : payslips) {
            System.out.println(p);
            total += p.pay;

            if (topper == null || p.pay > topper.pay) {
                topper = p;
            }
        }

        System.out.println("Total " + total);

        if (topper != null) {
            System.out.println("top earner " + topper.name);
        }

        sc.close();
    }
}
