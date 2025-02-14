package Heap;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HeapHelperTest {
    static Stream<int[]> parentIndexCases() {
        return Stream.of(
                new int[] { 1, 0 },
                new int[] { 2, 0 },
                new int[] { 3, 1 },
                new int[] { 4, 1 },
                new int[] { 5, 2 },
                new int[] { 6, 2 },
                new int[] { 7, 3 },
                new int[] { 8, 3 },
                new int[] { 9, 4 },
                new int[] { 10, 4 }
        );
    }

    @ParameterizedTest
    @MethodSource("parentIndexCases")
    void testGetParentIndex(int[] cases) {
        int input = cases[0];
        int expectedOutput = cases[1];

        int output = HeapHelper.getParentIndex(input);

        assertEquals(expectedOutput, output);
    }

    static Stream<int[]> leftChildrenIndexCases () {
        return Stream.of(
                new int[] { 0, 1 },
                new int[] { 1, 3 },
                new int[] { 2, 5 },
                new int[] { 3, 7 },
                new int[] { 4, 9 },
                new int[] { 5, 11 },
                new int[] { 6, 13 },
                new int[] { 7, 15 },
                new int[] { 8, 17 },
                new int[] { 9, 19 }
        );
    }


    @ParameterizedTest
    @MethodSource("leftChildrenIndexCases")
    void testGetLeftChildrenIndex(int[] cases) {
        int input = cases[0];
        int expectedOutput = cases[1];

        int output = HeapHelper.getLeftChildrenIndex(input);

        assertEquals(expectedOutput, output);
    }
}