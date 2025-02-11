package Stack;

import LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackLL<T> {
    SinglyLinkedList<T> list;

    public StackLL () {
        this.list = new SinglyLinkedList<>();
    }

    public StackLL (T value) {
        this();

        this.push(value);
    }

    public int size () {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public StackLL (T[] values) {
        this();

        for (T value: values) {
            this.push(value);
        }
    }

    public void push (T value) {
        this.list.prepend(value);
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.list.popHead();
    }

    public T top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return this.list.get(0);
    }

    public ArrayList<T> toArrayList() {
        return this.list.toArrayList();
    }
}
