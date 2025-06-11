import java.util.*;

public class MyConsole {

    private static double ans = 0.0;
    private static double preAns = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Scientific Calculator ===");
        System.out.println("Enter mathematical expressions using +, -, *, /, ^, sqrt(x), log(b,x), sin(x), cos(x), etc.");
        System.out.println("Use 'Ans' to refer to the last answer, 'PreAns' for the one before that.");
        System.out.println("Type 'help' for instructions or 'quit' to exit.");

        while (true) {
            System.out.print("\nEnter expression: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }

            try {
                String replacedInput = input.replace("Ans", String.valueOf(ans))
                                            .replace("PreAns", String.valueOf(preAns));

                List<String> tokens = Tokenizer.tokenize(replacedInput);
                List<String> postfix = ShuntingYard.infixToPostfix(tokens);
                double result = Evaluator.evaluate(postfix);

                preAns = ans;
                ans = result;

                System.out.println("Result: " + result);
            } catch (RuntimeException e) {
                System.out.println("[ERROR] " + e.getMessage());
                System.out.println("Try again or type 'help' for guidance.");
            }
        }

        scanner.close();
    }

    private static void printHelp() {
        System.out.println("\n=== Help ===");
        System.out.println(" - Enter arithmetic expressions: 3 + 4 * 2");
        System.out.println(" - Use parentheses: (2 + 3) * 5");
        System.out.println(" - Use functions: sin(30), cos(60), tan(45), sqrt(16), log(2, 8)");
        System.out.println(" - Constants: pi, e");
        System.out.println(" - Use 'Ans' to refer to the last answer, and 'PreAns' to the answer before that");
        System.out.println(" - Supported operators: +, -, *, /, %, ^");
        System.out.println(" - Type 'quit' to exit");
    }
}
