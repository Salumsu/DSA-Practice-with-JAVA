package utils;

@FunctionalInterface
public interface CompareTo<T> {
    int apply (T a);
}
