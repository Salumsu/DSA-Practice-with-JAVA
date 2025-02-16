package LinkedList.DoublyLinkedList;

import LinkedList.SinglyLinkedList.SinglyLinkedListNode;

public class DoublyLinkedListNode <T> {

    private T value;
    private DoublyLinkedListNode<T> next;
    private DoublyLinkedListNode<T> prev;

    public DoublyLinkedListNode (T value) {
        this(value, null);
    }

    public DoublyLinkedListNode (T value, DoublyLinkedListNode<T> next) {
        this(value, next, null);
    }

    public DoublyLinkedListNode (T value, DoublyLinkedListNode<T> next, DoublyLinkedListNode<T> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public DoublyLinkedListNode<T> getPrev() {
        return this.prev;
    }

    public void setPrev(DoublyLinkedListNode<T> prev) {
        this.prev = prev;
    }

    public DoublyLinkedListNode<T> getNext() {
        return this.next;
    }

    public void setNext(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

