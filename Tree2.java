import java.util.*;

class Node {
    String x;
    Node left;
    Node right;

    public Node() {
        left = right = null;
    }

    Node(String x) {
        this.x = x;
        left = right = null;
    }
}


class Atree {

    int h;
    char[] arr;
    Scanner sc = new Scanner(System.in);

    Atree() {
        System.out.println("Enter height of tree:");
        h = sc.nextInt();

        arr = new char[(int) Math.pow(2, h) - 1];

        for (int i = 0; i < arr.length; i++)
            arr[i] = '\0';
    }

    void create(int i) {

        if (i == 0)
            System.out.print("Enter root node: ");
        else if (i % 2 == 1)
            System.out.print("Enter left child of " + arr[(i - 1) / 2] + ": ");
        else
            System.out.print("Enter right child of " + arr[(i - 1) / 2] + ": ");

        arr[i] = sc.next().charAt(0);

        System.out.print("Does " + arr[i] + " have left child (Y/N)? ");
        String ch = sc.next();

        if ((ch.equals("Y") || ch.equals("y")) && (2 * (i + 1) - 1 < arr.length))
            create(2 * (i + 1) - 1);

        System.out.print("Does " + arr[i] + " have right child (Y/N)? ");
        ch = sc.next();

        if ((ch.equals("Y") || ch.equals("y")) && (2 * (i + 1) < arr.length))
            create(2 * (i + 1));
    }

    boolean isEmpty() {
        return arr[0] == '\0';
    }

    int size() {
        int s = 0;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] != '\0')
                s++;
        return s;
    }

    int search(char target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target)
                return i;
        return -1;
    }

    void preorder(int i) {

        if (i >= arr.length || arr[i] == '\0')
            return;

        System.out.print(arr[i] + " ");

        preorder(2 * (i + 1) - 1);
        preorder(2 * (i + 1));
    }

    void inorder(int i) {

        if (i >= arr.length || arr[i] == '\0')
            return;

        inorder(2 * (i + 1) - 1);
        System.out.print(arr[i] + " ");
        inorder(2 * (i + 1));
    }

    void postorder(int i) {

        if (i >= arr.length || arr[i] == '\0')
            return;

        postorder(2 * (i + 1) - 1);
        postorder(2 * (i + 1));
        System.out.print(arr[i] + " ");
    }

    void levelorder() {

        if (arr[0] == '\0')
            return;

        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while (!q.isEmpty()) {

            int i = q.poll();
            System.out.print(arr[i] + " ");

            int l = 2 * (i + 1) - 1;
            int r = 2 * (i + 1);

            if (l < arr.length && arr[l] != '\0')
                q.add(l);

            if (r < arr.length && arr[r] != '\0')
                q.add(r);
        }
    }
}

class Ltree{
    Node root;
    Scanner sc=new Scanner(System.in);

    Node build(Node r, Node p){
    if(r==null){
        r=new Node();
        System.out.print("Enter the value of root node: ");
    }
    else if(r==p.left){
        System.out.print("Enter the left child of "+p.x+" ");
    }
    else{
        System.out.print("Enter the right child of "+p.x+" ");
    }

    r.x=sc.next();

    System.out.print("Does "+r.x+" have left child (Y/N)? ");
    char ch=sc.next().charAt(0);
    if(ch=='y' || ch=='Y'){
        r.left=build(new Node(), r);
    }

    System.out.print("Does "+r.x+" have right child (Y/N)? ");
    char ch2=sc.next().charAt(0);
    if(ch2=='Y' || ch2=='y'){
        r.right=build(new Node(), r);
    }

    return r;   
}
    
    boolean isEmpty(){
        if(root==null) return true;
        else return false;
    }

     int size(Node r) {
        if (r == null)
            return 0;
        return 1 + size(r.left) + size(r.right);
    }

    int getSize() {
        return size(root);
    }

    boolean search(Node r, String key) {
        if (r == null)
            return false;

        if (r.x.equals(key))
            return true;

        return search(r.left, key) || search(r.right, key);
    }

    void preorder(Node r) {
        if (r == null)
            return;

        System.out.print(r.x + " ");
        preorder(r.left);
        preorder(r.right);
    }

    void inorder(Node r) {
        if (r == null)
            return;

        inorder(r.left);
        System.out.print(r.x + " ");
        inorder(r.right);
    }

    void postorder(Node r) {
        if (r == null)
            return;

        postorder(r.left);
        postorder(r.right);
        System.out.print(r.x + " ");
    }

