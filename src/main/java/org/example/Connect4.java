package org.example;

import javax.swing.*;
import java.awt.*;

public class Connect4 extends JFrame {
    static LabelStatus labelStatus = new LabelStatus();
    static ButtonReset buttonReset = new ButtonReset();

    public Connect4() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Connect Four");
        setResizable(false);
        setSize(450, 450);
        setLocationRelativeTo(null);
        Board board = new Board();
        add(board, BorderLayout.CENTER);

        Board2 board2 = new Board2();
        add(board2, BorderLayout.SOUTH);

        ButtonReset buttonReset = new ButtonReset();
        buttonReset.setName("ButtonReset");
        buttonReset.setText("Reset");
        buttonReset.addActionListener(buttonReset);
        setVisible(true);
        board2.add(buttonReset, BorderLayout.EAST);


    }

    public static void main(String[] args) {
        new Connect4();
    }
}

class LabelStatus extends JLabel {
    public LabelStatus() {
    }
}