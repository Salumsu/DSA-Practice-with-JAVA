package utils;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyAssertions {
    public static <T extends Comparable<T>> void assertArrayNotEquals (T[] a, T[] b, String message) {
        MyAssertions.assertArrayNotEquals(Arrays.asList(a), Arrays.asList(b), message);
    }

    public static <T extends Comparable<T>> void assertArrayNotEquals (List<T> a, List<T> b, String message) {
        assertEquals(a.size(), b.size());

        for (int i = 0; i < a.size(); i++) {
            assertNotEquals(a.get(i).compareTo(b.get(i)), 0,
                    message + ": Arrays at index " + i + " are equals: a[" + i + "] = " + a.get(i) + " and b[" + i + "] = " + b.get(i));
        }
    }
}
