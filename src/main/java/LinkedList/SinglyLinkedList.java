package LinkedList;

import java.util.ArrayList;

public class SinglyLinkedList<T> {

    private SinglyLinkedListNode<T> head;
    private int length;

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

    public SinglyLinkedList (ArrayList<T> values) {
        this.head = null;
        if (values.isEmpty()) {
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

        SinglyLinkedListNode<T> curr = new SinglyLinkedListNode<T>(values.get(0));
        this.head = curr;
        this.length++;

        for (T value : values.subList(1, values.size())) {
            SinglyLinkedListNode<T> node = new SinglyLinkedListNode<T>(value);
            curr.setNext(node);
            curr = curr.getNext();
            this.length++;
        }
    }

    public SinglyLinkedListNode<T> getHead() {
        return this.head;
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

    public T pop () {
        this.throwErrorWhenListIsEmpty();

        SinglyLinkedListNode<T> curr = this.getHead();
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
        SinglyLinkedListNode<T> curr = this.getHead();
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

    public T popHead () {
        this.throwErrorWhenListIsEmpty();

        T value = this.getHead().getValue();

        this.head = this.getHead().getNext();

        this.length--;
        return value;
    }

    public ArrayList<T> popHead (Integer count) {
        this.throwErrorWhenListIsEmpty();
        this.throwErrorWhenCountIsGreaterThanLength(count);

        ArrayList<T> list = new ArrayList<>();
        int currCount = 0;

        while (currCount < (count)) {
            list.add(this.getHead().getValue());
            this.head = this.getHead().getNext();
            currCount++;
        }

        this.length -= count;

        return list;
    }

    public void append (T value) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(value);
        if (this.getHead() == null) {
            this.head = node;
        }

        SinglyLinkedListNode<T> curr = this.getHead();
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }

        this.length++;
        curr.setNext(node);
    }

    public void prepend (T value) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(value);

        node.setNext(this.getHead());
        this.head = node;
        this.length++;
    }

    public void insert (T value, Integer index) {
        this.throwErrorWhenIndexOutOfBounds(index - 1);

        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(value);
        SinglyLinkedListNode<T> curr = this.getHead();
        SinglyLinkedListNode<T> prev = null;
        int currIndex = 0;

        while (currIndex < index && curr != null) {
            currIndex++;
            prev = curr;
            curr = curr.getNext();
        }

        if (prev == null) {
            SinglyLinkedListNode<T> temp = this.getHead();
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

        SinglyLinkedListNode<T> curr = this.getHead();
        SinglyLinkedListNode<T> prev = null;
        int currentIndex = 0;

        while (currentIndex < index && curr != null) {
            currentIndex++;
            prev = curr;
            curr = curr.getNext();
        }

        if (prev == null) {
            this.head = this.getHead().getNext();
        } else {
            prev.setNext(curr.getNext());
        }

        this.length--;

        if (curr != null) {
            return curr.getValue();
        }

        return null;
    }

    private void throwErrorWhenIndexOutOfBounds (Integer index) {
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void throwErrorWhenListIsEmpty () {
        if (this.getHead() == null) {
            throw new NullPointerException("The list is empty");
        }
    }

    private void throwErrorWhenCountIsGreaterThanLength (Integer count) {
        if (count > this.size()) {
            throw new IndexOutOfBoundsException("Count too high. Not enough nodes.");
        }
    }

    public ArrayList<T> toArrayList () {
        ArrayList<T> arrayList = new ArrayList<>();

        SinglyLinkedListNode<T> curr = this.getHead();

        while (curr != null) {
            arrayList.add(curr.getValue());
            curr = curr.getNext();
        }

        return arrayList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        SinglyLinkedListNode<T> curr = this.getHead();

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
