package Algorithms;

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
        for (int i = 0; i < arr.length; i++) {
            if (Utils.equals(arr[i], item)) {
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
        return binarySearch(arr, item, 0, arr.length);
    }

    private static <T extends Comparable<T>> int binarySearch (T[] arr, T item, int low, int high) {
        if (high == low) return -1;
        int mid = (low + high) / 2;

        if (Utils.equals(arr[mid], item)) {
            return mid;
        }

        if (arr[mid].compareTo(item) > 0) {
            return binarySearch(arr, item, low, mid);
        } else {
            return binarySearch(arr, item, mid + 1, high);
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
        if (arr.length == 0) return -1;
        if (Utils.equals(arr[0], item)) return 0;

        for (int i = 1; i < arr.length; i *= 2) {
            if (Utils.equals(arr[i], item)) {
                return i;
            // Check if the item at the currentIndex is already greater than the item
            } else if (arr[i].compareTo(item) > 0) {
                return binarySearch(arr, item, i / 2, i);
            }
        }

        return -1;
    }
}
