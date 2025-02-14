import Algorithms.Sorting;
import Heap.BinaryHeap;
import Heap.HeapHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Integer[]{1, 2, 3, 5, 7, 1});

        System.out.println(heap.toString());
    }
}