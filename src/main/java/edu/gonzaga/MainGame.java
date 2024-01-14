/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * 
 * Contributors:
 * 
 * Jake Snitily
 * Cody Kesselring
 * Duc Tu Do
 * Alex Sautereau
 * 
 * Copyright: 2023
 */
package edu.gonzaga;

import java.util.Scanner;

import javax.swing.BorderFactory;
//import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/* Main program class for launching your team's program. */
public class MainGame {

    JFrame mainWindowFrame;
    JLabel titleLabel;
    String[] playerNames;

    public static void main(String[] args) {
        System.out.println("Hello Team Game");
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        String[] playerNames = new String[2];

        // Set default values for player symbols
        char symbol1 = 'a';
        char symbol2 = 'b';

        char[] symbols = {symbol1, symbol2};

        Connect4GUI connect4GUI = new Connect4GUI();
        connect4GUI.startGame(symbols, board, playerNames);

        board.initBoard();

        /*
        boolean won = false;
        boolean draw = false;
        int currentPlayer = 0;
        
        while (!won && !draw) {
            boolean work = false;
            
            do {
                board.printBoard();
                System.out.println("Which column would you like to choose?");
                int move = scanner.nextInt();
                if (move >= 0 && move <= 6) {
                    work = board.makeMove(move, symbols[currentPlayer]);
                } else {
                    System.out.println("Invalid move. Please try again.");
                }
            }
            while (!work);
            won = board.checkWin();
            draw = board.checkDraw();
            currentPlayer = (currentPlayer + 1) % 2;
        }
        board.printBoard();
        if (won) {
            System.out.println("Congratulations Player " + symbols[currentPlayer] + ", you're better than Player: " + symbols[(currentPlayer + 1) % 2]);
        } else {
            System.out.println("Better luck next time!");
        }
        scanner.close();
        return;
        */


    }


}
