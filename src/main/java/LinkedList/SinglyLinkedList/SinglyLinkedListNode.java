package LinkedList.SinglyLinkedList;

public class SinglyLinkedListNode <T extends Comparable<T>> {
    private T value;
    private SinglyLinkedListNode<T> next;

    public SinglyLinkedListNode (T value) {
        this.value = value;
        this.next = null;
    }

    public SinglyLinkedListNode (T value, SinglyLinkedListNode<T> next) {
        this.value = value;
        this.next = next;
    }

    public T getValue () {
        return this.value;
    }

    public void setValue (T value) {
        this.value = value;
    }

    public SinglyLinkedListNode<T> getNext() {
        return this.next;
    }

    public void setNext(SinglyLinkedListNode<T> next) {
        this.next = next;
    }
}

