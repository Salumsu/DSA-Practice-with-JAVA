package LinkedList.SinglyLinkedList;

import LinkedList.LLNode;

public class SinglyLinkedListNode <T> extends LLNode<T> {
    public SinglyLinkedListNode (T value) {
        super(value);
    }

    @Override
    public SinglyLinkedListNode<T> getNext() {
        return (SinglyLinkedListNode<T>) super.getNext();
    }

    @Override
    public void setNext(T value) {
        this.next = new SinglyLinkedListNode<>(value);
    }
}

