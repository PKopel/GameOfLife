package main.logic;

public class Position {
    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Position)
            return this.x == ((Position) o).x && this.y == ((Position) o).y;
        else return false;
    }

    @Override
    public int hashCode() {
        int hash = 17 * x;
        hash += (hash + y) * 17;
        return hash;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
