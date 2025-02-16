package Heap;

import Algorithms.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * A generic Binary Heap implementation that supports both min-heaps and max-heaps.
 * This class requires a {@link Comparator} to define element ordering. However, if
 * the elements implement {@link Comparable}, the {@link #create()} factory method
 * can be used to construct a heap without explicitly passing a comparator.
 *
 * @param <T> The type of elements in the heap.
 */
public class BinaryHeap<T> implements HeapInterface<T> {
    final private boolean isMin; // true = min heap, else = max heap
    final private ArrayList<T> list;

    final private Comparator<T> comparator;

    public BinaryHeap (Comparator<T> comparator) {
        this(comparator, true);
    }

    public BinaryHeap (Comparator<T> comparator, boolean isMin) {
        this.isMin = isMin;
        this.list = new ArrayList<T>();
        this.comparator = comparator;
    }

    public BinaryHeap (Comparator<T> comparator, T[] arr) {
        this(comparator, true, arr);
    }

    public BinaryHeap(Comparator<T> comparator, boolean isMin, T[] arr) {
        this(comparator, isMin);
        List<T> heapified = HeapHelper.heapify(isMin, Arrays.asList(arr), comparator, true);
        this.list.addAll(heapified);
    }

    public static <T extends Comparable<T>> BinaryHeap<T> create() {
        return new BinaryHeap<>(Comparable::compareTo);
    }

    public static <T extends Comparable<T>> BinaryHeap<T> create(boolean isMin) {
        return new BinaryHeap<>(Comparable::compareTo, isMin);
    }

    public static <T extends Comparable<T>> BinaryHeap<T> create(T[] arr) {
        return new BinaryHeap<>(Comparable::compareTo, arr);
    }

    public static <T extends Comparable<T>> BinaryHeap<T> create(boolean isMin, T[] arr) {
        return new BinaryHeap<>(Comparable::compareTo, isMin, arr);
    }

    public void insert(T item) {
        this.list.add(item);

        HeapHelper.siftUp(this.isMin, this.list, this.comparator, this.list.size() - 1);
    }

    public T peek() {
        if (this.list.isEmpty()) return null;

        return this.list.getFirst();
    };

    public T poll() {
        return remove(0);
    }

    public T remove(int index) {
        if (index < 0 || index >= this.list.size()) return null;

        Utils.swap(this.list, index, this.list.size() - 1);
        T item = this.list.removeLast();
        HeapHelper.siftDown(this.isMin, this.list, this.comparator, index);

        return item;
    };

    public int size() {
        return this.list.size();
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void clear() {
        this.list.clear();
    }

    public T[] toArray(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, list.size());
        return list.toArray(array);
    }

    public ArrayList<T> toArrayList() {
        return new ArrayList<T>(this.list);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}
