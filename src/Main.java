import Algorithms.Sorting;
import Algorithms.Utils;
import HashTable.HashTable;
import LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {10, 20, 30, 100, 2, 11, 11, 2, 1};

        Integer[] sortedArr = Sorting.quickSort2(arr, true);

        System.out.println(Arrays.toString(sortedArr));
    }
}