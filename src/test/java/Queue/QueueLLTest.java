
import Queue.QueueLL;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class QueueLLTest {
    @Test
    void testEmptyQueue () {
        QueueLL<Integer> queue = new QueueLL<>();

        assertThrows(EmptyStackException.class, queue::dequeue);
        assertEquals(queue.size(), 0);
        assertTrue(queue.isEmpty());
    }

    @Test
    void testDequeue () {
        QueueLL<Integer> queue = new QueueLL<>(new Integer[]{1, 2, 3});
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    void testQueue () {
        QueueLL<Integer> queue = new QueueLL<>();

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