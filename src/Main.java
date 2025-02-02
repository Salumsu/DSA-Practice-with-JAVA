import LinkedList.SinglyLinkedList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));

        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>(list);
        System.out.println(linkedList.toString());
        System.out.println(linkedList.remove(3));

        System.out.println(linkedList.toString());
    }
}