import Algorithms.Sorting;
import Algorithms.Utils;
import HashTable.HashTable;
import LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {10, 22, 1, 13, 100};
        Integer[] sortedArr = Sorting.insertionSort(arr, true);

        System.out.println(Arrays.toString(sortedArr));
    }
}