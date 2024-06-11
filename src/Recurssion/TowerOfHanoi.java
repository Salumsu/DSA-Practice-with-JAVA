package Recurssion;

public class TowerOfHanoi {
    public void solve (int n, int fromTower, int toTower) {
        int auxTower = 6 - (fromTower + toTower);
        this.solve(n, fromTower, toTower, auxTower);
    }

    public void solve (int n, int fromTower, int toTower, int auxTower) {
        if (n == 1) {
            System.out.println("Move disk from tower " + fromTower + " to tower " + toTower);
            return;
        }

        this.solve(n - 1, fromTower, auxTower, toTower);

        System.out.println("Move disk from tower " + fromTower + " to tower " + toTower);

        this.solve(n - 1, auxTower, toTower, fromTower);
    }
}
