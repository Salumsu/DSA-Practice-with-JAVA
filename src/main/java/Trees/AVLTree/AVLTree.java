package Trees.AVLTree;

import Trees.BinaryTree;
import Trees.BinaryTreeNode;

import java.util.Comparator;
import java.util.List;

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

    public AVLTree (Comparator<T> comparator, List<T> values) {
        super(comparator, values);
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

    public static <T extends Comparable<T>> AVLTree<T> create (List<T> values) {
        return new AVLTree<T>(Comparable::compareTo, values);
    }

    @Override
    protected AVLTreeNode<T> castNode(BinaryTreeNode<T> node) {
        return (AVLTreeNode<T>) node;
    }

    @Override
    protected AVLTreeNode<T> getHeadNode () {
        return (AVLTreeNode<T>) this.head;
    }

    @Override
    public T getHeadValue() {
        if (this.head == null) return null;
        return this.getHeadNode().getValue();
    }

    @Override
    protected AVLTreeNode<T> newNode(T value) {
        return new AVLTreeNode<>(value);
    }

    public int getHeight () {
        if (this.head == null) return 0;
        return this.getHeadNode().getHeight() + 1;
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

    protected AVLTreeNode<T> balanceTree (AVLTreeNode<T> node) {
        int balanceFactor = node.getBalanceFactor();
        if (balanceFactor < -1) {
            // ZIGZAG
            if (node.getRight().getBalanceFactor() > 0) {

                node.setRight(this.rightRotate(node.getRight()));
            }

            return this.leftRotate(node);
        } else if (balanceFactor > 1) {
            if (node.getLeft().getBalanceFactor() < 0) {
                node.setLeft(this.leftRotate(node.getLeft()));
            }
            return this.rightRotate(node);
        }

        return node;
    }

    public ActionResult<T, AVLTreeNode<T>> doInsert(AVLTreeNode<T> curr, T value) {
        if (curr == null) {
            AVLTreeNode<T> newNode = new AVLTreeNode<>(value);
            return new ActionResult<>(newNode, newNode);
        }
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult <= 0) {
            ActionResult<T, AVLTreeNode<T>> result = doInsert(curr.getRight(), value);
            curr.setRight(result.newRoot());
        } else {
            ActionResult<T, AVLTreeNode<T>> result = doInsert(curr.getLeft(), value);
            curr.setLeft(result.newRoot());
        }

        curr.updateHeight();

        return new ActionResult<>(balanceTree(curr), null);
    }

    @Override
    public AVLTreeNode<T> search(T value) {
        return (AVLTreeNode<T>) super.search(value);
    }

    @Override
    public AVLTreeNode<T> remove(T value) {
        System.out.println("REMOVE: " + value);
        return (AVLTreeNode<T>) super.remove(value);
    }

    @Override
    protected ActionResult<T, AVLTreeNode<T>> doRemove (AVLTreeNode<T> parent, AVLTreeNode<T> curr, T value, boolean isRight) {
        if (curr == null) return new ActionResult<>(parent, null);
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult != 0) {
            ActionResult<T, AVLTreeNode<T>> result;
            if (compareResult < 0) {
                result = this.doRemove(curr, curr.getRight(), value, true);
            } else {
                result = this.doRemove(curr, curr.getLeft(), value, false);
            }

            if (result.node() != null) {
                curr.updateHeight();
                if (parent != null) {
                    if (isRight) {
                        parent.setRight(balanceTree(curr));
                    } else {
                        parent.setRight(balanceTree(curr));
                    }
                }
            }

            return result;
        }

        if (curr.getRight() != null && curr.getLeft() != null) {
            MinMaxReturn<T, AVLTreeNode<T>> successor = this.getSuccessorNode(curr);

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
            AVLTreeNode<T> node = curr.getRight() != null ? curr.getRight() : curr.getLeft();
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

    public void checkBalance () {
        _checkBalance(this.getHeadNode());
    }

    private void _checkBalance (AVLTreeNode<T> curr) {
        if (curr == null) return;
        System.out.println("NODE " + curr + ", HEIGHT: " + curr.getHeight() + ", BALANCE FACTOR: " + curr.getBalanceFactor());

        _checkBalance(curr.getLeft());
        _checkBalance(curr.getRight());
    }
}
