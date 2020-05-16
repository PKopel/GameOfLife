package main.display;

import main.logic.Board;
import main.logic.Properties;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class Starter extends JFrame {
    private final JTextField rules = new JTextField();
    private final JButton create = new JButton("CREATE");
    private final JButton exit = new JButton("EXIT");

    public Starter() {
        this.create.addActionListener(e -> {
            if (rules.getText().isEmpty()) return;
            Properties.getProperties().parseRules(rules.getText());
            AppView appView = new AppView(new Board());
            appView.setTitle("Game Of Life");
            appView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            appView.setSize(600, 600);
            appView.setVisible(true);
            dispose();
        });

        this.exit.addActionListener(e -> dispose());

        this.rules.setBorder(new TitledBorder("RULES"));
        this.add(BorderLayout.NORTH, rules);
        this.add(BorderLayout.CENTER, create);
    }
}
