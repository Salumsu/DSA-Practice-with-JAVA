import Algorithms.Searching;
import Algorithms.Sorting;
import Heap.BinaryHeap;
import Heap.HeapHelper;
import LinkedList.SinglyLinkedList.SinglyLinkedList;
import Trees.AVLTree.AVLTree;
import Trees.BinarySearchTree.BinarySearchTree;
import Trees.RedBlackTree.RedBlackTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = RedBlackTree.create(new Integer[]{7, 3, 18, 10, 22, 8, 11, 26});

        System.out.println(tree.levelOrderTraversal().toString());
    }
}