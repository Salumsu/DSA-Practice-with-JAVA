package HashTable;

import LinkedList.SinglyLinkedList.SinglyLinkedListNode;

public class HashNode<K, V> extends SinglyLinkedListNode<V> {
    private K key;
    private final int hashCode;

    public HashNode<K, V> next;

    public HashNode(K key, V value, int hashCode) {
        super(value);
        this.key = key;
        this.hashCode = hashCode;
        this.next = null;
    }

    public K getKey() {
        return this.key;
    }

    public int getHashCode() {
        return this.hashCode;
    }
}
