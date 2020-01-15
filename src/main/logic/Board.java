package main.logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Board implements Runnable {
    private HashMap<Position, Cell> fields = new HashMap<>();
    private LinkedList<Observer> observers = new LinkedList<>();
    private boolean running = false;
    private int sleepTime = 500;

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public HashMap<Position, Cell> getCells() {
        return fields;
    }

    public void reset() {
        fields.clear();
        observers.forEach(Observer::change);
    }

    public void addCell(Position position, Cell cell) {
        fields.put(position, cell);
        observers.forEach(Observer::change);
    }

    public void removeCell(Position position) {
        fields.remove(position);
        observers.forEach(Observer::change);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public int countLivingNeighbours(Position position) {
        int result = 0;
        for (Neighbours n : Neighbours.values()) {
            if (this.fields.containsKey(n.nextPosition(position)) && this.fields.get(n.nextPosition(position)).isAlive())
                result++;
        }
        return result;
    }

    @Override
    public void run() {
        while (!Thread.interrupted() && running) {
            HashMap<Position, Cell> tmp = new HashMap<>();
            fields.keySet().forEach(position -> {
                this.fields.get(position).setLivingNeighbours(this.countLivingNeighbours(position));
                for (Neighbours n : Neighbours.values()) {
                    Position newPosition = n.nextPosition(position);
                    if (!this.fields.containsKey(newPosition)) {
                        Cell newCell = new Cell(false);
                        newCell.setLivingNeighbours(this.countLivingNeighbours(newPosition));
                        tmp.put(newPosition, newCell);
                    }
                }
            });

            fields.putAll(tmp);

            fields.values().forEach(Cell::updateStatus);

            fields.keySet().stream().filter(position -> !this.fields.get(position).isAlive()).collect(Collectors.toList()).forEach(fields::remove);

            observers.forEach(Observer::change);

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                System.exit(0);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Position p : fields.keySet()) {
            result.append(p.toString()).append(": ").append(fields.get(p).isAlive()).append("\n");
        }
        return result.toString();
    }
}
