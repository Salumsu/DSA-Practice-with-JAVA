package LinkedList;

import java.util.EmptyStackException;

public class BasicLinkedList {
    protected static class Node {
        protected int value;
        protected Node next;

        protected Node (int value) {
            this.value = value;
            this.next = null;
        }
    }

    protected Node head;
    public int length = 0;

    public BasicLinkedList () {
        this.head = null;
    }

    public BasicLinkedList (int value) {
        this.head = new Node(value);
        this.length = 1;
    }

    public BasicLinkedList (int[] array) {
        this.length = array.length;
        if (array.length > 0) {
            this.head = new Node(array[0]);
            Node curr = this.head;

            for (int i = 1; i < array.length; i++) {
                curr.next = new Node(array[i]);
                curr = curr.next;
            }
        } else {
            this.head = null;
        }
    }

    public void prepend (int value) {
        this.length++;
        Node newNode = new Node(value);
        newNode.next = this.head;
        this.head = newNode;
    }

    public void append (int value) {
        this.length++;
        Node curr = this.head;
        if (curr == null) {
            this.head = new Node(value);
            return;
        }

        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node(value);
    }

    public void insert (int value, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be lower than 0");
        }
        this.length++;
        Node newNode = new Node(value);
        Node curr = this.head;
        int currIndex = 0;
        while (currIndex < index - 1 && curr.next != null) {
            curr = curr.next;
            currIndex++;
        }
        newNode.next = curr.next;
        curr.next = newNode;
    }

    public int shift () {
        this.length--;
        Node prevHead = this.head;
        this.head = this.head.next;

        return prevHead.value;
    }

    public int pop() {
        if (this.length == 0) {
            throw new EmptyStackException();
        }
        if (this.length == 1) {
            int val = this.head.value;
            this.head = null;
            return val;
        }

        Node curr = this.head;
        Node prev = curr;

        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        this.length--;
        prev.next = null;
        return curr.value;
    }

    public int delete(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index can't be lower than 0");
        }
        if (index >= this.length) {
            throw new IndexOutOfBoundsException("Index too high!");
        }
        this.length--;
        if (this.length == 1) {
            int val = this.head.value;
            this.head = null;
            return val;
        }

        Node curr = this.head;
        Node prev = curr;
        int currIndex = 0;

        while (currIndex < index) {
            prev = curr;
            curr = curr.next;
            currIndex++;
        }
        prev.next = curr.next;
        return curr.value;
    }

    public void clear () {
        this.head = null;
        this.length = 0;
    }

    public Integer findNthValueFromEndWithLength (int n) {
        if (n >= this.length) {
            throw new IndexOutOfBoundsException("Fewer nodes found");
        }
        int index = this.length - 1 - n;
            int currIndex = 0;
        Node curr = this.head;
        while (currIndex  < index) {
            curr = curr.next;
            currIndex++;
        }
        return curr.value;
    }

    public Integer findNthValueFromEnd (int n, int baseIndex) {
        if (baseIndex == 0) {
            return this.findNthValueFromEnd(n);
        } else {
            return this.findNthValueFromEnd(n - baseIndex);
        }
    }

    public Integer findNthValueFromEnd (int n) {
        Node curr = this.head;
        if (curr == null) {
            throw new IndexOutOfBoundsException("Fewer nodes found");
        }
        int count = 0;
        while (count < n && curr.next != null) {
            curr = curr.next;
            count++;
        }
        if (count < n) {
            throw new IndexOutOfBoundsException("Fewer nodes found");
        }
        Node nthNode = this.head;
        while (curr.next != null) {
            nthNode = nthNode.next;
            curr = curr.next;
        }

        return nthNode.value;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("[");
        Node curr = this.head;
        while (curr.next != null) {
            str.append(String.valueOf(curr.value));
            str.append(", ");
            curr = curr.next;
        }
        str.append(String.valueOf(curr.value));

        str.append("]");
        return str.toString();
    }

}
