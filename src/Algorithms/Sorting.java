package Algorithms;

import java.lang.reflect.Array;
import java.util.Arrays;

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
}
