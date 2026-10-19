
import java.util.*;

public class ClassMarksGrid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            int open = line.indexOf('[');
            int close = line.indexOf(']');

            if (open == -1 || close == -1) continue;

            String name = line.substring(0, open).trim();
            String[] values = line.substring(open + 1, close).split(",");

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(values[i].trim());
            }

            names.add(name);
            marksList.add(marks);
        }

        int n = names.size();

        if (n == 0) {
            sc.close();
            return;
        }

        int[] totals = new int[n];
        double[] subjectTotals = new double[3];
        int topper = 0;

        for (int i = 0; i < n; i++) {
            int[] marks = marksList.get(i);

            for (int j = 0; j < 3; j++) {
                totals[i] += marks[j];
                subjectTotals[j] += marks[j];
            }

            if (totals[i] > totals[topper]) {
                topper = i;
            }
        }

        System.out.print("Totals ");
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(names.get(i) + " " + totals[i]);
        }
        System.out.println();

        System.out.printf(Locale.US, "averages %.2f, %.2f, %.2f%n",
                subjectTotals[0] / n,
                subjectTotals[1] / n,
                subjectTotals[2] / n);

        System.out.println("topper " + names.get(topper) + " (" + totals[topper] + ")");
        sc.close();
    }
}

