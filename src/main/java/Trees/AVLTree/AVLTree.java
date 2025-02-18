package Trees.AVLTree;

import Trees.BinarySearchTree.BinarySearchTree;
import Trees.BinarySearchTree.BinarySearchTreeNode;
import Trees.BinaryTree;
import Trees.BinaryTreeNode;

import java.util.Comparator;

public class AVLTree<T> extends BinaryTree<T, AVLTreeNode<T>> {
    public AVLTree (Comparator<T> comparator) {
        super(comparator);
    }

    public AVLTree (Comparator<T> comparator, T value) {
        super(comparator, new AVLTreeNode<>(value));
    }

    public AVLTree (Comparator<T> comparator, T[] values) {
        super(comparator, values);
    }

    @Override
    protected AVLTreeNode<T> castNode(BinaryTreeNode<T> node) {
        return (AVLTreeNode<T>) node;
    }

    private AVLTreeNode<T> getHeadNode () {
        return (AVLTreeNode<T>) this.head;
    }

    public static <T extends Comparable<T>> AVLTree<T> create () {
        return new AVLTree<T>(Comparable::compareTo);
    }

    public static <T extends Comparable<T>> AVLTree<T> create (T value) {
        return new AVLTree<T>(Comparable::compareTo, value);
    }

    public static <T extends Comparable<T>> AVLTree<T> create (T[] values) {
        return new AVLTree<T>(Comparable::compareTo, values);
    }

    public AVLTreeNode<T> leftRotate(AVLTreeNode<T> node) {
        AVLTreeNode<T> right = node.getRight();
        AVLTreeNode<T> newRight = right.getLeft();

        right.setLeft(node);
        node.setRight(newRight);

        node.updateHeight();
        right.updateHeight();

        return right;
    }

    public AVLTreeNode<T> rightRotate(AVLTreeNode<T> node) {
        AVLTreeNode<T> left = node.getLeft();
        AVLTreeNode<T> newLeft = left.getRight();

        left.setRight(node);
        node.setLeft(newLeft);

        node.updateHeight();

        left.updateHeight();

        return left;
    }

    @Override
    public void insert(T value) {
        this.head = doInsert(this.getHeadNode(), value);

    }

    public AVLTreeNode<T> doInsert(AVLTreeNode<T> curr, T value) {
        if (curr == null) return new AVLTreeNode<>(value);
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult <= 0) {
            curr.setRight(doInsert(curr.getRight(), value));
        } else {
            curr.setLeft(doInsert(curr.getLeft(), value));
        }

        curr.updateHeight();

        int balanceFactor = curr.getBalanceFactor();
        if (balanceFactor < -1) {
            // ZIGZAG
            if (this.comparator.compare(curr.getRight().getValue(), value) > 0) {
                curr.setRight(this.rightRotate(curr.getRight()));
            }
            return this.leftRotate(curr);
        } else if (balanceFactor > 1) {
            if (this.comparator.compare(curr.getLeft().getValue(), value) <= 0) {
                curr.setLeft(this.leftRotate(curr.getLeft()));
            }
            return this.rightRotate(curr);
        }

        return curr;
    }


}
