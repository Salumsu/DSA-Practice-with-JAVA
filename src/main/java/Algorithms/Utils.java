package Algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class Utils {

    private Utils() {
        throw new UnsupportedOperationException("Utility class - cannot be instantiated");
    }

    public record SplitArray<T>(List<T> left, List<T> right){}

    /**
     * Splits an array into two halves at the given midpoint.
     *
     * @param <T>  The type of elements in the array, must implement {@link Comparable}.
     * @param arr  The array to be split.
     * @param mid  The index at which to split the array.
     * @return     A {@link SplitArray} containing the two halves.
     */
    public static <T extends Comparable<T>> SplitArray<T> splitArray (T[] arr, Integer mid) {
        return splitArray(Arrays.asList(arr), mid, true);
    }

    /**
     * Splits an array into two halves at the given midpoint.
     *
     * @param <T>  The type of elements in the array, must implement {@link Comparable}.
     * @param arr  The array to be split.
     * @param mid  The index at which to split the array.
     * @return     A {@link SplitArray} containing the two halves.
     */
    public static <T> SplitArray<T> splitArray (List<T> arr, Integer mid) {
        return splitArray(arr, mid, true);
    }

    /**
     * Splits an array into two halves at the given midpoint, with an option to include the midpoint.
     *
     * @param <T>        The type of elements in the array, must implement {@link Comparable}.
     * @param arr        The array to be split.
     * @param mid        The index at which to split the array.
     * @param includeMid {@code true} to include the midpoint in the first half, {@code false} to exclude it.
     * @return           A {@link SplitArray} containing the two halves.
     */
    public static <T> SplitArray<T> splitArray (T[] arr, Integer mid, boolean includeMid) {
        return splitArray(Arrays.asList(arr), mid, includeMid);
    }

    /**
     * Splits an array into two halves at the given midpoint, with an option to include the midpoint.
     *
     * @param <T>        The type of elements in the array, must implement {@link Comparable}.
     * @param arr        The array to be split.
     * @param mid        The index at which to split the array.
     * @param includeMid {@code true} to include the midpoint in the first half, {@code false} to exclude it.
     * @return           A {@link SplitArray} containing the two halves.
     */
    public static <T> SplitArray<T> splitArray (List<T> arr, Integer mid, boolean includeMid) {
        if (mid < 0 || mid > arr.size()) {
            throw new IllegalArgumentException("Invalid mid index");
        }

        List<T> left;
        List<T> right;

        if (arr.isEmpty()) {
            left = new ArrayList<T>();
            right = new ArrayList<T>();
        } else {
            left = new ArrayList<>(arr.subList(0, includeMid ? mid + 1 : mid));
            right = new ArrayList<>(arr.subList(mid + 1, arr.size()));
        }

        return new SplitArray<>(left, right);
    }

    /**
     * Checks if an array is empty or null.
     *
     * @param <T>  The type of elements in the array, must implement {@link Comparable}.
     * @param arr  The array to check.
     * @return     {@code true} if the array is null or has no elements, {@code false} otherwise.
     */
    public static <T> boolean isEmpty (T[] arr) {
        return arr == null || arr.length == 0;
    }

    /**
     * Swaps two elements in an array at the given indices.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array where elements will be swapped.
     * @param left  The index of the first element.
     * @param right The index of the second element.
     */
    public static <T> void swap (T[] arr, Integer left, Integer right) {
        swap(Arrays.asList(arr), left, right);
    }

    /**
     * Swaps two elements in an array at the given indices.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array where elements will be swapped.
     * @param left  The index of the first element.
     * @param right The index of the second element.
     */
    public static <T> void swap (List<T> arr, Integer left, Integer right) {
        if (left >= arr.size() || right >= arr.size()) {
            throw new IndexOutOfBoundsException();
        }

        T temp = arr.get(left);
        arr.set(left, arr.get(right));
        arr.set(right, temp);
    }


    /**
     * Returns the smaller of two comparable elements.
     *
     * @param <T>    The type of elements, must implement {@link Comparable}.
     * @param first  The first element.
     * @param second The second element.
     * @return       The smaller element.
     */
    public static <T extends Comparable<T>> T min (T first, T second) {
        if (first == null || second == null) {
            throw new NullPointerException();
        }
        return first.compareTo(second) < 0 ? first : second;
    }

    /**
     * Determines if two elements should be swapped when sorting in ascending order.
     *
     * @param <T>    The type of elements, must implement {@link Comparable}.
     * @param first  The first element.
     * @param second The second element.
     * @return       {@code true} if the first element is greater than the second, {@code false} otherwise.
     */
    public static <T extends Comparable<T>> boolean shouldSwap (T first, T second) {
        return Utils.shouldSwap(first, second, false, false);
    }

    /**
     * Determines if two elements should be swapped based on sorting order.
     *
     * @param <T>    The type of elements, must implement {@link Comparable}.
     * @param first  The first element.
     * @param second The second element.
     * @param desc   {@code true} for descending order, {@code false} for ascending order.
     * @return       {@code true} if the elements should be swapped, {@code false} otherwise.
     */
    public static <T extends Comparable<T>> boolean shouldSwap(T first, T second, boolean desc) {
        return Utils.shouldSwap(first, second, desc, false);
    }

    /**
     * Determines if two elements should be swapped based on sorting order, with an option to include equal elements.
     *
     * @param <T>          The type of elements, must implement {@link Comparable}.
     * @param first        The first element.
     * @param second       The second element.
     * @param desc         {@code true} for descending order, {@code false} for ascending order.
     * @param includeEqual {@code true} to swap when elements are equal, {@code false} to keep equal elements in place.
     * @return             {@code true} if the elements should be swapped, {@code false} otherwise.
     */
    public static <T extends Comparable<T>> boolean shouldSwap (T first, T second, boolean desc, boolean includeEqual) {
        int compareResult = first.compareTo(second);
        return desc ? compareResult < (includeEqual ? 1 : 0) : compareResult > (includeEqual ? -1 : 0);
    }

    /**
     * Determines if two elements should be swapped when sorting in ascending order.
     *
     * @param <T>    The type of elements, must implement {@link Comparable}.
     * @param first  The first element.
     * @param second The second element.
     * @return       {@code true} if the first element is greater than the second, {@code false} otherwise.
     */
    public static <T extends Object> boolean shouldSwap (T first, T second, Comparator<T> comparator) {
        return Utils.shouldSwap(first, second, comparator, false, false);
    }

    /**
     * Determines if two elements should be swapped based on sorting order.
     *
     * @param <T>    The type of elements, must implement {@link Comparable}.
     * @param first  The first element.
     * @param second The second element.
     * @param desc   {@code true} for descending order, {@code false} for ascending order.
     * @return       {@code true} if the elements should be swapped, {@code false} otherwise.
     */
    public static <T extends Object> boolean shouldSwap(T first, T second, Comparator<T> comparator, boolean desc) {
        return Utils.shouldSwap(first, second, comparator, desc, false);
    }

    /**
     * Determines if two elements should be swapped based on sorting order, with an option to include equal elements.
     *
     * @param <T>          The type of elements, must implement {@link Comparable}.
     * @param first        The first element.
     * @param second       The second element.
     * @param desc         {@code true} for descending order, {@code false} for ascending order.
     * @param includeEqual {@code true} to swap when elements are equal, {@code false} to keep equal elements in place.
     * @return             {@code true} if the elements should be swapped, {@code false} otherwise.
     */
    public static <T extends Object> boolean shouldSwap (T first, T second, Comparator<T> comparator, boolean desc, boolean includeEqual) {
        int compareResult = comparator.compare(first, second);
        return desc ? compareResult < (includeEqual ? 1 : 0) : compareResult > (includeEqual ? -1 : 0);
    }

    /**
     * Checks if two elements are equal.
     *
     * @param <T>    The type of elements, must implement {@link Comparable}.
     * @param first  The first element.
     * @param second The second element.
     * @return       {@code true} if both elements are equal, {@code false} otherwise.
     */
    public static <T extends Comparable<T>> boolean equals (T first, T second) {
        return first.compareTo(second) == 0;
    }

    public static <T extends Comparable<T>> T get(T[] arr, int index) {
        return arr[index];
    }

    public static <T extends Comparable<T>> T get(List<T> arr, int index) {
        return arr.get(index);
    }
}
