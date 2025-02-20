package Trees.BinarySearchTree;

import Trees.BinaryTree;
import Trees.BinaryTreeNode;

import java.util.Comparator;
import java.util.List;

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

    public BinarySearchTree (Comparator<T> comparator, List<T> values) {
        super(comparator, values);
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

    public static <T extends Comparable<T>> BinarySearchTree<T> create (List<T> values) {
        return new BinarySearchTree<T>(Comparable::compareTo, values);
    }

    @Override
    protected BinarySearchTreeNode<T> castNode(BinaryTreeNode<T> node) {
        return (BinarySearchTreeNode<T>) node;
    }

    @Override
    protected BinarySearchTreeNode<T> newNode(T value) {
        return new BinarySearchTreeNode<>(value);
    }

    @Override
    protected BinarySearchTreeNode<T> getHeadNode () {
        return (BinarySearchTreeNode<T>) this.head;
    }

    @Override
    public T getHeadValue () {
        if (this.head == null) return null;
        return this.getHeadNode().getValue();
    }

    private void setHead (T value) {
        this.head = new BinarySearchTreeNode<T>(value);
    }

    protected ActionResult<T, BinarySearchTreeNode<T>> doInsert (BinarySearchTreeNode<T> curr, T value) {
        if (curr == null) {
            BinarySearchTreeNode<T> newNode = new BinarySearchTreeNode<>(value);
            return new ActionResult<>(newNode, newNode);
        }
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult <= 0) {
            ActionResult<T, BinarySearchTreeNode<T>> result = doInsert(curr.getRight(), value);
            curr.setRight(result.newRoot());
        } else {
            ActionResult<T, BinarySearchTreeNode<T>> result = doInsert(curr.getLeft(), value);
            curr.setLeft(result.newRoot());
        }

        return new ActionResult<>(curr, null);
    }

    @Override
    public BinarySearchTreeNode<T> search(T value) {
        return (BinarySearchTreeNode<T>) super.search(value);
    }

    @Override
    public BinarySearchTreeNode<T> remove(T value) {
        return (BinarySearchTreeNode<T>) super.remove(value);
    }

    @Override
    protected ActionResult<T, BinarySearchTreeNode<T>> doRemove(BinarySearchTreeNode<T> parent, BinarySearchTreeNode<T> curr, T value, boolean isRight) {
        if (curr == null) return null;
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult != 0) {
            if (compareResult < 0) {
                return this.doRemove(curr, curr.getRight(), value, true);
            } else {
                return this.doRemove(curr, curr.getLeft(), value, false);
            }
        }

        if (curr.getRight() != null && curr.getLeft() != null) {
            MinMaxReturn<T, BinarySearchTreeNode<T>> successor = this.getSuccessorNode(curr);

            if (curr == successor.parent()) {
                curr.setRight(successor.parent().getRight().getRight());
            } else {
                successor.parent().setLeft(successor.node().getRight());
            }
            T val = curr.getValue();
            curr.setValue(successor.node().getValue());

            successor.node().setValue(val);

            curr = successor.node();
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

        return new ActionResult<>(parent, curr);
    }
}
