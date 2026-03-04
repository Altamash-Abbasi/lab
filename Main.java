public class Main {
    static class MyStack<T> {
        private T[] arr;
        private int top;
        private int capacity;
      
        MyStack(int size) {
            capacity = size;
            arr = (T[]) new Object[size];
            top = -1;
        }

        void push(T item) {
            if (top == capacity - 1) return;
            arr[++top] = item;
        }

        T pop() {
            if (top == -1) return null;
            return arr[top--];
        }

        boolean isEmpty() {
            return top == -1;
        }
    }

    static class MyQueue<T> {
        private T[] arr;
        private int front, rear, size, capacity;
      
        MyQueue(int capacity) {
            this.capacity = capacity;
            arr = (T[]) new Object[capacity];
            front = 0;
            rear = -1;
            size = 0;
        }

        void enqueue(T item) {
            if (size == capacity) return;
            rear = (rear + 1) % capacity;
            arr[rear] = item;
            size++;
        }

        T dequeue() {
            if (size == 0) return null;
            T item = arr[front];
            front = (front + 1) % capacity;
            size--;
            return item;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }

    static void printNumbers(MyStack<? extends Number> s) {
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }

    static void addNumbers(MyQueue<? super Integer> q) {
        q.enqueue(10);
        q.enqueue(20);
    }

    public static void main(String[] args) {
        MyStack<Integer> s = new MyStack<>(5);
        s.push(1);
        s.push(2);
        printNumbers(s);

        MyQueue<Number> q = new MyQueue<>(5);
        addNumbers(q);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}
