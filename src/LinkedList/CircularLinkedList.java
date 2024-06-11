package LinkedList;

import java.util.EmptyStackException;

public class CircularLinkedList {
    protected class Node {
        public int value;
        public Node next;

        public Node (int value) {
            this.value = value;
            this.next = null;
        }

        public Node (int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node head;
    public int length = 0;

    public CircularLinkedList () {
        this.head = null;
        this.length = 0;
    }

    public CircularLinkedList (int value) {
        this.head = new Node(value);
        this.head.next = head;
        this.length = 1;
    }

    public CircularLinkedList (int[] array) {
        if (array.length > 0) {
            this.length = array.length;
            this.head = new Node(array[0]);
            Node curr = this.head;
            curr.next = head;

            for (int i = 1; i < array.length; i++) {
                Node newNode = new Node(array[i], this.head);
                curr.next = newNode;
                curr = newNode;
            }

        } else {
            this.head = null;
            this.length = 0;
        }
    }

    private Node getLastNode () {
        Node curr = this.head;
        while (curr.next != this.head) {
            curr = curr.next;
        }

        return curr;
    }

    public void prepend (int value) {
        Node curr = this.getLastNode();
        Node newNode = new Node(value, this.head);
        curr.next = newNode;
        this.head = newNode;
        this.length++;
    }

    public void append (int value) {
        Node curr = this.getLastNode();
        Node newNode = new Node(value, this.head);
        curr.next = newNode;
        this.length++;
    }

    public int shift () {
        if (this.length == 0) {
            throw new EmptyStackException();
        }
        Node temp = this.head;
        if (this.head.next == this.head) {
            this.head = null;
        } else {
            Node curr = this.getLastNode();
            this.head = this.head.next;
            curr.next = this.head;
        }

        this.length--;
        return temp.value;
    }

    public int pop () {
        if (this.length == 0) {
            throw new EmptyStackException();
        }
        Node curr = this.head;
        if (this.head.next == this.head) {
            this.head = null;
        } else {
            Node prev = curr;
            while (curr.next != this.head) {
                prev = curr;
                curr = curr.next;
            }
            prev.next = this.head;
        }
        this.length--;
        return curr.value;
    }

    public String toString() {
        if (this.length == 0) {
            return "[]";
        }
        StringBuilder str = new StringBuilder("[");
        Node curr = this.head;
        if (curr != null) {
            str.append(curr.value);
        }

        while (curr.next != this.head) {
            curr = curr.next;
            str.append(", ");
            str.append(curr.value);
        }

        str.append("]");

        return str.toString();
    }
}
