interface MembershipPlan {
    String getName();

    int getMonths();

    double calculateFee();
}

class MonthlyPlan implements MembershipPlan {

    public String getName() {
        return "Monthly";
    }

    public int getMonths() {
        return 1;
    }

    public double calculateFee() {
        return 1000.00;
    }
}

class QuarterlyPlan implements MembershipPlan {

    public String getName() {
        return "Quarterly";
    }

    public int getMonths() {
        return 3;
    }

    public double calculateFee() {
        return 1000.00 * 3 * 0.90;
    }
}

class AnnualPlan implements MembershipPlan {

    public String getName() {
        return "Annual";
    }

    public int getMonths() {
        return 12;
    }

    public double calculateFee() {
        return 1000.00 * 12 * 0.75;
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum MembershipStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;

    public Membership(
            Member member,
            MembershipPlan plan) {

        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;
    }

    public void checkIn() {

        if (status == MembershipStatus.ACTIVE) {
            System.out.println(
                    member.getName() +
                            " checked in successfully.");
        } else {
            System.out.println(
                    "Check-in denied: " +
                            member.getName() +
                            "'s membership is " +
                            status + ".");
        }
    }

    public void freeze() {

        if (status == MembershipStatus.ACTIVE) {
            status = MembershipStatus.FROZEN;

            System.out.println(
                    member.getName() +
                            "'s membership frozen.");

            System.out.println("Status: Frozen.");
        } else if (status == MembershipStatus.EXPIRED) {
            System.out.println(
                    "Cannot freeze an Expired membership.");
        } else {
            System.out.println(
                    "Membership is already Frozen.");
        }
    }

    public void unfreeze() {

        if (status == MembershipStatus.FROZEN) {
            status = MembershipStatus.ACTIVE;

            System.out.println(
                    member.getName() +
                            "'s membership unfrozen.");

            System.out.println("Status: Active.");
        } else if (status == MembershipStatus.EXPIRED) {
            System.out.println(
                    "Cannot unfreeze an Expired membership.");
        } else {
            System.out.println(
                    "Membership is already Active.");
        }
    }

    public void expire() {

        status = MembershipStatus.EXPIRED;

        System.out.println(
                member.getName() +
                        "'s membership expired.");

        System.out.println("Status: Expired.");
    }

    public void displayDetails() {

        System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
                plan.getName(),
                member.getName(),
                plan.calculateFee());
    }
}

public class FitZoneMembershipDesk {

    public static void main(String[] args) {

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());

        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        ashaMembership.displayDetails();
        raviMembership.displayDetails();

        ashaMembership.checkIn();

        ashaMembership.freeze();

        ashaMembership.checkIn();

        raviMembership.expire();

        raviMembership.freeze();
    }
}