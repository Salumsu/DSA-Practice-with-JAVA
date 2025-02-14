package Heap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
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
        BinaryHeap<Integer> heap = new BinaryHeap<>(input);

        TestHelper.testMinHeap(heap.toArrayList(), edgeCase);
    }

    @ParameterizedTest
    @MethodSource("heapifyCases")
    void testMaxBinaryHeapConstructor(Integer[] input, String edgeCase) {
        BinaryHeap<Integer> heap = new BinaryHeap<>(false, input);

        TestHelper.testMaxHeap(heap.toArrayList(), edgeCase);
    }

    @Test
    void testBinaryHeapFunctions() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Integer[]{1, 2, 3});
        assertEquals(heap.poll(), 1);
        assertEquals(heap.size(), 2);
        assertFalse(heap.isEmpty());

        heap.insert(1);
        heap.insert(0);
        assertEquals(heap.size(), 4);
        assertEquals(heap.poll(), 0);

        heap.insert(10);
        heap.insert(25);
        heap.insert(3);
        heap.insert(42);
        heap.insert(17);
        heap.insert(8);
        heap.insert(99);
        heap.insert(1);
        heap.insert(56);
        heap.insert(73);
        heap.insert(12);
        heap.insert(37);
        heap.insert(68);
        heap.insert(91);
        heap.insert(5);
        heap.insert(88);
        heap.insert(20);
        heap.insert(47);
        heap.insert(33);
        heap.insert(61);

        assertEquals(heap.size(), 23);

        assertEquals(heap.poll(), 1);
        assertEquals(heap.poll(), 1);
        assertEquals(heap.poll(), 2);
        assertEquals(heap.poll(), 3);
        assertEquals(heap.poll(), 3);
        assertEquals(heap.poll(), 5);
        assertEquals(heap.poll(), 8);
        assertEquals(heap.poll(), 10);
        assertEquals(heap.poll(), 12);
        assertEquals(heap.poll(), 17);
        assertEquals(heap.poll(), 20);
        assertEquals(heap.poll(), 25);
        assertEquals(heap.poll(), 33);
        assertEquals(heap.poll(), 37);
        assertEquals(heap.poll(), 42);
        assertEquals(heap.poll(), 47);
        assertEquals(heap.poll(), 56);
        assertEquals(heap.poll(), 61);
        assertEquals(heap.poll(), 68);
        assertEquals(heap.poll(), 73);
        assertEquals(heap.poll(), 88);
        assertEquals(heap.poll(), 91);
        assertEquals(heap.poll(), 99);

        assertEquals(heap.size(), 0);
        assertTrue(heap.isEmpty());
    }

}