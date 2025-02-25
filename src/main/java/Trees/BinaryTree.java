package Trees;

import Queue.QueueAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class BinaryTree<T, Node extends BinaryTreeNode<T>> {
    /**
     * A record representing a node and its parent in the BST.
     *
     * @param parent The parent of the found node.
     * @param node   The found node (either successor or predecessor).
     */
    protected record MinMaxReturn<T, Node extends BinaryTreeNode<T>>(Node parent, Node node) {}

    public record ActionResult<T, Node extends BinaryTreeNode<T>>(Node newRoot, Node node) {}

    protected Node head;

    final protected Comparator<T> comparator;

    public BinaryTree (Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public BinaryTree (Comparator<T> comparator, BinaryTreeNode<T> head) {
        this(comparator);
        this.head = castNode(head);
    }

    public BinaryTree (Comparator<T> comparator, T[] values) {
        this(comparator, Arrays.asList(values));
    }

    public BinaryTree (Comparator<T> comparator, List<T> values) {
        this(comparator);
        if (values.isEmpty()) {
            this.head = null;
        }

        for (T value: values) {
            this.insert(value);
        }
    }

    protected abstract Node castNode(BinaryTreeNode<T> node);
    // FOR TESTING PURPOSES
    public abstract T getHeadValue ();

    protected abstract Node newNode(T value);

    public Node insert (T value) {
        System.out.println("NEW VALUE: " + value);
        ActionResult<T, Node> result = doInsert(castNode(this.head), value);
        System.out.println("NEW ROOT: " + result.newRoot() + " : " + result.node());

        this.head = result.newRoot();
        System.out.println("NEW ROOT: " + this.head);

        return result.node();
    }

    protected abstract ActionResult<T, Node> doInsert (Node curr, T value);

    private void replaceHead (BinaryTreeNode<T> newHead) {
        this.head = castNode(newHead);
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

    public BinaryTreeNode<T> remove(T value) {
        if (this.head == null) {
            return null;
        }

        ActionResult<T, Node> result = this.doRemove(null, castNode(this.head), value, false);

        return result != null ? result.node() : null;
    }

    protected abstract ActionResult<T, Node> doRemove(Node parent, Node curr, T value, boolean isRight);

    /**
     * Finds the maximum node in a given subtree.
     * The minimum node is the leftmost node in the subtree.
     *
     * @param parent The parent of the current node.
     * @param curr   The node to start the search from.
     * @return A {@code MinMaxReturn} containing the minimum node and its parent.
     */
    protected MinMaxReturn<T, Node> doGetMinNode(Node parent, Node curr) {
        if (curr.getLeft() == null) return new MinMaxReturn<>(parent, curr);

        return doGetMinNode(curr, castNode(curr.getLeft()));
    }

    protected MinMaxReturn<T, Node> getMinNode(Node curr) {
        return doGetMinNode(null, curr);
    }

    protected MinMaxReturn<T, Node> getSuccessorNode (Node curr) {
        if (curr == null || curr.getRight() == null) return null;

        return doGetMinNode(curr, castNode(curr.getRight()));
    }

    /**
     * Finds the maximum node in a given subtree.
     * The maximum node is the rightmost node in the subtree.
     *
     * @param parent The parent of the current node.
     * @param curr   The node to start the search from.
     * @return A {@code MinMaxReturn} containing the maximum node and its parent.
     */
    protected MinMaxReturn<T, Node> doGetMaxNode(Node parent, Node curr) {
        if (curr.getRight() == null) return new MinMaxReturn<>(parent, curr);

        return doGetMaxNode(curr, castNode(curr.getRight()));
    }

    protected MinMaxReturn<T, Node> getMaxNode(Node curr) {
        return doGetMaxNode(null, curr);
    }

    protected MinMaxReturn<T, Node> getPredecessorNode (Node curr) {
        if (curr == null || curr.getLeft() == null) return null;

        return doGetMaxNode(curr, castNode(curr.getLeft()));
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

    protected void doInorderTraversal (ArrayList<T> arrayList, BinaryTreeNode<T> curr) {
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

    protected void doPreOrderTraversal (ArrayList<T> arrayList, Node curr) {
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

    protected void doPostOrderTraversal (ArrayList<T> arrayList, Node curr) {
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

    protected void doLevelOrderTraversal (ArrayList<T> arrayList, QueueAL<Node> queue, boolean print) {
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
