package Heap;

public interface HeapInterface<T> {
    void insert(T item);
    T peek();

    T poll();
    T remove(int index);
    int size();
    boolean isEmpty();
    void clear();
}
