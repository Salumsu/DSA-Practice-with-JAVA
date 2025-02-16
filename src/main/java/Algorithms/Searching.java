package Algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Searching {

    /**
     * LINEAR SEARCH <br /> <br />
     *
     * Performs a linear search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to search through.
     * @param item  The target item to find.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search checks each element sequentially until the target is found.
     * It works on both sorted and unsorted arrays but is inefficient for large datasets.
     */
    public static <T extends Comparable<T>> int linearSearch (T[] arr, T item) {
        return linearSearch(Arrays.asList(arr), item);
    }

    /**
     * LINEAR SEARCH <br /> <br />
     *
     * Performs a linear search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The array to search through.
     * @param item  The target item to find.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search checks each element sequentially until the target is found.
     * It works on both sorted and unsorted arrays but is inefficient for large datasets.
     */
    public static <T extends Comparable<T>> int linearSearch (List<T> arr, T item) {
        return linearSearch(arr, item, Comparable::compareTo);
    }

    /**
     * LINEAR SEARCH <br /> <br />
     *
     * Performs a linear search on the given array.
     *
     * @param <T>   The type of Object of elements in the array.
     * @param arr   The array to search through.
     * @param item  The target item to find.
     * @param comparator A comparator instance.
     * @return      The index of the item if found, otherwise -1.
     */
    public static <T extends Object> int linearSearch (T[] arr, T item, Comparator<T> comparator) {
        return linearSearch(Arrays.asList(arr), item, comparator);
    }

    /**
     * LINEAR SEARCH <br /> <br />
     *
     * Performs a linear search on the given array.
     *
     * @param <T>   The type of Object of elements in the array.
     * @param arr   The array to search through.
     * @param item  The target item to find.
     * @param comparator A comparator instance.
     * @return      The index of the item if found, otherwise -1.
     */

    // UNFORTUNATELY, WE NEED TO PASS AN OBJECT WITH SIMILAR VALUE
    public static <T extends Object> int linearSearch (List<T> arr, T item, Comparator<T> comparator) {
        for (int i = 0; i < arr.size(); i++) {
            if (comparator.compare(arr.get(i), item) == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * BINARY SEARCH <br /> <br />
     * Performs a binary search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The **sorted** array to search through.
     * @param item  The target item to find.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search works by repeatedly dividing the search range in half.
     * <b>The array must be sorted before calling this function.</b>
     * If the array is not sorted, the behavior is undefined.
     */
    public static <T extends Comparable<T>> int binarySearch (T[] arr, T item) {
        if (arr.length == 0) return -1;
        return binarySearch(Arrays.asList(arr), item, 0, arr.length);
    }

    /**
     * BINARY SEARCH <br /> <br />
     * Performs a binary search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The **sorted** array to search through.
     * @param item  The target item to find.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search works by repeatedly dividing the search range in half.
     * <b>The array must be sorted before calling this function.</b>
     * If the array is not sorted, the behavior is undefined.
     */
    public static <T extends Comparable<T>> int binarySearch (List<T> arr, T item) {
        if (arr.isEmpty()) return -1;
        return binarySearch(arr, item, 0, arr.size());
    }

    private static <T extends Comparable<T>> int binarySearch (List<T> arr, T item, int low, int high) {
        return binarySearch(arr, item, low, high, Comparable::compareTo);
    }

    /**
     * BINARY SEARCH <br /> <br />
     * Performs a binary search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The **sorted** array to search through.
     * @param item  The target item to find.
     * @param comparator A comparator instance.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search works by repeatedly dividing the search range in half.
     * <b>The array must be sorted before calling this function.</b>
     * If the array is not sorted, the behavior is undefined.
     */
    public static <T extends Object> int binarySearch (T[] arr, T item, Comparator<T> comparator) {
        return binarySearch(Arrays.asList(arr), item, comparator);
    }

    /**
     * BINARY SEARCH <br /> <br />
     * Performs a binary search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The **sorted** array to search through.
     * @param item  The target item to find.
     * @param comparator A comparator instance.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search works by repeatedly dividing the search range in half.
     * <b>The array must be sorted before calling this function.</b>
     * If the array is not sorted, the behavior is undefined.
     */
    public static <T extends Object> int binarySearch (List<T> arr, T item, Comparator<T> comparator) {
        if (arr.isEmpty()) return -1;
        return binarySearch(arr, item, 0, arr.size(), comparator);
    }

    private static <T extends Object> int binarySearch (List<T> arr, T item, int low, int high, Comparator<T> comparator) {
        if (high == low) return -1;
        int mid = (low + high) / 2;

        if (comparator.compare(arr.get(mid), item) == 0) {
            return mid;
        }

        if (comparator.compare(arr.get(mid), item) > 0) {
            return binarySearch(arr, item, low, mid, comparator);
        } else {
            return binarySearch(arr, item, mid + 1, high, comparator);
        }
    }

    /**
     * EXPONENTIAL SEARCH <br /> <br />
     * Performs a exponential search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The **sorted** array to search through.
     * @param item  The target item to find.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search works by checking indices in exponentially increasing steps: 1, 2, 4, 8, ...
     * If the value at the current index exceeds the target,
     * a binary search is performed between the previous index (i / 2) and the current index.
     * <b>The array must be sorted before calling this function.</b>
     * If the array is not sorted, the behavior is undefined.
     */
    public static <T extends Comparable<T>> int exponentialSearch (T[] arr, T item) {
        return exponentialSearch(Arrays.asList(arr), item);
    }

    /**
     * EXPONENTIAL SEARCH <br /> <br />
     * Performs a exponential search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The **sorted** array to search through.
     * @param item  The target item to find.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search works by checking indices in exponentially increasing steps: 1, 2, 4, 8, ...
     * If the value at the current index exceeds the target,
     * a binary search is performed between the previous index (i / 2) and the current index.
     * <b>The array must be sorted before calling this function.</b>
     * If the array is not sorted, the behavior is undefined.
     */
    public static <T extends Comparable<T>> int exponentialSearch (List<T> arr, T item) {
        return exponentialSearch(arr, item, Comparable::compareTo);
    }

    /**
     * EXPONENTIAL SEARCH <br /> <br />
     * Performs a exponential search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The **sorted** array to search through.
     * @param item  The target item to find.
     * @param comparator A comparator instance.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search works by checking indices in exponentially increasing steps: 1, 2, 4, 8, ...
     * If the value at the current index exceeds the target,
     * a binary search is performed between the previous index (i / 2) and the current index.
     * <b>The array must be sorted before calling this function.</b>
     * If the array is not sorted, the behavior is undefined.
     */
    public static <T extends Object> int exponentialSearch (T[] arr, T item, Comparator<T> comparator) {
        return exponentialSearch(Arrays.asList(arr), item, comparator);
    }

    /**
     * EXPONENTIAL SEARCH <br /> <br />
     * Performs a exponential search on the given array.
     *
     * @param <T>   The type of elements in the array, must implement {@link Comparable}.
     * @param arr   The **sorted** array to search through.
     * @param item  The target item to find.
     * @param comparator A comparator instance.
     * @return      The index of the item if found, otherwise -1.
     *
     * This search works by checking indices in exponentially increasing steps: 1, 2, 4, 8, ...
     * If the value at the current index exceeds the target,
     * a binary search is performed between the previous index (i / 2) and the current index.
     * <b>The array must be sorted before calling this function.</b>
     * If the array is not sorted, the behavior is undefined.
     */
    public static <T extends Object> int exponentialSearch (List<T> arr, T item, Comparator<T> comparator) {
        if (arr.isEmpty()) return -1;
        if (comparator.compare(arr.getFirst(), item) == 0) return 0;

        for (int i = 1; i < arr.size(); i *= 2) {
            if (comparator.compare(arr.get(i), item) == 0) {
                return i;
                // Check if the item at the currentIndex is already greater than the item

            } else if ( comparator.compare(arr.get(i), item) > 0) {
                return binarySearch(arr, item, i / 2, i, comparator);
            }
        }

        return -1;
    }
}
