package Algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    static Stream<Integer[][]> splitArrayCases() {
        return Stream.of(
                new Integer[][]{ {0}, {}, {}, {} },                                      // Empty array
                new Integer[][]{ {0}, {42}, {42}, {} },                                  // Single element
                new Integer[][]{ {2}, {1, 2, 3, 4, 5}, {1, 2, 3}, {4, 5} },              // Odd number of items
                new Integer[][]{ {2}, {1, 2, 3, 4, 5, 6}, {1, 2, 3}, {4, 5, 6} }               // Even number of items
        );
    }

    static Stream<Integer[][]> splitArrayCasesWithoutMid() {
        return Stream.of(
                new Integer[][]{ {0}, {}, {}, {} },                                      // Empty array
                new Integer[][]{ {0}, {42}, {}, {} },                                  // Single element
                new Integer[][]{ {2}, {1, 2, 3, 4, 5}, {1, 2}, {4, 5} },              // Odd number of items
                new Integer[][]{ {2}, {1, 2, 3, 4, 5, 6}, {1, 2}, {4, 5, 6} }               // Even number of items
        );
    }

    @ParameterizedTest
    @MethodSource("splitArrayCases")
    void testSplitArray(Integer[][] caseData) {
        Integer mid = caseData[0][0];
        Integer[] input = caseData[1];
        Integer[] expectedLeft = caseData[2];
        Integer[] expectedRight = caseData[3];

        Utils.SplitArray splitArray = Utils.splitArray(input, mid);

        assertArrayEquals(expectedLeft, splitArray.left(),
                "Left array:" + Arrays.toString(splitArray.left()) + " \n didn't match the expected value: " + Arrays.toString(expectedLeft)
        );
        assertArrayEquals(expectedRight, splitArray.right(),
                "Right array:" + Arrays.toString(splitArray.right()) + " \n didn't match the expected value: " + Arrays.toString(expectedRight)
        );
    }

    @ParameterizedTest
    @MethodSource("splitArrayCasesWithoutMid")
    void testSplitArrayWithoutMid(Integer[][] caseData) {
        Integer mid = caseData[0][0];
        Integer[] input = caseData[1];
        Integer[] expectedLeft = caseData[2];
        Integer[] expectedRight = caseData[3];

        Utils.SplitArray splitArray = Utils.splitArray(input, mid, false);

        assertArrayEquals(expectedLeft, splitArray.left(),
                "Left array:" + Arrays.toString(splitArray.left()) + " \n didn't match the expected value: " + Arrays.toString(expectedLeft)
        );
        assertArrayEquals(expectedRight, splitArray.right(),
                "Right array:" + Arrays.toString(splitArray.right()) + " \n didn't match the expected value: " + Arrays.toString(expectedRight)
        );
    }

    @Test
    void testIsEmpty () {
        assertTrue(Utils.isEmpty(new Integer[]{}));
        assertFalse(Utils.isEmpty(new Integer[]{1}));
    }

    @Test
    void testSwap () {
        Integer[] arr = {1, 2, 3, 4, 5};
        Utils.swap(arr, 0, 3);

        assertArrayEquals(arr, new Integer[]{4, 2, 3, 1, 5});
        assertThrows(IndexOutOfBoundsException.class, () -> Utils.swap(arr, 10, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> Utils.swap(arr, 2, 15));
    }

    static Stream<Arguments> minCases() {
        return Stream.of(
                Arguments.of(1, 2, 1),               // Integer
                Arguments.of(1L, 2L, 1L),            // Long
                Arguments.of(1.1, 2.2, 1.1),         // Double
                Arguments.of(1.1f, 2.2f, 1.1f),      // Float
                Arguments.of((short) 1, (short) 2, (short) 1), // Short
                Arguments.of((byte) 1, (byte) 2, (byte) 1),    // Byte
                Arguments.of('a', 'c', 'a'),         // Character
                Arguments.of("a", "b", "a"),         // String
                Arguments.of(true, false, false)     // Boolean
        );
    }

    @ParameterizedTest
    @MethodSource("minCases")
    <T extends Comparable<T>> void testMin(T first, T second, T expected) {
        T output = Utils.min(first, second);
        assertEquals(expected, output);
    }

    static Stream<Arguments> shouldSwapCases() {
        return Stream.of(
                // Ascending order (false = should not swap, true = should swap)
                Arguments.of(1, 2, false, false),  // 1 < 2, no swap
                Arguments.of(2, 1, false, true),   // 2 > 1, should swap
                Arguments.of(1, 1, false, false),  // equal, no swap
                Arguments.of(-1, 1, false, false), // -1 < 1, no swap
                Arguments.of(1, -1, false, true),  // 1 > -1, should swap

                // Descending order (true = reversed mode)
                Arguments.of(1, 2, true, true),    // 1 < 2, should swap in descending
                Arguments.of(2, 1, true, false),   // 2 > 1, no swap in descending
                Arguments.of(1, 1, true, false),   // equal, no swap
                Arguments.of(-1, 1, true, true),   // -1 < 1, should swap
                Arguments.of(1, -1, true, false),  // 1 > -1, no swap

                // Edge cases with different types
                Arguments.of('a', 'b', false, false),  // 'a' < 'b', no swap in asc
                Arguments.of('b', 'a', false, true),   // 'b' > 'a', should swap in asc
                Arguments.of('a', 'b', true, true),    // 'a' < 'b', should swap in desc
                Arguments.of('b', 'a', true, false),   // 'b' > 'a', no swap in desc

                Arguments.of("apple", "banana", false, false), // "apple" < "banana", no swap
                Arguments.of("banana", "apple", false, true),  // "banana" > "apple", should swap
                Arguments.of("apple", "banana", true, true),   // swap in descending
                Arguments.of("banana", "apple", true, false),  // no swap in descending

                Arguments.of(1.1, 2.2, false, false),  // 1.1 < 2.2, no swap in asc
                Arguments.of(2.2, 1.1, false, true),   // 2.2 > 1.1, should swap in asc
                Arguments.of(1.1, 2.2, true, true),    // should swap in descending
                Arguments.of(2.2, 1.1, true, false)    // no swap in descending
        );
    }

    @ParameterizedTest
    @MethodSource("shouldSwapCases")
    <T extends Comparable<T>> void testShouldSwap(T first, T second, boolean desc, boolean expected) {
        boolean output = Utils.shouldSwap(first, second, desc);
        assertEquals(expected, output);
    }

    static Stream<Arguments> equalTestCases() {
        return Stream.of(
                Arguments.of(1, 2, false),
                Arguments.of(1, 1, true),
                Arguments.of(2, 1, false)
        );
    }

    @ParameterizedTest
    @MethodSource("equalTestCases")
    <T extends Comparable<T>> void testEqual (T first, T second, boolean expected) {
        boolean output = Utils.equals(first, second);

        assertEquals(expected, output);
    }
}