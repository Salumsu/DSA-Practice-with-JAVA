package Algorithms;

import Heap.BinaryHeap;

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
    // BUBBLE SORT
    public static <T extends Comparable<T>> T[] bubbleSort (T[] arr) {
        return bubbleSort(arr, false, false);
    }

    public static <T extends Comparable<T>> T[] bubbleSort (T[] arr, boolean desc) {
        return bubbleSort(arr, desc, false);
    }

    public static <T extends Comparable<T>> T[] bubbleSort (T[] arr, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        if (newArr.length == 0 || newArr.length == 1) return newArr;

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < newArr.length - 1; i++) {
                if (Utils.shouldSwap(newArr[i], newArr[i + 1], desc)) {
                    Utils.swap(newArr, i, i + 1);
                    isSorted = false;
                }
            }
        }

        return newArr;
    }

    // MERGE SORT
    public static <T extends Comparable<T>> T[] mergeSort (T[] arr) {
        return mergeSort(arr, false, false);
    }

    public static <T extends Comparable<T>> T[] mergeSort (T[] arr, boolean desc) {
        return mergeSort(arr, desc, false);
    }

    public static <T extends Comparable<T>> T[] mergeSort (T[] arr, boolean desc, boolean clone) {
        return doMergeSort(clone ? arr.clone() : arr, desc);
    }

    private static <T extends Comparable<T>> T[] doMergeSort (T[] arr, boolean desc) {
        if (arr.length == 0 || arr.length == 1) return arr;

        int mid = arr.length / 2;
        Utils.SplitArray<T> splitArray = Utils.splitArray(arr, mid);
        T[] left = doMergeSort(splitArray.left(), desc);
        T[] right = doMergeSort(splitArray.right(), desc);

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (Utils.shouldSwap(left[leftIndex], right[rightIndex], desc)) {
                arr[leftIndex + rightIndex] = right[rightIndex++];
            } else {
                arr[leftIndex + rightIndex] = left[leftIndex++];
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

    // INSERTION SORT
    public static <T extends Comparable<T>> T[] insertionSort (T[] arr) {
        return insertionSort(arr, false, false);
    }

    public static <T extends Comparable<T>> T[] insertionSort (T[] arr, boolean desc) {
        return insertionSort(arr, desc, false);
    }

    public static <T extends Comparable<T>> T[] insertionSort (T[] arr, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        if (newArr.length == 0 || newArr.length == 1) return newArr;

        for (int i = 1; i < newArr.length; i++) {
            int currentIndex = i;
            T currentItem = newArr[currentIndex];
            while (currentIndex > 0 && Utils.shouldSwap(currentItem, arr[currentIndex - 1], !desc)) {
                newArr[currentIndex] = newArr[currentIndex - 1];
                currentIndex--;
            }

            newArr[currentIndex] = currentItem;
        }

        return newArr;
    }

    // QUICK SORT
    public static <T extends Comparable<T>> T[] quickSort(T[] arr) {
        return quickSort(arr, false, false);
    }

    public static <T extends Comparable<T>> T[] quickSort(T[] arr, boolean desc) {
        return quickSort(arr, desc, false);
    }

    public static <T extends Comparable<T>> T[] quickSort (T[] arr, boolean desc, boolean clone) {
        return doQuickSort(clone ? arr.clone() : arr, 0, arr.length - 1, desc);
    }


    private static <T extends Comparable<T>> T[] doQuickSort(T[] arr, Integer start, Integer pivotIndex, boolean desc) {
        if (start >= pivotIndex) return arr;

        int left = start;
        int right = pivotIndex - 1;

        while (left <= right) {
            while (Utils.shouldSwap(arr[left], arr[pivotIndex], !desc, true) && left <= right) {
                left++;
            }

            while (right >= 0 && Utils.shouldSwap(arr[right], arr[pivotIndex], desc, true) && left <= right) {
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

        doQuickSort(arr, start, left - 1, desc);
        doQuickSort(arr, left + 1, pivotIndex, desc);

        return arr;
    }

    // QUICK SORT 2
    public static <T extends Comparable<T>> T[] quickSort2(T[] arr) {
        return quickSort2(arr, false, false);
    }

    public static <T extends Comparable<T>> T[] quickSort2(T[] arr, boolean desc) {
        return quickSort2(arr, desc, false);
    }

    public static <T extends Comparable<T>> T[] quickSort2 (T[] arr, boolean desc, boolean clone) {
        return doQuickSort2(clone ? arr.clone() : arr, 0, arr.length - 1, desc);
    }

    private static <T extends Comparable<T>> T[] doQuickSort2(T[] arr, Integer start, Integer pivotIndex, boolean desc) {
        if (start >= pivotIndex) return arr;

        int j = start - 1;

        for (int i = start; i < pivotIndex; i++) {
            if (Utils.shouldSwap(arr[i], arr[pivotIndex], !desc, true)) {
                Utils.swap(arr, i, ++j);
            }
        }

        Utils.swap(arr, pivotIndex, ++j);

        doQuickSort2(arr, 0, j - 1, desc);
        doQuickSort2(arr, j + 1, pivotIndex, desc);

        return arr;
    }

    // SELECTION SORT
    public static <T extends Comparable<T>> T[] selectionSort (T[] arr) {
        return selectionSort(arr, false, false);
    }

    public static <T extends Comparable<T>> T[] selectionSort (T[] arr, boolean desc) {
        return selectionSort(arr, desc, false);
    }

    public static <T extends Comparable<T>> T[] selectionSort (T[] arr, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        if (newArr.length == 0 || newArr.length == 1) return newArr;

        for (int i = 0; i < newArr.length - 1; i++) {
            int candidateIndex = i;

            for (int j = i + 1; j < newArr.length; j++) {
                if (Utils.shouldSwap(newArr[j], newArr[candidateIndex], desc)) {
                    candidateIndex = j;
                }
            }

            Utils.swap(newArr, candidateIndex, i);
        }

        return newArr;
    }

    // HEAP SORT
    public static <T extends Comparable<T>> T[] heapSort (T[] arr) {
        return heapSort(arr, false, false);
    }

    public static <T extends Comparable<T>> T[] heapSort (T[] arr, boolean desc) {
        return heapSort(arr, desc, false);
    }

    public static <T extends Comparable<T>> T[] heapSort (T[] arr, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        if (newArr.length == 0 || newArr.length == 1) return newArr;

        BinaryHeap binaryHeap = new BinaryHeap(!desc);

        binaryHeap.heapify(newArr);
        int sortedCount = 1;

        while (sortedCount < newArr.length) {
            Utils.swap(newArr, 0, newArr.length - sortedCount);
            binaryHeap.siftDown(newArr, 0, newArr.length - sortedCount);
            sortedCount++;
        }

        return newArr;
    }
}
