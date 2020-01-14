package main.display;

import main.logic.Board;
import main.logic.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppView extends JFrame implements Observer {
    private BoardView boardView;
    private final ExecutorService exec = Executors.newCachedThreadPool();
    private JButton stopButton = new JButton("START");

    public AppView(Board board){
        this.boardView = new BoardView(board);

        board.addObserver(this);
        stopButton.addActionListener(e -> {
            if (board.isRunning()){
                board.setRunning(false);
                stopButton.setText("START");
            } else {
                board.setRunning(true);
                exec.execute(board);
                stopButton.setText("STOP");
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
