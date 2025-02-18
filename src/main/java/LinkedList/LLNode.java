package LinkedList;


public abstract class LLNode<T> {
    protected T value;
    protected LLNode<T> next;

    public LLNode(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue () {
        return this.value;
    }

    public void setValue (T value) {
        this.value = value;
    }

    public LLNode<T> getNext() {
        return this.next;
    }

    public void setNext(LLNode<T> next) {
        this.next = next;
    }

    public abstract void setNext(T value);

    public void removeNext() {
        this.next = null;
    }
}
