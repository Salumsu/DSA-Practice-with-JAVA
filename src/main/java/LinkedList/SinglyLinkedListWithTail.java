package LinkedList;

import java.util.ArrayList;

public class SinglyLinkedListWithTail<T> extends SinglyLinkedList<T> {
    private SinglyLinkedListNode<T> tail = null;

    public SinglyLinkedListWithTail () {
        super();
    }

    public SinglyLinkedListWithTail (T value) {
        super(value);
    }

    public SinglyLinkedListWithTail (T[] values) {
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

        this.tail = curr;
    }

    public T getTail () {
        this.throwErrorWhenListIsEmpty();

        return this.tail.getValue();
    }

    @Override
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
            this.tail = prev;
            prev.setNext(null);
        }

        this.length--;

        return curr.getValue();
    }

    @Override
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
            this.tail = null;
        } else {
            this.tail = prev;
            prev.setNext(null);
        }

        this.length -= count;

        while (curr != null) {
            list.add(curr.getValue());
            curr = curr.getNext();
        }

        return list;
    }

    @Override
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
        this.tail = node;
    }

    @Override
    public void prepend (T value) {
        SinglyLinkedListNode<T> node = new SinglyLinkedListNode<>(value);

        if (this.head == null) {
            this.tail = node;
        }

        node.setNext(this.head);
        this.head = node;
        this.length++;
    }

    @Override
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

        if (index == this.length) {
            this.tail = node;
        }

        this.length++;
        node.setNext(curr);
        prev.setNext(node);
    }

    @Override
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
        if (index == this.length) {
            this.tail = prev;
        }


        if (curr != null) {

            return curr.getValue();
        }

        return null;
    }
}
