package Trees;

import Trees.AVLTree.AVLTree;
import Trees.AVLTree.AVLTreeNode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    static Stream<Integer[][]> avlEdgeCases() {
        return Stream.of(
                new Integer[][]{
                        {10},
                        {10}
                },
                new Integer[][]{
                        {10, 20, 30},
                        {20, 10, 30}
                },
                new Integer[][]{
                        {30, 20, 10},
                        {20, 10, 30}
                },
                new Integer[][]{
                        {30, 10, 20},
                        {20, 10, 30}
                },
                new Integer[][]{
                        {10, 30, 20},
                        {20, 10, 30}
                },
                new Integer[][]{
                        {20, 10, 30, 5, 15, 25, 35},
                        {20, 10, 30, 5, 15, 25, 35}
                },
                new Integer[][]{
                        {10, 20, 30, 40, 50},
                        {20, 10, 40, 30, 50}
                },
                new Integer[][]{
                        {50, 40, 30, 20, 10},
                        {40, 20, 50, 10, 30}
                },
                new Integer[][]{
                        {10, 20, 30, 25, 5, 15, 35, 40, 50},
                        {20, 10, 30, 5, 15, 25, 40, 35, 50}
                },
                new Integer[][] {
                        {20, 30, 25, 21, 23, 35, 33, 34},
                        {25, 21, 33, 20, 23, 30, 35, 34}
                }
        );
    }

    @ParameterizedTest
    @MethodSource("avlEdgeCases")
    void testEdgeCaseInsert (Integer[][] cases) {
        Integer[] input = cases[0];
        Integer[] expectedOutput = cases[1];
        // The constructor that accepts an array uses the insert internally
        AVLTree<Integer> avlTree = AVLTree.create(input);

        Integer[] output = avlTree.levelOrderTraversal().toArray(new Integer[0]);

        // System.out.println("INPUT: " + Arrays.toString(input) + ", OUTPUT: " + Arrays.toString(output) + ", EXPECTED OUTPUT: " + Arrays.toString(expectedOutput));
        assertArrayEquals(expectedOutput, output);
    }

    static Stream<Arguments> rotationsEdgeCases() {
        return Stream.of(
                Arguments.of(new Integer[]{3, 2, 1}, 2, 2),
                Arguments.of(new Integer[]{1, 2, 3}, 2, 2),
                Arguments.of(new Integer[]{3, 1, 2}, 2, 2),
                Arguments.of(new Integer[]{1, 3, 2}, 2, 2)
        );
    }

    @ParameterizedTest
    @MethodSource("rotationsEdgeCases")
    void testRotations (Integer[] input, Integer expectedHeadValue, int expectedHeight) {
        AVLTree<Integer> avlTree = AVLTree.create(input);

        assertEquals(expectedHeadValue, avlTree.getHeadValue(), "HEAD VALUE DOESN'T MATCH");
        assertEquals(expectedHeight, avlTree.getHeight(), "HEIGHT DOESN'T MATCH");
    }
    
    // @ParameterizedTest
    // @MethodSource("edgeCases")
    // void testEdgeCases (int from, int step, int count, Integer[] removeInputs, Integer[] expectedRemoveOutputs, int[] expectedHeights) {
    //     List<Integer> list = IntStream.iterate(from, n -> n + step) // Start at 10, decrement by 1
    //             .limit(count)
    //             .boxed()
    //             .toList();
    //
    //     AVLTree<Integer> avlTree = AVLTree.create(list);
    //     assertEquals(expectedHeights[0], avlTree.getHeight(), "EXPECTED HEIGHT: " + expectedHeights[0] + " DIDNT MATCH ACTUAL HEIGHT: " + avlTree.getHeight());
    //
    //     for (int i = 0; i < removeInputs.length; i++) {
    //         AVLTreeNode<Integer> removed = avlTree.remove(removeInputs[i]);
    //         Integer removedValue = removed != null ? removed.getValue() : null;
    //         assertEquals(expectedRemoveOutputs[i], removedValue, "THE EXPECTED REMOVED NODE: " + expectedRemoveOutputs[i] + "DIDN'T MATCH THE REMOVED VALUE: " + removedValue);
    //
    //         assertEquals(expectedHeights[i + 1], avlTree.getHeight(), "EXPECTED HEIGHT: " + expectedHeights[i + 1] + " DIDNT MATCH ACTUAL HEIGHT: " + avlTree.getHeight());
    //     }
    // }
}