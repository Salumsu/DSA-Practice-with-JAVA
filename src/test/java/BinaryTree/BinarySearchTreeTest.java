package BinaryTree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void testEmptyTree () {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        ArrayList<Integer> result = bst.inOrderTraversal();

        assertArrayEquals(new ArrayList<Integer>().toArray(), result.toArray());
    }

    @Test
    void testSingleInitialValue () {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(1);
        ArrayList<Integer> result = bst.inOrderTraversal();

        assertEquals(result.getFirst(), 1);
    }

    static Stream<Arguments> inOrderTraversalCases() {
        return Stream.of(
                Arguments.of(new Integer[]{2, 1, 3}, new Integer[]{1, 2, 3}),
                Arguments.of(new Integer[]{}, new Integer[]{}),
                Arguments.of(new Integer[]{5}, new Integer[]{5}),
                Arguments.of(new Integer[]{5, 4, 3, 2, 1}, new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of(new Integer[]{5, 3, 7, 3, 6, 8}, new Integer[]{3, 3, 5, 6, 7, 8}),
                Arguments.of(new Integer[]{5, 3, 7, 3, 6, 8}, new Integer[]{3, 3, 5, 6, 7, 8}),
                Arguments.of(new Integer[]{10, 5, 15, 2, 7, 12, 18}, new Integer[]{2, 5, 7, 10, 12, 15, 18}),
                Arguments.of(new String[]{"dog", "cat", "elephant", "ant", "zebra"}, new String[]{"ant", "cat", "dog", "elephant", "zebra"}),
                Arguments.of(new String[]{}, new String[]{}),
                Arguments.of(new String[]{"apple"}, new String[]{"apple"}),
                Arguments.of(new String[]{"banana", "apple", "cherry"}, new String[]{"apple", "banana", "cherry"}),
                Arguments.of(new String[]{"z", "y", "x", "w", "v"}, new String[]{"v", "w", "x", "y", "z"}),
                Arguments.of(new String[]{"giraffe", "elephant", "hippo", "elephant", "antelope"}, new String[]{"antelope", "elephant", "elephant", "giraffe", "hippo"}),
                Arguments.of(new String[]{"sun", "moon", "star", "planet", "comet"}, new String[]{"comet", "moon", "planet", "star", "sun"})
        );
    }

    @ParameterizedTest
    @MethodSource("inOrderTraversalCases")
    <T extends Comparable<T>> void testInOrderTraversal (T[] input, T[] expectedOutput) {
        BinarySearchTree<T> bst = new BinarySearchTree<>(input);
        ArrayList<T> output = bst.inOrderTraversal();

        assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    void testInOrderTraversalPrint() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Integer[]{});
        ArrayList<Integer> output = bst.inOrderTraversal(true);

        assertNull(output);
    }

    static Stream<Arguments> preOrderTraversalCases() {
        return Stream.of(
                Arguments.of(new Integer[]{2, 1, 3}, new Integer[]{2, 1, 3}),
                Arguments.of(new Integer[]{}, new Integer[]{}),
                Arguments.of(new Integer[]{5}, new Integer[]{5}),
                Arguments.of(new Integer[]{5, 4, 3, 2, 1}, new Integer[]{5, 4, 3, 2, 1}),
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of(new Integer[]{5, 3, 7, 3, 6, 8}, new Integer[]{5, 3, 3, 7, 6, 8}),
                Arguments.of(new Integer[]{10, 5, 15, 2, 7, 12, 18}, new Integer[]{10, 5, 2, 7, 15, 12, 18}),
                Arguments.of(new String[]{"dog", "cat", "elephant", "ant", "zebra"}, new String[]{"dog", "cat", "ant", "elephant", "zebra"}),
                Arguments.of(new String[]{}, new String[]{}),
                Arguments.of(new String[]{"apple"}, new String[]{"apple"}),
                Arguments.of(new String[]{"banana", "apple", "cherry"}, new String[]{"banana", "apple", "cherry"}),
                Arguments.of(new String[]{"z", "y", "x", "w", "v"}, new String[]{"z", "y", "x", "w", "v"}),
                Arguments.of(new String[]{"giraffe", "elephant", "hippo", "elephant", "antelope"},
                        new String[]{"giraffe", "elephant", "antelope", "elephant", "hippo"}),
                Arguments.of(new String[]{"sun", "moon", "star", "planet", "comet"},
                        new String[]{"sun", "moon", "comet", "star", "planet"})
        );
    }

    @ParameterizedTest
    @MethodSource("preOrderTraversalCases")
    <T extends Comparable<T>> void testPreOrderTraversal (T[] input, T[] expectedOutput) {
        BinarySearchTree<T> bst = new BinarySearchTree<>(input);
        ArrayList<T> output = bst.preOrderTraversal();

        assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    void testPreOrderTraversalPrint() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Integer[]{});
        ArrayList<Integer> output = bst.preOrderTraversal(true);

        assertNull(output);
    }

    static Stream<Arguments> postOrderTraversalCases() {
        return Stream.of(
                Arguments.of(new Integer[]{2, 1, 3}, new Integer[]{1, 3, 2}),
                Arguments.of(new Integer[]{}, new Integer[]{}),
                Arguments.of(new Integer[]{5}, new Integer[]{5}),
                Arguments.of(new Integer[]{5, 4, 3, 2, 1}, new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{5, 4, 3, 2, 1}),
                Arguments.of(new Integer[]{5, 3, 7, 3, 6, 8}, new Integer[]{3, 3, 6, 8, 7, 5}),
                Arguments.of(new Integer[]{10, 5, 15, 2, 7, 12, 18}, new Integer[]{2, 7, 5, 12, 18, 15, 10}),
                Arguments.of(new String[]{"dog", "cat", "elephant", "ant", "zebra"}, new String[]{"ant", "cat", "zebra", "elephant", "dog"}),
                Arguments.of(new String[]{}, new String[]{}),
                Arguments.of(new String[]{"apple"}, new String[]{"apple"}),
                Arguments.of(new String[]{"banana", "apple", "cherry"}, new String[]{"apple", "cherry", "banana"}),
                Arguments.of(new String[]{"z", "y", "x", "w", "v"}, new String[]{"v", "w", "x", "y", "z"}),
                Arguments.of(new String[]{"giraffe", "elephant", "hippo", "elephant", "antelope"},
                        new String[]{"antelope", "elephant", "elephant", "hippo", "giraffe"}),
                Arguments.of(new String[]{"sun", "moon", "star", "planet", "comet"},
                        new String[]{"comet", "planet", "star", "moon", "sun"})
        );
    }

    @ParameterizedTest
    @MethodSource("postOrderTraversalCases")
    <T extends Comparable<T>> void testPostOrderTraversal (T[] input, T[] expectedOutput) {
        BinarySearchTree<T> bst = new BinarySearchTree<>(input);
        ArrayList<T> output = bst.postOrderTraversal();

        assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    void testPostOrderTraversalPrint() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Integer[]{});
        ArrayList<Integer> output = bst.postOrderTraversal(true);

        assertNull(output);
    }

    static Stream<Arguments> levelOrderTraversalCases() {
        return Stream.of(
                Arguments.of(new Integer[]{2, 1, 3}, new Integer[]{2, 1, 3}),
                Arguments.of(new Integer[]{}, new Integer[]{}),
                Arguments.of(new Integer[]{5}, new Integer[]{5}),
                Arguments.of(new Integer[]{5, 4, 3, 2, 1}, new Integer[]{5, 4, 3, 2, 1}),
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of(new Integer[]{5, 3, 7, 2, 4, 6, 8}, new Integer[]{5, 3, 7, 2, 4, 6, 8}),
                Arguments.of(new Integer[]{10, 5, 15, 2, 7, 12, 18}, new Integer[]{10, 5, 15, 2, 7, 12, 18}),
                Arguments.of(new String[]{"dog", "cat", "elephant", "ant", "zebra"},
                        new String[]{"dog", "cat", "elephant", "ant", "zebra"}),
                Arguments.of(new String[]{}, new String[]{}),
                Arguments.of(new String[]{"apple"}, new String[]{"apple"}),
                Arguments.of(new String[]{"banana", "apple", "cherry"},
                        new String[]{"banana", "apple", "cherry"}),
                Arguments.of(new String[]{"z", "y", "x", "w", "v"},
                        new String[]{"z", "y", "x", "w", "v"}),
                Arguments.of(new String[]{"giraffe", "elephant", "hippo", "antelope"},
                        new String[]{"giraffe", "elephant", "hippo", "antelope"}),
                Arguments.of(new String[]{"sun", "moon", "star", "planet", "comet"},
                        new String[]{"sun", "moon", "comet", "star", "planet"})
        );
    }

    @ParameterizedTest
    @MethodSource("levelOrderTraversalCases")
    <T extends Comparable<T>> void testLevelOrderTraversal (T[] input, T[] expectedOutput) {
        BinarySearchTree<T> bst = new BinarySearchTree<>(input);
        ArrayList<T> output = bst.levelOrderTraversal();

        assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    void testLevelOrderTraversalPrint() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>(new Integer[]{});
        ArrayList<Integer> output = bst.levelOrderTraversal(true);

        assertNull(output);
    }

}