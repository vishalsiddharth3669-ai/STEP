public class FeeAccount {

    void pay(double amount) {
        System.out.println(
                "Paid in one go (day-scholar account)");
    }

    static class HostelFeeAccount extends FeeAccount {

        @Override
        void pay(double amount) {
            System.out.println(
                    "Paid in two installments (hostel account)");
        }
    }

    static int hostelCount = 0;
    static int dayScholarCount = 0;

    static void processPayment(
            FeeAccount account, double amount) {

        if (account instanceof HostelFeeAccount) {

            HostelFeeAccount hostel = (HostelFeeAccount) account;

            hostel.pay(amount);
            hostelCount++;

        } else {

            account.pay(amount);
            dayScholarCount++;
        }
    }

    public static void main(String[] args) {

        FeeAccount[] accounts = {
                new HostelFeeAccount(),
                new HostelFeeAccount(),
                new FeeAccount(),
                new FeeAccount()
        };

        double amount = 60000;

        for (FeeAccount account : accounts) {
            processPayment(account, amount);
        }

        System.out.println(
                "Hostel accounts processed: "
                        + hostelCount
                        + " | Day-scholar accounts processed: "
                        + dayScholarCount);
    }
}