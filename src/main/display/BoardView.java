package main.display;

import main.logic.Board;
import main.logic.Cell;
import main.logic.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class BoardView extends JPanel {
    private Board board;
    private int cellSize = 20;
    private int shiftX = 0;
    private int shiftY = 0;
    private int previousMouseX = 0;
    private int previousMouseY = 0;

    public BoardView(Board board) {
        this.board = board;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Position position = new Position((e.getX() - shiftX) / cellSize, (e.getY() - shiftY) / cellSize);
                if (board.getCells().containsKey(position)) board.removeCell(position);
                else board.addCell(position, new Cell(true));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                previousMouseX = e.getX();
                previousMouseY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                shiftX += e.getX() - previousMouseX;
                shiftY += e.getY() - previousMouseY;
                repaint();
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                cellSize += e.getWheelRotation();
                if (cellSize < 10) cellSize = 10;
                else if (cellSize > 40) cellSize = 40;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        int vertical = shiftX % cellSize;
        while (vertical < this.getWidth()) {
            g.drawLine(vertical, 0, vertical, this.getHeight());
            vertical += cellSize;
        }
        int horizontal = shiftY % cellSize;
        while (horizontal < this.getHeight()) {
            g.drawLine(0, horizontal, this.getWidth(), horizontal);
            horizontal += cellSize;
        }
        board.getCells().entrySet().forEach(entry -> {
            if (entry.getValue().isAlive())
                g.fillRect(entry.getKey().x * cellSize + shiftX, entry.getKey().y * cellSize + shiftY, cellSize, cellSize);
        });
    }
}
