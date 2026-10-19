
import java.util.*;

public class StudentClubRegistration {
    static class Student {
        String rollNumber;
        String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Student)) return false;
            Student other = (Student) obj;
            return rollNumber.equals(other.rollNumber);
        }

        public int hashCode() {
            return rollNumber.hashCode();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Set<Student> members = new LinkedHashSet<>();
        List<String> results = new ArrayList<>();
        int count = 0;

        while (sc.hasNext()) {
            String operation = sc.next();
            String roll = sc.next();
            String name = sc.next();
            Student student = new Student(roll, name);

            if (operation.equals("ADD")) {
                if (members.add(student)) {
                    results.add("Added");
                    count++;
                } else {
                    results.add("duplicate rejected");
                }
            } else if (operation.equals("CONTAINS")) {
                results.add("contains: " + members.contains(student));
            }
        }

        for (String result : results) {
            System.out.println(result);
        }

        System.out.println("member count " + count);
        sc.close();
    }
}
