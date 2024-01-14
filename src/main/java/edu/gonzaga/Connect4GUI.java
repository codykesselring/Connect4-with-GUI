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
 * 
 * Description: this is our GUI class that takes logic fro mthe main and board class to have a visual representation of what's going on behind the scenes.
 * This has many gamestate changes, so there are many action listeners that check for certain criteria throughout the game, including checking for a win after
 * every turn, making sure the right colored piece is placed, inputting the player names, a title screen, an end screen, and a draw screen. 
 */
package edu.gonzaga;

import java.util.Scanner;

import javax.swing.BorderFactory;
//import javax.swing.Box;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

/* Main program class for launching your team's program. */
public class Connect4GUI{
    
    private static Board board;
    private JFrame mainWindowFrame;
    private static JPanel gamePanel;
    private JLabel currentPlayerLabel;  //displaying the current player
    JButton buttonDarkMode = new JButton("D");
    private JLabel titleLabel = new JLabel("Connect 4");
    private boolean darkModeActive = false;
    
     public void startGame(char[] symbols, Board board, String[] playerNames) {
        // Create a panel for player name input
        JPanel playerNamePanel = new JPanel(new GridLayout(3, 2));

        ImageIcon logoIcon = new ImageIcon("src/images/titlepic.png"); // Replace with the actual path to your logo image
        JLabel logoLabel = new JLabel(logoIcon);
        playerNamePanel.add(logoLabel);
        playerNamePanel.add(new JLabel("Connect 4 pieces in a row to win. Don't let other player connect 4 before you."));
        
        JTextField playerNameField1 = new JTextField();
        JTextField playerNameField2 = new JTextField();



        playerNamePanel.add(new JLabel("Enter name for Player 1:"));
        playerNamePanel.add(playerNameField1);
        playerNamePanel.add(new JLabel("Enter name for Player 2:"));
        playerNamePanel.add(playerNameField2);

        // Show the dialog and wait for user input
        int result = JOptionPane.showConfirmDialog(mainWindowFrame, playerNamePanel, "Enter Player Names", JOptionPane.OK_CANCEL_OPTION);


        if (result == JOptionPane.OK_OPTION) {
            // User clicked OK, retrieve player names from the text fields
            String playerName1 = playerNameField1.getText();
            String playerName2 = playerNameField2.getText();
            if(playerNameField1.getText().isEmpty()){
                playerName1 = "player1";
            }
            if(playerNameField2.getText().isEmpty()){
                playerName2 = "player2";
            }
            playerNames[0] = playerName1;
            playerNames[1] = playerName2;

            // Continue with the game setup
            setupGUI(symbols, board, playerNames);
        } else {
            // User clicked Cancel or closed the dialog, handle accordingly
            System.out.println("User canceled the input.");
        }
    }
    void setupGUI(char[] symbols, Board board, String[] playerNames) {
        this.board = board;
        mainWindowFrame = new JFrame("Connect 4 App");
        mainWindowFrame.setSize(450, 600);
        mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindowFrame.setResizable(false);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = createTopPanel();
        gamePanel = createGamePanel();
        JButton[] buttons = createButtonPanel();

        // Add the top panel to the main panel
        mainPanel.add(topPanel, BorderLayout.PAGE_START);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        // Add the current player label to the top panel
        currentPlayerLabel = new JLabel("Current Player: " + playerNames[0]);
        topPanel.add(currentPlayerLabel);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        setupButtonActionListeners(buttons, symbols, playerNames, currentPlayerLabel);
        setUpDarkModeActionListeners(buttonDarkMode, topPanel, gamePanel, currentPlayerLabel, buttonPanel);

      

        for (int i = 0; i < buttons.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);

        mainWindowFrame.setContentPane(mainPanel);
        mainWindowFrame.setLocationRelativeTo(null);
        mainWindowFrame.setVisible(true);

    }

    private void setupButtonActionListeners(JButton[] buttons, char[] symbols, String[] playerNames, JLabel currentPlayerLabel) {
        for (int i = 0; i < buttons.length; i++) {
            int column = i;
            buttons[i].addActionListener(e -> handleButtonClick(column, symbols, playerNames, currentPlayerLabel));
        }
    }
    
    private void setUpDarkModeActionListeners(JButton button, JPanel p, JPanel d, JLabel player, JPanel f) {
        button.addActionListener(e -> handleButtonClick2(buttonDarkMode, p, d, player, f));
    }

