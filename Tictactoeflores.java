
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tictactoeflores;             


import java.awt.*;
import java.awt.event.*;

public class Tictactoeflores extends Frame implements ActionListener {
    Button[][] buttons = new Button[3][3];
    Button newGameBtn;
    Label status;
    boolean xTurn = true;
    boolean gameOver = false;

    public Tictactoeflores() {
        setTitle("TICTACTOEE");
        setLayout(new BorderLayout());
        setSize(320, 380);
        setResizable(false); 

        Panel grid = new Panel(new GridLayout(3, 3, 5, 5)); // add spacing
        Font font = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setFont(font);
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].addActionListener(this);
                grid.add(buttons[i][j]);
            }
        }

        add(grid, BorderLayout.CENTER);

        Panel bottomPanel = new Panel(new BorderLayout());
        newGameBtn = new Button("New Game");
        newGameBtn.setFont(new Font("Arial", Font.PLAIN, 14));
        newGameBtn.addActionListener(e -> resetGame());
        bottomPanel.add(newGameBtn, BorderLayout.EAST);

        status = new Label("SI X PA");
        status.setFont(new Font("Arial", Font.BOLD, 18));
        status.setAlignment(Label.CENTER);
        bottomPanel.add(status, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameOver) return;

        Button clicked = (Button) e.getSource();
        if (!clicked.getLabel().equals("")) return;

        clicked.setLabel(xTurn ? "X" : "O");
        clicked.setBackground(xTurn ? Color.CYAN : Color.PINK);

        if (checkWin()) {
            status.setText((xTurn ? "X" : "O") + " ANG DAOG!!");
            gameOver = true;
        } else if (isDraw()) {
            status.setText("TABLA!!");
            gameOver = true;
        } else {
            xTurn = !xTurn;
            status.setText((xTurn ? "X's" : "O's") + " nasad");
        }
    }

    private boolean checkWin() {
        String current = xTurn ? "X" : "O";

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (match(current, buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (match(current, buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }

        if (match(current, buttons[0][0], buttons[1][1], buttons[2][2])) return true;
        if (match(current, buttons[0][2], buttons[1][1], buttons[2][0])) return true;

        return false;
    }

    private boolean match(String player, Button b1, Button b2, Button b3) {
        if (b1.getLabel().equals(player) &&
            b2.getLabel().equals(player) &&
            b3.getLabel().equals(player)) {
            b1.setBackground(Color.GREEN);
            b2.setBackground(Color.GREEN);
            b3.setBackground(Color.GREEN);
            return true;
        }
        return false;
    }

    private boolean isDraw() {
        for (Button[] row : buttons)
            for (Button b : row)
                if (b.getLabel().equals("")) return false;
        return true;
    }

    private void resetGame() {
        for (Button[] row : buttons)
            for (Button b : row) {
                b.setLabel("");
                b.setBackground(Color.WHITE);
                b.setEnabled(true);
            }
        xTurn = true;
        gameOver = false;
        status.setText("SI X PA");
    }

    public static void main(String[] args) {
        new Tictactoeflores();
    }
}
