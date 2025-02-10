package Stack;

import Queue.QueueAL;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackAL<T> {
    private ArrayList<T> arrayList;

    public StackAL () {
        this.arrayList = new ArrayList<>();
    }

    public StackAL (T value) {
        this();

        this.push(value);
    }

    public StackAL(T[] values) {
        this();

        for (T value: values) {
            this.push(value);
        }
    }

    public int size () {
        return this.arrayList.size();
    }

    public boolean isEmpty() {
        return this.arrayList.isEmpty();
    }

    public void push (T value) {
        this.arrayList.addFirst(value);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.arrayList.removeFirst();
    }

    public T top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.arrayList.getFirst();
    }

    public ArrayList<T> getList() {
        return this.arrayList;
    }
}
