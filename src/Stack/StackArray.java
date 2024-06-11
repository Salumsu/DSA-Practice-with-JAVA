package Stack;

import java.util.EmptyStackException;

public class StackArray {
    private int capacity = 10;
    private int top = -1;
    private int[] array = new int[capacity];

    public StackArray () {
        this.top = -1;
    }

    public StackArray (int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
    }

    public boolean isEmpty () {
        return this.top == -1;
    }

    public int size () {
        return this.top + 1;
    }

    public int top () {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.array[this.top];
    }

    public void push (int value) {
        if (this.top >= this.capacity - 1) {
            throw new StackOverflowError();
        }
        this.array[++this.top] = value;
    }

    public int pop () {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        int value = this.array[this.top--];
        return value;
    }

    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder str = new StringBuilder("[");
        str.append(this.array[0]);
        for (int i = 1; i <= this.top; i++) {
            str.append(", ");
            str.append(array[i]);
        }

        str.append("]");
        return str.toString();
    }
}
