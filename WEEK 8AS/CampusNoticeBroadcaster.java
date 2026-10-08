import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[Email → " +
                        student.getName() +
                        "] " +
                        notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[SMS → " +
                        student.getName() +
                        "] " +
                        notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {

    public void send(Student student, Notice notice) {
        System.out.println(
                "[App → " +
                        student.getName() +
                        "] " +
                        notice.getTitle());
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    public Student(
            String name,
            String department,
            List<NotificationChannel> preferredChannels) {

        this.name = name;
        this.department = department;
        this.preferredChannels = preferredChannels;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getPreferredChannels() {
        return preferredChannels;
    }
}

class Notice {
    private String title;
    private Set<String> targetDepartments;

    public Notice(
            String title,
            Set<String> targetDepartments) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Notice title cannot be empty.");
        }

        if (targetDepartments == null ||
                targetDepartments.isEmpty()) {

            throw new IllegalArgumentException(
                    "At least one target department is required.");
        }

        this.title = title;
        this.targetDepartments = targetDepartments;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTargetDepartments() {
        return targetDepartments;
    }
}

class NoticeBoard {

    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void postNotice(
            String title,
            Set<String> departments) {

        try {
            Notice notice = new Notice(title, departments);

            System.out.println(
                    "Notice '" +
                            title +
                            "' posted to " +
                            String.join(", ", departments) +
                            ".");

            for (Student student : students) {

                if (departments.contains(
                        student.getDepartment())) {

                    for (NotificationChannel channel : student.getPreferredChannels()) {

                        channel.send(student, notice);
                    }
                }
            }

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Cannot post notice: " +
                            e.getMessage());
        }
    }
}

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {

        Student asha = new Student(
                "Asha",
                "CSE",
                Arrays.asList(
                        new EmailChannel(),
                        new AppChannel()));

        Student ravi = new Student(
                "Ravi",
                "ECE",
                Arrays.asList(
                        new SmsChannel()));

        NoticeBoard board = new NoticeBoard();

        board.addStudent(asha);
        board.addStudent(ravi);

        board.postNotice(
                "Lab Closed Tomorrow",
                new HashSet<>(
                        Arrays.asList("CSE")));

        board.postNotice(
                "Fee Deadline Extended",
                new HashSet<>(
                        Arrays.asList("CSE", "ECE")));

        board.postNotice(
                "Sports Day",
                new HashSet<>());
    }
}