package LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListWithTailTest {
    @Test
    void testEmptyList () {
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();

        assertThrows(NullPointerException.class, list::getHead);
        assertThrows(NullPointerException.class, list::getTail);
        assertThrows(NullPointerException.class, list::pop);
        assertThrows(NullPointerException.class, () -> list.pop(2));
        assertThrows(NullPointerException.class, list::popHead);
        assertThrows(NullPointerException.class, () -> list.popHead(2));
        assertThrows(NullPointerException.class, () -> list.remove(0));
    }

    @Test
    void testSizeAfterAddingElements() {
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
        list.append(10);
        list.append(20);
        list.append(30);
        assertEquals(3, list.size());
    }

    @Test
    void testHeadAfterPrepending() {
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
        list.prepend(50);
        list.prepend(40);
        list.prepend(30);
        assertEquals(30, list.getHead());
    }

    @Test
    void testTailAfterPrepending() {
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
        list.prepend(50);
        list.prepend(40);
        list.prepend(30);
        assertEquals(50, list.getTail());
    }

    @Test
    void testSizeAfterRemovingElements() {
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
        list.append(1);
        list.append(2);
        list.append(3);
        System.out.println(list.size());
        list.remove(2);
        assertEquals(2, list.size());
    }

    @Test
    void testHeadAfterRemovingFirstElement() {
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.remove(0);
        assertEquals(2, list.getHead());
    }

    @Test
    void testTailAfterRemovingLastElement() {
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
        list.append(1);
        list.append(2);
        list.append(3);
        list.remove(2);
        assertEquals(2, list.getTail());
    }

    @Test
    void testOrderAfterMultipleOperations() {
        SinglyLinkedListWithTail<Integer> list = new SinglyLinkedListWithTail<>();
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
        SinglyLinkedListWithTail<T> list = new SinglyLinkedListWithTail<>(input);
        T[] output = list.toArray(clazz);

        assertEquals(list.size(), input.length);
        assertEquals(input[0], list.getHead());
        assertEquals(input[input.length - 1], list.getTail());
        assertArrayEquals(output, expectedOutout);
    }


}