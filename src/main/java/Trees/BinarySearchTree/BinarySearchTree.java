package Trees.BinarySearchTree;

import Heap.BinaryHeap;
import Queue.QueueAL;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class requires a {@link Comparator} to define element ordering. However, if
 * the elements implement {@link Comparable}, the {@link #create()} factory method
 * can be used to construct a binary search tree without explicitly passing a comparator.
 *
 * @param <T> The type of elements in the heap.
 */
public class BinarySearchTree<T> {
    protected BinarySearchTreeNode<T> head;
    final private Comparator<T> comparator;

    public BinarySearchTree (Comparator<T> comparator) {
        this.head = null;
        this.comparator = comparator;
    }

    public BinarySearchTree (Comparator<T> comparator, T value) {
        this.head = new BinarySearchTreeNode<T>(value);
        this.comparator = comparator;
    }

    public BinarySearchTree (Comparator<T> comparator, T[] values) {
        this.comparator = comparator;

        if (values.length == 0) {
            this.head = null;
        }

        for (T value: values) {
            this.insert(value);
        }
    }

    public static <T extends Comparable<T>> BinarySearchTree<T> create () {
        return new BinarySearchTree<T>(Comparable::compareTo);
    }

    public static <T extends Comparable<T>> BinarySearchTree<T> create (T value) {
        return new BinarySearchTree<T>(Comparable::compareTo, value);
    }

    public static <T extends Comparable<T>> BinarySearchTree<T> create (T[] values) {
        return new BinarySearchTree<T>(Comparable::compareTo, values);
    }


    private void setHead (T value) {
        this.head = new BinarySearchTreeNode<T>(value);
    }

    private void replaceHead (BinarySearchTreeNode<T> newHead) {
        this.head = newHead;
    }

    public void insert (T value) {
        if (head == null) {
            this.setHead(value);
            return;
        }

        this.doInsert(this.head, value);
    }

    private void doInsert (BinarySearchTreeNode<T> curr, T value) {
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult <= 0) {
            if (curr.getRight() == null) {
                curr.setRight(value);
            } else {
                this.doInsert(curr.getRight(), value);
            }
        } else {
            if (curr.getLeft() == null) {
                curr.setLeft(value);
            } else {
                this.doInsert(curr.getLeft(), value);
            }
        }
    }

    public BinarySearchTreeNode<T> search(T value) {
        if (this.head == null) {
            return null;
        }

        return this.doSearch(value, this.head);
    }

    private BinarySearchTreeNode<T> doSearch (T value, BinarySearchTreeNode<T> curr) {
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

    public BinarySearchTreeNode<T> remove(T value) {
        if (this.head == null) {
            return null;
        }

        return this.doRemove(null, this.head, value, false);
    }

    private BinarySearchTreeNode<T> doRemove(BinarySearchTreeNode<T> parent, BinarySearchTreeNode<T> curr, T value, boolean isRight) {
        int compareResult = this.comparator.compare(curr.getValue(), value);
        if (compareResult != 0) {
            BinarySearchTreeNode<T> searchNode = null;
            boolean _isRight = false;
            if (compareResult < 0) {
                searchNode = curr.getRight();
                _isRight = true;
            } else {
                searchNode = curr.getLeft();
            }

            if (searchNode != null) {
                return this.doRemove(curr, searchNode, value, _isRight);
            }
            return null;
        }

        if (curr.getRight() != null && curr.getLeft() != null) {
            SuccPredReturn<T> successor = this.getInorderSuccessor(curr, curr.getRight());

            if (curr == successor.parent()) {
                curr.setRight(successor.parent().getRight().getRight());
            } else {
                successor.parent().removeLeft();
            }
            T val = curr.getValue();
            curr.setValue(successor.node().getValue());

            successor.node().setValue(val);

            return successor.node();
        } else if (curr.getRight() != null || curr.getLeft() != null) {
            BinarySearchTreeNode<T> right = curr.getRight() != null ? curr.getRight() : curr.getLeft();
            if (parent == null) {
                this.head = right;
            } else if (isRight) {
                parent.setRight(right);
            } else {
                parent.setLeft(right);
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
        return curr;
    }

    /**
     * A record representing a node and its parent in the BST.
     *
     * @param parent The parent of the found node.
     * @param node   The found node (either successor or predecessor).
     */
    private record SuccPredReturn <T>(BinarySearchTreeNode<T> parent, BinarySearchTreeNode<T> node) {}

    /**
     * Finds the inorder successor of a given node.
     * The inorder successor is the leftmost node in the right subtree.
     *
     * @param parent The node being removed, from which the search starts.
     * @param curr   The root of the right subtree.
     * @return A {@code SuccPredReturn} containing the inorder successor and its parent.
     */
    private SuccPredReturn<T> getInorderSuccessor(BinarySearchTreeNode<T> parent, BinarySearchTreeNode<T> curr) {
        if (curr.getLeft() == null) return new SuccPredReturn<>(parent, curr);

        return getInorderSuccessor(curr, curr.getLeft());
    }

    /**
     * Finds the inorder predecessor of a given node.
     * The inorder predecessor is the rightmost node in the left subtree.
     *
     * @param parent The node being removed, from which the search starts.
     * @param curr   The root of the left subtree.
     * @return A {@code SuccPredReturn} containing the inorder predecessor and its parent.
     */
    private SuccPredReturn<T> getInorderPredecessor(BinarySearchTreeNode<T> parent, BinarySearchTreeNode<T> curr) {
        if (curr.getRight() == null) return new SuccPredReturn<>(parent, curr);

        return getInorderPredecessor(curr, curr.getRight());
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

    private void doInorderTraversal (ArrayList<T> arrayList, BinarySearchTreeNode<T> curr) {
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
            this.doPreOrderTraversal(arrayList, this.head);
        }

        return arrayList;
    }

    private void doPreOrderTraversal (ArrayList<T> arrayList, BinarySearchTreeNode<T> curr) {
        if (arrayList == null) {
            System.out.println(curr.getValue());
        } else {
            arrayList.add(curr.getValue());
        }

        if (curr.getLeft() != null) {
            this.doPreOrderTraversal(arrayList, curr.getLeft());
        }

        if (curr.getRight() != null) {
            this.doPreOrderTraversal(arrayList, curr.getRight());
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
            this.doPostOrderTraversal(arrayList, this.head);
        }

        return arrayList;
    }

    private void doPostOrderTraversal (ArrayList<T> arrayList, BinarySearchTreeNode<T> curr) {
        if (curr.getLeft() != null) {
            this.doPostOrderTraversal(arrayList, curr.getLeft());
        }

        if (curr.getRight() != null) {
            this.doPostOrderTraversal(arrayList, curr.getRight());
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
        // TODO: Use your own implementation of Queue
        QueueAL<BinarySearchTreeNode<T>> queue = new QueueAL<>();

        if (this.head == null) {
            if (print) {
                System.out.println("The binary search three is empty.");
            }
        } else {
            queue.enqueue(this.head);
            doLevelOrderTraversal(arrayList, queue, print);
        }

        return arrayList;
    }

    private void doLevelOrderTraversal (ArrayList<T> arrayList, QueueAL<BinarySearchTreeNode<T>> queue, boolean print) {
        if (queue.isEmpty()) return;
        QueueAL<BinarySearchTreeNode<T>> tempQueue = new QueueAL<>();
        while (!queue.isEmpty()) {
            BinarySearchTreeNode<T> node = queue.dequeue();
            if (node.getLeft() != null) {
                tempQueue.enqueue(node.getLeft());
            }
            if (node.getRight() != null) {
                tempQueue.enqueue(node.getRight());
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
