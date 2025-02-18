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

        assertNull(list.removeFirst());
        assertNull(list.removeLast());
        assertNull(list.getHeadValue());
        assertNull(list.getTailValue());
    }

    @Test
    void testSizeAfterAddingElements() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        assertEquals(3, list.size());
    }

    @Test
    void testHeadAfterPrepending() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addLast(50);
        list.addLast(40);
        list.addLast(30);
        assertEquals(50, list.getHeadValue());
    }

    @Test
    void testSizeAfterRemovingElements() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.remove(2);
        assertEquals(2, list.size());
    }

    @Test
    void testHeadAfterRemovingFirstElement() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.remove(0);
        assertEquals(2, list.getHeadValue());
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
    <T extends Comparable<T>> void toArrayList(Class<T> clazz, T[] input, T[] expectedOutout) {
        SinglyLinkedList<T> list = new SinglyLinkedList<>(input);

        @SuppressWarnings("unchecked")
        T[] output = (T[]) list.toArrayList().toArray(new Comparable[0]);

        assertEquals(input.length, list.size());
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
        Integer[] output = first.toArrayList().toArray(new Integer[0]);

        assertArrayEquals(expectedOutput, output);
    }
}