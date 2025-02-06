import Algorithms.Sorting;
import Algorithms.Utils;
import HashTable.HashTable;
import LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] arr = {"zebra", "apple", "banana", "zebra"};

        String[] sortedArr = Sorting.quickSort(arr, true);

        System.out.println(Arrays.toString(sortedArr));
    }
}