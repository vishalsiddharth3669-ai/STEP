import java.util.Arrays;

public class FineLedger {

    public static void main(String[] args) {

        PremiumMember p = new PremiumMember(
                "MEM5",
                2000,
                "Coach Riya");

        p.chargeLateFee(200);

        System.out.println(
                p.getTotalLateFees());

        int[] history = p.getLateFeeHistory();

        history[0] = 999;

        System.out.println(
                Arrays.toString(
                        p.getLateFeeHistory()));
    }
}

class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    private int[] lateFeeHistory;
    private int lateFeeCount;

    public GymMember(
            String memberId,
            int monthlyFee) {

        if (memberId == null ||
                memberId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;

        lateFeeHistory = new int[10];
        lateFeeCount = 0;
    }

    protected void chargeLateFee(int amount) {

        if (lateFeeCount < lateFeeHistory.length) {

            lateFeeHistory[lateFeeCount] = amount;
            lateFeeCount++;
        }
    }

    public int[] getLateFeeHistory() {

        return Arrays.copyOf(
                lateFeeHistory,
                lateFeeCount);
    }

    public int getTotalLateFees() {

        int total = 0;

        for (int i = 0; i < lateFeeCount; i++) {
            total += lateFeeHistory[i];
        }

        return total;
    }
}

class PremiumMember extends GymMember {

    private String trainerName;

    public PremiumMember(
            String memberId,
            int monthlyFee,
            String trainerName) {

        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {

        super.chargeLateFee(amount / 2);
    }
}