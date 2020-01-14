package main.display;

import main.logic.Board;
import main.logic.Cell;
import main.logic.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoardView extends JPanel {
    private Board board;
    private int cellSize = this.getWidth() / 100;
    ;

    public BoardView(Board board) {
        this.board = board;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() / cellSize + " " + e.getY() / cellSize);
                board.addCell(new Position(e.getX() / cellSize, e.getY() / cellSize), new Cell(true));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int cellSize = this.getWidth() / 100;
        int vertical = 0;
        while (vertical < this.getWidth()) {
            g.drawLine(vertical, 0, vertical, this.getHeight());
            vertical += cellSize;
        }
        int horizontal = 0;
        while (horizontal < this.getHeight()) {
            g.drawLine(0, horizontal, this.getWidth(), horizontal);
            horizontal += cellSize;
        }
        g.setColor(Color.GREEN);
        board.getCells().entrySet().forEach(entry -> {
            if (entry.getValue().isAlive())
                g.fillRect(entry.getKey().x * cellSize, entry.getKey().y * cellSize, cellSize, cellSize);
        });
    }
}
