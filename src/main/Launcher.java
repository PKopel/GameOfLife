package main;

import main.display.Starter;

import javax.swing.*;

public class Launcher {
    public static void main(String[] args) {
        Starter starter = new Starter();
        starter.setTitle("Game Of Life");
        starter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        starter.setSize(400, 100);
        starter.setVisible(true);
    }
}
