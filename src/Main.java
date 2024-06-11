import LinkedList.BasicLinkedList;
import LinkedList.CircularLinkedList;
import LinkedList.DoublyLinkedList;
import LinkedList.StackLinkedList;
import Recurssion.IsArraySorted;

public class Main {
    public static void main(String[] args) {
        int[] initialValue = {5, 4, 3, 2};
        BasicLinkedList ll = new BasicLinkedList(initialValue);

        System.out.println(ll.findNthValueFromEnd(0, 1));
    }
}