package LinkedList.DoublyLinkedList;

import LinkedList.LinkedList;
import LinkedList.LLNode;
import LinkedList.SinglyLinkedList.SinglyLinkedListNode;

public class DoublyLinkedList<T> extends LinkedList<T, DoublyLinkedListNode<T>> {
    public DoublyLinkedList () {
        super();
    }

    public DoublyLinkedList (T value) {
        super(new DoublyLinkedListNode<>(value));
    }

    public DoublyLinkedList (T[] values) {
        super(values);
    }

    @Override
    protected DoublyLinkedListNode<T> castNode(LLNode<T> node) {
        return (DoublyLinkedListNode<T>) node;
    }

    @Override
    protected DoublyLinkedListNode<T> newNode (T value) {
        return new DoublyLinkedListNode<>(value);
    }

    @Override
    public void addLast(T value) {
        DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<>(value);
        if (this.tail != null) {
            this.tail.setNext(newNode);
            newNode.setPrev(this.tail);
        } else {
            this.head = newNode;
        }
        this.tail = newNode;
        this.length++;
    }

    @Override
    public T removeFirst() {
        if (this.head == null) return null;
        T value = this.head.getValue();

        this.head = castNode(this.head.getNext());
        if (this.head == null) {
            this.tail = null;
        } else {
            this.head.removePrev();
        }
        this.length--;

        return value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size() || this.head == null) return null;
        if (index == 0) return removeFirst();
        if (index == this.size() - 1) return removeLast();

        DoublyLinkedListNode<T> curr = this.head;
        DoublyLinkedListNode<T> prev = null;

        int currentIndex = 0;
        while (currentIndex < index && curr != null) {
            currentIndex++;
            prev = curr;
            curr = castNode(curr.getNext());
        }

        if (curr == null) return null;

        DoublyLinkedListNode<T> next = curr.getNext();
        prev.setNext(next);
        next.setPrev(prev);

        this.length--;

        return curr.getValue();
    }

    @Override
    public T removeLast() {
        System.out.println("weeee");

        if (this.tail == null) return null;
        System.out.println("YOHOO");

        T value = this.tail.getValue();
        this.tail = this.tail.getPrev();
        if (this.tail != null) {
            this.tail.removeNext();
        } else {
            this.head = null;
        }

        this.length--;

        return value;
    }
}
