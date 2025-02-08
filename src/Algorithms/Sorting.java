package Algorithms;

import Heap.BinaryHeap;

import java.util.Arrays;

/**
 * A utility class that provides sorting algorithms.
 * <p>
 * This class can only sort elements that implement {@link Comparable},
 * such as {@link Integer}, {@link String}, or any other class that
 * implements {@code Comparable<T>}.
 * </p>
 *
 * @author Salumsu
 */
public class Sorting {
    public static <T extends Comparable<T>> T[] bubbleSort (T[] arr, boolean clone) {
         return bubbleSort(clone ? arr.clone() : arr);
    }

    public static <T extends Comparable<T>> T[] bubbleSort (T[] arr) {
        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }
        }

        return arr;
    }

    public static <T extends Comparable<T>> T[] mergeSort (T[] arr, boolean clone) {
        return mergeSort(clone ? arr.clone() : arr);
    }

    public static <T extends Comparable<T>> T[] mergeSort (T[] arr) {
        if (arr.length == 0 || arr.length == 1) return arr;

        int mid = arr.length / 2;
        Utils.SplitArray<T> splitArray = Utils.splitArray(arr, mid);
        T[] left = mergeSort(splitArray.left());
        T[] right = mergeSort(splitArray.right());

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex].compareTo(right[rightIndex]) < 0) {
                arr[leftIndex + rightIndex] = left[leftIndex++];
            } else {
                arr[leftIndex + rightIndex] = right[rightIndex++];
            }
        }

        boolean isLeftRemaining = leftIndex < left.length;
        T[] withLeftOver = isLeftRemaining ? left : right;
        int leftOverIndex = isLeftRemaining ? leftIndex : rightIndex;
        int otherIndex = isLeftRemaining ? rightIndex : leftIndex;

        while (leftOverIndex < withLeftOver.length) {
            arr[leftOverIndex + otherIndex] = withLeftOver[leftOverIndex++];
        }

        return arr;
    }

    public static <T extends Comparable<T>> T[] insertionSort (T[] arr, boolean clone) {
        return insertionSort(clone ? arr.clone() : arr);
    }

    public static <T extends Comparable<T>> T[] insertionSort (T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int currentIndex = i;
            T currentItem = arr[currentIndex];
            while (currentIndex > 0 && currentItem.compareTo(arr[currentIndex - 1]) < 0) {
                arr[currentIndex] = arr[currentIndex - 1];
                currentIndex--;
            }

            arr[currentIndex] = currentItem;
        }

        return arr;
    }

    public static <T extends Comparable<T>> T[] quickSort(T[] arr, boolean clone) {
        return quickSort(clone ? arr.clone() : arr);
    }

    public static <T extends Comparable<T>> T[] quickSort(T[] arr) {
        return doQuickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> T[] doQuickSort(T[] arr, Integer start, Integer pivotIndex) {
        if (start >= pivotIndex) return arr;

        int left = start;
        int right = pivotIndex - 1;

        while (left <= right) {
            while (arr[left].compareTo(arr[pivotIndex]) <= 0 && left <= right) {
                left++;
            }
            while (right >= 0 && arr[right].compareTo(arr[pivotIndex]) >= 0 && left <= right) {
                right--;
            }

            if (right < left) {
                break;
            }

            Utils.swap(arr, left, right);
        }

        if (left != pivotIndex) {
            Utils.swap(arr, left, pivotIndex);
        }

        doQuickSort(arr, start, left - 1);
        doQuickSort(arr, left + 1, pivotIndex);

        return arr;
    }

    public static <T extends Comparable<T>> T[] quickSort2(T[] arr, boolean clone) {
        return quickSort2(clone ? arr.clone() : arr);
    }

    public static <T extends Comparable<T>> T[] quickSort2(T[] arr) {
        return doQuickSort2(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> T[] doQuickSort2(T[] arr, Integer start, Integer pivotIndex) {
        if (start >= pivotIndex) return arr;

        int j = start - 1;

        for (int i = start; i < pivotIndex; i++) {
            if (arr[i].compareTo(arr[pivotIndex]) <= 0) {
                Utils.swap(arr, i, ++j);
            }
        }

        Utils.swap(arr, pivotIndex, ++j);

        doQuickSort2(arr, 0, j - 1);
        doQuickSort2(arr, j + 1, pivotIndex);

        return arr;
    }

    public static <T extends Comparable<T>> T[] selectionSort (T[] arr, boolean clone) {
        return selectionSort(clone ? arr.clone() : arr);
    }

    public static <T extends Comparable<T>> T[] selectionSort (T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int candidateIndex = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[candidateIndex]) < 0) {
                    candidateIndex = j;
                }
            }

            Utils.swap(arr, candidateIndex, i);
        }

        return arr;
    }

    public static <T extends Comparable<T>> T[] heapSort (T[] arr, boolean clone) {
        return heapSort(clone ? arr.clone() : arr);
    }
    public static <T extends Comparable<T>> T[] heapSort (T[] arr) {
        if (arr.length == 0 || arr.length == 1) return arr;

        BinaryHeap binaryHeap = new BinaryHeap(false);

        binaryHeap.heapify(arr);
        int sortedCount = 1;

        while (sortedCount < arr.length) {
            Utils.swap(arr, 0, arr.length - sortedCount);
            binaryHeap.siftDown(arr, 0, arr.length - sortedCount);
            sortedCount++;
        }

        return arr;
    }
}
