import LinkedList.BasicLinkedList;
import LinkedList.CircularLinkedList;
import LinkedList.DoublyLinkedList;
import LinkedList.StackLinkedList;
import Recurssion.IsArraySorted;
import Stack.DynamicStackArray;
import Stack.IsSymbolsBalanced;
import Stack.StackArray;

public class Main {
    public static void main(String[] args) {
        IsSymbolsBalanced checker = new IsSymbolsBalanced();

        System.out.println(checker.isBalanced("()(()[()])"));
    }
}