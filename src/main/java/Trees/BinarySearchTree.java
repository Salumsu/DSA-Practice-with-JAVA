package Trees;

import Queue.QueueAL;

import java.util.ArrayList;

class BinarySearchTreeNode <T extends Comparable<T>> {
    private T value;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

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
}

public class BinarySearchTree<T extends Comparable<T>> {
    BinarySearchTreeNode<T> head;
    int count;

    public BinarySearchTree () {
        this.head = null;
    }

    public BinarySearchTree (T value) {
        this.head = new BinarySearchTreeNode<T>(value);
    }

    public BinarySearchTree (T[] values) {
        if (values.length == 0) {
            this.head = null;
        }

        for (int i = 0; i < values.length; i++) {
            this.insert(values[i]);
        }
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
        int compareResult = curr.getValue().compareTo(value);
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
