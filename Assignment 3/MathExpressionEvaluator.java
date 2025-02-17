import java.util.*;
import java.util.regex.*;

public class MathExpressionEvaluator {

    private static final Map<String, Integer> precedence = new HashMap<>();

    static {

        precedence.put("(", 0);
        precedence.put(")", 0);
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        precedence.put("^", 3);

    }

    public static double parse(String str) { //parse the function so that it can be calculated

        if (str.equals("exit")) { // exit condition
            System.out.println("Exiting program.");
            return Double.NaN;
        }

        // tokenize the input string and split it
        String regex = "(\\d*\\.\\d+|\\d+|[+\\-*/^=()])|([a-zA-Z]+)";
        List<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile(regex).matcher(str);

        while (matcher.find()) {
            String token = matcher.group();

            // handle negative
            if (token.equals("-") && (tokens.isEmpty() || tokens.get(tokens.size() - 1).equals("(") || isOperator(tokens.get(tokens.size() - 1)))) {
                matcher.find();
                token = "-" + matcher.group(); 
            }

            // add token to list
            tokens.add(token);
        }

        return evaluateExpression(tokens);
    }

    private static double evaluateExpression(List<String> tokens) {
        //evaluate the + or -
        return evaluateAddSubtract(tokens);
    }

    private static double evaluateAddSubtract(List<String> tokens) {
        double result = evaluateMultiplyDivide(tokens); // evaluate * and /

        // Now handle + and -
        while (!tokens.isEmpty()) {
            String token = tokens.get(0);

            if (token.equals("+") || token.equals("-")) {
                tokens.remove(0); //remove + or -
                double nextValue = evaluateMultiplyDivide(tokens); // evaluate * or /

                if (token.equals("+")) {
                    result += nextValue;
                } else {
                    result -= nextValue;
                }
            } else {
                break;
            }
        }

        return result;
    }

    //handle * and /
    private static double evaluateMultiplyDivide(List<String> tokens) {
        double result = evaluateParentheses(tokens); //evaluate()

        // now handle * and /
        while (!tokens.isEmpty()) {
            String token = tokens.get(0);

            if (token.equals("*") || token.equals("/")) {
                tokens.remove(0); //remove * or /
                double nextValue = evaluateParentheses(tokens); // evaluate anything inside ()

                if (token.equals("*")) {
                    result *= nextValue;
                } else {
                    result /= nextValue;
                }
            } else {
                break;
            }
        }

        return result;
    }

    // handle ()
    private static double evaluateParentheses(List<String> tokens) {
        // if() then, evaluate the expression inside
        if (tokens.get(0).equals("(")) {
            tokens.remove(0); // Remove '('
            double value = evaluateAddSubtract(tokens); // evaluate the expression inside ()
            if (tokens.get(0).equals(")")) {
                tokens.remove(0); // Remove ')'
            }
            return value;
        } else {
            // if no (), it's just a number or function
            return evaluateNumberOrFunction(tokens);
        }
    }

    // handle numbers or functions
    private static double evaluateNumberOrFunction(List<String> tokens) {
        String token = tokens.remove(0);
        boolean isNegative = token.startsWith("-");

        if (isNegative) {
            token = token.substring(1); // remove -
        }

        if (isNumber(token)) {
            double value = Double.parseDouble(token);
            return isNegative ? -value : value;
        } else if (isFunction(token)) {
            double value = applyFunction(token, evaluateParentheses(tokens)); // apply function to the value
            return isNegative ? -value : value;
        } else {
            throw new IllegalArgumentException("Unexpected token: " + token);
        }
    }

    // apply a function to a number
    private static double applyFunction(String function, double value) {
        switch (function) {
            case "sin":
                return Math.sin(value);
            case "cos":
                return Math.cos(value);
            case "tan":
                return Math.tan(value);
            case "sqrt":
                return Math.sqrt(value);
            default:
                throw new IllegalArgumentException("Unknown function: " + function);
        }
    }

    // check if a token is a number
    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // check if a token is a trigonometric function
    private static boolean isFunction(String token) {
        return token.equals("sin") || token.equals("cos") || token.equals("tan") || token.equals("sqrt");
    }

    // check if a token is an operator
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^");
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            String expr = input.nextLine();
            double result = parse(expr);

            if (Double.isNaN(result)) {
                break;
            }

            System.out.println(String.valueOf(Math.round(result)));
        }
    }
}
