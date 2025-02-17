import java.util.*;
import java.util.regex.*;

public class MathExpressionEvaluator {
    private static final Map<String, Integer> operatorPrecedence = new HashMap<>();

    static {
        operatorPrecedence.put("(", 0);
        operatorPrecedence.put(")", 0);
        operatorPrecedence.put("+", 1);
        operatorPrecedence.put("-", 1);
        operatorPrecedence.put("*", 2);
        operatorPrecedence.put("/", 2);
        operatorPrecedence.put("^", 3);
    }

    public static double calculateExpression(String input) {
        if (input.equalsIgnoreCase("exit")) {
            System.out.println("Terminating program.");
            return Double.NaN;
        }

        // Tokenize the input string
        String tokenPattern = "(\\d*\\.\\d+|\\d+|[+\\-*/^=()])|([a-zA-Z]+)";
        List<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile(tokenPattern).matcher(input);

        while (matcher.find()) {
            String currentToken = matcher.group();

            // Handle negative numbers
            if (currentToken.equals("-") &&
                (tokens.isEmpty() || tokens.get(tokens.size() - 1).equals("(") || isOperator(tokens.get(tokens.size() - 1)))) {
                matcher.find();
                currentToken = "-" + matcher.group();
            }

            tokens.add(currentToken);
        }

        return evaluateTokens(tokens);
    }

    private static double evaluateTokens(List<String> tokens) {
        return handleAdditionAndSubtraction(tokens);
    }

    private static double handleAdditionAndSubtraction(List<String> tokens) {
        double result = handleMultiplicationAndDivision(tokens);

        while (!tokens.isEmpty()) {
            String operator = tokens.get(0);
            if (operator.equals("+") || operator.equals("-")) {
                tokens.remove(0);
                double nextValue = handleMultiplicationAndDivision(tokens);
                result = operator.equals("+") ? result + nextValue : result - nextValue;
            } else {
                break;
            }
        }

        return result;
    }

    private static double handleMultiplicationAndDivision(List<String> tokens) {
        double result = processParentheses(tokens);

        while (!tokens.isEmpty()) {
            String operator = tokens.get(0);
            if (operator.equals("*") || operator.equals("/")) {
                tokens.remove(0);
                double nextValue = processParentheses(tokens);
                result = operator.equals("*") ? result * nextValue : result / nextValue;
            } else {
                break;
            }
        }

        return result;
    }

    private static double processParentheses(List<String> tokens) {
        if (tokens.get(0).equals("(")) {
            tokens.remove(0); // Remove '('
            double innerValue = handleAdditionAndSubtraction(tokens);
            if (tokens.get(0).equals(")")) {
                tokens.remove(0); // Remove ')'
            }
            return innerValue;
        } else {
            return parseNumberOrFunction(tokens);
        }
    }

    private static double parseNumberOrFunction(List<String> tokens) {
        String current = tokens.remove(0);
        boolean isNegative = current.startsWith("-");

        if (isNegative) {
            current = current.substring(1);
        }

        if (isNumeric(current)) {
            double number = Double.parseDouble(current);
            return isNegative ? -number : number;
        } else if (isValidFunction(current)) {
            double functionArgument = processParentheses(tokens);
            double computedValue = applyMathFunction(current, functionArgument);
            return isNegative ? -computedValue : computedValue;
        } else {
            throw new IllegalArgumentException("Unexpected token: " + current);
        }
    }

    private static double applyMathFunction(String function, double argument) {
        switch (function) {
            case "sin":
                return Math.sin(argument);
            case "cos":
                return Math.cos(argument);
            case "tan":
                return Math.tan(argument);
            case "sqrt":
                return Math.sqrt(argument);
            default:
                throw new IllegalArgumentException("Unsupported function: " + function);
        }
    }

    private static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isValidFunction(String token) {
        return token.equals("sin") || token.equals("cos") || token.equals("tan") || token.equals("sqrt");
    }

    private static boolean isOperator(String token) {
        return operatorPrecedence.containsKey(token);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String inputExpression = scanner.nextLine();
            double result = calculateExpression(inputExpression);
            if (Double.isNaN(result)) {
                break;
            }
            System.out.println(Math.round(result));
        }
        scanner.close();
    }
}
