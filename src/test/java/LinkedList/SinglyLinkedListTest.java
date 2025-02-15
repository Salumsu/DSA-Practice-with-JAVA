package LinkedList;

import LinkedList.SinglyLinkedList.SinglyLinkedList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    @Test
    void testEmptyList () {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        assertThrows(NullPointerException.class, list::getHead);
        assertThrows(NullPointerException.class, list::pop);
        assertThrows(NullPointerException.class, () -> list.pop(2));
        assertThrows(NullPointerException.class, list::popHead);
        assertThrows(NullPointerException.class, () -> list.popHead(2));
        assertThrows(NullPointerException.class, () -> list.remove(0));
    }

    @Test
    void testSizeAfterAddingElements() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.append(10);
        list.append(20);
        list.append(30);
        assertEquals(3, list.size());
    }

    @Test
    void testHeadAfterPrepending() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.prepend(50);
        list.prepend(40);
        list.prepend(30);
        assertEquals(30, list.getHead());
    }

    @Test
    void testSizeAfterRemovingElements() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.remove(2);
        assertEquals(2, list.size());
    }

    @Test
    void testHeadAfterRemovingFirstElement() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.remove(0);
        assertEquals(2, list.getHead());
    }

    @Test
    void testOrderAfterMultipleOperations() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.prepend(0);
        list.remove(2);
        assertArrayEquals(new Integer[]{0, 1, 3}, list.toArray(Integer.class));
    }

    static <T> Stream<Arguments> arrayConstructorCases() {
        return Stream.of(
                Arguments.of(Integer.class, new Integer[]{1, 2, 3}, new Integer[]{1, 2, 3}),
                Arguments.of(Double.class, new Double[]{1.1, 2.2, 3.3}, new Double[]{1.1, 2.2, 3.3}),
                Arguments.of(String.class, new String[]{"a", "b", "c"}, new String[]{"a", "b", "c"}),
                Arguments.of(Character.class, new Character[]{'x', 'y', 'z'}, new Character[]{'x', 'y', 'z'}),
                Arguments.of(Long.class, new Long[]{100L, 200L, 300L}, new Long[]{100L, 200L, 300L}),
                Arguments.of(Short.class, new Short[]{10, 20, 30}, new Short[]{10, 20, 30}),
                Arguments.of(Byte.class, new Byte[]{1, 2, 3}, new Byte[]{1, 2, 3}),
                Arguments.of(Float.class, new Float[]{1.5f, 2.5f, 3.5f}, new Float[]{1.5f, 2.5f, 3.5f})
        );
    }

    @ParameterizedTest
    @MethodSource("arrayConstructorCases")
    <T extends Comparable<T>> void testToArray(Class<T> clazz, T[] input, T[] expectedOutout) {
        SinglyLinkedList<T> list = new SinglyLinkedList<>(input);
        T[] output = list.toArray(clazz);

        assertEquals(list.size(), input.length);
        assertArrayEquals(output, expectedOutout);
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