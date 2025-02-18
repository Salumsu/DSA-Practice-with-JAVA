package Trees;


import Trees.BinarySearchTree.BinarySearchTreeNode;

public abstract class BinaryTreeNode<T> {
    protected T value;
    protected BinaryTreeNode<T> left;
    protected BinaryTreeNode<T> right;

    public BinaryTreeNode(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public T getValue () {
        return this.value;
    }

    public void setValue (T value) {
        this.value = value;
    }

    public BinaryTreeNode<T> getLeft () {
        return this.left;
    }

    public void setLeft (BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight () {
        return this.right;
    }

    public void setRight (BinaryTreeNode<T> right) {
        this.right = right;
    }

    public void removeLeft() {
        this.left = null;
    }

    public void removeRight() {
        this.right = null;
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
