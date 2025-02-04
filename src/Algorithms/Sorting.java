package Algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Sorting {
    public static <T extends Comparable<T>> T[] bubbleSort (T[] arr) {
        boolean isSorted = false;
        T[] sorted = arr.clone();

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < sorted.length - 1; i++) {
                if (sorted[i].compareTo(sorted[i + 1]) > 0) {
                    T temp = sorted[i];
                    sorted[i] = sorted[i + 1];
                    sorted[i + 1] = temp;
                    isSorted = false;
                }
            }
        }

        return sorted;
    }

    public static <T extends Comparable<T>> T[] mergeSort (T[] arr, boolean clone) {
        T[] newArr = clone ? arr.clone() : arr;
        return mergeSort(newArr);
    }

    public static <T extends Comparable<T>> T[] mergeSort (T[] arr) {
        if (arr.length == 0 || arr.length == 1) return arr;

        int mid = arr.length / 2;
        T[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
        T[] right = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));

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
}
