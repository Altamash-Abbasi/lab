import java.util.*;

public class Convex {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Checks if angle p-q-r is <= 180 (not a left turn)
        boolean isLE180(Point q, Point r) {
            long cross = crossProduct(this, q, r);
            return cross <= 0;
        }

        static long crossProduct(Point p, Point q, Point r) {
            // (q - p) x (r - q)
            long x1 = q.x - p.x;
            long y1 = q.y - p.y;
            long x2 = r.x - q.x;
            long y2 = r.y - q.y;
            return x1 * y2 - y1 * x2;
        }

        static long dist2(Point a, Point b) {
            long dx = a.x - b.x;
            long dy = a.y - b.y;
            return dx * dx + dy * dy;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }


    static class Node {
        Point data;
        Node next, prev;

        Node(Point data) {
            this.data = data;
        }
    }

    static class DCLL {
        private Node head = null;
        private int size = 0;

        void create(Point[] a, int n) {
            for (int i = 0; i < n; i++) {
                insertEnd(a[i]);
            }
        }

        private void insertEnd(Point p) {
            Node node = new Node(p);

            if (head ==null ) {
                head = node;
                head.next = head;
                head.prev = head;
            } else {
                Node tail = head.prev;
                tail.next = node;
                node.prev = tail;
                node.next = head;
                head.prev = node;
            }
            size++;
        }

        Node getFirst() {
            return head;
        }

        void del(Node x) {
            if (x == null || head == null || size == 0) return;

            if (size == 1) {
                head = null;
                size = 0;
                return;
            }

            x.prev.next = x.next;
            x.next.prev = x.prev;

            if (x == head) {
                head = x.next;
            }

            size--;
        }

        void display() {
            if (head == null) {
                System.out.println("Empty Hull");
                return;
            }

            Node cur = head;
            do {
                System.out.println(cur.data);
                cur = cur.next;
            } while (cur != head);
        }
    }

    //Sorting for this Algorithm
    static void sortPointsByAngle(Point[] a) {

        // Find pivot: lowest y, if tie lowest x
        int pivotIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i].y < a[pivotIndex].y ||
                    (a[i].y == a[pivotIndex].y && a[i].x < a[pivotIndex].x)) {
                pivotIndex = i;
            }
        }

        // Swap pivot into index 0
        Point temp = a[0];
        a[0] = a[pivotIndex];
        a[pivotIndex] = temp;

        Point pivot = a[0];

        // Sort remaining points by polar angle around pivot
        Arrays.sort(a, 1, a.length, (p1, p2) -> {
            long cross = Point.crossProduct(pivot, p1, p2);

            if (cross > 0) return -1;
            if (cross < 0) return 1;

            // Collinear: closer first
            return Long.compare(Point.dist2(pivot, p1), Point.dist2(pivot, p2));
        });
    }

    //Convex Hull Algorithm 
    static void getConvexHull(Point[] a, int n) {

        DCLL dcll = new DCLL();
        dcll.create(a, n);

        Node x = dcll.getFirst();

        if (x == null || n < 3) {
            System.out.println("Convex hull not possible (need at least 3 points).");
            dcll.display();
            return;
        }

        Node x0 = x;
        Node xr = x.next;
        Node xrr = xr.next;

        while (xrr != x0 || xr != xrr) {
            xrr = xr.next;

            if (x.data.isLE180(xr.data, xrr.data)) {
                dcll.del(xr);
                xr = x;
                x = x.prev;
            } else {
                x = xr;
                xr = xrr;
            }
        }

        System.out.println("\nVertices of Convex Hull:");
        dcll.display();
    }

    public static void main(String[] args) {

        Point[] points = {
                new Point(0, 0),
                new Point(5, 0),
                new Point(10, 0),
                new Point(10, 5),
                new Point(5, 5),
                new Point(10, 10),
                new Point(5, 10),
                new Point(0, 10),
                new Point(0, 5)
        };
        
    
        sortPointsByAngle(points);

        getConvexHull(points, points.length);
    }
}
