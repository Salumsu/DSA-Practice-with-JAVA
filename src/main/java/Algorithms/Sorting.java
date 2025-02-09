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
    public enum SortType {
        BUBBLE,
        MERGE,
        INSERTION,
        QUICKSORT,
        QUICKSORT2,
        SELECTION,
        HEAP,
    }

    /**
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param type  The sorting algorithm to use (defined by an {@code Sorting.SortType}).
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] sort (T[] arr, SortType type) {
        return sort(arr, type, false, false);
    }

    /**
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param type  The sorting algorithm to use (defined by an {@link SortType}).
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] sort (T[] arr, SortType type, boolean desc) {
        return sort(arr, type, desc, false);
    }

    /**
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param type  The sorting algorithm to use (defined by an {@link SortType}).
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone  {@code true} to sort a copy of the array, leaving the original unchanged;
     *               {@code false} to sort the array in place.
     * @return      The sorted array (sorted in place).
     */
    public static <T extends Comparable<T>> T[] sort (T[] arr, SortType type, boolean desc, boolean clone) {
        T[] sortedArr = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(arr, desc, clone);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(arr, desc, clone);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(arr, desc, clone);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(arr, desc, clone);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(arr, desc, clone);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(arr, desc, clone);
            case Sorting.SortType.HEAP -> Sorting.heapSort(arr, desc, clone);
        };

        return sortedArr;
    }


    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] bubbleSort (T[] arr) {
        return bubbleSort(arr, false, false);
    }

    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] bubbleSort (T[] arr, boolean desc) {
        return bubbleSort(arr, desc, false);
    }

    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
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

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] mergeSort (T[] arr) {
        return mergeSort(arr, false, false);
    }

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] mergeSort (T[] arr, boolean desc) {
        return mergeSort(arr, desc, false);
    }

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Comparable<T>> T[] mergeSort (T[] arr, boolean desc, boolean clone) {
        return doMergeSort(clone ? arr.clone() : arr, desc);
    }

    private static <T extends Comparable<T>> T[] doMergeSort (T[] arr, boolean desc) {
        if (arr.length == 0 || arr.length == 1) return arr;

        int mid = arr.length / 2 - 1;
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

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] insertionSort (T[] arr) {
        return insertionSort(arr, false, false);
    }

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] insertionSort (T[] arr, boolean desc) {
        return insertionSort(arr, desc, false);
    }

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Comparable<T>> T[] insertionSort (T[] arr, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        if (newArr.length == 0 || newArr.length == 1) return newArr;

        for (int i = 1; i < newArr.length; i++) {
            int currentIndex = i;
            T currentItem = newArr[currentIndex];
            while (currentIndex > 0 && Utils.shouldSwap(currentItem, newArr[currentIndex - 1], !desc)) {
                newArr[currentIndex] = newArr[currentIndex - 1];
                currentIndex--;
            }

            newArr[currentIndex] = currentItem;
        }

        return newArr;
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] quickSort(T[] arr) {
        return quickSort(arr, false, false);
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] quickSort(T[] arr, boolean desc) {
        return quickSort(arr, desc, false);
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
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

    /**
     * QUICK SORT 2 <br /><br />
     * This is just a different implementation of {@code quickSort} <br /> <br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] quickSort2(T[] arr) {
        return quickSort2(arr, false, false);
    }

    /**
     * QUICK SORT 2 <br /><br />
     * This is just a different implementation of {@code quickSort} <br /> <br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] quickSort2(T[] arr, boolean desc) {
        return quickSort2(arr, desc, false);
    }

    /**
     * QUICK SORT 2 <br /><br />
     * This is just a different implementation of {@code quickSort} <br /> <br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
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

    /**
     * SELECTION SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] selectionSort (T[] arr) {
        return selectionSort(arr, false, false);
    }

    /**
     * SELECTION SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] selectionSort (T[] arr, boolean desc) {
        return selectionSort(arr, desc, false);
    }

    /**
     * SELECTION SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Comparable<T>> T[] selectionSort (T[] arr, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        if (newArr.length == 0 || newArr.length == 1) return newArr;

        for (int i = 0; i < newArr.length - 1; i++) {
            int candidateIndex = i;

            for (int j = i + 1; j < newArr.length; j++) {
                if (Utils.shouldSwap(newArr[j], newArr[candidateIndex], !desc)) {
                    candidateIndex = j;
                }
            }

            Utils.swap(newArr, candidateIndex, i);
        }

        return newArr;
    }

    /**
     * HEAP SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] heapSort (T[] arr) {
        return heapSort(arr, false, false);
    }

    /**
     * HEAP SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> T[] heapSort (T[] arr, boolean desc) {
        return heapSort(arr, desc, false);
    }

    /**
     * HEAP SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Comparable<T>> T[] heapSort (T[] arr, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        if (newArr.length == 0 || newArr.length == 1) return newArr;

        BinaryHeap binaryHeap = new BinaryHeap(desc);

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
