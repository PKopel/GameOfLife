package main;

import main.display.AppView;
import main.logic.Board;
import main.logic.Properties;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        Properties.getProperties().parseRules(args[0]);
        AppView appView = new AppView(new Board());
        appView.setTitle("Game Of Life");
        appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appView.setSize(600, 600);
        appView.setVisible(true);
    }
}
