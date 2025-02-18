package LinkedList;

public abstract class DLLNode<T> extends LLNode<T> {
    protected DLLNode<T> prev;

    public DLLNode(T value) {
        super(value);
    }

    public DLLNode<T> getPrev() {
        return this.prev;
    }

    public void setPrev(DLLNode<T> prev) {
        this.prev = prev;
    }

    public abstract void setPrev(T value);

    public void removePrev() {
        this.prev = null;
    }
}
