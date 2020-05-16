package main.display;

import main.logic.Board;
import main.logic.Observer;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("FieldCanBeLocal")
public class AppView extends JFrame implements Observer {
    private final BoardView boardView;
    private final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
    private final JButton stopButton = new JButton("START");
    private final JButton resetButton = new JButton("RESET");
    private final JButton exitButton = new JButton("EXIT");
    private final JButton returnButton = new JButton("RETURN");
    private final JSlider sleepTime = new JSlider(100, 1000);
    private ScheduledFuture<?> runningBoard = null;
    private boolean running = false;

    public AppView(Board board) {
        this.boardView = new BoardView(board);

        board.addObserver(this);

        this.stopButton.addActionListener(e -> {
            if (running) {
                running = false;
                runningBoard.cancel(false);
                stopButton.setText("START");
            } else {
                running = true;
                runningBoard = exec.scheduleAtFixedRate(board, 0, sleepTime.getValue(), TimeUnit.MILLISECONDS);
                stopButton.setText("STOP");
            }
        });

        this.resetButton.addActionListener(e -> board.reset());

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

        this.sleepTime.addChangeListener(e -> {
                    runningBoard.cancel(false);
                    runningBoard = exec.scheduleAtFixedRate(board, sleepTime.getValue(), sleepTime.getValue(), TimeUnit.MILLISECONDS);
                }
        );

        this.sleepTime.setBorder(new TitledBorder("ANIMATION SPEED"));

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
