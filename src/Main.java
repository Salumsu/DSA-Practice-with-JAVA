import Algorithms.Sorting;
import Algorithms.Utils;
import HashTable.HashTable;
import LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {
                12, -5, 78, 23, 9, 45, -32, 67, 89, 21,
                34, -76, 56, 90, 3, -18, 27, 64, -99, 50,
                11, 83, -45, 29, 72, -60, 38, 95, -12, 47
        };
        Integer[] sortedArr = Sorting.quickSort(arr, true);

        System.out.println(Arrays.toString(sortedArr));
    }
}