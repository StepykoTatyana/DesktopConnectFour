package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Button extends JButton implements ActionListener {
    static boolean flagWinner = false;
    static ArrayList<Button> buttonArrayListForColorX = new ArrayList<>();
    static ArrayList<Button> buttonArrayListForColorO = new ArrayList<>();
    static ArrayList<Button> buttonArrayListForColor = new ArrayList<>();


    public Button() {

        this.setFont(new Font("Arial", Font.BOLD, 20));
        this.setFocusPainted(false);
        this.setBackground(Color.LIGHT_GRAY);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!flagWinner) {
            Board.arrayListNew = Board.arrayList.stream().filter(x -> x.getText().equals(" ")).toList();
            List<Button> buttonList = new ArrayList<>();
            for (Button button : Board.arrayListNew) {
                if (buttonList.size() != 0) {
                    if (buttonList.stream().noneMatch(x -> x.getName().matches(String.format("%s?.", button.getName())))) {
                        buttonList.add(button);
                    }
                } else {
                    buttonList.add(button);
                }
            }

            for (Button buttonArray : buttonList) {
                if (buttonArray.getText().equals(" ")) {
                    if (getName().matches(String.format("%s?.", buttonArray.getName()))) {
                        if (Board.activationCounter % 2 == 0) {
                            Button button = Board.allButtonsOfBoard.stream()
                                    .filter(x -> x.getName()
                                            .equals(buttonArray.getName()))
                                    .findFirst().get();
                            button.setText("X");
                            buttonArray.setText("X");
                            buttonArray.setText("X");
                        } else {
                            Button button = Board.allButtonsOfBoard.stream()
                                    .filter(x -> x.getName()
                                            .equals(buttonArray.getName()))
                                    .findFirst().get();
                            button.setText("O");
                            buttonArray.setText("O");
                            buttonArray.setText("O");
                        }
                        Board.activationCounter++;
                        break;
                    }
                }
            }

            CheckWinner();
        }

    }

    public void CheckWinner() {
        if (!flagWinner) {
            HORIZONTAL();
        }
        if (!flagWinner) {
            VERTICAL();
        }
        if (!flagWinner) {
            DIAGONAL();
        }
        if (!flagWinner) {
            DIAGONAL2();
        }

        buttonArrayListForColor = buttonArrayListForColorX.size() == 0 ? buttonArrayListForColorO : buttonArrayListForColorX;
        if (flagWinner) {
            for (Button button1 : buttonArrayListForColor) {
                for (Button button : Board.allButtonsOfBoard) {
                    if (button1.equals(button)) {
                        button.setOpaque(true);
                        button.setBackground(Color.GREEN);
                        break;
                    }
                }


            }
        }
    }

    private void DIAGONAL2() {
        ArrayList<ArrayList<Button>> arrayLists = new ArrayList<>();
        int h = 0;

        ArrayList<Button> arrayListButton = new ArrayList<>();
        for (Button button : Board.allButtonsOfBoard) {
            h++;

            arrayListButton.add(button);
            if (h % 7 == 0) {
                arrayLists.add(arrayListButton);
                arrayListButton = new ArrayList<>();
            }
        }
        for (int i = 0; i < 4; i++) {
            buttonArrayListForColorX = new ArrayList<>();
            buttonArrayListForColorO = new ArrayList<>();
            if (check2(arrayLists, i)) {
                flagWinner = true;
                break;

            }
        }

    }

    private boolean check2(ArrayList<ArrayList<Button>> arrayLists, int i) {
        int counterX = 0;
        int counterO = 0;

        labelArray:
        for (int b = 5; b >= 0; b--) {
            for (int m = 0; m <= 6; m++) {
                if (counterX == 4 || counterO == 4) {
                    //System.out.println("X/O WINS Diagonal");
                    break labelArray;
                }
                if (arrayLists.size() - b - i == m) {
                    if (arrayLists.get(b).get(m).getText().equals("X")) {
                        buttonArrayListForColorX.add(arrayLists.get(b).get(m));
                        counterX++;
                    } else {
                        buttonArrayListForColorX = new ArrayList<>();
                        counterX = 0;
                    }
                    if (arrayLists.get(b).get(m).getText().equals("O")) {
                        buttonArrayListForColorO.add(arrayLists.get(b).get(m));
                        counterO++;
                    } else {
                        buttonArrayListForColorO = new ArrayList<>();
                        counterO = 0;
                    }
                }
            }
        }
        return counterX == 4 || counterO == 4;
    }

    private void DIAGONAL() {
        // DIAGONAL
        ArrayList<ArrayList<Button>> arrayLists = new ArrayList<>();
        int h = 0;
        ArrayList<Button> arrayListButton = new ArrayList<>();
        for (Button button : Board.allButtonsOfBoard) {
            h++;

            arrayListButton.add(button);
            if (h % 7 == 0) {
                arrayLists.add(arrayListButton);
                arrayListButton = new ArrayList<>();
            }
        }
        Integer[] integers = {0, -1, 1, -2, 2};
        for (int ii : integers) {
            buttonArrayListForColorX = new ArrayList<>();
            buttonArrayListForColorO = new ArrayList<>();
            if (check(arrayLists, ii)) {
                flagWinner = true;
                break;
            }
        }
    }

    private void VERTICAL() {
        int counterX = 0;
        int counterO = 0;
        int i = 0;
        // VERTICAL
        loop:
        for (Button button : Board.allButtonsOfBoard) {
            i++;
            counterX = 0;
            counterO = 0;
            if (i % 6 == 0) {
                List<Button> buttonArrayList = Board.allButtonsOfBoard.stream()
                        .filter(x -> x.getName()
                                .matches(String.format("%s?.", button.getName())))
                        .toList();
                buttonArrayListForColorX = new ArrayList<>();
                buttonArrayListForColorO = new ArrayList<>();
                for (Button button1 : buttonArrayList) {
                    if (button1.getText().equals("X")) {
                        buttonArrayListForColorX.add(button1);
                        counterX++;
                    } else {
                        buttonArrayListForColorX = new ArrayList<>();
                        counterX = 0;
                    }
                    if (button1.getText().equals("O")) {
                        buttonArrayListForColorO.add(button1);
                        counterO++;
                    } else {
                        buttonArrayListForColorO = new ArrayList<>();
                        counterO = 0;
                    }
                    if (counterX == 4 || counterO == 4) {
                        flagWinner = true;
                        //System.out.println("X/O WINS VERTICAL");
                        break loop;
                    }
                }

            }
        }
    }

    public boolean check(ArrayList<ArrayList<Button>> arrayLists, int a) {
        int counterX = 0;
        int counterO = 0;
        int i = 0;

        labelArray:
        for (int b = 0; b < 6; b++) {
            for (int m = 0; m <= 6; m++) {
                if (counterX == 4 || counterO == 4) {
                    //System.out.println("X/O WINS Diagonal");
                    break labelArray;
                    // Победил Х
                }
                if (b - m == a) {
                    if (arrayLists.get(b).get(m).getText().equals("X")) {
                        buttonArrayListForColorX.add(arrayLists.get(b).get(m));
                        counterX++;
                    } else {
                        buttonArrayListForColorX = new ArrayList<>();
                        counterX = 0;
                    }
                    if (arrayLists.get(b).get(m).getText().equals("O")) {
                        buttonArrayListForColorO.add(arrayLists.get(b).get(m));
                        counterO++;
                    } else {
                        buttonArrayListForColorO = new ArrayList<>();
                        counterO = 0;
                    }
                }
            }
        }
        return counterX == 4 || counterO == 4;
    }


    public void HORIZONTAL() {
        int counterX = 0;
        int counterO = 0;
        int i = 0;

        ArrayList<ArrayList<Button>> arrayLists = new ArrayList<>();
        int h = 0;
        ArrayList<Button> arrayListButton = new ArrayList<>();
        for (Button button : Board.allButtonsOfBoard) {
            h++;

            arrayListButton.add(button);
            if (h % 7 == 0) {
                arrayLists.add(arrayListButton);
                arrayListButton = new ArrayList<>();
            }
        }

        labelArray:
        for (int b = 0; b < 6; b++) {
            if (!flagWinner) {
                buttonArrayListForColorO = new ArrayList<>();
                buttonArrayListForColorX = new ArrayList<>();
                counterX = 0;
                counterO = 0;
            }

            for (int m = 0; m <= 6; m++) {
                if (arrayLists.get(b).get(m).getText().equals("X")) {
                    buttonArrayListForColorX.add(arrayLists.get(b).get(m));
                    counterX++;

                } else {
                    buttonArrayListForColorX = new ArrayList<>();
                    counterX = 0;
                }
                if (arrayLists.get(b).get(m).getText().equals("O")) {
                    buttonArrayListForColorO.add(arrayLists.get(b).get(m));
                    counterO++;
                } else {
                    buttonArrayListForColorO = new ArrayList<>();
                    counterO = 0;
                }
                if (counterX == 4 || counterO == 4) {
                    // System.out.println("X/O WINS HORIZONTAL");
                    flagWinner = true;
                    break labelArray;
                }
            }

        }
    }
}

