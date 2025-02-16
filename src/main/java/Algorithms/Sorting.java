package Algorithms;

import Heap.HeapHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param type  The sorting algorithm to use (defined by an {@code Sorting.SortType}).
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> sort (List<T> arr, SortType type) {
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
    public static <T extends Comparable<T>> List<T> sort (List<T> arr, SortType type, boolean desc) {
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
        T[] newArr = clone ? arr.clone() : arr;
        sort(Arrays.asList(newArr), type, desc, false);
        return newArr;
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
    public static <T extends Comparable<T>> List<T> sort (List<T> arr, SortType type, boolean desc, boolean clone) {
        return sort(arr, type, Comparable::compareTo, desc, clone);
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
    public static <T extends Object> T[] sort (T[] arr, Sorting.SortType type, Comparator<T> comparator) {
        return sort(arr, type, comparator, false, false);
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
    public static <T extends Object> List<T> sort (List<T> arr, Sorting.SortType type, Comparator<T> comparator) {
        return sort(arr, type, comparator, false, false);
    }

    /**
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param type  The sorting algorithm to use (defined by an {@link Sorting.SortType}).
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] sort (T[] arr, Sorting.SortType type, Comparator<T> comparator, boolean desc) {
        return sort(arr, type, comparator, desc, false);
    }

    /**
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param type  The sorting algorithm to use (defined by an {@link Sorting.SortType}).
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> sort (List<T> arr, Sorting.SortType type, Comparator<T> comparator, boolean desc) {
        return sort(arr, type, comparator, desc, false);
    }

    /**
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param type  The sorting algorithm to use (defined by an {@link Sorting.SortType}).
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone  {@code true} to sort a copy of the array, leaving the original unchanged;
     *               {@code false} to sort the array in place.
     * @return      The sorted array (sorted in place).
     */
    public static <T extends Object> T[] sort (T[] arr, Sorting.SortType type, Comparator<T> comparator, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        sort(Arrays.asList(newArr), type, comparator, desc, false);
        return newArr;
    }

    /**
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param type  The sorting algorithm to use (defined by an {@link Sorting.SortType}).
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone  {@code true} to sort a copy of the array, leaving the original unchanged;
     *               {@code false} to sort the array in place.
     * @return      The sorted array (sorted in place).
     */
    public static <T extends Object> List<T> sort (List<T> arr, Sorting.SortType type, Comparator<T> comparator, boolean desc, boolean clone) {
        List<T> newArr = clone ? new ArrayList<>(arr) : arr;
        return switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(newArr, comparator, desc, clone);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(newArr, comparator, desc, clone);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(newArr, comparator, desc, clone);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(newArr, comparator, desc, clone);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(newArr, comparator, desc, clone);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(newArr, comparator, desc, clone);
            case Sorting.SortType.HEAP -> Sorting.heapSort(newArr, comparator, desc, clone);
        };
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
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> bubbleSort (List<T> arr) {
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
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> bubbleSort (List<T> arr, boolean desc) {
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
        bubbleSort(Arrays.asList(newArr), desc, false);
        return newArr;
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
    public static <T extends Comparable<T>> List<T> bubbleSort (List<T> arr, boolean desc, boolean clone) {
        return bubbleSort(arr, Comparable::compareTo, desc, clone);
    }

    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] bubbleSort (T[] arr, Comparator<T> comparator) {
        return bubbleSort(arr, comparator, false, false);
    }

    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> bubbleSort (List<T> arr, Comparator<T> comparator) {
        return bubbleSort(arr, comparator,  false, false);
    }

    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] bubbleSort (T[] arr, Comparator<T> comparator, boolean desc) {
        return bubbleSort(arr, comparator, desc, false);
    }

    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> bubbleSort (List<T> arr, Comparator<T> comparator, boolean desc) {
        return bubbleSort(arr, comparator, desc, false);
    }

    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Object> T[] bubbleSort (T[] arr, Comparator<T> comparator, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        bubbleSort(Arrays.asList(newArr), comparator, desc, false);
        return newArr;
    }

    /**
     * BUBBLE SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Object> List<T> bubbleSort (List<T> arr, Comparator<T> comparator, boolean desc, boolean clone) {
        List<T> newArr = clone ? new ArrayList<>(arr) : arr;
        if (newArr.isEmpty() || newArr.size() == 1) return newArr;

        boolean isSorted = false;

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < newArr.size() - 1; i++) {
                if (Utils.shouldSwap(newArr.get(i), newArr.get(i + 1), comparator, desc)) {
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
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> mergeSort (List<T> arr) {
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
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> mergeSort (List<T> arr, boolean desc) {
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
        T[] newArr = clone ? arr.clone() : arr;

        doMergeSort(Arrays.asList(newArr), desc);

        return newArr;
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
    public static <T extends Comparable<T>> List<T> mergeSort (List<T> arr, boolean desc, boolean clone) {
        return doMergeSort(clone ? new ArrayList<>(arr) : arr, desc);
    }

    private static <T extends Comparable<T>> List<T> doMergeSort (List<T> arr, boolean desc) {
        return doMergeSort(arr, Comparable::compareTo, desc);
    }

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] mergeSort (T[] arr, Comparator<T> comparator) {
        return mergeSort(arr, comparator, false, false);
    }

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> mergeSort (List<T> arr, Comparator<T> comparator) {
        return mergeSort(arr, comparator, false, false);
    }

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] mergeSort (T[] arr, Comparator<T> comparator, boolean desc) {
        return mergeSort(arr, comparator, desc, false);
    }

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> mergeSort (List<T> arr, Comparator<T> comparator, boolean desc) {
        return mergeSort(arr, comparator, desc, false);
    }

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Object> T[] mergeSort (T[] arr, Comparator<T> comparator, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;

        doMergeSort(Arrays.asList(newArr), comparator, desc);

        return newArr;
    }

    /**
     * MERGE SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Object> List<T> mergeSort (List<T> arr, Comparator<T> comparator, boolean desc, boolean clone) {
        return doMergeSort(clone ? new ArrayList<>(arr) : arr, comparator, desc);
    }

    private static <T extends Object> List<T> doMergeSort (List<T> arr, Comparator<T> comparator, boolean desc) {
        if (arr.isEmpty() || arr.size() == 1) return arr;

        int mid = arr.size() / 2 - 1;
        Utils.SplitArray<T> splitArray = Utils.splitArray(arr, mid);
        List<T> left = doMergeSort(splitArray.left(), comparator, desc);
        List<T> right = doMergeSort(splitArray.right(), comparator, desc);

        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (Utils.shouldSwap(left.get(leftIndex), right.get(rightIndex), comparator, desc)) {
                arr.set(leftIndex + rightIndex, right.get(rightIndex++));
            } else {
                arr.set(leftIndex + rightIndex, left.get(leftIndex++));
            }
        }

        boolean isLeftRemaining = leftIndex < left.size();
        List<T> withLeftOver = isLeftRemaining ? left : right;
        int leftOverIndex = isLeftRemaining ? leftIndex : rightIndex;
        int otherIndex = isLeftRemaining ? rightIndex : leftIndex;

        while (leftOverIndex < withLeftOver.size()) {
            arr.set(leftOverIndex + otherIndex, withLeftOver.get(leftOverIndex++));
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
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> insertionSort (List<T> arr) {
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
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> insertionSort (List<T> arr, boolean desc) {
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

        insertionSort(Arrays.asList(newArr), desc, false);

        return newArr;
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
    public static <T extends Comparable<T>> List<T> insertionSort (List<T> arr, boolean desc, boolean clone) {
        return insertionSort(arr, Comparable::compareTo, desc, clone);
    }

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] insertionSort (T[] arr, Comparator<T> comparator) {
        return insertionSort(arr, comparator, false, false);
    }

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> insertionSort (List<T> arr, Comparator<T> comparator) {
        return insertionSort(arr, comparator, false, false);
    }

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] insertionSort (T[] arr, Comparator<T> comparator, boolean desc) {
        return insertionSort(arr, comparator, desc, false);
    }

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> insertionSort (List<T> arr, Comparator<T> comparator, boolean desc) {
        return insertionSort(arr, comparator, desc, false);
    }

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Object> T[] insertionSort (T[] arr, Comparator<T> comparator, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;

        insertionSort(Arrays.asList(newArr), comparator, desc, false);

        return newArr;
    }

    /**
     * INSERTION SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Object> List<T> insertionSort (List<T> arr, Comparator<T> comparator, boolean desc, boolean clone) {
        List<T> newArr = clone ? new ArrayList<>(arr) : arr;
        if (newArr.isEmpty() || newArr.size() == 1) return newArr;

        for (int i = 1; i < newArr.size(); i++) {
            int currentIndex = i;
            T currentItem = newArr.get(currentIndex);
            while (currentIndex > 0 && Utils.shouldSwap(currentItem, newArr.get(currentIndex - 1), comparator, !desc)) {
                newArr.set(currentIndex, newArr.get(currentIndex - 1));
                currentIndex--;
            }

            newArr.set(currentIndex, currentItem);
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
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> quickSort(List<T> arr) {
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
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> quickSort(List<T> arr, boolean desc) {
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
        T[] newArr = clone ? arr.clone() : arr;
        doQuickSort(Arrays.asList(newArr), 0, arr.length - 1, desc);
        return newArr;
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
    public static <T extends Comparable<T>> List<T> quickSort (List<T> arr, boolean desc, boolean clone) {
        return doQuickSort(clone ? new ArrayList<>(arr) : arr, 0, arr.size() - 1, desc);
    }

    private static <T extends Comparable<T>> List<T> doQuickSort(List<T> arr, Integer start, Integer pivotIndex, boolean desc) {
        return doQuickSort(arr, Comparable::compareTo, start, pivotIndex, desc);
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] quickSort(T[] arr, Comparator<T> comparator) {
        return quickSort(arr, comparator, false, false);
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> quickSort(List<T> arr, Comparator<T> comparator) {
        return quickSort(arr, comparator, false, false);
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> T[] quickSort(T[] arr, Comparator<T> comparator, boolean desc) {
        return quickSort(arr, comparator, desc, false);
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Object> List<T> quickSort(List<T> arr, Comparator<T> comparator, boolean desc) {
        return quickSort(arr, comparator, desc, false);
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Object> T[] quickSort (T[] arr, Comparator<T> comparator, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        doQuickSort(Arrays.asList(newArr), comparator, 0, arr.length - 1, desc);
        return newArr;
    }

    /**
     * QUICK SORT <br /><br />
     * Sorts the given array in ascending or descending order, with an option to clone the array.
     *
     * @param <T>   The type of elements in the array.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @param clone {@code true} to sort a copy of the array, leaving the original unchanged;
     *              {@code false} to sort the array in place.
     * @return      The sorted array (in-place or copied based on {@code clone}).
     */
    public static <T extends Object> List<T> quickSort (List<T> arr, Comparator<T> comparator, boolean desc, boolean clone) {
        return doQuickSort(clone ? new ArrayList<>(arr) : arr, comparator, 0, arr.size() - 1, desc);
    }

    private static <T extends Object> List<T> doQuickSort(List<T> arr, Comparator<T> comparator, Integer start, Integer pivotIndex, boolean desc) {
        if (start >= pivotIndex) return arr;

        int left = start;
        int right = pivotIndex - 1;

        while (left <= right) {
            while (Utils.shouldSwap(arr.get(left), arr.get(pivotIndex), comparator, !desc, true) && left <= right) {
                left++;
            }

            while (right >= 0 && Utils.shouldSwap(arr.get(right), arr.get(pivotIndex), comparator, desc, true) && left <= right) {
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

        doQuickSort(arr, comparator, start, left - 1, desc);
        doQuickSort(arr, comparator, left + 1, pivotIndex, desc);

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
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> quickSort2(List<T> arr) {
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
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> quickSort2(List<T> arr, boolean desc) {
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
        T[] newArr = clone ? arr.clone() : arr;
        doQuickSort2(Arrays.asList(newArr), 0, arr.length - 1, desc);
        return newArr;
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
    public static <T extends Comparable<T>> List<T> quickSort2 (List<T> arr, boolean desc, boolean clone) {
        return doQuickSort2(clone ? new ArrayList<>(arr) : arr, 0, arr.size() - 1, desc);
    }

    private static <T extends Comparable<T>> List<T> doQuickSort2(List<T> arr, Integer start, Integer pivotIndex, boolean desc) {
        return doQuickSort2(arr, Comparable::compareTo, start, pivotIndex, desc);
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
    public static <T extends Object> T[] quickSort2(T[] arr, Comparator<T> comparator) {
        return quickSort2(arr, comparator, false, false);
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
    public static <T extends Object> List<T> quickSort2(List<T> arr, Comparator<T> comparator) {
        return quickSort2(arr, comparator, false, false);
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
    public static <T extends Object> T[] quickSort2(T[] arr, Comparator<T> comparator, boolean desc) {
        return quickSort2(arr, comparator, desc, false);
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
    public static <T extends Object> List<T> quickSort2(List<T> arr, Comparator<T> comparator, boolean desc) {
        return quickSort2(arr, comparator, desc, false);
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
    public static <T extends Object> T[] quickSort2 (T[] arr, Comparator<T> comparator, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        doQuickSort2(Arrays.asList(newArr), comparator, 0, arr.length - 1, desc);
        return newArr;
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
    public static <T extends Object> List<T> quickSort2 (List<T> arr, Comparator<T> comparator, boolean desc, boolean clone) {
        return doQuickSort2(clone ? new ArrayList<>(arr) : arr, comparator, 0, arr.size() - 1, desc);
    }

    private static <T extends Object> List<T> doQuickSort2(List<T> arr, Comparator<T> comparator, Integer start, Integer pivotIndex, boolean desc) {
        if (start >= pivotIndex) return arr;

        int j = start - 1;

        for (int i = start; i < pivotIndex; i++) {
            if (Utils.shouldSwap(arr.get(i), arr.get(pivotIndex), comparator, !desc, true)) {
                Utils.swap(arr, i, ++j);
            }
        }

        Utils.swap(arr, pivotIndex, ++j);

        doQuickSort2(arr, comparator, 0, j - 1, desc);
        doQuickSort2(arr, comparator, j + 1, pivotIndex, desc);

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
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> selectionSort (List<T> arr) {
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
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> selectionSort (List<T> arr, boolean desc) {
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
        selectionSort(Arrays.asList(newArr), desc, false);
        return newArr;
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
    public static <T extends Comparable<T>> List<T> selectionSort (List<T> arr, boolean desc, boolean clone) {
        return selectionSort(arr, Comparable::compareTo, desc, clone);
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
    public static <T extends Object> T[] selectionSort (T[] arr, Comparator<T> comparator) {
        return selectionSort(arr, comparator, false, false);
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
    public static <T extends Object> List<T> selectionSort (List<T> arr, Comparator<T> comparator) {
        return selectionSort(arr, comparator, false, false);
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
    public static <T extends Object> T[] selectionSort (T[] arr, Comparator<T> comparator, boolean desc) {
        return selectionSort(arr, comparator, desc, false);
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
    public static <T extends Object> List<T> selectionSort (List<T> arr, Comparator<T> comparator, boolean desc) {
        return selectionSort(arr, comparator, desc, false);
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
    public static <T extends Object> T[] selectionSort (T[] arr, Comparator<T> comparator, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        selectionSort(Arrays.asList(newArr), comparator, desc, false);
        return newArr;
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
    public static <T extends Object> List<T> selectionSort (List<T> arr, Comparator<T> comparator, boolean desc, boolean clone) {
        List<T> newArr = clone ? new ArrayList(arr) : arr;
        if (newArr.isEmpty() || newArr.size() == 1) return newArr;

        for (int i = 0; i < newArr.size() - 1; i++) {
            int candidateIndex = i;

            for (int j = i + 1; j < newArr.size(); j++) {
                if (Utils.shouldSwap(newArr.get(j), newArr.get(candidateIndex), comparator, !desc)) {
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
     * Sorts the given array in ascending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> heapSort (List<T> arr) {
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
     * Sorts the given array in ascending or descending order.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to be sorted.
     * @param desc  {@code true} for descending order, {@code false} for ascending.
     * @return      The sorted array (sorted in place).
     *
     * <b>Note:</b> This method modifies the input array.
     */
    public static <T extends Comparable<T>> List<T> heapSort (List<T> arr, boolean desc) {
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
        heapSort(Arrays.asList(newArr), desc, false);
        return newArr;
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
    public static <T extends Comparable<T>> List<T> heapSort (List<T> arr, boolean desc, boolean clone) {
        return heapSort(arr, Comparable::compareTo, desc, clone);
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
    public static <T extends Object> T[] heapSort (T[] arr, Comparator<T> comparator) {
        return heapSort(arr, comparator, false, false);
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
    public static <T extends Object> List<T> heapSort (List<T> arr, Comparator<T> comparator) {
        return heapSort(arr, comparator, false, false);
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
    public static <T extends Object> T[] heapSort (T[] arr, Comparator<T> comparator, boolean desc) {
        return heapSort(arr, comparator, desc, false);
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
    public static <T extends Object> List<T> heapSort (List<T> arr, Comparator<T> comparator, boolean desc) {
        return heapSort(arr, comparator, desc, false);
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
    public static <T extends Object> T[] heapSort (T[] arr, Comparator<T> comparator, boolean desc, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        heapSort(Arrays.asList(newArr), comparator, desc, false);
        return newArr;
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
    public static <T extends Object> List<T> heapSort (List<T> arr, Comparator<T> comparator, boolean desc, boolean clone) {
        List<T> newArr = clone ? new ArrayList<>(arr) : arr;
        if (newArr.isEmpty() || newArr.size() == 1) return newArr;

        HeapHelper.heapify(desc, newArr, comparator);
        int sortedCount = 1;

        while (sortedCount < newArr.size()) {
            Utils.swap(newArr, 0, newArr.size() - sortedCount);
            HeapHelper.siftDown(desc, newArr, comparator, 0, newArr.size() - sortedCount);
            sortedCount++;
        }

        return newArr;
    }
}
