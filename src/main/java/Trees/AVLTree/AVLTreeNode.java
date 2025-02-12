package Trees.AVLTree;

import Trees.BinarySearchTree.BinarySearchTreeNode;

public class AVLTreeNode<T extends Comparable<T>> extends BinarySearchTreeNode<T> {
    protected int height;
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
}
