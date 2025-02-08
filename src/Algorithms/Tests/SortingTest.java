package Algorithms.Tests;

import Algorithms.Sorting;

import java.util.Arrays;

public class SortingTest {
    public static <T extends Comparable<T>> void test (T[] arr, Sorting.SortType type) {
        System.out.println("");
        System.out.println("====== " + type + " SORT ========");
        System.out.println("");

        System.out.println("PARAMETERS: ARR ");
        T[] _arr = arr.clone();
        T[] sortedArr = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(_arr);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(_arr);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(_arr);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(_arr);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(_arr);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(_arr);
            case Sorting.SortType.HEAP -> Sorting.heapSort(_arr);
            default -> throw new Error("Invalid sorting type");
        };

        System.out.println(Arrays.toString(_arr));
        System.out.println(Arrays.toString(sortedArr));

        System.out.println("PARAMETERS: ARR, desc = false");
        _arr = arr.clone();
        sortedArr = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(_arr, false);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(_arr, false);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(_arr, false);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(_arr, false);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(_arr, false);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(_arr, false);
            case Sorting.SortType.HEAP -> Sorting.heapSort(_arr, false);
            default -> throw new Error("Invalid sorting type");
        };
        System.out.println(Arrays.toString(_arr));
        System.out.println(Arrays.toString(sortedArr));

        System.out.println("PARAMETERS: ARR, desc = true");
        _arr = arr.clone();
        sortedArr = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(_arr, true);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(_arr, true);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(_arr, true);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(_arr, true);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(_arr, true);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(_arr, true);
            case Sorting.SortType.HEAP -> Sorting.heapSort(_arr, true);
            default -> throw new Error("Invalid sorting type");
        };
        System.out.println(Arrays.toString(_arr));
        System.out.println(Arrays.toString(sortedArr));

        System.out.println("PARAMETERS: ARR, desc = false, clone = false");
        _arr = arr.clone();
        sortedArr = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(_arr, false, false);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(_arr, false, false);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(_arr, false, false);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(_arr, false, false);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(_arr, false, false);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(_arr, false, false);
            case Sorting.SortType.HEAP -> Sorting.heapSort(_arr, false, false);
            default -> throw new Error("Invalid sorting type");
        };
        System.out.println(Arrays.toString(_arr));
        System.out.println(Arrays.toString(sortedArr));

        System.out.println("PARAMETERS: ARR, desc = false, clone = true");
        _arr = arr.clone();
        sortedArr = switch (type) {
            case Sorting.SortType.BUBBLE -> Sorting.bubbleSort(_arr, false, true);
            case Sorting.SortType.MERGE -> Sorting.mergeSort(_arr, false, true);
            case Sorting.SortType.INSERTION -> Sorting.insertionSort(_arr, false, true);
            case Sorting.SortType.QUICKSORT -> Sorting.quickSort(_arr, false, true);
            case Sorting.SortType.QUICKSORT2 -> Sorting.quickSort2(_arr, false, true);
            case Sorting.SortType.SELECTION -> Sorting.selectionSort(_arr, false, true);
            case Sorting.SortType.HEAP -> Sorting.heapSort(_arr, false, true);
            default -> throw new Error("Invalid sorting type");
        };
        System.out.println(Arrays.toString(_arr));
        System.out.println(Arrays.toString(sortedArr));
    }
}
