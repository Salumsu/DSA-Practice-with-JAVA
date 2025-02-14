package HashTable;

public interface HashTableInterface<K, V extends Comparable<V>> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    V remove(K key);
    int size();
    boolean isEmpty();
}
