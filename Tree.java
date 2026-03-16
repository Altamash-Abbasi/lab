import java.util.*;

class Node {
    char x;
    Node left;
    Node right;

    Node(char x) {
        this.x = x;
        left = right = null;
    }
}

class btree{
    
}

class Expression {
    Stack<Node> st;

    Node create(String s) {
        st = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {
                Node n = new Node(ch);
                st.push(n);
            } else {
                Node n = new Node(ch);

                Node r = st.pop();
                Node l = st.pop();

                n.left = l;
                n.right = r;

                st.push(n);
            }
        }
        return st.pop();
    }

    int eval(Node root) {

        if (root.left == null && root.right == null)
            return root.x - '0';

        int leval = eval(root.left);
        int reval = eval(root.right);

        switch (root.x) {
            case '+':
                return leval + reval;
            case '-':
                return leval - reval;
            case '*':
                return leval * reval;
            case '/':
                return leval / reval;
        }

        return 0;
    }
}

public class Tree {
    public static void main(String[] args) {

        String s = "35+92*+";   // postfix expression

        Expression e1 = new Expression();

        Node root = e1.create(s);

        int result = e1.eval(root);

        System.out.println(result);
    }
}
