package utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ObjectTest<T extends Comparable<T>> {
    public T value;

    public ObjectTest(T value) {
        this.value = value;
    }

    public static <U extends Comparable<U>> List<U> toArray (List<ObjectTest<U>> objects) {
        ArrayList<U> items = new ArrayList<>();
        for (ObjectTest<U> object : objects) {
            items.add(object.value);
        }

        return items;
    }

    public static <U extends Comparable<U>> List<ObjectTest<U>> createObjectTests(U[] values) {
        ArrayList<ObjectTest<U>> items = new ArrayList<>();

        for (U value: values) {
            items.add(new ObjectTest<>(value));
        }

        return items;
    }
}
