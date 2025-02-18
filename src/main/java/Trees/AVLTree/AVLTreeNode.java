package Trees.AVLTree;

import Trees.BinarySearchTree.BinarySearchTreeNode;
import Trees.BinaryTreeNode;

public class AVLTreeNode<T> extends BinaryTreeNode<T> {
    protected T value;
    protected int height;
    AVLTreeNode<T> left;
    AVLTreeNode<T> right;

    public AVLTreeNode(T value) {
        super(value);
        this.height = 0;
    }

    public int getHeight () {
        return this.height;
    }

    public void setHeight (int height) {
        this.height = height;
    }

    public void incrementHeight () {
        this.height++;
    }

    public void decrementHeight () {
        this.height--;
    }

    public void updateHeight () {
        this.height = 1 + Math.max(
                this.getLeft() != null ? this.getLeft().getHeight() : -1,
                this.getRight() != null ? this.getRight().getHeight() : -1
        );
    }

    public int getBalanceFactor () {
        return (this.getLeft() != null ? this.getLeft().getHeight() : -1)
                -
                (this.getRight() != null ? this.getRight().getHeight() : -1);
    }

    @Override
    public AVLTreeNode<T> getLeft () {
        return (AVLTreeNode<T>) super.getLeft();
    }

    @Override
    public AVLTreeNode<T> getRight () {
        return (AVLTreeNode<T>) super.getRight();
    }

    @Override
    public void setLeft (BinaryTreeNode<T> left) {
        this.left = (AVLTreeNode<T>) left;
    }

    public void setLeft (T value) {
        this.left = new AVLTreeNode<>(value);
    }

    @Override
    public void setRight (BinaryTreeNode<T> right) {
        this.right = (AVLTreeNode<T>) right;
    }

    public void setRight (T value) {
        this.right = new AVLTreeNode<>(value);
    }
}
