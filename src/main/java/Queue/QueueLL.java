package Queue;

import LinkedList.SinglyLinkedListWithTail;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class QueueLL <T> {
    private SinglyLinkedListWithTail<T> list;

    public QueueLL () {
        this.list = new SinglyLinkedListWithTail<>();
    }

    public QueueLL (T value) {
        this();

        this.enqueue(value);
    }

    public QueueLL(T[] values) {
        this();

        for (T value: values) {
            this.enqueue(value);
        }
    }

    public int size () {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void enqueue (T value) {
        this.list.append(value);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.list.popHead();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.list.get(0);
    }

    public T rear() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.list.getTail();
    }

    public ArrayList<T> toArrayList() {
        return this.list.toArrayList();
    }
}
