package Recurssion;

public class IsArraySorted {
    public boolean solve (int[] array) {
        return this.solve(array, 0);
    }

    public boolean solve (int[] array, int n) {
        if (n >= array.length - 2) {
            return array[n] < array[n + 1];
        }
        return array[n] < array[n+1] & this.solve(array, n + 1);
    }
}
