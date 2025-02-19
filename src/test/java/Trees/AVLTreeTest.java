package Trees;

import Trees.AVLTree.AVLTree;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
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

        System.out.println("INPUT: " + Arrays.toString(input) + ", OUTPUT: " + Arrays.toString(output) + ", EXPECTED OUTPUT: " + Arrays.toString(expectedOutput));
        assertArrayEquals(expectedOutput, output);
    }
}