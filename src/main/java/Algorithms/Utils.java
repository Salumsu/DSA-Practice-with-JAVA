package Algorithms;

import java.util.Arrays;

public final class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public record SplitArray<T>(T[] left, T[] right){}

    public static <T extends Comparable<T>> SplitArray<T> splitArray (T[] arr, Integer mid) {
        return splitArray(arr, mid, true);
    }

    public static <T extends Comparable<T>> SplitArray<T> splitArray (T[] arr, Integer mid, boolean includeMid) {
        if (mid < 0 || mid > arr.length) {
            throw new IllegalArgumentException("Invalid mid index");
        }

        T[] left;
        T[] right;

        if (isEmpty(arr)) {
            left = Arrays.copyOfRange(arr, 0, 0);
            right = Arrays.copyOfRange(arr, 0, 0);
        } else {
            left = Arrays.copyOfRange(arr, 0, includeMid ? mid + 1 : mid);
            right = Arrays.copyOfRange(arr, mid + 1, arr.length);
        }

        return new SplitArray<>(left, right);
    }

    public static <T extends Comparable<T>> boolean isEmpty (T[] arr) {
        return arr.length == 0;
    }

    public static <T extends Comparable<T>> void swap (T[] arr, Integer left, Integer right) {
        if (left >= arr.length || right >= arr.length) {
            throw new IndexOutOfBoundsException();
        }

        T temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public static <T extends Comparable<T>> T min (T first, T second) {
        if (first == null || second == null) {
            throw new NullPointerException();
        }
        return first.compareTo(second) < 0 ? first : second;
    }

    public static <T extends Comparable<T>> boolean shouldSwap (T first, T second) {
        return Utils.shouldSwap(first, second, false, false);
    }

    public static <T extends Comparable<T>> boolean shouldSwap(T first, T second, boolean desc) {
        return Utils.shouldSwap(first, second, desc, false);
    }

    public static <T extends Comparable<T>> boolean shouldSwap (T first, T second, boolean desc, boolean includeEqual) {
        int compareResult = first.compareTo(second);
        return desc ? compareResult < (includeEqual ? 1 : 0) : compareResult > (includeEqual ? -1 : 0);
    }
}
