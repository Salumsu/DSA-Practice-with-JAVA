package utils;

import java.util.Comparator;

public class CompareObjectTests<T extends Comparable<T>> implements Comparator<ObjectTest<T>> {
    @Override
    public int compare(ObjectTest<T> a, ObjectTest<T> b) {
        return a.value.compareTo(b.value);
    }
}
