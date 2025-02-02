import HashTable.HashTable;
import LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer, String> hashTable = new HashTable<Integer, String>(5);

        hashTable.add(1, "One");
        hashTable.add(6, "Six");
        hashTable.add(11, "Eleven");

        hashTable.remove(11);

        System.out.println(hashTable.get(1));
        System.out.println(hashTable.get(6));
        System.out.println(hashTable.get(11));
    }
}