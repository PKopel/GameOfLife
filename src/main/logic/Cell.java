package main.logic;

public class Cell {
    private boolean alive;
    private int livingNeighbours = 0;

    public Cell(boolean alive) {
        this.alive = alive;
    }

    public void setLivingNeighbours(int livingNeighbours) {
        this.livingNeighbours = livingNeighbours;
    }

    public boolean isAlive() {
        return alive;
    }

    public void updateStatus() {
        if (alive) {
            alive = Properties.getProperties().getStayingAlive().contains(livingNeighbours);
        } else {
            alive = Properties.getProperties().getGettingAlive().contains(livingNeighbours);
        }
    }
}
