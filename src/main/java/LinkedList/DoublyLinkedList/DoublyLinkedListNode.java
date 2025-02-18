package LinkedList.DoublyLinkedList;

import LinkedList.DLLNode;

public class DoublyLinkedListNode <T> extends DLLNode<T> {


    public DoublyLinkedListNode (T value) {
        super(value);
    }

    @Override
    public DoublyLinkedListNode<T> getNext() {
        return (DoublyLinkedListNode<T>) super.getNext();
    }

    @Override
    public DoublyLinkedListNode<T> getPrev() {
        return (DoublyLinkedListNode<T>) super.getPrev();
    }

    @Override
    public void setNext(T value) {
        this.next = new DoublyLinkedListNode<>(value);
    }

    @Override
    public void setPrev(T value) {
        this.prev = new DoublyLinkedListNode<>(value);
    }
}

