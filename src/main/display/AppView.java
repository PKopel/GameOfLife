package main.display;

import main.logic.Board;
import main.logic.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings("FieldCanBeLocal")
public class AppView extends JFrame implements Observer {
    private BoardView boardView;
    private final ExecutorService exec = Executors.newCachedThreadPool();
    private JButton stopButton = new JButton("START");
    private JButton resetButton = new JButton("RESET");
    private JButton exitButton = new JButton("EXIT");
    private JButton returnButton = new JButton("RETURN");
    private JSlider sleepTime = new JSlider(100, 1000);

    public AppView(Board board) {
        this.boardView = new BoardView(board);

        board.addObserver(this);

        this.stopButton.addActionListener(e -> {
            if (board.isRunning()) {
                board.setRunning(false);
                stopButton.setText("START");
            } else {
                board.setRunning(true);
                exec.execute(board);
                stopButton.setText("STOP");
            }
        });

        this.resetButton.addActionListener(e -> {
            board.reset();
        });

        this.exitButton.addActionListener(e -> {
            exec.shutdownNow();
            dispose();
        });

        this.returnButton.addActionListener(e -> {
            Starter starter = new Starter();
            starter.setTitle("Game Of Life");
            starter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            starter.setSize(400, 100);
            starter.setVisible(true);
            dispose();
        });

        this.sleepTime.addChangeListener(e ->
                board.setSleepTime(sleepTime.getValue())
        );

        this.sleepTime.setBorder(new TitledBorder("FRAME LENGTH"));

        JPanel controls = new JPanel();
        controls.setLayout(new FlowLayout());
        controls.add(sleepTime);
        controls.add(stopButton);
        controls.add(resetButton);
        controls.add(returnButton);
        controls.add(exitButton);

        this.add(boardView);
        this.add(BorderLayout.SOUTH, controls);
    }

    @Override
    public void change() {
        boardView.repaint();
    }
}
