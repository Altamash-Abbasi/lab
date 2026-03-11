public class Linked {
    public static void main(String[] args){
        Node head = new Node(15);

        head = head.insert(30, 1, head);
        head = head.insert(0, 0, head);
        head = head.insert(20, 1, head);
        head=head.insert(33, 2, head);
        head=head.insert(32, 3, head);
        head=head.insert(36, 4, head);
        head=head.insert(31, 5, head);
        head=head.insert(23, 6, head);
        head=head.insert(34, 7, head);
        head=head.insert(38, 8, head);


        System.out.println(head.size(head));
        System.out.println(head.isempty(head));
        head.display(head);
        
        Node temp4=head;
        while(temp4!=null){
            if(temp4.data%2!=0 && )
        }

    }
}


class Node{
    int data;
    Node next;

    public Node() {
    }

    
    Node(int data){
        this.data = data;
    }

    Node insert(int data, int index, Node head){
        Node n = new Node(data);

        if(index == 0){
            n.next = head;
            return n;
        }

        Node current = head;
        for(int i = 0; i < index - 1; i++){
            current = current.next;
        }

        n.next = current.next;
        current.next = n;
        return head;
    }

    int size(Node head){
        int count = 0;
        Node current = head;

        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    boolean isempty(Node head){
        return head == null;
    }

    void display(Node head){
        Node current = head;
        while(current != null){
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    Node delete(int index, Node head){
        if(head == null) return null;

        if(index == 0){
            return head.next;
        }

        Node current = head;
        for(int i = 0; i < index - 1; i++){
            current = current.next;
        }

        current.next = current.next.next;
        return head;
    }
}
