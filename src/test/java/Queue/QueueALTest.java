package Queue;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class QueueALTest {
    @Test
    void testEmptyQueue () {
        QueueAL<Integer> queue = new QueueAL<>();

        assertThrows(EmptyStackException.class, queue::dequeue);
        assertEquals(queue.size(), 0);
        assertTrue(queue.isEmpty());
    }

    @Test
    void testDequeue () {
        QueueAL<Integer> queue = new QueueAL<>(new Integer[]{1, 2, 3});
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    void testQueue () {
        QueueAL<Integer> queue = new QueueAL<>();

        assertThrows(EmptyStackException.class, queue::peek);

        queue.enqueue(3);
        assertEquals(queue.peek(), 3);

        queue.enqueue(4);
        assertEquals(queue.rear(), 4);

        assertEquals(queue.dequeue(), 3);
        assertEquals(queue.dequeue(), 4);

        assertThrows(EmptyStackException.class, queue::rear);
    }
}