import java.util.ArrayList;
import java.util.List;

public class OnlineExaminationSystem {

    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                new MultipleChoiceQuestion(
                        "Question 1",
                        5,
                        "B"));

        exam.addQuestion(
                new TrueFalseQuestion(
                        "Question 2",
                        5,
                        true));

        Attempt attempt = exam.start(student);

        attempt.answerQuestion(
                0,
                "C");

        attempt.answerQuestion(
                1,
                "True");

        attempt.submit();

        attempt.answerQuestion(
                0,
                "B");
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

abstract class Question {

    protected String text;
    protected int marks;

    public Question(String text, int marks) {
        this.text = text;
        this.marks = marks;
    }

    public abstract boolean evaluate(String answer);

    public int getMarks() {
        return marks;
    }

    public String getText() {
        return text;
    }
}

class MultipleChoiceQuestion extends Question {

    private String correctAnswer;

    public MultipleChoiceQuestion(
            String text,
            int marks,
            String correctAnswer) {

        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {

    private boolean correctAnswer;

    public TrueFalseQuestion(
            String text,
            int marks,
            boolean correctAnswer) {

        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {

    private String correctAnswer;

    public ShortAnswerQuestion(
            String text,
            int marks,
            String correctAnswer) {

        super(text, marks);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {

    private String name;
    private List<Question> questions = new ArrayList<>();

    public Examination(String name) {
        this.name = name;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Attempt start(Student student) {

        System.out.println(
                name
                        + " started by "
                        + student.getName());

        return new Attempt(
                student,
                this,
                questions);
    }

    public String getName() {
        return name;
    }
}

class Attempt {

    private Student student;
    private Examination examination;
    private List<Question> questions;
    private String[] answers;
    private boolean submitted;

    public Attempt(
            Student student,
            Examination examination,
            List<Question> questions) {

        this.student = student;
        this.examination = examination;
        this.questions = questions;
        this.answers = new String[questions.size()];
        this.submitted = false;
    }

    public void answerQuestion(
            int questionIndex,
            String answer) {

        if (submitted) {

            System.out.println(
                    "Cannot change answers for a submitted examination.");

            return;
        }

        answers[questionIndex] = answer;

        System.out.println(
                "Answer recorded for Question "
                        + (questionIndex + 1));
    }

    public void submit() {

        if (submitted) {
            return;
        }

        submitted = true;

        System.out.println(
                examination.getName()
                        + " submitted by "
                        + student.getName());

        int totalScore = 0;
        int totalMarks = 0;

        for (int i = 0; i < questions.size(); i++) {

            Question question = questions.get(i);

            boolean correct = question.evaluate(answers[i]);

            if (correct) {

                totalScore += question.getMarks();

                System.out.println(
                        "Result: "
                                + question.getText()
                                + ": Correct ("
                                + question.getMarks()
                                + " points)");

            } else {

                System.out.println(
                        "Result: "
                                + question.getText()
                                + ": Incorrect (0 points)");
            }

            totalMarks += question.getMarks();
        }

        System.out.println(
                "Total score: "
                        + totalScore
                        + "/"
                        + totalMarks);
    }
}