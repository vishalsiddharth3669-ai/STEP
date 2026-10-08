import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    private String title;
    private int maxMarks;
    private LocalDate dueDate;

    public Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public abstract double applyPenalty(double marks, long lateDays);
}

class CodingAssignment extends Assignment {

    public CodingAssignment(
            String title,
            int maxMarks,
            LocalDate dueDate) {

        super(title, maxMarks, dueDate);
    }

    public double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.10;
        return marks * (1 - penalty);
    }
}

class WrittenAssignment extends Assignment {

    public WrittenAssignment(
            String title,
            int maxMarks,
            LocalDate dueDate) {

        super(title, maxMarks, dueDate);
    }

    public double applyPenalty(double marks, long lateDays) {
        double penalty = lateDays * 0.20;
        return marks * (1 - penalty);
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

enum SubmissionStatus {
    SUBMITTED,
    GRADED
}

class Submission {
    private Student student;
    private Assignment assignment;
    private LocalDate submissionDate;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(
            Student student,
            Assignment assignment,
            LocalDate submissionDate) {

        this.student = student;
        this.assignment = assignment;
        this.submissionDate = submissionDate;
        this.status = SubmissionStatus.SUBMITTED;
    }

    public boolean grade(double awardedMarks) {

        if (status == SubmissionStatus.GRADED) {
            System.out.println(
                    "Cannot grade again: submission is already graded.");
            return false;
        }

        long lateDays = 0;

        if (submissionDate.isAfter(assignment.getDueDate())) {
            lateDays = ChronoUnit.DAYS.between(
                    assignment.getDueDate(),
                    submissionDate);
        }

        finalMarks = assignment.applyPenalty(
                awardedMarks,
                lateDays);

        if (finalMarks > assignment.getMaxMarks()) {
            finalMarks = assignment.getMaxMarks();
        }

        status = SubmissionStatus.GRADED;

        System.out.printf(
                "%s graded: %.0f/%d",
                student.getName(),
                finalMarks,
                assignment.getMaxMarks());

        if (lateDays > 0) {
            double percentage = lateDays *
                    (assignment instanceof CodingAssignment ? 10 : 20);

            System.out.printf(
                    " after %.0f%% late penalty",
                    percentage);
        }

        System.out.println(".");
        System.out.println("Status: Graded.");

        return true;
    }

    public boolean resubmit() {

        if (status == SubmissionStatus.GRADED) {
            System.out.println(
                    "Cannot resubmit: '" +
                            assignment.getTitle() +
                            "' has already been graded.");
            return false;
        }

        System.out.println("Resubmission accepted.");
        return true;
    }

    public void displaySubmission() {

        long lateDays = 0;

        if (submissionDate.isAfter(assignment.getDueDate())) {
            lateDays = ChronoUnit.DAYS.between(
                    assignment.getDueDate(),
                    submissionDate);
        }

        if (lateDays == 0) {
            System.out.println(
                    student.getName() +
                            "'s submission for '" +
                            assignment.getTitle() +
                            "' received (on time).");
        } else {
            System.out.println(
                    student.getName() +
                            "'s submission for '" +
                            assignment.getTitle() +
                            "' received (" +
                            lateDays +
                            " days late).");
        }

        System.out.println("Status: Submitted.");
    }
}

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Assignment coding = new CodingAssignment(
                "Linked List Lab",
                50,
                LocalDate.of(2026, 3, 10));

        Assignment written = new WrittenAssignment(
                "Design Essay",
                50,
                LocalDate.of(2026, 3, 12));

        Submission ashaSubmission = new Submission(
                asha,
                coding,
                LocalDate.of(2026, 3, 10));

        Submission raviSubmission = new Submission(
                ravi,
                written,
                LocalDate.of(2026, 3, 14));

        ashaSubmission.displaySubmission();
        raviSubmission.displaySubmission();

        ashaSubmission.grade(45);
        raviSubmission.grade(40);

        ashaSubmission.resubmit();
    }
}