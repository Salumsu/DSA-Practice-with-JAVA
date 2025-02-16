package LinkedList.DoublyLinkedList;

import LinkedList.LinkedListInterface;
import LinkedList.SinglyLinkedList.SinglyLinkedList;
import LinkedList.SinglyLinkedList.SinglyLinkedListNode;
import utils.CompareTo;

import java.util.Comparator;

public class DoublyLinkedList<T> implements LinkedListInterface<T> {

    DoublyLinkedListNode<T> head;
    DoublyLinkedListNode<T> tail;

    private int length;

    public DoublyLinkedList () {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public DoublyLinkedList (T value) {
        this.head = new DoublyLinkedListNode<T>(value);
        this.tail = this.head;
        this.length = 1;
    }

    public DoublyLinkedList (T[] values) {
        this.head = null;
        if (values.length == 0) {
            this.length = 0;
            return;
        }

        /*
            Other way of setting the length is by using:
                values.size()
            But I'm not sure if its an O(1) function, it might loop through
            the list and count them, in that case, it would add another O(n) size
            to this operation.

            But incrementing the length each time would also add another O(n) size right?
            I am not sure :)

            PS: I had no internet when I wrote this.
         */

        DoublyLinkedListNode<T> curr = new DoublyLinkedListNode<T>(values[0]);
        this.head = curr;
        this.length++;

        for (int i = 1; i < values.length; i++) {
            T value = values[i];
            DoublyLinkedListNode<T> node = new DoublyLinkedListNode<T>(value);
            curr.setNext(node);
            curr = curr.getNext();
            this.length++;
        }

        this.tail = curr;
    }

    @Override
    public void addFirst(T value) {
        DoublyLinkedListNode<T> node = new DoublyLinkedListNode<>(value, this.head);
        this.head = node;
        this.length++;
    }

    @Override
    public void addLast(T value) {
        DoublyLinkedListNode<T> node = new DoublyLinkedListNode<>(value);
        if (this.head == null) this.head = node;
        DoublyLinkedListNode<T> curr = this.head;

        while (curr.getNext() != null) {
            curr = curr.getNext();
        }

        node.setPrev(curr);
        curr.setNext(node);
        this.tail = node;
        this.length++;
    }

    @Override
    public T removeFirst() {
        if (this.head == null) return null;

        T val = this.head.getValue();
        this.head = this.head.getNext();
        this.length--;
        return val;
    }

    @Override
    public T removeLast() {
        if (this.head == null) return null;
        DoublyLinkedListNode<T> curr = this.head;
        DoublyLinkedListNode<T> prev = null;

        while (curr.getNext() != null) {
            prev = curr;
            curr = curr.getNext();
        }

        if (prev == null) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = prev;
            prev.setNext(null);
        }

        this.length--;
        return curr.getValue();
    }

    @Override
    public boolean contains (T value) {
        if ((value instanceof Comparable<?>)) {
            @SuppressWarnings("unchecked")  // Suppress warning due to generic casting
            Comparable<T> comparableValue = (Comparable<T>) value;
            return _contains(value, comparableValue::compareTo);
        }

        throw new Error("Comparator is required since values do not extend Comparable class");
    }

    @Override
    public boolean contains (T value, Comparator<T> comparator) {
        return _contains(value, (a) -> comparator.compare(a, value));
    }

    private boolean _contains (T value, CompareTo<T> compareTo) {
        if (!this.isEmpty()) {
            DoublyLinkedListNode<T> curr = this.head;

            while (curr != null) {
                if (compareTo.apply(curr.getValue()) == 0) return true;
                curr = curr.getNext();
            }
        }

        return false;
    }

    public void merge (DoublyLinkedList<T> other) {
        if (this.head == null) {
            this.head = other.head;
        } else {
            this.tail.setNext(other.head);
            other.head.setPrev(this.tail);
        }

        this.tail = other.tail;
        this.length += other.size();
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }
}
