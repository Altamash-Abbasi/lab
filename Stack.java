import java.util.*;


// Generic Stack Implementation
class MyStack<T> {
    private ArrayList<T> stack;

    public MyStack() {
        stack = new ArrayList<>();
    }

    public void push(T value) {
        stack.add(value);
    }

    public T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}


public class InfixPostfixEvaluation {

    // Function to define precedence
    public static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // Infix to Postfix Conversion
    public static String infixToPostfix(String infix) {

        MyStack<Character> stack = new MyStack<>();
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {

            char ch = infix.charAt(i);

            // If operand, add to postfix
            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            }

            // If '(', push
            else if (ch == '(') {
                stack.push(ch);
            }

            // If ')', pop until '('
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop(); // remove '('
            }

            // If operator
            else {
                while (!stack.isEmpty() &&
                        precedence(stack.peek()) >= precedence(ch)) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    // Postfix Evaluation
    public static double evaluatePostfix(String postfix) {

        MyStack<Double> stack = new MyStack<>();

        for (int i = 0; i < postfix.length(); i++) {

            char ch = postfix.charAt(i);

            // If digit, push
            if (Character.isDigit(ch)) {
                stack.push((double) (ch - '0'));
            }

            // If operator
            else {
                double val2 = stack.pop();
                double val1 = stack.pop();

                switch (ch) {
                    case '+':
                        stack.push(val1 + val2);
                        break;
                    case '-':
                        stack.push(val1 - val2);
                        break;
                    case '*':
                        stack.push(val1 * val2);
                        break;
                    case '/':
                        stack.push(val1 / val2);
                        break;
                    case '^':
                        stack.push(Math.pow(val1, val2));
                        break;
                }
            }
        }

        return stack.pop();
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Infix Expression: ");
        String infix = sc.nextLine();

        String postfix = infixToPostfix(infix);

        System.out.println("Postfix Expression: " + postfix);

        System.out.println("Evaluation Result: " + 
                           evaluatePostfix(postfix));
    }
}
