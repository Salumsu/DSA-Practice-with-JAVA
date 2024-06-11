package LinkedList;

import java.util.EmptyStackException;

public class DoublyLinkedList {
    protected class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node (int value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public Node head;
    public int length = 0;

    private int age = 0;

    public DoublyLinkedList () {
        this.head = null;
    }

    public DoublyLinkedList (int value) {
        this.head = new Node(value);
        this.length = 1;
    }

    public DoublyLinkedList (int[] array) {
        if (array.length > 0) {
            this.length = array.length;
            this.head = new Node(array[0]);
            Node curr = this.head;
            Node prev = curr;

            for (int i = 1; i < array.length; i++) {
                prev = curr;
                curr.next = new Node(array[i]);
                curr = curr.next;
                curr.prev = prev;
            }
        } else {
            this.head = null;
        }
    }

    public void prepend (int value) {
        this.length++;
        Node newNode = new Node(value);
        newNode.next = this.head;
        this.head.prev = newNode;
        this.head = newNode;
    }

    public void append (int value) {
        this.length++;
        Node newNode = new Node(value);
        if (this.head == null) {
            this.head = newNode;
        }

        if (this.head.next == null) {
            this.head.next = newNode;
            newNode.prev = this.head;
        }

        Node curr = this.head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        newNode.prev = curr;
    }

    public void insert (int value, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be lower than 0");
        }
        Node curr = this.head;
        Node newNode = new Node(value);
        if (curr == null) {
            this.head = newNode;
            this.length++;
            return;
        }
        if (index == 0) {
            this.prepend(value);
            return;
        }

        Node prev = curr;
        int currIndex = 0;
        while (currIndex < index && curr != null) {
            prev = curr;
            curr = curr.next;
            currIndex++;
        }
        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = curr;
        if (curr != null) {
            curr.prev = newNode;
        }
        this.length++;
    }

    public int shift () {
        if (this.length == 0) {
            throw new EmptyStackException();
        }
        this.length--;
        Node temp = this.head;
        this.head = this.head.next;
        this.head.prev = null;
        return temp.value;
    }

    public int pop () {
        if (this.length == 0) {
            throw new EmptyStackException();
        }
        if (this.length == 1) {
            Node temp = this.head;
            this.head = null;
        }
        Node curr = this.head;
        Node prev = curr;

        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        this.length--;
        return curr.value;
    }

    public int delete (int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be lower than 0");
        }
        if (index >= this.length) {
            throw new IndexOutOfBoundsException("Index too high");
        }
        if (this.length == 1) {
            Node temp = this.head;
            this.head = null;
            this.length--;
            return temp.value;
        }
        if (index == 0) {
            Node temp = this.head;
            this.head = this.head.next;
            this.head.prev = null;
            this.length--;
            return temp.value;
        }
        Node prev = this.head;
        Node curr = prev;
        int currIndex = 0;
        while (currIndex < index ) {
            prev = curr;
            curr = curr.next;
            currIndex++;
        }
        Node next = curr.next;
        prev.next = next;
        next.prev = prev;
        return curr.value;
    }

    public String toString() {
        if (this.length == 0) {
            return "[]";
        }
        StringBuilder str = new StringBuilder("[");
        Node curr = this.head;
        while (curr.next != null) {
            str.append(curr.value);
            str.append(", ");
            curr = curr.next;
        }
        str.append(curr.value);
        str.append("]");
        return str.toString();
    }

    public String toStringBounce() {
        if (this.length == 0) {
            return "[]";
        }
        StringBuilder str = new StringBuilder("[");
        Node curr = this.head;
        while (curr.next != null) {
            str.append(curr.value);
            str.append(", ");
            curr = curr.next;
        }

        if (curr.prev != null) {
            str.append(curr.value);
            str.append(", ");
            curr = curr.prev;
        }

        while (curr.prev != null) {
            str.append(curr.value);
            str.append(", ");
            curr = curr.prev;
        }
        str.append(curr.value);
        str.append("]");

        return str.toString();
    }
}
