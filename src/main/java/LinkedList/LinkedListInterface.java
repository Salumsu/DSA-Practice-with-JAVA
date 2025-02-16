package LinkedList;

import java.util.Comparator;

public interface LinkedListInterface<T> {
    void addFirst(T value);
    void addLast(T value);
    T removeFirst();
    T removeLast();
    boolean contains(T value, Comparator<T> comparator);
    boolean contains(T value);
    int size();
    boolean isEmpty();
}
