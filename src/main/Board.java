package main;

import java.util.*;
import java.util.stream.Collectors;

public class Board {
    private HashMap<Position, Cell> fields = new HashMap<>();
    private LinkedList<Observer> observers = new LinkedList<>();

    public HashMap<Position, Cell> getCells() {
        return fields;
    }

    public boolean addObserver(Observer observer) {
        return observers.add(observer);
    }

    public boolean isEmpty() {
        return fields.isEmpty();
    }

    public int countLivingNeighbours(Position position) {
        int result = 0;
        for (Neighbours n : Neighbours.values()) {
            if (this.fields.get(n.nextPosition(position)).isAlive()) result++;
        }
        return result;
    }

    public void day() {
        fields.keySet().forEach(position -> {
            this.fields.get(position).setLivingNeighbours(this.countLivingNeighbours(position));
            for (Neighbours n : Neighbours.values()) {
                Position newPosition = n.nextPosition(position);
                if (!this.fields.containsKey(newPosition)) {
                    Cell newCell = new Cell();
                    newCell.setLivingNeighbours(this.countLivingNeighbours(newPosition));
                    this.fields.put(newPosition, newCell);
                }
            }
        });

        fields.values().forEach(Cell::updateStatus);

        fields.keySet().stream().filter(position -> this.fields.get(position).isAlive()).collect(Collectors.toList()).forEach(fields::remove);

        observers.forEach(Observer::change);
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
