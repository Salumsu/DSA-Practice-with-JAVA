package LinkedList.SinglyLinkedList;

public interface SinglyLinkedListInterface<T extends Comparable<T>> {
    void addFirst(T value);
    void addLast(T value);
    T removeFirst();
    T removeLast();
    boolean contains(T value);
    int size();
    boolean isEmpty();
}
