package Trees;

import java.util.Comparator;
import java.util.List;

public abstract class SelfBalancingBinaryTree<T, Node extends BinaryTreeNode<T>> extends BinaryTree<T, Node> {
    public SelfBalancingBinaryTree(Comparator<T> comparator) {
        super(comparator);
    }

    public SelfBalancingBinaryTree(Comparator<T> comparator, BinaryTreeNode<T> head) {
        super(comparator, head);
    }

    public SelfBalancingBinaryTree(Comparator<T> comparator, T[] values) {
        super(comparator, values);
    }

    public SelfBalancingBinaryTree(Comparator<T> comparator, List<T> values) {
        super(comparator, values);
    }

    protected abstract Node leftRotate(Node node);
    protected abstract Node rightRotate(Node node);
    protected abstract Node balanceTree(Node node);
}
