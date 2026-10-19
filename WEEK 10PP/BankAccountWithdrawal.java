
import java.util.*;

public class BankAccountWithdrawal {
    static class Account {
        String id;
        double balance;
        boolean savings;

        Account(String id, double balance, boolean savings) {
            this.id = id;
            this.balance = balance;
            this.savings = savings;
        }

        void withdraw(double amount) {
            if (savings && balance - amount < 1000) {
                System.out.println(id + " rejected: minimum balance 1000");
            } else if (!savings && balance - amount < -5000) {
                System.out.println(id + " rejected: overdraft limit 5000");
            } else {
                balance -= amount;
                System.out.println(id + " balance " + format(balance));
            }
        }
    }

    static String format(double value) {
        if (value == (long) value)
            return String.valueOf((long) value);
        return String.valueOf(value);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Account> accounts = new LinkedHashMap<>();

        while (sc.hasNext()) {
            String operation = sc.next();

            if (operation.equals("Savings") || operation.equals("Current")) {
                String id = sc.next();
                double balance = sc.nextDouble();
                accounts.put(id, new Account(id, balance, operation.equals("Savings")));
            } else if (operation.equals("WITHDRAW")) {
                String id = sc.next();
                double amount = sc.nextDouble();

                if (!accounts.containsKey(id)) {
                    System.out.println("Account not found");
                } else {
                    accounts.get(id).withdraw(amount);
                }
            }
        }
        sc.close();
    }
}
