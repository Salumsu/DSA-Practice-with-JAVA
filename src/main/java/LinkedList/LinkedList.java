package LinkedList;

import LinkedList.SinglyLinkedList.SinglyLinkedListNode;
import Trees.BinaryTreeNode;
import org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService;

import java.util.ArrayList;

public abstract class LinkedList<T, Node extends LLNode<T>> {

    protected Node head;
    protected Node tail;
    protected int length;

    public LinkedList () {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public LinkedList (Node head) {
        this.head = head;
        this.tail = head;
        this.length = 1;
    }

    public LinkedList (T[] values) {
        this.head = null;
        if (values.length == 0) {
            this.length = 0;
            return;
        }

        for (T value : values) {
            this.addLast(value);
        }
    }

    protected abstract Node newNode(T value);

    protected abstract Node castNode(LLNode<T> node);

    public int size() {
        return this.length;
    }

    public boolean isEmpty() {return this.head == null;}

    public void addFirst(T value) {
        Node newNode = this.newNode(value);
        newNode.setNext(this.head);

        if (this.head == null) {
            this.tail = newNode;
        }
        this.head = newNode;
        this.length++;
    }

    public void addLast(T value) {
        Node newNode = this.newNode(value);
        if (this.tail != null) {
            this.tail.setNext(newNode);
        } else {
            this.head = newNode;
        }
        this.tail = newNode;
        this.length++;
    }

    public T removeFirst() {
        if (this.head == null) return null;
        T value = this.head.getValue();

        this.head = castNode(this.head.getNext());
        if (this.head == null) {
            this.tail = null;
        }
        this.length--;

        return value;
    }

    public abstract T removeLast();

    public T remove(int index) {
        if (index < 0 || index >= this.size() || this.head == null) return null;
        if (index == 0) return removeFirst();
        if (index == this.size() - 1) return removeLast();

        Node curr = this.head;
        Node prev = null;

        int currentIndex = 0;
        while (currentIndex < index && curr != null) {
            currentIndex++;
            prev = curr;
            curr = castNode(curr.getNext());
        }

        if (curr == null) return null;

        prev.setNext(curr.getNext());

        this.length--;

        return curr.getValue();
    }

    public T getHeadValue() {
        if (this.head == null) return null;
        return this.head.getValue();
    }

    public T getTailValue() {
        if (this.tail == null) return null;
        return this.tail.getValue();
    }

    public ArrayList<T> toArrayList () {
        ArrayList<T> arrayList = new ArrayList<>();

        Node curr = this.head;

        while (curr != null) {
            arrayList.add(curr.getValue());
            curr = castNode(curr.getNext());
        }

        return arrayList;
    }

    public void merge (LinkedList<T, Node> other) {
        this.length += other.size();
        if (this.tail == null) {
            this.head = other.head;
        } else {
            this.tail.setNext(other.head);
            if (other.tail != null) {
                this.tail = other.tail;
            }
        }
    }
}
