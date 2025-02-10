import Algorithms.Sorting;
import BinaryTree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {
                5, 3, 8, 2, 4, 7, 9
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>(arr);

        ArrayList<Integer> result = bst.levelOrderTraversal();
        System.out.println(result.toString());
        System.out.println(Arrays.toString(result.toArray()));

    }
}