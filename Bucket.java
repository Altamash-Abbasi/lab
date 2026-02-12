public class Bucket {
    public static void main(String[] args) {

        Node head = new Node(3);
        head = Node.insertAtEnd(head, 2);
        head = Node.insertAtEnd(head, 2);
        head = Node.insertAtEnd(head, 7);

        int max = max(head);
        System.out.println("Max: " + max);

        Bucket bc = new Bucket();
        Node sorted = bc.sort(head);

        Node.display(sorted);
    }

    Node sort(Node head) {

        if (head == null) return null;

        int max = max(head);

        Node[] bucket = new Node[max + 1];

        Node temp = head;

        // Step 1: Distribute elements into buckets
        while (temp != null) {
            int value = temp.data;

            // Insert at beginning of bucket
            Node newNode = new Node(value);
            newNode.next = bucket[value];
            bucket[value] = newNode;

            temp = temp.next;
        }

        // Step 2: Collect from buckets
        Node sortedStart = null;
        Node sortedTail = null;

        for (int i = 0; i <= max; i++) {

            Node current = bucket[i];

            while (current != null) {

                Node nextNode = current.next;
                current.next = null;

                if (sortedStart == null) {
                    sortedStart = current;
                    sortedTail = current;
                } else {
                    sortedTail.next = current;
                    sortedTail = current;
                }

                current = nextNode;
            }
        }

        return sortedStart;
    }

    static int max(Node head) {
        Node temp = head;
        int max = head.data;

        while (temp != null) {
            if (temp.data > max)
                max = temp.data;
            temp = temp.next;
        }
        return max;
    }

    Node radixSort(Node head) {

    if (head == null) return null;

    int max = max(head);

    // Find number of digits
    int exp = 1;   // 1, 10, 100, 1000...

    while (max / exp > 0) {

        Node[] bucket = new Node[10];
        Node[] bucketTail = new Node[10];

        Node temp = head;

        // Step 1: Distribute nodes into buckets
        while (temp != null) {

            int digit = (temp.data / exp) % 10;

            Node nextNode = temp.next;
            temp.next = null;

            if (bucket[digit] == null) {
                bucket[digit] = temp;
                bucketTail[digit] = temp;
            } else {
                bucketTail[digit].next = temp;
                bucketTail[digit] = temp;
            }

            temp = nextNode;
        }

        // Step 2: Collect buckets
        head = null;
        Node tail = null;

        for (int i = 0; i < 10; i++) {

            if (bucket[i] != null) {

                if (head == null) {
                    head = bucket[i];
                    tail = bucketTail[i];
                } else {
                    tail.next = bucket[i];
                    tail = bucketTail[i];
                }
            }
        }

        exp *= 10;
    }

    return head;
}

}


class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }

    static Node insertAtEnd(Node head, int data) {
        Node newNode = new Node(data);

        if (head == null)
            return newNode;

        Node temp = head;
        while (temp.next != null)
            temp = temp.next;

        temp.next = newNode;
        return head;
    }

    static void display(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }
}
