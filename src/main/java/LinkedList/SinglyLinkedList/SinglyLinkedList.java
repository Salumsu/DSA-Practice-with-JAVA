package LinkedList.SinglyLinkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class SinglyLinkedList<T extends Comparable<T>> implements SinglyLinkedListInterface<T> {

    protected SinglyLinkedListNode<T> head;
    protected int length;

    public int size() {
        return this.length;
    }
    public boolean isEmpty() {return this.head == null;}

    public SinglyLinkedList () {
        this.head = null;
        this.length = 0;
    }

    public SinglyLinkedList (T value) {
        this.head = new SinglyLinkedListNode<T>(value);
        this.length = 1;
    }

    public SinglyLinkedList (T[] values) {
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

        SinglyLinkedListNode<T> curr = new SinglyLinkedListNode<T>(values[0]);
        this.head = curr;
        this.length++;

        for (int i = 1; i < values.length; i++) {
            T value = values[i];
            SinglyLinkedListNode<T> node = new SinglyLinkedListNode<T>(value);
            curr.setNext(node);
            curr = curr.getNext();
            this.length++;
        }
    }

    public T getHead() {
        this.throwErrorWhenListIsEmpty();

        return this.head.getValue();
    }

    public T get (Integer index) {
        this.throwErrorWhenListIsEmpty();

        SinglyLinkedListNode<T> curr = head;

        int currIndex = 0;

        while (curr != null && currIndex < index) {
            curr = curr.getNext();
            currIndex++;
        }

        if (curr == null) {
            throw new IndexOutOfBoundsException();
        }

        return curr.getValue();
    }

    public T removeLast() {
        return this.pop();
    }

    public T pop () {
        this.throwErrorWhenListIsEmpty();

        SinglyLinkedListNode<T> curr = this.head;
        SinglyLinkedListNode<T> prev = null;

        while (curr.getNext() != null) {
            prev = curr;
            curr = curr.getNext();
        }

        if (prev == null) {
            this.head = null;
        } else {
            prev.setNext(null);
        }

        this.length--;

        return curr.getValue();
    }

    public ArrayList<T> pop (Integer count) {
        this.throwErrorWhenListIsEmpty();
        this.throwErrorWhenCountIsGreaterThanLength(count);

        ArrayList<T> list = new ArrayList<>();
        SinglyLinkedListNode<T> curr = this.head;
        SinglyLinkedListNode<T> prev = null;

        int currIndex = 0;

        while (currIndex + count < this.size()) {
            prev = curr;
            curr = curr.getNext();
            currIndex++;
        }

        if (prev == null) {
            this.head = null;
        } else {
            prev.setNext(null);
        }

        this.length -= count;

        while (curr != null) {
            list.add(curr.getValue());
            curr = curr.getNext();
        }

        return list;
    }

    public T removeFirst () {
        return this.popHead();
    }

    public T popHead () {
        this.throwErrorWhenListIsEmpty();

        T value = this.head.getValue();

        this.head = this.head.getNext();

        this.length--;
        return value;
    }

    public ArrayList<T> popHead (Integer count) {
        this.throwErrorWhenListIsEmpty();
        this.throwErrorWhenCountIsGreaterThanLength(count);

        ArrayList<T> list = new ArrayList<>();
        int currCount = 0;

        while (currCount < (count)) {
            list.add(this.head.getValue());
            this.head = this.head.getNext();
            currCount++;
        }

        this.length -= count;

        return list;
    }

    public void addLast(T value) {
        this.append(value);
    }

    public void append (T value) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(value);
        if (this.head == null) {
            this.head = node;
        } else {
            SinglyLinkedListNode<T> curr = this.head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }

            curr.setNext(node);
        }
        this.length++;
    }

    public void addFirst(T value) {
        this.prepend(value);
    }

    public void prepend (T value) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(value);

        node.setNext(this.head);
        this.head = node;
        this.length++;
    }

    public void insert (T value, Integer index) {
        this.throwErrorWhenIndexOutOfBounds(index - 1);

        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(value);
        SinglyLinkedListNode<T> curr = this.head;
        SinglyLinkedListNode<T> prev = null;
        int currIndex = 0;

        while (currIndex < index && curr != null) {
            currIndex++;
            prev = curr;
            curr = curr.getNext();
        }

        if (prev == null) {
            SinglyLinkedListNode<T> temp = this.head;
            this.head = node;
            this.head.setNext(temp);
            return;
        }

        this.length++;
        node.setNext(curr);
        prev.setNext(node);
    }

    public T remove (Integer index) {
        this.throwErrorWhenListIsEmpty();
        this.throwErrorWhenIndexOutOfBounds(index);

        SinglyLinkedListNode<T> curr = this.head;
        SinglyLinkedListNode<T> prev = null;
        int currentIndex = 0;

        while (currentIndex < index && curr != null) {
            currentIndex++;
            prev = curr;
            curr = curr.getNext();
        }

        if (prev == null) {
            this.head = this.head.getNext();
        } else {
            prev.setNext(curr.getNext());
        }

        this.length--;

        if (curr != null) {
            return curr.getValue();
        }

        return null;
    }

    public boolean contains (T value) {
        if (!this.isEmpty()) {
            SinglyLinkedListNode<T> curr = this.head;

            while (curr != null) {
                if (curr.getValue().compareTo(value) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    protected void throwErrorWhenIndexOutOfBounds (Integer index) {
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    protected void throwErrorWhenListIsEmpty () {
        if (this.head == null) {
            throw new NullPointerException("The list is empty");
        }
    }

    protected void throwErrorWhenCountIsGreaterThanLength (Integer count) {
        if (count > this.size()) {
            throw new IndexOutOfBoundsException("Count too high. Not enough nodes.");
        }
    }

    public ArrayList<T> toArrayList () {
        ArrayList<T> arrayList = new ArrayList<>();

        SinglyLinkedListNode<T> curr = this.head;

        while (curr != null) {
            arrayList.add(curr.getValue());
            curr = curr.getNext();
        }

        return arrayList;
    }

    public T[] toArray (Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] list = (T[]) Array.newInstance(clazz, this.length);

        SinglyLinkedListNode<T> curr = this.head;

        for (int i = 0; i < this.length; i++) {
            list[i] = curr.getValue();
            curr = curr.getNext();
        }

        return list;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        SinglyLinkedListNode<T> curr = this.head;

        while (curr != null) {
            stringBuilder.append(curr.getValue());
            curr = curr.getNext();

            if (curr != null) {
                stringBuilder.append(", ");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
