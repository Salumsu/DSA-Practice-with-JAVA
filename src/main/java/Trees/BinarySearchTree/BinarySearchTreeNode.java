package Trees.BinarySearchTree;

import Trees.BinaryTreeNode;

public class BinarySearchTreeNode <T> extends BinaryTreeNode<T> {
    protected T value;

    public BinarySearchTreeNode(T value) {
        super(value);
    }

    @Override
    public BinarySearchTreeNode<T> getLeft () {
        return (BinarySearchTreeNode<T>) super.getLeft();
    }

    @Override
    public BinarySearchTreeNode<T> getRight () {
        return (BinarySearchTreeNode<T>) super.getRight();
    }

    @Override
    public void setLeft (BinaryTreeNode<T> left) {
        this.left = (BinarySearchTreeNode<T>) left;
    }

    public void setLeft (T value) {
        this.left = new BinarySearchTreeNode<>(value);
    }

    @Override
    public void setRight (BinaryTreeNode<T> right) {
        this.right = (BinarySearchTreeNode<T>) right;
    }

    public void setRight (T value) {
        this.right = new BinarySearchTreeNode<>(value);
    }
}

