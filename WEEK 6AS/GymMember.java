public class GymMember {

    protected String memberId;
    protected int monthlyFee;
    protected int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {

        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {

        sessionsAttended++;
    }

    public int getSessionsAttended() {

        return sessionsAttended;
    }

    public void displayInfo() {

        System.out.println(
                "Standard Member | Sessions: "
                        + sessionsAttended);
    }

    public static String signUpBatch(
            String[] memberIds,
            int monthlyFee) {

        int signedUp = 0;
        int rejected = 0;

        for (String id : memberIds) {

            try {
                new GymMember(id, monthlyFee);
                signedUp++;
            } catch (IllegalArgumentException e) {
                rejected++;
            }
        }

        return "Signed Up: " + signedUp
                + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        try {
            new GymMember("GM1", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        PremiumMember p = new PremiumMember(
                "MEM01",
                2000,
                "Coach Riya");

        p.attendSession();
        p.attendSession();

        System.out.println(
                p.getSessionsAttended());

        String[] ids = {
                "MEM1",
                "GM1",
                "MEM2",
                " ",
                "MEM3"
        };

        System.out.println(
                signUpBatch(ids, 1000));
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
    public void displayInfo() {

        System.out.println(
                "Premium Member | Trainer: "
                        + trainerName
                        + " | Sessions: "
                        + sessionsAttended);
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {

        super.chargeLateFee(amount / 2);
    }
}