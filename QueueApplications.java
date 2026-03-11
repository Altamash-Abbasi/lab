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
        @SuppressWarnings("unchecked")
    public T getRear() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return (T) arr[rear];
    }
}


public class QueueApplications {


    public static boolean railroadRearrangement(int[] input) {

        int n = input.length;
        int k = 4; // number of holding tracks

        CircularQueue<Integer>[] track = new CircularQueue[k];
        for (int i = 0; i < k; i++) {
            track[i] = new CircularQueue<>(n);
        }

        int nextCarToOutput = 1;

        for (int i = 0; i < n; i++) {

            if (input[i] == nextCarToOutput) {

                System.out.println("Move car " + input[i] + " from input track to output track");
                nextCarToOutput++;

                boolean moved;

                do {
                    moved = false;

                    for (int j = 0; j < k; j++) {

                        if (!track[j].isEmpty() &&
                                track[j].peek() == nextCarToOutput) {

                            System.out.println("Move car " + track[j].dequeue() +
                                    " from holding track " + (j + 1) + " to output track");

                            nextCarToOutput++;
                            moved = true;
                            j = -1; // restart checking tracks
                        }
                    }

                } while (moved);

            } else {

                int c = input[i];
                int bestTrack = -1;
                int bestLast = 0;

                for (int j = 0; j < k; j++) {

                    if (!track[j].isEmpty()) {

                        int lastCar = track[j].getRear();

                        if (c < lastCar && lastCar > bestLast) {
                            bestLast = lastCar;
                            bestTrack = j;
                        }

                    } else if (bestTrack == -1) {
                        bestTrack = j;
                    }
                }

                if (bestTrack == -1) {
                    return false;
                }

                track[bestTrack].enqueue(c);

                System.out.println("Move car " + c +
                        " from input track to holding track " + (bestTrack + 1));
            }
        }

        return true;
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
