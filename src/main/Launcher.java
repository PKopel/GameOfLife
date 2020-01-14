package main;

import main.display.AppView;
import main.logic.Board;
import main.logic.Cell;
import main.logic.Position;
import main.logic.Properties;

import javax.swing.*;
import java.util.LinkedList;

public class Launcher {
    public static void main(String[] args) {
        LinkedList<Integer> gettingAlive = new LinkedList<>();
        gettingAlive.add(3);
        LinkedList<Integer> stayingAlive = new LinkedList<>();
        stayingAlive.add(2);
        stayingAlive.add(3);
        Properties.getProperties().setGettingAlive(gettingAlive);
        Properties.getProperties().setStayingAlive(stayingAlive);
        Board board = new Board();
        board.addCell(new Position(1,1), new Cell(true));
        board.addCell(new Position(1,2), new Cell(true));
        board.addCell(new Position(1, 3), new Cell(true));
        //board.addCell(new Position(2,2), new Cell(true));
        AppView appView = new AppView(board);
        appView.setTitle("Animal Evolution");
        appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appView.setSize(300, 180);
        appView.setVisible(true);
        board.run();
    }
}
