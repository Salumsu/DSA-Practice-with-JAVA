package LinkedList.SinglyLinkedList;

import LinkedList.LinkedList;
import LinkedList.LLNode;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class SinglyLinkedList<T> extends LinkedList<T, SinglyLinkedListNode<T>> {

    public SinglyLinkedList () {
        super();
    }

    public SinglyLinkedList (T value) {
        super(new SinglyLinkedListNode<T>(value));
    }

    public SinglyLinkedList (T[] values) {
        super(values);
    }

    @Override
    protected SinglyLinkedListNode<T> castNode(LLNode<T> node) {
        return (SinglyLinkedListNode<T>) node;
    }

    @Override
    protected SinglyLinkedListNode<T> newNode (T value) {
        return new SinglyLinkedListNode<>(value);
    }

    @Override
    public T removeLast() {
        SinglyLinkedListNode<T> curr = this.head;
        if (curr == null) return null;
        SinglyLinkedListNode<T> prev = null;

        while (curr.getNext() != null) {
            prev = curr;
            curr = curr.getNext();
        }

        if (prev == null) {
            this.head = null;
        } else {
            prev.removeNext();
        }

        this.length--;

        return curr.getValue();
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
