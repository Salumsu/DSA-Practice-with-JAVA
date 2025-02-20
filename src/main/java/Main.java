import Algorithms.Searching;
import Algorithms.Sorting;
import Heap.BinaryHeap;
import Heap.HeapHelper;
import LinkedList.SinglyLinkedList.SinglyLinkedList;
import Trees.AVLTree.AVLTree;
import Trees.BinarySearchTree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int start = 10;
        int step = -1;  // Can be positive or negative
        long count = 5; // Number of elements

        List<Integer> list = IntStream.iterate(start, n -> n + step)
                .limit(count)
                .boxed()
                .toList();

        System.out.println(list);
    }
}