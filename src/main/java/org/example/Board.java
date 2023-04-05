package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {
    public static int activationCounter;
    public static ArrayList<Button> arrayList = new ArrayList<>();
    public static ArrayList<Button> allButtonsOfBoard = new ArrayList<>();
    public static List<Button> arrayListNew = new ArrayList<>();

    public Board() {

        setLayout(new GridLayout(6, 7));
        setVisible(true);
        String[] strings = {"A", "B", "C", "D", "E", "F", "G"};
        for (int i = 6; i >= 1; i--) {

            for (String c : strings) {

                Button button = new Button();
                button.setName("Button" + c + i);
                button.setText(" ");
                button.addActionListener(button);
                add(button);
                allButtonsOfBoard.add(button);

            }
        }

        for (int i = 1; i <= 6; i++) {
            for (String c : strings) {

                Button button = new Button();
                button.setName("Button" + c + i);
                button.setText(" ");
                arrayList.add(button);
            }
        }

    }

    public static void changeEnabledButtons(Boolean b) {
        for (Button button : Board.allButtonsOfBoard){
            button.setEnabled(b);
        }
    }
}

class Board2 extends JPanel {
    public Board2() {
        setLayout(new BorderLayout());
        setVisible(true);

    }
}
