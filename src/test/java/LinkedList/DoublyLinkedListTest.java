package LinkedList;

import LinkedList.DoublyLinkedList.DoublyLinkedList;
import LinkedList.SinglyLinkedList.SinglyLinkedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {
    static <T> Stream<Arguments> removeFirstCases() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3, null}),
                Arguments.of(new Double[]{1.1, 2.2, 3.3}, new Double[]{1.1, 2.2}),
                Arguments.of(new String[]{"a", "b", "c"}, new String[]{"a"}),
                Arguments.of(new Character[]{'x', 'y', 'z'}, new Character[]{'x', 'y', 'z'}),
                Arguments.of(new Long[]{100L, 200L, 300L}, new Long[]{100L, 200L, 300L}),
                Arguments.of(new Short[]{10, 20, 30}, new Short[]{10, 20, 30}),
                Arguments.of(new Byte[]{1, 2, 3}, new Byte[]{1, 2, 3}),
                Arguments.of(new Float[]{1.5f, 2.5f, 3.5f}, new Float[]{1.5f, 2.5f, 3.5f}),
                Arguments.of(new Integer[]{}, new Integer[]{null, null})
        );
    }

    @ParameterizedTest
    @MethodSource("removeFirstCases")
    <T extends Comparable<T>> void testRemoveFirst(T[] input, T[] removeInputs) {
        DoublyLinkedList<T> list = new DoublyLinkedList<>(input);

        assertEquals(list.size(), input.length);
        for (T remove : removeInputs) {
            assertEquals(remove, list.removeFirst());
        }
    }

    static <T> Stream<Arguments> removeLastCases() {
        return Stream.of(
                Arguments.of(new Integer[]{1, 2, 3}, new Integer[]{3, 2, 1, null}),
                Arguments.of(new Double[]{1.1, 2.2, 3.3}, new Double[]{3.3, 2.2}),
                Arguments.of(new String[]{"a", "b", "c"}, new String[]{"c"}),
                Arguments.of(new Character[]{'x', 'y', 'z'}, new Character[]{'z', 'y', 'x'}),
                Arguments.of(new Long[]{100L, 200L, 300L}, new Long[]{300L, 200L, 100L}),
                Arguments.of(new Short[]{10, 20, 30}, new Short[]{30, 20, 10}),
                Arguments.of(new Byte[]{1, 2, 3}, new Byte[]{3, 2, 1}),
                Arguments.of(new Float[]{1.5f, 2.5f, 3.5f}, new Float[]{3.5f, 2.5f, 1.5f}),
                Arguments.of(new Integer[]{}, new Integer[]{null, null})
        );
    }

    @ParameterizedTest
    @MethodSource("removeLastCases")
    <T extends Comparable<T>> void testRemoveLast(T[] input, T[] removeInputs) {
        DoublyLinkedList<T> list = new DoublyLinkedList<>(input);

        assertEquals(list.size(), input.length);
        for (T remove : removeInputs) {
            assertEquals(remove, list.removeLast());
        }
    }

    static Stream<Integer[][]> mergeCases() {
        return Stream.of(
                new Integer[][]{{}, {}, {}}, // Both Empty
                new Integer[][]{{}, {2, 3}, {2, 3}}, // First one empty
                new Integer[][]{{1, 2}, {}, {1, 2}}, // Second one empty
                new Integer[][]{{3, 4}, {1, 2}, {3, 4, 1, 2}} // Both not empty
        );
    }

    @ParameterizedTest
    @MethodSource("mergeCases")
    void testMerge(Integer[][] cases) {
        Integer[] firstInput = cases[0];
        Integer[] secondInput = cases[1];
        Integer[] expectedOutput = cases[2];

        SinglyLinkedList<Integer> first = new SinglyLinkedList<>(firstInput);
        SinglyLinkedList<Integer> second = new SinglyLinkedList<>(secondInput);
        first.merge(second);
        Integer[] output = first.toArray(Integer.class);

        assertArrayEquals(expectedOutput, output);
    }
}