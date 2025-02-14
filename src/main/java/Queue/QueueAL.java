package Queue;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class QueueAL <T> implements QueueInterface<T> {
    private ArrayList<T> arrayList;

    public QueueAL () {
        this.arrayList = new ArrayList<>();
    }

    public QueueAL (T value) {
        this();

        this.enqueue(value);
    }

    public QueueAL(T[] values) {
        this();

        for (T value: values) {
            this.enqueue(value);
        }
    }

    public int size () {
        return this.arrayList.size();
    }

    public boolean isEmpty() {
        return this.arrayList.isEmpty();
    }

    public void enqueue (T value) {
        this.arrayList.addLast(value);
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.arrayList.removeFirst();
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.arrayList.getFirst();
    }

    public T rear() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.arrayList.getLast();
    }

    public void merge (QueueAL<T> other) {
        this.arrayList.addAll(other.toArrayList());
    }

    public ArrayList<T> toArrayList() {
        return this.arrayList;
    }
}
