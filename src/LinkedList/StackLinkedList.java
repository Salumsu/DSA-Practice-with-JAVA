package LinkedList;

import java.util.EmptyStackException;

public class StackLinkedList {
    private class Node {
        protected int value;
        protected Node next;

        protected Node (int value) {
            this.value = value;
            this.next = null;
        }
    }

    protected Node head;
    public int length;

    public StackLinkedList () {
        this.head = null;
    }

    public StackLinkedList (int value) {
        this.head = new Node(value);
        this.length = 1;
    }

    public StackLinkedList (int[] array) {
        if (array.length > 0) {
            this.head = new Node(array[0]);
            Node curr = this.head;

            for (int i = 1; i < array.length; i++) {
                Node newNode = new Node(array[i]);
                curr.next = newNode;
                curr = newNode;
            }
            this.length = array.length;
        } else {
            this.head = null;
            this.length = 0;
        }
    }

    public void push (int value) {
        Node newNode = new Node(value);
        newNode.next = this.head;

        this.head = newNode;
        this.length++;
    }

    public int pop () {
        if (!this.isEmpty()) {
            Node temp = this.head;
            this.head = temp.next;
            this.length--;
            return temp.value;
        }

        throw new EmptyStackException();

    }

    public boolean isEmpty () {
        return this.length == 0;
    }

    public String toString () {
        if (this.length == 0) {
            return "[]";
        }
        StringBuilder str = new StringBuilder("[");
        str.append(this.head.value);
        Node curr = this.head.next;
        while (curr != null) {
            str.append(", ");
            str.append(curr.value);
            curr = curr.next;
        }
        str.append("]");
        return str.toString();
    }
}
