package Heap;

import Algorithms.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HeapHelper {
    public static int getParentIndex (int index) {
        return (index - 1) / 2;
    }

    public static int getLeftChildrenIndex (int index) {
        return (2 * index) + 1;
    }

    /**
     * Determines whether the given item should sift up in the heap.
     *
     * @param <T>    The type of elements in the heap, which must be comparable.
     * @param isMin  Indicates whether the heap is a min-heap (true) or max-heap (false).
     * @param item   The item being inserted or repositioned.
     * @param parent The parent of the item in the heap.
     * @return       true if the item should swap with its parent, false otherwise.
     */
    private static <T extends Comparable<T>> boolean shouldSiftUp (boolean isMin, T item, T parent) {
        return isMin ? item.compareTo(parent) < 0 : item.compareTo(parent) > 0;
    }

    public static <T extends Comparable<T>> T[] siftUp (boolean isMin, T[] arr, int index) {
        return siftUp(isMin, arr, index, false);
    }

    public static <T extends Comparable<T>> List<T> siftUp (boolean isMin, List<T> arr, int index) {
        return siftUp(isMin, arr, index, false);
    }

    /**
     * Performs the sift-up operation to maintain the heap property.
     *
     * @param <T>    The type of elements in the heap, which must be comparable.
     * @param isMin  Indicates whether the heap is a min-heap (true) or max-heap (false).
     * @param arr    The array representing the heap.
     * @param index  The index of the element to sift up.
     * @param clone  If true, the operation is performed on a cloned version of the array.
     * @return       The modified array after the sift-up operation.
     */
    public static <T extends Comparable<T>> T[] siftUp (boolean isMin, T[] arr, int index, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        siftUp(isMin, Arrays.asList(arr), index, false);
        return newArr;
    }

    public static <T extends Comparable<T>> List<T> siftUp (boolean isMin, List<T> arr, int index, boolean clone) {
        List<T> newArr = clone ? new ArrayList<>(arr) : arr;
        int parentIndex = HeapHelper.getParentIndex(index);

        while (parentIndex >= 0 && HeapHelper.shouldSiftUp(isMin, Utils.get(newArr, index), Utils.get(newArr, parentIndex))) {
            Utils.swap(newArr, index, parentIndex);
            index = parentIndex;
            parentIndex = HeapHelper.getParentIndex(parentIndex);
        }

        return newArr;
    }

    /**
     * Determines the direction in which the current node should sift down in the heap.
     *
     * @param isMin   Indicates whether the heap is a min-heap (true) or max-heap (false).
     * @param current The current node being evaluated.
     * @param left    The left child of the current node.
     * @param right   The right child of the current node.
     * @param <T>     The type of elements in the heap, which must be comparable.
     * @return        -1 if the current node should swap with the left child,
     *                 1 if it should swap with the right child,
     *                 0 if no sift-down is needed.
     */
    private static <T extends Comparable<T>> int shouldSiftDown (boolean isMin, T current, T left, T right) {
        if (right == null && left == null) return 0;
        boolean leftIsLarger = right == null || left.compareTo(right) > 0;

        if (right != null && isMin == leftIsLarger) {
            boolean rightIsLarger = right.compareTo(current) > 0;
            return isMin ? (rightIsLarger ? 0 : 1) : (rightIsLarger ? 1 : 0);
        }

        leftIsLarger = left.compareTo(current) > 0;
        return isMin ? leftIsLarger ? 0 : -1 : leftIsLarger ? -1 : 0;
    }

    public static <T extends Comparable<T>> T[] siftDown(boolean isMin, T[] arr, int index) {
        return HeapHelper.siftDown(isMin, arr, index, arr.length, false);
    }

    public static <T extends Comparable<T>> List<T> siftDown(boolean isMin, List<T> arr, int index) {
        return HeapHelper.siftDown(isMin, arr, index, arr.size(), false);
    }

    public static <T extends Comparable<T>> T[] siftDown(boolean isMin, T[] arr, int index, int max) {
        return HeapHelper.siftDown(isMin, arr, index, max, false);
    }

    public static <T extends Comparable<T>> List<T> siftDown(boolean isMin, List<T> arr, int index, int max) {
        return HeapHelper.siftDown(isMin, arr, index, max, false);
    }

    /**
     * Performs the sift-down operation to maintain the heap property.
     *
     * @param <T>    The type of elements in the heap, which must be comparable.
     * @param isMin  Indicates whether the heap is a min-heap (true) or max-heap (false).
     * @param arr    The array representing the heap.
     * @param index  The index of the element to sift down.
     * @param max    The maximum valid index in the heap.
     * @param clone  If true, the operation is performed on a cloned version of the array.
     * @return       The modified array after the sift-down operation.
     */
    public static <T extends Comparable<T>> T[] siftDown(boolean isMin, T[] arr, int index, int max, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        siftDown(isMin, Arrays.asList(arr), index, max, false);
        return newArr;
    }

    public static <T extends Comparable<T>> List<T> siftDown(boolean isMin, List<T> arr, int index, int max, boolean clone) {
        List<T> newArr = clone ? new ArrayList<>(arr) : arr;

        int shouldSiftDown = 0;
        do {
            int leftChildrenIndex = HeapHelper.getLeftChildrenIndex(index);
            if (leftChildrenIndex >= max) break;
            T left = Utils.get(newArr, leftChildrenIndex);
            T right = leftChildrenIndex + 1 < max ? Utils.get(newArr, leftChildrenIndex + 1) : null;
            shouldSiftDown = HeapHelper.shouldSiftDown(isMin, Utils.get(newArr, index), left, right);

            if (shouldSiftDown != 0) {
                int newIndex = leftChildrenIndex;
                if (shouldSiftDown == 1) {
                    newIndex = leftChildrenIndex + 1;
                }

                Utils.swap(newArr, index, newIndex);
                index = newIndex;
            };
        } while (shouldSiftDown != 0);

        return newArr;
    }

    public static <T extends Comparable<T>> T[] heapify (boolean isMin, T[] arr) {
        return heapify(isMin, arr, false);
    }

    /**
     * Constructs a BinaryHeap with optional min-heap or max-heap behavior.
     *
     * @param <T>    The type of elements stored in the heap, which must extend {@link Comparable}.
     * @param isMin  Determines whether the heap is a min-heap (true) or max-heap (false).
     * @param arr    An optional array of elements to be heapified upon initialization.
     * @param clone  If true, creates a cloned copy of the input array before heapification.
     * @return       A properly constructed BinaryHeap instance.
     */
    public static <T extends Comparable<T>> T[] heapify (boolean isMin, T[] arr, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;

        for (int i = newArr.length / 2; i >= 0; i--) {
            HeapHelper.siftDown(isMin, newArr, i);
        }

        return newArr;
    }

    public static <T extends Comparable<T>> void printHeap (T[] arr) {
        StringBuilder sb = new StringBuilder();

        int lineCount = 1;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append("   ");
            count++;

            if (count == lineCount) {
                sb.append("\n");
                count = 0;
                lineCount *= 2;
            }
        }

        System.out.println(sb.toString());
    }
}
