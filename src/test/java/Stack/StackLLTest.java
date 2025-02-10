
import Stack.StackLL;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackLLTest {
    @Test
    void testEmptyStack () {
        StackLL<Integer> stack = new StackLL<>();

        assertThrows(EmptyStackException.class, stack::pop);
        assertEquals(stack.size(), 0);
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPop () {
        StackLL<Integer> stack = new StackLL<>(new Integer[]{1, 2, 3});
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void testStack () {
        StackLL<Integer> stack = new StackLL<>();

        assertThrows(EmptyStackException.class, stack::top);

        stack.push(3);
        assertEquals(stack.top(), 3);

        stack.push(4);
        assertEquals(stack.top(), 4);

        assertEquals(stack.pop(), 4);
        assertEquals(stack.pop(), 3);

        assertThrows(EmptyStackException.class, stack::top);
    }
}