package Heap;

import Algorithms.Utils;

import java.util.Arrays;

public class BinaryHeap {

    private boolean isMin; // true = min heap, else = max heap

    public BinaryHeap () {
        this.isMin = false;
    }
    public BinaryHeap (boolean isMin) {
        this.isMin = isMin;
    }

    public int getParentIndex (int index) {
        return (index - 1) / 2;
    }

    public int getLeftChildrenIndex (int index) {
        return (2 * index) + 1;
    }

    private <T extends Comparable<T>> boolean shouldSiftUp (T item, T parent) {
        return this.isMin ? item.compareTo(parent) < 0 : item.compareTo(parent) > 0;
    }
    public <T extends Comparable<T>> void siftUp (T[] arr, int index) {
        int parentIndex = this.getParentIndex(index);

        while (parentIndex >= 0 && this.shouldSiftUp(arr[index], arr[parentIndex])) {
            Utils.swap(arr, index, parentIndex);
            index = parentIndex;
            parentIndex = this.getParentIndex(parentIndex);
        }
    }

    /**
     * -1 = left child
     * 0 = no sift
     * 1 = right child
     */
    private <T extends Comparable<T>> int shouldSiftDown (T current, T left, T right) {
        if (right == null && left == null) return 0;
        boolean leftIsLarger = right == null || left.compareTo(right) > 0;

        if (right != null && this.isMin == leftIsLarger) {
            boolean rightIsLarger = right.compareTo(current) > 0;
            return this.isMin ? (rightIsLarger ? 0 : 1) : (rightIsLarger ? 1 : 0);
        }

        leftIsLarger = left.compareTo(current) > 0;
        return this.isMin ? leftIsLarger ? 0 : -1 : leftIsLarger ? -1 : 0;
    }

    public <T extends Comparable<T>> void siftDown(T[] arr, int index) {
        this.siftDown(arr, index, arr.length);
    }

    public <T extends Comparable<T>> void siftDown(T[] arr, int index, int max) {
        int leftChildrenIndex = this.getLeftChildrenIndex(index);
        if (leftChildrenIndex >= max) return;
        T left = arr[leftChildrenIndex];
        T right = leftChildrenIndex + 1 < max ? arr[leftChildrenIndex + 1] : null;

        int shouldSiftDown = this.shouldSiftDown(arr[index], left, right);
        while(shouldSiftDown != 0) {
            int newIndex = leftChildrenIndex;
            if (shouldSiftDown == 1) {
                newIndex = leftChildrenIndex + 1;
            }

            Utils.swap(arr, index, newIndex);
            index = newIndex;
            leftChildrenIndex = this.getLeftChildrenIndex(index);
            if (leftChildrenIndex >= max) break;
            left = arr[leftChildrenIndex];
            right = leftChildrenIndex + 1 < max ? arr[leftChildrenIndex + 1] : null;
            shouldSiftDown = this.shouldSiftDown(arr[index], left, right);
        }
    }

    public static void testShouldSiftDown () {
        System.out.println("=====");
        System.out.println("MIN HEAP: ");

        BinaryHeap binaryHeap = new BinaryHeap(true);
        System.out.println(binaryHeap.shouldSiftDown(2, 3, 4)); // 0
        System.out.println(binaryHeap.shouldSiftDown(2, 1, 4)); // -1
        System.out.println(binaryHeap.shouldSiftDown(2, 3, 1)); // 1
        System.out.println(binaryHeap.shouldSiftDown(2, 3, null)); // 0
        System.out.println(binaryHeap.shouldSiftDown(2, 1, null));  // -1
        System.out.println(binaryHeap.shouldSiftDown(2, null, null)); // 0

        System.out.println("=====");
        System.out.println("MAX HEAP");

        BinaryHeap binaryHeap1 = new BinaryHeap();
        System.out.println(binaryHeap1.shouldSiftDown(2, 3, 4)); // 1
        System.out.println(binaryHeap1.shouldSiftDown(2, 1, 4)); // 1
        System.out.println(binaryHeap1.shouldSiftDown(2, 3, 1)); // -1
        System.out.println(binaryHeap1.shouldSiftDown(2, 3, null)); // -1
        System.out.println(binaryHeap1.shouldSiftDown(2, 1, null));  // 0
        System.out.println(binaryHeap1.shouldSiftDown(2, null, null)); // 0
    }

    public <T extends Comparable<T>> void heapify (T[] arr) {
        for (int i = arr.length / 2; i >= 0; i--) {
            siftDown(arr, i);
        }
    }

    public static void testBuildHeap () {
        BinaryHeap binaryHeap = new BinaryHeap();

        Integer[] test = {10, 2, 3, 5, 1, 4, 1, 100, 20, 11, 6};
        binaryHeap.heapify(test);

        printHeap(test);
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
