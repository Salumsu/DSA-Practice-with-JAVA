package Trees;

import Queue.QueueAL;
import Trees.BinarySearchTree.BinarySearchTreeNode;

import java.util.ArrayList;
import java.util.Comparator;

public abstract class BinaryTree<T, Node extends BinaryTreeNode<T>> {
    /**
     * A record representing a node and its parent in the BST.
     *
     * @param parent The parent of the found node.
     * @param node   The found node (either successor or predecessor).
     */
    protected record SuccPredReturn<T, Node extends BinaryTreeNode<T>>(Node parent, Node node) {}

    protected BinaryTreeNode<T> head;
    final protected Comparator<T> comparator;

    public BinaryTree (Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinaryTree (Comparator<T> comparator, BinaryTreeNode<T> head) {
        this(comparator);
        this.head = head;
    }

    public BinaryTree (Comparator<T> comparator, T[] values) {
        this(comparator);
        if (values.length == 0) {
            this.head = null;
        }

        for (T value: values) {
            this.insert(value);
        }
    }

    protected abstract Node castNode(BinaryTreeNode<T> node);

    public abstract void insert (T value);

    private void replaceHead (BinaryTreeNode<T> newHead) {
        this.head = newHead;
    }

    public BinaryTreeNode<T> search(T value) {
        if (this.head == null) {
            return null;
        }

        return this.doSearch(value, this.head);
    }

    private BinaryTreeNode<T> doSearch (T value, BinaryTreeNode<T> curr) {
        if (curr == null) return null;
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult == 0) {
            return curr;
        } else if (compareResult > 0) {
            return doSearch(value, curr.getLeft());
        } else {
            return doSearch(value, curr.getRight());
        }
    }

    /**
     * Finds the inorder successor of a given node.
     * The inorder successor is the leftmost node in the right subtree.
     *
     * @param parent The node being removed, from which the search starts.
     * @param curr   The root of the right subtree.
     * @return A {@code SuccPredReturn} containing the inorder successor and its parent.
     */
    protected SuccPredReturn<T, Node> getInorderSuccessor(Node parent, Node curr) {
        if (curr.getLeft() == null) return new SuccPredReturn<>(parent, curr);

        return getInorderSuccessor(curr, castNode(curr.getLeft()));
    }

    /**
     * Finds the inorder predecessor of a given node.
     * The inorder predecessor is the rightmost node in the left subtree.
     *
     * @param parent The node being removed, from which the search starts.
     * @param curr   The root of the left subtree.
     * @return A {@code SuccPredReturn} containing the inorder predecessor and its parent.
     */
    protected SuccPredReturn<T, Node> getInorderPredecessor(Node parent, Node curr) {
        if (curr.getRight() == null) return new SuccPredReturn<>(parent, curr);

        return getInorderPredecessor(curr, castNode(curr.getRight()));
    }

    /**
     * @return      The {@link ArrayList} result of the traversal.
     */
    public ArrayList<T> inOrderTraversal() {
        return this.inOrderTraversal(false);
    }

    /**
     * @param print {@code true} will print the result, {@code false} will return an {@link ArrayList}.
     * @return An {@link ArrayList} if {@code print} is false, otherwise {@code null}.
     */
    public ArrayList<T> inOrderTraversal(boolean print) {
        ArrayList<T> arrayList = print ? null : new ArrayList<>();

        if (this.head == null) {
            if (print) {
                System.out.println("The binary search three is empty.");
            }
        } else {
            this.doInorderTraversal(arrayList, this.head);
        }

        return arrayList;
    }

    private void doInorderTraversal (ArrayList<T> arrayList, BinaryTreeNode<T> curr) {
        if (curr.getLeft() != null) {
            this.doInorderTraversal(arrayList, curr.getLeft());
        }

        // Print it when arrayList is null
        if (arrayList == null) {
            System.out.println(curr.getValue());
        } else {
            arrayList.add(curr.getValue());
        }
        if (curr.getRight() != null) {
            this.doInorderTraversal(arrayList, curr.getRight());
        }
    }

    /**
     * @return      The {@link ArrayList} result of the traversal.
     */
    public ArrayList<T> preOrderTraversal () {
        return this.preOrderTraversal(false);
    }
    /**
     * @param print {@code true} will print the result, {@code false} will return an {@link ArrayList}.
     * @return An {@link ArrayList} if {@code print} is false, otherwise {@code null}.
     */

    public ArrayList<T> preOrderTraversal (boolean print) {
        ArrayList<T> arrayList = print ? null : new ArrayList<>();

        if (this.head == null) {
            if (print) {
                System.out.println("The binary search three is empty.");
            }
        } else {
            this.doPreOrderTraversal(arrayList, castNode(this.head));
        }

        return arrayList;
    }

    private void doPreOrderTraversal (ArrayList<T> arrayList, Node curr) {
        if (arrayList == null) {
            System.out.println(curr.getValue());
        } else {
            arrayList.add(curr.getValue());
        }

        if (curr.getLeft() != null) {
            this.doPreOrderTraversal(arrayList, castNode(curr.getLeft()));
        }

        if (curr.getRight() != null) {
            this.doPreOrderTraversal(arrayList, castNode(curr.getRight()));
        }
    }

    /**
     * @return      The {@link ArrayList} result of the traversal.
     */
    public ArrayList<T> postOrderTraversal () {
        return this.postOrderTraversal(false);
    }
    /**
     * @param print {@code true} will print the result, {@code false} will return an {@link ArrayList}.
     * @return An {@link ArrayList} if {@code print} is false, otherwise {@code null}.
     */
    public ArrayList<T> postOrderTraversal (boolean print) {
        ArrayList<T> arrayList = print ? null : new ArrayList<>();

        if (this.head == null) {
            if (print) {
                System.out.println("The binary search three is empty.");
            }
        } else {
            this.doPostOrderTraversal(arrayList, castNode(this.head));
        }

        return arrayList;
    }

    private void doPostOrderTraversal (ArrayList<T> arrayList, Node curr) {
        if (curr.getLeft() != null) {
            this.doPostOrderTraversal(arrayList, castNode(curr.getLeft()));
        }

        if (curr.getRight() != null) {
            this.doPostOrderTraversal(arrayList, castNode(curr.getRight()));
        }

        if (arrayList == null) {
            System.out.println(curr.getValue());
        } else {
            arrayList.add(curr.getValue());
        }
    }

    public ArrayList<T> levelOrderTraversal () {
        return levelOrderTraversal(false);
    }

    public ArrayList<T> levelOrderTraversal (boolean print) {
        ArrayList<T> arrayList = print ? null : new ArrayList<>();
        QueueAL<Node> queue = new QueueAL<>();

        if (this.head == null) {
            if (print) {
                System.out.println("The binary search three is empty.");
            }
        } else {
            queue.enqueue(castNode(this.head));
            doLevelOrderTraversal(arrayList, queue, print);
        }

        return arrayList;
    }

    private void doLevelOrderTraversal (ArrayList<T> arrayList, QueueAL<Node> queue, boolean print) {
        if (queue.isEmpty()) return;
        QueueAL<Node> tempQueue = new QueueAL<>();
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            if (node.getLeft() != null) {
                tempQueue.enqueue(castNode(node.getLeft()));
            }
            if (node.getRight() != null) {
                tempQueue.enqueue(castNode(node.getRight()));
            }
            if (print) {
                System.out.println(node.getValue());
            } else {
                arrayList.add(node.getValue());
            }
        }

        queue.merge(tempQueue);
        doLevelOrderTraversal(arrayList, queue, print);
    }
}
