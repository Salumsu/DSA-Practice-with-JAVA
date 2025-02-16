package HashTable;

import java.util.ArrayList;
import java.util.Objects;

public class HashTable<K, V> implements HashTableInterface<K, V> {
    private int tableSize;
    private int currSize;
    private final double threshHold = 0.7; //

    private ArrayList<HashNode<K, V>> list;

    public HashTable () {
        this.tableSize = 10;
        this.currSize = 0;

        this.initializeList();
    }

    public HashTable (Integer size) {
        this.tableSize = size;
        this.currSize = 0;

        this.initializeList();
    }

    public void initializeList () {
        this.list = new ArrayList<HashNode<K, V>>();
        for (int i = 0; i < this.tableSize; i++) {
            list.add(null);
        }
    }

    public int size() {
        return this.currSize;
    }

    public boolean isEmpty () {
        return this.size() == 0;
    }

    private final int hashCode (K key) {
        return Objects.hashCode(key);
    }

    private int getItemIndex (K key) {
        int hashCode = this.hashCode(key);
        int index = Math.abs(hashCode % this.tableSize);

        return index;
    }

    private double getCurrentLoadFactor () {
        double loadFactor = 1.0 * this.size() / this.tableSize;
        return loadFactor;
    }

    private void resize (Double factor) {
        ArrayList<HashNode<K, V>> temp = this.list;
        this.list = new ArrayList<HashNode<K, V>>();
        this.tableSize = (int) (this.tableSize * factor);
        this.currSize = 0;

        for (int i = 0; i < this.tableSize; i++) {
            this.list.add(null);
        }

        for (HashNode<K, V> curr : temp) {
            while (curr != null) {
                this.add(curr.getKey(), curr.getValue());
                curr = curr.next;
            }
        }
    }

    public V get (K key) {
        int index = getItemIndex(key);
        int hashCode = this.hashCode(key);

        HashNode<K, V> head = this.list.get(index);

        while (head != null && !(head.getKey().equals(key) && hashCode == head.getHashCode())) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        return head.getValue();
    }

    public boolean containsKey (K key) {
        return this.get(key) != null;
    }

    public void put (K key, V value) {
        this.add(key, value);
    }

    public void add (K key, V value) {
        int index = getItemIndex(key);
        int hashCode = this.hashCode(key);

        HashNode<K, V> head = this.list.get(index);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);

        if (head == null) {
            this.currSize++;
            this.list.set(index, newNode);
            return;
        }

        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.getKey().equals(key) && head.getHashCode() == hashCode) {
                head.setValue(value);
                return;
            }
            prev = head;
            head = head.next;
        }

        this.currSize++;
        prev.next = newNode;

        double loadFactor = this.getCurrentLoadFactor();
        if (loadFactor >= this.threshHold) {
            this.resize(2.0);
        }
    }

    public V remove (K key) {
        int index = getItemIndex(key);
        int hashCode = this.hashCode(key);

        HashNode<K, V> head = this.list.get(index);
        HashNode<K, V> prev = null;

        while (head != null && !(head.getKey().equals(key) && head.getHashCode() == hashCode)) {
            prev = head;
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        this.currSize--;
        V value = head.getValue();
        if (prev == null) {
            this.list.set(index, head.next);
        } else {
            prev.next = head.next;
        }

        double loadFactor = this.getCurrentLoadFactor();
        if (loadFactor < 0.2 && this.tableSize > 10) {
            this.resize(0.5);
        }

        return value;
    }
}