    private void handleButtonClick2(JButton buttonDarkMode2, JPanel top, JPanel grid, JLabel playerLabel, JPanel bottom) {
        
        if(!darkModeActive){
            //Setup dark mode
            buttonDarkMode2.setBackground(Color.WHITE);
            top.setBackground(Color.BLACK);
            grid.setBackground(Color.BLACK);
            bottom.setBackground(Color.BLACK);
            top.getComponent(0).setForeground(Color.WHITE);
            // Set Connect 4 text to white

            playerLabel.setForeground(Color.WHITE);
            darkModeActive = true;
        } else {
            buttonDarkMode2.setBackground(Color.GRAY);
            top.setBackground(Color.WHITE);
            grid.setBackground(Color.LIGHT_GRAY);
            bottom.setBackground(Color.WHITE);
            top.getComponent(0).setForeground(Color.BLACK);
            playerLabel.setForeground(Color.BLACK);
            darkModeActive = false;
        }
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Connect 4");
        buttonDarkMode.setPreferredSize(new Dimension(40, 40));
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 40));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        topPanel.add(titleLabel);
        topPanel.add(buttonDarkMode);
        return topPanel;
    }

    private static JButton[] createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton[] buttons = new JButton[7];
        for (int i = 0; i < 7; i++) {
            JButton button = new JButton("" + (i));
            button.setPreferredSize(new Dimension(44, 30));
            buttonPanel.add(button);
            buttons[i] = button;
        }
        return buttons;
    }

    private static JPanel createGamePanel() {
        JPanel gamePanel = new JPanel(new GridLayout(6, 7, 5, 5));
        // Use the clear.jpg image
        ImageIcon placeholderIcon = createClearImage();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JLabel label = new JLabel(placeholderIcon);
                gamePanel.add(label);
            }
        }
        return gamePanel;
    }


    private  void resetGame() {
         board.resetBoard();
         clearGamePanel();
    }


    private void handleButtonClick(int column, char[] players, String[] playerNames, JLabel currentPlayerLabel) {
        // get the current player information before making a move
        char currentPlayerSymbol = players[0];
        int currentPlayerIndex = (currentPlayerSymbol == 'a') ? 0 : 1;
    
        // Make a move on the board
        boolean work = board.makeMove(column, currentPlayerSymbol);
        // If the move was invalid, display an error in the GUI
        if (!work) {
            JOptionPane.showMessageDialog(mainWindowFrame, "Invalid move from player: " + playerNames[currentPlayerIndex] + ", the column: " + column + " is full.\n Please try again: ", "Invalid Move", JOptionPane.ERROR_MESSAGE);

            return;
        }
        // Update the current player label after making the move
        SwingUtilities.invokeLater(() -> currentPlayerLabel.setText("Current Player: " + playerNames[(currentPlayerIndex + 1) % 2]));
    
        // switch to the next player
        players[0] = (currentPlayerSymbol == 'a') ? 'b' : 'a';
    
        // update the GUI to reflect the move
        SwingUtilities.invokeLater(Connect4GUI::updateGamePanel);
    
        // Check for a win after updating the GUI
        if (board.checkWin()) {
            int winnerIndex = (currentPlayerSymbol == 'a') ? 0 : 1;
            // DISPLAY WINNER Message with the picture of the winning token
            JOptionPane.showMessageDialog(mainWindowFrame, "Congratulations " + playerNames[winnerIndex] + ", you're better than " + playerNames[(winnerIndex + 1) % 2] + "!", "Winner!", JOptionPane.INFORMATION_MESSAGE, (currentPlayerSymbol == 'a') ? createYellowChipImage() : createRedChipImage());

            // After a win, ask the user if they want to play again
            int result = JOptionPane.showConfirmDialog(mainWindowFrame, "Would you like to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetGame();
                currentPlayerLabel.setText("Current Player: " + playerNames[0]);
            } else {
                // If the user doesn't want to play again, exit the program
                System.exit(0);
            }

        }
        // Check for a draw after updating the GUI
        if (board.checkDraw()) {
            JOptionPane.showMessageDialog(mainWindowFrame, "Draw!");
            // After a draw, ask the user if they want to play again
            int result = JOptionPane.showConfirmDialog(mainWindowFrame, "Would you like to play again?", "Play Again?", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                resetGame();
                // Show a goodluck message to the next round
                JOptionPane.showMessageDialog(mainWindowFrame, "Good luck!");
                //wait for the user to click ok
                currentPlayerLabel.setText("Current Player: " + playerNames[0]);
            } else {
                // If the user doesn't want to play again, exit the program
                System.exit(0);
            }
        }
    }
    
    

    private static void updateGamePanel() {
        char[][] gameBoard = board.getBoard();
        JPanel gamePanel = getGamePanel();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JLabel label = (JLabel) gamePanel.getComponent(i * 7 + j);
                if (gameBoard[i][j] == 'a') {
                    label.setIcon(createYellowChipImage());
                } else if (gameBoard[i][j] == 'b') {
                    label.setIcon(createRedChipImage());
                }
            }
        }
    }

    private static void clearGamePanel() {
         char [][] gameBoard = board.getBoard();
         JPanel gamePanel = getGamePanel();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                JLabel label = (JLabel) gamePanel.getComponent(i * 7 + j);
                if (gameBoard[i][j] == 'a') {
                    label.setIcon(createYellowChipImage());
                } else if (gameBoard[i][j] == 'b') {
                    label.setIcon(createRedChipImage());
                } else if (gameBoard[i][j] == ' ') {
                    label.setIcon(createClearImage());
                }
            }
        }
    }

    private static ImageIcon createYellowChipImage() {
        // Provide the actual path to the yellow chip image file
        String imagePath = "src/images/yellow.png";

        // Load the image from the file
        ImageIcon yellowChipIcon = new ImageIcon(imagePath);

        // Resize the image to fit the 50x50 dimensions
        Image image = yellowChipIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        yellowChipIcon = new ImageIcon(image);

        return yellowChipIcon;
    }

    private static ImageIcon createRedChipImage() {
        // Provide the actual path to the red chip image file
        String imagePath = "src/images/red.png";

        // Load the image from the file
        ImageIcon redChipIcon = new ImageIcon(imagePath);

        // Resize the image to fit the 50x50 dimensions
        Image image = redChipIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        redChipIcon = new ImageIcon(image);

        return redChipIcon;
    }

    private static ImageIcon createClearImage() {
        // Provide the actual path to the red chip image file
        String imagePath = "src/images/clear.jpg";

        // Load the image from the file
        ImageIcon clearIcon = new ImageIcon(imagePath);

        // Resize the image to fit the 50x50 dimensions
        Image image = clearIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        clearIcon = new ImageIcon(image);

        return clearIcon;
    }

    private static JPanel getGamePanel() {
        return gamePanel;
    }
}

