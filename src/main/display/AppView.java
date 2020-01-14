package main.display;

import main.logic.Board;
import main.logic.Observer;

import javax.swing.*;
import java.awt.*;

public class AppView extends JFrame implements Observer {
    private BoardView boardView;
    private JButton stopButton = new JButton("STOP");

    public AppView(Board board){
        this.boardView = new BoardView(board);

        board.addObserver(this);
        stopButton.addActionListener(action -> {
            if (board.isRunning()){
                board.setRunning(false);
            } else {
                board.setRunning(true);
                board.run();
            }
        });

        this.add(boardView);
        this.add(BorderLayout.SOUTH, stopButton);
    }

    @Override
    public void change() {
        boardView.repaint();
    }
}
