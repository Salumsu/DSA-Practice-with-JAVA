package Trees.BinarySearchTree;

import Heap.BinaryHeap;
import Queue.QueueAL;
import Trees.BinaryTree;
import Trees.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class requires a {@link Comparator} to define element ordering. However, if
 * the elements implement {@link Comparable}, the {@link #create()} factory method
 * can be used to construct a binary search tree without explicitly passing a comparator.
 *
 * @param <T> The type of elements in the heap.
 */
public class BinarySearchTree<T> extends BinaryTree<T, BinarySearchTreeNode<T>> {
    public BinarySearchTree (Comparator<T> comparator) {
        super(comparator);
        this.head = null;
    }

    public BinarySearchTree (Comparator<T> comparator, T value) {
        super(comparator, new BinarySearchTreeNode<T>(value));
    }

    public BinarySearchTree (Comparator<T> comparator, T[] values) {
        super(comparator, values);
    }

    @Override
    protected BinarySearchTreeNode<T> castNode(BinaryTreeNode<T> node) {
        return (BinarySearchTreeNode<T>) node;
    }

    private BinarySearchTreeNode<T> getHeadNode () {
        return (BinarySearchTreeNode<T>) this.head;
    }

    public static <T extends Comparable<T>> BinarySearchTree<T> create () {
        return new BinarySearchTree<T>(Comparable::compareTo);
    }

    public static <T extends Comparable<T>> BinarySearchTree<T> create (T value) {
        return new BinarySearchTree<T>(Comparable::compareTo, value);
    }

    public static <T extends Comparable<T>> BinarySearchTree<T> create (T[] values) {
        return new BinarySearchTree<T>(Comparable::compareTo, values);
    }

    private void setHead (T value) {
        this.head = new BinarySearchTreeNode<T>(value);
    }

    @Override
    public void insert (T value) {
        this.head = this.doInsert(this.getHeadNode(), value);
    }

    private BinarySearchTreeNode<T> doInsert (BinarySearchTreeNode<T> curr, T value) {
        if (curr == null) return new BinarySearchTreeNode<>(value);
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult <= 0) {
            curr.setRight(doInsert(curr.getRight(), value));
        } else {
            curr.setLeft(doInsert(curr.getLeft(), value));
        }

        return curr;
    }

    @Override
    public BinarySearchTreeNode<T> search(T value) {
        return (BinarySearchTreeNode<T>) super.search(value);
    }

    public BinarySearchTreeNode<T> remove(T value) {
        if (this.head == null) {
            return null;
        }

        return this.doRemove(null, this.getHeadNode(), value, false);
    }

    private BinarySearchTreeNode<T> doRemove(BinarySearchTreeNode<T> parent, BinarySearchTreeNode<T> curr, T value, boolean isRight) {
        int compareResult = this.comparator.compare(curr.getValue(), value);
        System.out.println("CURR NODE: " + curr);
        if (compareResult != 0) {
            BinarySearchTreeNode<T> searchNode = null;
            boolean _isRight = false;
            if (compareResult < 0) {
                searchNode = curr.getRight();
                _isRight = true;
            } else {
                searchNode = curr.getLeft();
            }

            if (searchNode != null) {
                System.out.println("SEARCH NODE: " + searchNode);
                return this.doRemove(curr, searchNode, value, _isRight);
            }
            return null;
        }

        if (curr.getRight() != null && curr.getLeft() != null) {
            SuccPredReturn<T, BinarySearchTreeNode<T>> successor = this.getInorderSuccessor(curr, curr.getRight());

            if (curr == successor.parent()) {
                curr.setRight(successor.parent().getRight().getRight());
            } else {
                successor.parent().removeLeft();
            }
            T val = curr.getValue();
            curr.setValue(successor.node().getValue());

            successor.node().setValue(val);

            return successor.node();
        } else if (curr.getRight() != null || curr.getLeft() != null) {
            BinarySearchTreeNode<T> node = curr.getRight() != null ? curr.getRight() : curr.getLeft();
            if (parent == null) {
                this.head = node;
            } else if (isRight) {
                parent.setRight(node);
            } else {
                parent.setLeft(node);
            }
        } else {
            if (parent == null) {
                this.head = null;
            } else if (isRight) {
                parent.removeRight();
            } else {
                parent.removeLeft();
            }
        }
        return curr;
    }
}
