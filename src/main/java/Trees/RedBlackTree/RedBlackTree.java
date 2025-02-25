package Trees.RedBlackTree;

import Trees.AVLTree.AVLTreeNode;
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

    private record NodeWithLoc<T, Node extends RedBlackTreeNode<T>> (Node node, boolean isLeft) {};

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
        RedBlackTreeNode<T> right = node.getRight();
        RedBlackTreeNode<T> newRight = right.getLeft();

        right.setLeft(node);
        node.setRight(newRight);

        return right;
    }

    @Override
    protected RedBlackTreeNode<T> rightRotate(RedBlackTreeNode<T> node) {
        RedBlackTreeNode<T> left = node.getLeft();
        RedBlackTreeNode<T> newLeft = left.getRight();

        left.setRight(node);
        node.setLeft(newLeft);

        return left;
    }

    @Override
    protected RedBlackTreeNode<T> balanceTree(RedBlackTreeNode<T> node) {
        return null;
    }

    static protected class CurrentNode<T> {
        public RedBlackTreeNode<T> node;
        public RedBlackTreeNode<T> parent;

        public RedBlackTreeNode<T> rotateNode;
        public boolean isLeftRotate;
    };

    @Override
    protected ActionResult<T, RedBlackTreeNode<T>> doInsert(RedBlackTreeNode<T> curr, T value) {
        CurrentNode<T> currNode = new CurrentNode<>();
        ActionResult<T, RedBlackTreeNode<T>> result = doInsert(null, curr, value, currNode);
        result.newRoot().toBlack();

        return result;
    }

    protected ActionResult<T, RedBlackTreeNode<T>> doInsert(RedBlackTreeNode<T> parent, RedBlackTreeNode<T> curr, T value, CurrentNode<T> currNodeRef) {
        System.out.println("+=====");
        System.out.println(curr);
        if (curr == null) {
            RedBlackTreeNode<T> newNode = newNode(value);
            if (parent == null) {
                newNode.toBlack();
            }
            currNodeRef.node = newNode;
            currNodeRef.parent = parent;

            return new ActionResult<>(newNode, newNode);
        }
        int compareResult = this.comparator.compare(curr.getValue(), value);
        ActionResult<T, RedBlackTreeNode<T>> result;
        if (compareResult <= 0) {
            result = doInsert(curr, curr.getRight(), value, currNodeRef);
            curr.setRight(result.newRoot());
        } else {
            result = doInsert(curr, curr.getLeft(), value, currNodeRef);
            curr.setLeft(result.newRoot());
        }

        if (curr == currNodeRef.parent && curr.isRed()) {
            System.out.println("TARGET NODE: " + currNodeRef.node);
            System.out.println("PARENT NODE: " + curr);
            System.out.println("GRANDPARENT NODE: " + parent);
            if (parent == null) {
                curr.toBlack();
            } else {
                NodeWithLoc<T, RedBlackTreeNode<T>> uncle = getOtherChild(parent, curr);
                boolean isUncleRed = uncle.node() != null && uncle.node().isRed();

                if (isUncleRed) {
                    uncle.node().toggleColor();
                    // POINT TO THE NEW TARGET NODE
                } else {
                    currNodeRef.rotateNode = parent;
                    if (uncle.isLeft()) {
                        if (this.comparator.compare(curr.getValue(), currNodeRef.node.getValue()) > 0) {
                            parent.setRight(rightRotate(curr));
                        }
                        currNodeRef.isLeftRotate = true;
                    } else {
                        if (this.comparator.compare(curr.getValue(), currNodeRef.node.getValue()) <= 0) {
                            parent.setLeft(leftRotate(curr));
                        }
                        currNodeRef.isLeftRotate = false;
                    }
                }

                curr.toggleColor();
                parent.toggleColor();
                currNodeRef.node = null;
            }
        }

        // IF TARGET NODE IS THE CURR, SET THE CURRENT PARENT AS THE TARGET NODES PARENT
        if (currNodeRef.node == curr) {
            currNodeRef.parent = parent;
        }

        if (currNodeRef.rotateNode == curr) {
            RedBlackTreeNode<T> newRoot;
            if (currNodeRef.isLeftRotate) {
                newRoot = leftRotate(curr);
            } else {
                newRoot = rightRotate(curr);
            }
            currNodeRef.rotateNode = null;

            return new ActionResult<>(newRoot, result.node());
        }

        return new ActionResult<>(curr, result.node());
    }

    private NodeWithLoc<T, RedBlackTreeNode<T>> getOtherChild (RedBlackTreeNode<T> parent, RedBlackTreeNode<T> child) {
        if (this.comparator.compare(parent.getValue(), child.getValue()) < 0) {
            return new NodeWithLoc<>(parent.getLeft(), true);
        }
        return new NodeWithLoc<>(parent.getRight(), false);
    }

    @Override
    protected ActionResult<T, RedBlackTreeNode<T>> doRemove(RedBlackTreeNode<T> parent, RedBlackTreeNode<T> curr, T value, boolean isRight) {
        return null;
    }
}