    void levelorder() {
        if (root == null)
            return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            Node temp = q.poll();
            System.out.print(temp.x + " ");

            if (temp.left != null)
                q.add(temp.left);

            if (temp.right != null)
                q.add(temp.right);
        }
    }

}

class Expression {
    Stack<Node> st;

    Node create(String s) {
        st = new Stack<>();
        String[] tokens=s.split(" ");

        for(String token:tokens){
           if(Character.isDigit(token.charAt(0))){
            st.push(new Node(token));
           }
           else{
            Node n=new Node(token);

            Node r=st.pop();
            Node l=st.pop();

            n.right=r;
            n.left=l;

            st.push(n);
           }
        }
        return st.pop();
    }

    int eval(Node root) {

        if (root.left == null && root.right == null)
            return Integer.parseInt(root.x);

        int leval = eval(root.left);
        int reval = eval(root.right);

        switch (root.x) {
            case "+":
                return leval + reval;
            case "-":
                return leval - reval;
            case "*":
                return leval * reval;
            case "/":
                return leval / reval;
        }

        return 0;
    }
}

public class Tree {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Atree at = null;
        Ltree lt = new Ltree();

        int choice, subchoice;

        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Array Tree");
            System.out.println("2. Linked Tree");
            System.out.println("3. Expression Tree");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                // ================= ARRAY TREE =================
                case 1:
                    do {
                        System.out.println("\n--- ARRAY TREE MENU ---");
                        System.out.println("1. Create Tree");
                        System.out.println("2. Traversals");
                        System.out.println("3. Size");
                        System.out.println("4. Search");
                        System.out.println("0. Back");
                        System.out.print("Enter choice: ");
                        subchoice = sc.nextInt();

                        switch (subchoice) {
                            case 1:
                                at = new Atree();
                                at.create(0);
                                break;

                            case 2:
                                if (at == null || at.isEmpty()) {
                                    System.out.println("Tree is empty!");
                                    break;
                                }
                                System.out.print("Preorder: ");
                                at.preorder(0);
                                System.out.print("\nInorder: ");
                                at.inorder(0);
                                System.out.print("\nPostorder: ");
                                at.postorder(0);
                                System.out.print("\nLevelorder: ");
                                at.levelorder();
                                System.out.println();
                                break;

                            case 3:
                                if (at == null)
                                    System.out.println("Create tree first!");
                                else
                                    System.out.println("Size: " + at.size());
                                break;

                            case 4:
                                if (at == null) {
                                    System.out.println("Create tree first!");
                                    break;
                                }
                                System.out.print("Enter element: ");
                                char ch = sc.next().charAt(0);
                                int pos = at.search(ch);
                                if (pos != -1)
                                    System.out.println("Found at index: " + pos);
                                else
                                    System.out.println("Not found");
                                break;
                        }
                    } while (subchoice != 0);
                    break;


                // ================= LINKED TREE =================
                case 2:
                    do {
                        System.out.println("\n--- LINKED TREE MENU ---");
                        System.out.println("1. Create Tree");
                        System.out.println("2. Traversals");
                        System.out.println("3. Size");
                        System.out.println("4. Search");
                        System.out.println("0. Back");
                        System.out.print("Enter choice: ");
                        subchoice = sc.nextInt();

                        switch (subchoice) {
                            case 1:
                                lt.root = lt.build(null, null);
                                break;

                            case 2:
                                if (lt.isEmpty()) {
                                    System.out.println("Tree is empty!");
                                    break;
                                }
                                System.out.print("Preorder: ");
                                lt.preorder(lt.root);
                                System.out.print("\nInorder: ");
                                lt.inorder(lt.root);
                                System.out.print("\nPostorder: ");
                                lt.postorder(lt.root);
                                System.out.print("\nLevelorder: ");
                                lt.levelorder();
                                System.out.println();
                                break;

                            case 3:
                                System.out.println("Size: " + lt.getSize());
                                break;

                            case 4:
                                System.out.print("Enter element: ");
                                String key = sc.next();
                                if (lt.search(lt.root, key))
                                    System.out.println("Found");
                                else
                                    System.out.println("Not found");
                                break;
                        }
                    } while (subchoice != 0);
                    break;


                // ================= EXPRESSION TREE =================
                case 3:
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter postfix expression: ");
                    String s = sc.nextLine();

                    Expression e = new Expression();
                    Node root = e.create(s);

                    int result = e.eval(root);
                    System.out.println("Result: " + result);
                    break;

            }

        } while (choice != 0);

        System.out.println("Program Ended.");
    }
}
