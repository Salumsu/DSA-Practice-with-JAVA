package Trees.RedBlackTree;

import Trees.BinaryTreeNode;

public class RedBlackTreeNode<T> extends BinaryTreeNode<T> {
    protected boolean isBlack;
    public RedBlackTreeNode(T value) {
        super(value);
        this.isBlack = false;
    }

    @Override
    public void setLeft(T value) {
        this.left = new RedBlackTreeNode<>(value);
    }

    @Override
    public void setRight(T value) {
        this.right = new RedBlackTreeNode<T>(value);
    }

    @Override
    public RedBlackTreeNode<T> getLeft() {
        return (RedBlackTreeNode<T>) this.left;
    }

    @Override
    public RedBlackTreeNode<T> getRight() {
        return (RedBlackTreeNode<T>) this.right;
    }
    public void toRed () {
        this.isBlack = false;
    }

    public void toBlack () {
        this.isBlack = true;
    }

    public void toggle () {
        this.isBlack = !this.isBlack;
    }

    public boolean isBlack () {
        return this.isBlack;
    }
}
