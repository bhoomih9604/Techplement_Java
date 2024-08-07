import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Quiz> quizzes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    createQuiz();
                    break;
                case 2:
                    addQuestionToQuiz();
                    break;
                case 3:
                    takeQuiz();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nQuiz Generator Menu:");
        System.out.println("1. Create a new quiz");
        System.out.println("2. Add a question to an existing quiz");
        System.out.println("3. Take a quiz");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createQuiz() {
        System.out.print("Enter the title of the new quiz: ");
        String title = scanner.nextLine();
        quizzes.add(new Quiz(title));
        System.out.println("Quiz created successfully.");
    }

    private static void addQuestionToQuiz() {
        System.out.print("Enter the title of the quiz to add a question to: ");
        String title = scanner.nextLine();
        Quiz quiz = findQuizByTitle(title);
        if (quiz != null) {
            System.out.print("Enter the question text: ");
            String questionText = scanner.nextLine();
            List<String> options = new ArrayList<>();
            System.out.print("Enter the number of options: ");
            int numOptions = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < numOptions; i++) {
                System.out.print("Enter option " + (i + 1) + ": ");
                options.add(scanner.nextLine());
            }
            System.out.print("Enter the index of the correct answer (1-" + numOptions + "): ");
            int correctAnswerIndex = Integer.parseInt(scanner.nextLine()) - 1;
            quiz.addQuestion(new Question(questionText, options, correctAnswerIndex));
            System.out.println("Question added successfully.");
        } else {
            System.out.println("Quiz not found.");
        }
    }

    private static void takeQuiz() {
        System.out.print("Enter the title of the quiz to take: ");
        String title = scanner.nextLine();
        Quiz quiz = findQuizByTitle(title);
        if (quiz != null) {
            int score = 0;
            List<Question> questions = quiz.getQuestions();
            for (Question question : questions) {
                System.out.println(question.getQuestionText());
                List<String> options = question.getOptions();
                for (int i = 0; i < options.size(); i++) {
                    System.out.println((i + 1) + ". " + options.get(i));
                }
                System.out.print("Enter your answer (1-" + options.size() + "): ");
                int answer = Integer.parseInt(scanner.nextLine()) - 1;
                if (answer == question.getCorrectAnswerIndex()) {
                    score++;
                }
            }
            System.out.println("You scored " + score + " out of " + questions.size());
        } else {
            System.out.println("Quiz not found.");
        }
    }

    private static Quiz findQuizByTitle(String title) {
        for (Quiz quiz : quizzes) {
            if (quiz.getTitle().equalsIgnoreCase(title)) {
                return quiz;
            }
        }
        return null;
    }
}
