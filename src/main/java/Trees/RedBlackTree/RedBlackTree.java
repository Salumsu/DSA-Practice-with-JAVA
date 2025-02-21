package Trees.RedBlackTree;

import Trees.BinaryTree;
import Trees.BinaryTreeNode;
import Trees.SelfBalancingBinaryTree;

import java.util.Comparator;
import java.util.List;

public class RedBlackTree<T> extends SelfBalancingBinaryTree<T, RedBlackTreeNode<T>> {
    public RedBlackTree (Comparator<T> comparator) {
        super(comparator);
        this.head = null;
    }

    public RedBlackTree (Comparator<T> comparator, T value) {
        super(comparator, new RedBlackTreeNode<T>(value));
    }

    public RedBlackTree (Comparator<T> comparator, T[] values) {
        super(comparator, values);
    }

    public RedBlackTree (Comparator<T> comparator, List<T> values) {
        super(comparator, values);
    }

    public static <T extends Comparable<T>> RedBlackTree<T> create () {
        return new RedBlackTree<T>(Comparable::compareTo);
    }

    public static <T extends Comparable<T>> RedBlackTree<T> create (T value) {
        return new RedBlackTree<T>(Comparable::compareTo, value);
    }

    public static <T extends Comparable<T>> RedBlackTree<T> create (T[] values) {
        return new RedBlackTree<T>(Comparable::compareTo, values);
    }

    public static <T extends Comparable<T>> RedBlackTree<T> create (List<T> values) {
        return new RedBlackTree<T>(Comparable::compareTo, values);
    }

    @Override
    protected RedBlackTreeNode<T> castNode(BinaryTreeNode<T> node) {
        return (RedBlackTreeNode<T>) node;
    }



    @Override
    public T getHeadValue() {
        return this.head.getValue();
    }

    @Override
    protected RedBlackTreeNode<T> newNode(T value) {
        return new RedBlackTreeNode<>(value);
    }

    @Override
    protected RedBlackTreeNode<T> leftRotate(RedBlackTreeNode<T> node) {
        return null;
    }

    @Override
    protected RedBlackTreeNode<T> rightRotate(RedBlackTreeNode<T> node) {
        return null;
    }

    @Override
    protected RedBlackTreeNode<T> balanceTree(RedBlackTreeNode<T> node) {
        return null;
    }

    @Override
    protected ActionResult<T, RedBlackTreeNode<T>> doInsert(RedBlackTreeNode<T> curr, T value) {
        if (curr == null) {
            RedBlackTreeNode<T> newNode = newNode(value);
            newNode.toBlack();

            return new ActionResult<>(newNode, newNode);
        }
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult <= 0) {
            ActionResult<T, RedBlackTreeNode<T>> result = doInsert(curr.getRight(), value);
            curr.setRight(result.newRoot());
        } else {
            ActionResult<T, RedBlackTreeNode<T>> result = doInsert(curr.getLeft(), value);
            curr.setLeft(result.newRoot());
        }

        // CASE 1 RIGHT
        if (compareResult <= 0) {

        } else {

        }

        return null;
    }

    @Override
    protected ActionResult<T, RedBlackTreeNode<T>> doRemove(RedBlackTreeNode<T> parent, RedBlackTreeNode<T> curr, T value, boolean isRight) {
        return null;
    }
}
