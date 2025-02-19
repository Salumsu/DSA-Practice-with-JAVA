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

public class Main {
    public static void main(String[] args) {
        AVLTree<Integer> bst = AVLTree.create(new Integer[]{25, 21, 20, 23, 34, 30, 31, 35});

        bst.checkBalance();
        System.out.println(bst.levelOrderTraversal().toString());

        bst.remove(30);
        bst.checkBalance();
        bst.remove(31);

        System.out.println(bst.levelOrderTraversal().toString());
    }
}