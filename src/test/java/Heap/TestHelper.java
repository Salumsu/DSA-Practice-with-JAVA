package Heap;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestHelper {
    public static <T extends Comparable<T>> void testMinHeap(T[] arr, String edgeCase) {
        testMinHeap(Arrays.asList(arr), edgeCase);
    }

    public static <T extends Comparable<T>> void testMaxHeap(T[] arr, String edgeCase) {
        testMaxHeap(Arrays.asList(arr), edgeCase);
    }

    public static <T extends Comparable<T>> void testMinHeap(List<T> arr, String edgeCase) {
        Objects.requireNonNull(arr, "Input array cannot be null");

        for (int i = 0; i < arr.size(); i++) {
            int leftIndex = HeapHelper.getLeftChildrenIndex(i);
            if (leftIndex < arr.size()) {
                assertTrue(arr.get(leftIndex).compareTo(arr.get(i)) >= 0,
                        String.format(edgeCase + ": Min-heap property violated at index %d: parent=%s, left child=%s",
                                i, arr.get(i), arr.get(leftIndex)));
            }
            if (leftIndex + 1 < arr.size()) {
                assertTrue(arr.get(leftIndex + 1).compareTo(arr.get(i)) >= 0,
                        String.format(edgeCase + ": Min-heap property violated at index %d: parent=%s, right child=%s",
                                i, arr.get(i), arr.get(leftIndex + 1)));
            }
        }
    }

    public static <T extends Comparable<T>> void testMaxHeap(List<T> arr, String edgeCase) {
        Objects.requireNonNull(arr, "Input array cannot be null");

        for (int i = 0; i < arr.size(); i++) {
            int leftIndex = HeapHelper.getLeftChildrenIndex(i);
            if (leftIndex < arr.size()) {
                assertTrue(arr.get(leftIndex).compareTo(arr.get(i)) <= 0,
                        String.format(edgeCase + ": Min-heap property violated at index %d: parent=%s, left child=%s",
                                i, arr.get(i), arr.get(leftIndex)));
            }
            if (leftIndex + 1 < arr.size()) {
                assertTrue(arr.get(leftIndex + 1).compareTo(arr.get(i)) <= 0,
                        String.format(edgeCase + ": Min-heap property violated at index %d: parent=%s, right child=%s",
                                i, arr.get(i), arr.get(leftIndex + 1)));
            }
        }
    }
}
