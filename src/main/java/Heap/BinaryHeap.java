package Heap;

import Algorithms.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class BinaryHeap<T extends Comparable<T>> {
    private boolean isMin; // true = min heap, else = max heap
    private ArrayList<T> list;

    public BinaryHeap () {
        this(true);
    }

    public BinaryHeap (boolean isMin) {
        this.isMin = isMin;
        this.list = new ArrayList<T>();
    }

    public BinaryHeap(T[] arr) {
        this(true, arr);
    }

    public BinaryHeap(boolean isMin, T[] arr) {
        this(isMin);
        T[] heapified = HeapHelper.heapify(isMin, arr, true);
        this.list.addAll(Arrays.asList(heapified));
    }

    public void insert(T item) {
        this.list.add(item);

        HeapHelper.siftUp(this.isMin, this.list, this.list.size() - 1);
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
        HeapHelper.siftDown(this.isMin, this.list, index);

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

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) java.lang.reflect.Array.newInstance(Comparable.class, list.size());
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
