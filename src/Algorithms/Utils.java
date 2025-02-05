package Algorithms;

import java.util.Arrays;

public class Utils {
    public record SplitArray<T>(T[] left, T[] right){}

    public static <T extends Comparable<T>> SplitArray<T> splitArray (T[] arr, Integer mid) {
        return splitArray(arr, mid, true);
    }

    public static <T extends Comparable<T>> SplitArray<T> splitArray (T[] arr, Integer mid, boolean includeMid) {
        if (mid < 0 || mid > arr.length) {
            throw new IllegalArgumentException("Invalid mid index");
        }

        T[] left = Arrays.copyOfRange(arr, 0, mid);
        T[] right = Arrays.copyOfRange(arr, includeMid ? mid : mid + 1, arr.length);

        return new SplitArray<>(left, right);
    }

}
