import Algorithms.Sorting;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {
                5, 3, 8, 3, 7, 9, 3, 5, 5, 8, 2, 2, 9, 1, 1,
                4, 6, 6, 7, 4, 10, 10, 2, 5, 3, 8, 6, 9, 7, 1
        };

        Integer[] sorted = Sorting.insertionSort(arr, true, true);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(sorted));
    }
}