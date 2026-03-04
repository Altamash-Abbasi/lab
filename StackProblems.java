import java.util.*;

public class StackProblems {

    static class Frame {
        int n;
        char from, to, aux;

        Frame(int n, char from, char to, char aux) {
            this.n = n;
            this.from = from;
            this.to = to;
            this.aux = aux;
        }
    }

    public static void towerOfHanoi(int n, char from, char to, char aux) {
        Stack<Frame> stack = new Stack<>();
        stack.push(new Frame(n, from, to, aux));

        while (!stack.isEmpty()) {
            Frame current = stack.pop();

            if (current.n == 1) {
                System.out.println("Move disk 1 from " +
                        current.from + " to " + current.to);
            } else {
                stack.push(new Frame(current.n - 1,
                        current.aux, current.to, current.from));

                stack.push(new Frame(1,
                        current.from, current.to, current.aux));

                stack.push(new Frame(current.n - 1,
                        current.from, current.aux, current.to));
            }
        }
    }

    static class Cell {
        int x, y;
        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static boolean solveMaze(int[][] maze) {
        int n = maze.length;
        Stack<Cell> stack = new Stack<>();
        boolean[][] visited = new boolean[n][n];

        stack.push(new Cell(0, 0));

        int[] dx = {1, -1, 0, 0};  // Down, Up
        int[] dy = {0, 0, 1, -1};  // Right, Left

        while (!stack.isEmpty()) {
            Cell current = stack.pop();
            int x = current.x;
            int y = current.y;

            if (x == n - 1 && y == n - 1) {
                System.out.println("Path Found!");
                return true;
            }

            if (x >= 0 && y >= 0 && x < n && y < n &&
                    maze[x][y] == 1 && !visited[x][y]) {

                visited[x][y] = true;

                for (int i = 0; i < 4; i++) {
                    stack.push(new Cell(x + dx[i], y + dy[i]));
                }
            }
        }

        return false;
    }

    public static int factorial(int n) {
        Stack<Integer> stack = new Stack<>();
        for (int i = n; i > 1; i--)
            stack.push(i);

        int result = 1;
        while (!stack.isEmpty())
            result *= stack.pop();

        return result;
    }

    public static int fib(int n) {
        Stack<Integer> stack = new Stack<>();
        stack.push(n);

        int result = 0;

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (current <= 1) {
                result += current;
            } else {
                stack.push(current - 1);
                stack.push(current - 2);
            }
        }

        return result;
    }

    public static int gcd(int a, int b) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{a, b});

        while (!stack.isEmpty()) {
            int[] pair = stack.pop();
            int x = pair[0];
            int y = pair[1];

            if (y == 0)
                return x;

            stack.push(new int[]{y, x % y});
        }

        return -1;
    }

    public static void decimalToBinary(int n) {
        Stack<Integer> stack = new Stack<>();

        while (n > 0) {
            stack.push(n % 2);
            n /= 2;
        }

        while (!stack.isEmpty())
            System.out.print(stack.pop());

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\nSTACK PROBLEMS MENU");
            System.out.println("1. Tower of Hanoi");
            System.out.println("2. Rat in Maze (4 Directions)");
            System.out.println("3. Factorial");
            System.out.println("4. Fibonacci");
            System.out.println("5. GCD");
            System.out.println("6. Decimal to Binary");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number of disks: ");
                    int n = sc.nextInt();
                    towerOfHanoi(n, 'A', 'C', 'B');
                    break;

                case 2:
                    int[][] maze = {
                            {1, 0, 0, 0},
                            {1, 1, 0, 1},
                            {0, 1, 0, 0},
                            {1, 1, 1, 1}
                    };
                    boolean result = solveMaze(maze);
                    if (!result)
                        System.out.println("No Path Found!");
                    break;

                case 3:
                    System.out.print("Enter n: ");
                    int f = sc.nextInt();
                    System.out.println("Factorial: " + factorial(f));
                    break;

                case 4:
                    System.out.print("Enter n: ");
                    int fib = sc.nextInt();
                    System.out.println("Fibonacci: " + fib(fib));
                    break;

                case 5:
                    System.out.print("Enter first number: ");
                    int a = sc.nextInt();
                    System.out.print("Enter second number: ");
                    int b = sc.nextInt();
                    System.out.println("GCD: " + gcd(a, b));
                    break;

                case 6:
                    System.out.print("Enter decimal number: ");
                    int d = sc.nextInt();
                    System.out.print("Binary: ");
                    decimalToBinary(d);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}
