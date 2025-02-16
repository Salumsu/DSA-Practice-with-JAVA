package Heap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {
    public static Stream<Arguments> heapifyCases() {
        return Stream.of(
                Arguments.of(new Integer[]{}, "Empty array"),
                Arguments.of(new Integer[]{1}, "Single element"),
                Arguments.of(new Integer[]{3, 1, 4, 1, 5, 9}, "Random unsorted"),
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, "Already sorted ascending"),
                Arguments.of(new Integer[]{5, 4, 3, 2, 1}, "Already sorted descending"),
                Arguments.of(new Integer[]{10, 20, 10, 20, 10}, "Repeating elements"),
                Arguments.of(new Integer[]{7, 6, 5, 4, 3, 2, 1}, "Strictly decreasing order"),
                Arguments.of(new Integer[]{15, 10, 30, 40, 50, 20, 35}, "Mixed random order"),
                Arguments.of(new Integer[]{100, 90, 80, 70, 60, 50}, "Large numbers descending")
        );
    }

    @ParameterizedTest
    @MethodSource("heapifyCases")
    void testMinBinaryHeapConstructor(Integer[] input, String edgeCase) {
        BinaryHeap<Integer> heap = BinaryHeap.create(input);

        TestHelper.testMinHeap(heap.toArrayList(), edgeCase);
    }

    @ParameterizedTest
    @MethodSource("heapifyCases")
    void testMaxBinaryHeapConstructor(Integer[] input, String edgeCase) {
        BinaryHeap<Integer> heap = BinaryHeap.create(false, input);

        TestHelper.testMaxHeap(heap.toArrayList(), edgeCase);
    }

    static Stream<Integer[][]> sortingCapabilityTestCases() {
        return Stream.of(
                new Integer[][]{ {}, {} },                                      // Empty array
                new Integer[][]{ {42}, {42} },                                  // Single element
                new Integer[][]{ {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5} },            // Already sorted
                new Integer[][]{ {5, 4, 3, 2, 1}, {1, 2, 3, 4, 5} },            // Reverse sorted
                new Integer[][]{ {3, 1, 2, 3, 3, 1}, {1, 1, 2, 3, 3, 3} },      // Duplicates
                new Integer[][]{ {-3, -1, -4, -2, 0}, {-4, -3, -2, -1, 0} },    // Negative numbers
                new Integer[][]{ {7, 7, 7, 7, 7}, {7, 7, 7, 7, 7} }             // All equal elements
        );
    }

    @ParameterizedTest
    @MethodSource("sortingCapabilityTestCases")
    void testSortingCapability(Integer[][] cases) {
        Integer[] input = cases[0];
        Integer[] expectedPollOutputs = cases[1];
        BinaryHeap<Integer> heap = BinaryHeap.create(input);

        for (Integer expected : expectedPollOutputs) {
            assertEquals(expected, heap.poll());
        }
    }

}