package Stack;

public class DynamicStackArray<T> {
    protected int minCapacity = 10;
    protected int capacity = 10;
    protected int top = -1;
    protected T[] array;

    public DynamicStackArray() {
        this.array = (T[]) new Object[this.capacity];
    }

    public DynamicStackArray(int minCapacity) {
        this.minCapacity = minCapacity;
        this.capacity = minCapacity;
        this.array = (T[]) new Object[minCapacity];
    }

    public DynamicStackArray(int minCapacity, int capacity) {
        this.minCapacity = minCapacity;
        this.capacity = Math.max(minCapacity, capacity % 2 == 0 ? capacity : capacity + 1);
        this.array = (T[]) new Object[this.capacity];
    }

    protected void doubleSize() {
        if (this.top < this.capacity - 1) {
            return;
        }
        T[] temp = (T[]) new Object[this.capacity * 2];
        System.arraycopy(this.array, 0, temp, 0, this.capacity);
        this.capacity *= 2;
        this.array = temp;
    }

    protected void shrink() {
        if (this.top > (this.capacity / 4) && this.capacity < this.minCapacity * 2) {
            return;
        }
        T[] temp = (T[]) new Object[this.capacity / 2];
        System.arraycopy(this.array, 0, temp, 0, this.capacity / 2);
        this.capacity /= 2;
        this.array = temp;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public int size() {
        return this.top + 1;
    }

    public T top() {
        return this.array[this.top];
    }

    public void push(T value) {
        this.doubleSize();
        this.array[++this.top] = value;
    }

    public T pop() {
        this.shrink();
        T value = this.array[this.top];
        this.array[this.top--] = null; // Clear reference to help with garbage collection
        return value;
    }

    public int getCurrentCapacity() {
        return this.capacity;
    }

    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }
        StringBuilder str = new StringBuilder("[");
        str.append(this.array[0]);
        for (int i = 1; i <= this.top; i++) {
            str.append(", ");
            str.append(this.array[i]);
        }

        str.append("]");
        return str.toString();
    }
}
