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
        AppView appView = new AppView( new Board());
        appView.setTitle("Game Of Life");
        appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appView.setSize(600, 600);
        appView.setVisible(true);
    }
}
