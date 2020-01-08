package main;

public class Cell {
    private boolean alive = true;
    private int livingNeighbours = 0;

    public void setLivingNeighbours(int livingNeighbours){
        this.livingNeighbours = livingNeighbours;
    }

    public void resetNeighbours(){
        livingNeighbours = 0;
    }

    public int getLivingNeighbours(){
        return this.livingNeighbours;
    }

    public boolean isAlive(){
        return alive;
    }

    public void updateStatus(){

    }
}
