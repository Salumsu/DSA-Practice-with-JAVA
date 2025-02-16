package Trees.BinarySearchTree;

public class BinarySearchTreeNode <T> {
    protected T value;
    protected BinarySearchTreeNode<T> left;
    protected BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode(T value) {
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

    public BinarySearchTreeNode<T> getLeft () {
        return this.left;
    }

    public void setLeft (BinarySearchTreeNode<T> left) {
        this.left = left;
    }

    public void setLeft (T value) {
        this.left = new BinarySearchTreeNode<>(value);
    }

    public BinarySearchTreeNode<T> getRight () {
        return this.right;
    }

    public void setRight (BinarySearchTreeNode<T> right) {
        this.right = right;
    }

    public void setRight (T value) {
        this.right = new BinarySearchTreeNode<>(value);
    }

    public void removeLeft() {
        this.left = null;
    }

    public void removeRight() {
        this.right = null;
    }
}

