import java.util.*;

class CircularQueue<T> {

    private Object[] arr;
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        arr = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void enqueue(T data) {
        if (isFull()) {
            throw new RuntimeException("Queue is Full");
        }
        rear = (rear + 1) % capacity;
        arr[rear] = data;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        T data = (T) arr[front];
        front = (front + 1) % capacity;
        size--;
        return data;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return (T) arr[front];
    }

    public int size() {
        return size;
    }
}


public class QueueApplications {


    public static boolean railroadRearrangement(int[] input) {

        int n = input.length;
        CircularQueue<Integer> holdingTrack = new CircularQueue<>(n);
        int expected = 1;
        int i = 0;

        while (i < n) {

            if (input[i] == expected) {
                System.out.println("Move car " + input[i] + " to output");
                expected++;
                i++;
            } else {
                holdingTrack.enqueue(input[i]);
                System.out.println("Move car " + input[i] + " to holding track");
                i++;
            }

            while (!holdingTrack.isEmpty() &&
                    holdingTrack.peek() == expected) {

                System.out.println("Move car " + holdingTrack.dequeue() + " from holding to output");
                expected++;
            }
        }

        return expected == n + 1;
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void labelComponents(int[][] image) {

        int rows = image.length;
        int cols = image[0].length;
        int label = 2;  // start labeling from 2

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (image[i][j] == 1) {

                    CircularQueue<Point> queue =
                            new CircularQueue<>(rows * cols);

                    queue.enqueue(new Point(i, j));
                    image[i][j] = label;

                    while (!queue.isEmpty()) {
                        Point p = queue.dequeue();

                        for (int k = 0; k < 4; k++) {
                            int nx = p.x + dx[k];
                            int ny = p.y + dy[k];

                            if (nx >= 0 && ny >= 0 &&
                                    nx < rows && ny < cols &&
                                    image[nx][ny] == 1) {

                                image[nx][ny] = label;
                                queue.enqueue(new Point(nx, ny));
                            }
                        }
                    }

                    label++;
                }
            }
        }

        System.out.println("Labeled Image:");
        for (int[] row : image) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== QUEUE APPLICATIONS MENU =====");
            System.out.println("1. Railroad Car Rearrangement");
            System.out.println("2. Image Component Labeling");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    int[] input = {5, 8, 1, 7, 4, 2, 9, 6, 3};
                    boolean possible = railroadRearrangement(input);

                    if (possible)
                        System.out.println("Rearrangement Possible");
                    else
                        System.out.println("Rearrangement Not Possible");
                    break;

                case 2:
                    int[][] image = {
                            {1, 1, 0, 0},
                            {1, 0, 0, 1},
                            {0, 0, 1, 1},
                            {0, 1, 1, 0}
                    };
                    labelComponents(image);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
