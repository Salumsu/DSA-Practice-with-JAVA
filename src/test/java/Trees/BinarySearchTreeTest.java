package Trees;

import Trees.BinarySearchTree.BinarySearchTree;
import Trees.BinarySearchTree.BinarySearchTreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {
    @Test
    void testEmptyTree () {
        BinarySearchTree<Integer> bst = BinarySearchTree.create();
        ArrayList<Integer> result = bst.inOrderTraversal();

        assertArrayEquals(new ArrayList<Integer>().toArray(), result.toArray());
    }

    @Test
    void testSingleInitialValue () {
        BinarySearchTree<Integer> bst = BinarySearchTree.create(1);
        ArrayList<Integer> result = bst.inOrderTraversal();

        assertEquals(result.getFirst(), 1);
    }

    static Stream<Arguments> searchCases() {
        return Stream.of(
                Arguments.of(new Integer[]{2, 1, 3}, new Integer[]{1, 2, 3, 4}, new Integer[]{1, 2, 3, null}),
                Arguments.of(new Integer[]{}, new Integer[]{1, 2, 3}, new Integer[]{null, null, null}),
                Arguments.of(new Integer[]{5}, new Integer[]{5, 6}, new Integer[]{5, null}),
                Arguments.of(new Integer[]{5, 4, 3, 2, 1}, new Integer[]{1, 3, 5, 6}, new Integer[]{1, 3, 5, null}),
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 3, 5, 6}, new Integer[]{1, 3, 5, null}),
                Arguments.of(new Integer[]{5, 3, 7, 3, 6, 8}, new Integer[]{3, 5, 6, 7, 8, 9}, new Integer[]{3, 5, 6, 7, 8, null}),
                Arguments.of(new Integer[]{10, 5, 15, 2, 7, 12, 18}, new Integer[]{2, 5, 7, 10, 12, 15, 18, 20}, new Integer[]{2, 5, 7, 10, 12, 15, 18, null}),
                Arguments.of(new String[]{"dog", "cat", "elephant", "ant", "zebra"}, new String[]{"ant", "cat", "lion", "zebra"}, new String[]{"ant", "cat", null, "zebra"}),
                Arguments.of(new String[]{}, new String[]{"apple", "banana"}, new String[]{null, null}),
                Arguments.of(new String[]{"apple"}, new String[]{"apple", "banana"}, new String[]{"apple", null}),
                Arguments.of(new String[]{"banana", "apple", "cherry"}, new String[]{"apple", "banana", "grape"}, new String[]{"apple", "banana", null}),
                Arguments.of(new String[]{"z", "y", "x", "w", "v"}, new String[]{"v", "w", "x", "y", "z", "a"}, new String[]{"v", "w", "x", "y", "z", null}),
                Arguments.of(new String[]{"giraffe", "elephant", "hippo", "elephant", "antelope"}, new String[]{"antelope", "elephant", "lion"}, new String[]{"antelope", "elephant", null}),
                Arguments.of(new String[]{"sun", "moon", "star", "planet", "comet"}, new String[]{"comet", "moon", "planet", "star", "sun", "asteroid"}, new String[]{"comet", "moon", "planet", "star", "sun", null})
        );
    }

    @ParameterizedTest
    @MethodSource("searchCases")
    <T extends Comparable<T>> void testSearch (T[] inputArray, T[] searchInputs, T[] expectedResults) {
        BinarySearchTree<T> bst = BinarySearchTree.create(inputArray);

        for (int i = 0; i < searchInputs.length; i++) {
            BinarySearchTreeNode<T> output = bst.search(searchInputs[i]);

            assertEquals(expectedResults[i], output != null ? output.getValue() : null);
        }
    }

    static Stream<Arguments> removeCases() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{1, 2, 3},
                        new Integer[]{2, 3, 4},
                        new Integer[]{2, 3, null},
                        new Integer[][]{{1, 3}, {1}, {1}}, // Inorder
                        new Integer[][]{{1, 3}, {1}, {1}}, // Preorder
                        new Integer[][]{{3, 1}, {1}, {1}}, // Postorder
                        new Integer[][]{{1, 3}, {1}, {1}}  // Level order
                ),
                Arguments.of(
                        new Integer[]{10, 5, 15, 2, 7, 12, 18},
                        new Integer[]{15, 5, 10},
                        new Integer[]{15, 5, 10},
                        new Integer[][]{
                                {2, 5, 7, 10, 12, 18},
                                {2, 7, 10, 12, 18},
                                {2, 7, 12, 18}
                        },
                        new Integer[][]{
                                {10, 5, 2, 7, 18, 12},
                                {10, 7, 2, 18, 12},
                                {12, 7, 2, 18}
                        },
                        new Integer[][]{
                                {2, 7, 5, 12, 18, 10},
                                {2, 7, 12, 18, 10},
                                {2, 7, 18, 12}
                        },
                        new Integer[][]{
                                {10, 5, 18, 2, 7, 12},
                                {10, 7, 18, 2, 12},
                                {12, 7, 18, 2}
                        }
                ),
                Arguments.of(
                        new Integer[]{5},
                        new Integer[]{5},
                        new Integer[]{5},
                        new Integer[][]{{}},
                        new Integer[][]{{}},
                        new Integer[][]{{}},
                        new Integer[][]{{}}
                ),
                Arguments.of(
                        new Integer[]{5, 3, 7, 6, 8},
                        new Integer[]{3, 7, 8},
                        new Integer[]{3, 7, 8},
                        new Integer[][]{
                                {5, 6, 7, 8},
                                {5, 6, 8},
                                {5, 6}
                        },
                        new Integer[][]{
                                {5, 7, 6, 8},
                                {5, 8, 6},
                                {5, 6}
                        },
                        new Integer[][]{
                                {6, 8, 7, 5},
                                {6, 8, 5},
                                {6, 5}
                        },
                        new Integer[][]{
                                {5, 7, 6, 8},
                                {5, 8, 6},
                                {5, 6}
                        }
                ),
                Arguments.of(
                        new String[]{"dog", "cat", "elephant", "ant", "zebra"},
                        new String[]{"cat", "dog", "lion"},
                        new String[]{"cat", "dog", null},
                        new String[][]{
                                { "ant", "dog", "elephant", "zebra" },
                                { "ant", "elephant", "zebra" },
                                { "ant", "elephant", "zebra" }
                        },
                        new String[][]{
                                { "dog", "ant", "elephant", "zebra" },
                                { "elephant", "ant", "zebra" },
                                { "elephant", "ant", "zebra" }
                        },
                        new String[][]{
                                { "ant", "zebra", "elephant", "dog" },
                                { "ant", "zebra", "elephant" },
                                { "ant", "zebra", "elephant" }
                        },
                        new String[][]{
                                { "dog", "ant", "elephant", "zebra" },
                                { "elephant", "ant", "zebra" },
                                { "elephant", "ant", "zebra" }
                        }
                ),
                // SUCCESSOR HAS A RIGHT NODE
                Arguments.of(
                        new Integer[]{25, 21, 20, 23, 34, 30, 31, 35},
                        new Integer[]{25},
                        new Integer[]{25},
                        new Integer[][]{
                                {20, 21, 23, 30, 31, 34, 35}
                        },
                        new Integer[][]{
                                {30, 21, 20, 23, 34, 31, 35}
                        },
                        new Integer[][]{
                                {20, 23, 21, 31, 35, 34, 30}
                        },
                        new Integer[][]{
                                {30, 21, 34, 20, 23, 31, 35}
                        }
                )
        );
    }

    @ParameterizedTest
    @MethodSource("removeCases")
    <T extends Comparable<T>> void testRemove (
            T[]inputArray,
            T[] removeInputs,
            T[] expectedResults,
            T[][] expectedInorderOutput,
            T[][] expectedPreorderOutput,
            T[][] expectedPostorderOutput,
            T[][] expectedLevelorderOutput
            ) {
        BinarySearchTree<T> bst = BinarySearchTree.create(inputArray);

        for (int i = 0; i < removeInputs.length; i++) {
            BinarySearchTreeNode<T> res = bst.remove(removeInputs[i]);

            assertEquals(expectedResults[i], res != null ? res.getValue() : null);

            @SuppressWarnings("unchecked")
            T[] inorderOutput = (T[]) bst.inOrderTraversal().toArray((T[]) new Comparable[0]);
            @SuppressWarnings("unchecked")
            T[] preorderOutput = (T[]) bst.preOrderTraversal().toArray((T[]) new Comparable[0]);
            @SuppressWarnings("unchecked")
            T[] postorderOutput = (T[]) bst.postOrderTraversal().toArray((T[]) new Comparable[0]);
            @SuppressWarnings("unchecked")
            T[] levelorderOutput = (T[]) bst.levelOrderTraversal().toArray((T[]) new Comparable[0]);

            assertArrayEquals(expectedInorderOutput[i], inorderOutput);
            assertArrayEquals(expectedPreorderOutput[i], preorderOutput);
            assertArrayEquals(expectedPostorderOutput[i], postorderOutput);
            assertArrayEquals(expectedLevelorderOutput[i], levelorderOutput);
        }
    }

    static Stream<Arguments> insertCases() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{10, 5, 15},  // Initial BST
                        new Integer[]{3, 7, 12, 18}, // Insert these values
                        new Integer[]{3, 5, 7, 10, 12, 15, 18}, // Expected Inorder
                        new Integer[]{10, 5, 3, 7, 15, 12, 18}, // Expected Preorder
                        new Integer[]{3, 7, 5, 12, 18, 15, 10}, // Expected Postorder
                        new Integer[]{10, 5, 15, 3, 7, 12, 18} // Expected Level Order
                ),
                Arguments.of(
                        new Integer[]{20, 10, 30},
                        new Integer[]{5, 15, 25, 35},
                        new Integer[]{5, 10, 15, 20, 25, 30, 35},
                        new Integer[]{20, 10, 5, 15, 30, 25, 35},
                        new Integer[]{5, 15, 10, 25, 35, 30, 20},
                        new Integer[]{20, 10, 30, 5, 15, 25, 35}
                ),
                Arguments.of(
                        new Integer[]{50, 25, 75},
                        new Integer[]{10, 30, 60, 90},
                        new Integer[]{10, 25, 30, 50, 60, 75, 90},
                        new Integer[]{50, 25, 10, 30, 75, 60, 90},
                        new Integer[]{10, 30, 25, 60, 90, 75, 50},
                        new Integer[]{50, 25, 75, 10, 30, 60, 90}
                ),
                Arguments.of(
                        new Integer[]{5},
                        new Integer[]{3, 7, 1, 4, 6, 8},
                        new Integer[]{1, 3, 4, 5, 6, 7, 8},
                        new Integer[]{5, 3, 1, 4, 7, 6, 8},
                        new Integer[]{1, 4, 3, 6, 8, 7, 5},
                        new Integer[]{5, 3, 7, 1, 4, 6, 8}
                ),
                Arguments.of(
                        new String[]{"dog", "cat", "elephant"},
                        new String[]{"ant", "zebra", "fox"},
                        new String[]{"ant", "cat", "dog", "elephant", "fox", "zebra"},
                        new String[]{"dog", "cat", "ant", "elephant", "zebra", "fox"},
                        new String[]{"ant", "cat", "fox", "zebra", "elephant", "dog"},
                        new String[]{"dog", "cat", "elephant", "ant", "zebra", "fox"}
                )
        );
    }


    @ParameterizedTest
    @MethodSource("insertCases")
    <T extends Comparable<T>> void insertTest (
            T[]inputArray,
            T[] insertInputs,
            T[] expectedInorderOutput,
            T[] expectedPreorderOutput,
            T[] expectedPostorderOutput,
            T[] expectedLevelorderOutput
    ) {
        BinarySearchTree<T> bst = BinarySearchTree.create(inputArray);

        for (int i = 0; i < insertInputs.length; i++) {
            bst.insert(insertInputs[i]);
        }

        @SuppressWarnings("unchecked")
        T[] inorderOutput = (T[]) bst.inOrderTraversal().toArray((T[]) new Comparable[0]);
        @SuppressWarnings("unchecked")
        T[] preorderOutput = (T[]) bst.preOrderTraversal().toArray((T[]) new Comparable[0]);
        @SuppressWarnings("unchecked")
        T[] postorderOutput = (T[]) bst.postOrderTraversal().toArray((T[]) new Comparable[0]);
        @SuppressWarnings("unchecked")
        T[] levelorderOutput = (T[]) bst.levelOrderTraversal().toArray((T[]) new Comparable[0]);

        System.out.println("EXPECTED OUTPUT: " + Arrays.toString(expectedInorderOutput) + ", OUTPUT: " + Arrays.toString(inorderOutput));
        System.out.println("EXPECTED OUTPUT: " + Arrays.toString(expectedPreorderOutput) + ", OUTPUT: " + Arrays.toString(preorderOutput));
        System.out.println("EXPECTED OUTPUT: " + Arrays.toString(expectedPostorderOutput) + ", OUTPUT: " + Arrays.toString(postorderOutput));
        System.out.println("EXPECTED OUTPUT: " + Arrays.toString(expectedLevelorderOutput) + ", OUTPUT: " + Arrays.toString(levelorderOutput));

        assertArrayEquals(expectedInorderOutput, inorderOutput);
        assertArrayEquals(expectedPreorderOutput, preorderOutput);
        assertArrayEquals(expectedPostorderOutput, postorderOutput);
        assertArrayEquals(expectedLevelorderOutput, levelorderOutput);
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
        BinarySearchTree<T> bst = BinarySearchTree.create(input);
        ArrayList<T> output = bst.inOrderTraversal();

        assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    void testInOrderTraversalPrint() {
        BinarySearchTree<Integer> bst = BinarySearchTree.create(new Integer[]{});
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
        BinarySearchTree<T> bst = BinarySearchTree.create(input);
        ArrayList<T> output = bst.preOrderTraversal();

        assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    void testPreOrderTraversalPrint() {
        BinarySearchTree<Integer> bst = BinarySearchTree.create(new Integer[]{});
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
        BinarySearchTree<T> bst = BinarySearchTree.create(input);
        ArrayList<T> output = bst.postOrderTraversal();

        assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    void testPostOrderTraversalPrint() {
        BinarySearchTree<Integer> bst = BinarySearchTree.create(new Integer[]{});
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
        BinarySearchTree<T> bst = BinarySearchTree.create(input);
        ArrayList<T> output = bst.levelOrderTraversal();

        assertArrayEquals(expectedOutput, output.toArray());
    }

    @Test
    void testLevelOrderTraversalPrint() {
        BinarySearchTree<Integer> bst = BinarySearchTree.create(new Integer[]{});
        ArrayList<Integer> output = bst.levelOrderTraversal(true);

        assertNull(output);
    }

}