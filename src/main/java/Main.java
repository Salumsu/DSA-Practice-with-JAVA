import Algorithms.Searching;
import Algorithms.Sorting;
import Heap.BinaryHeap;
import Heap.HeapHelper;
import LinkedList.SinglyLinkedList.SinglyLinkedList;
import Trees.BinarySearchTree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = BinarySearchTree.create(new Integer[]{3, 1, 2, 0});
        System.out.println(bst.inOrderTraversal().toString());
    }
}