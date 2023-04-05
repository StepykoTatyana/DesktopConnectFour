package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class ButtonReset extends JButton implements ActionListener {
    public ButtonReset() {
        this.setFont(new Font("Arial", Font.BOLD, 15));
        setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        Connect4.labelStatus.setText("Game is not started");

        if (action.equals("Start")) {
            Connect4.labelStatus.setText("Game in progress");
            Connect4.buttonReset.setText("Reset");
            Board.changeEnabledButtons(true);
        }

        if (action.equals("Reset")) {
            Connect4.buttonReset.setText("Start");

            for (Button button : Board.allButtonsOfBoard) {
                button.setText(" ");
                button.setContentAreaFilled(true);
                button.setBackground(Color.LIGHT_GRAY);
                button.setFont(new Font("Arial", Font.BOLD, 20));

            }

            for (Button button : Board.arrayList) {
                button.setText(" ");
            }
            for (Button button : Board.arrayListNew) {
                button.setText(" ");
            }
            Button.buttonArrayListForColor = new ArrayList<>();
            Button.flagWinner = false;

            Board.activationCounter = 0;


        }
    }
}


