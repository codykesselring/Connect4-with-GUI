/*
 * this class handles all the board logic so that the two players can play a coherent game; this includes, initializing the board, resetting it, 
 * making a move, checking for a win, annd checking for tie.
 */
package edu.gonzaga;

public class Board {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    
    private char[][] board;

    public void initBoard(){
        board = new char[ROWS][COLUMNS];
        for(int i = 0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                board[i][j] = ' ';
            }
        }
    }
    // reset function
    public void resetBoard(){
        for(int i = 0; i<ROWS; i++){
            for(int j = 0; j<COLUMNS; j++){
                board[i][j] = ' ';
            }
        }
    }

    public void printBoard(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("0 1 2 3 4 5 6");
    }

    public boolean makeMove(int column, char player){
        for(int i = ROWS-1; i>=0; i--){
            if(board[i][column] == ' '){
                board[i][column] = player;
                return true;
            }
        }
        System.out.println("Invalid move from player: " + player + ", the columns: " + column + " is full.\n Please try again: ");
        return false;
    }

    public char[][] getBoard() {
        return board.clone();
    }

    public boolean checkWin() {
        // Check for horizontal 4 in a row
        for (int currRow = 0; currRow < ROWS; currRow++) { // for each row
            for (int currCol = 0; currCol < COLUMNS; currCol++) { // for each column
                if (currCol < COLUMNS - 3) { // if the current column is less than 3 from the end
                    if (board[currRow][currCol] != ' ') { // if the current spot is not empty
                        if (board[currRow][currCol] == board[currRow][currCol + 1] && board[currRow][currCol] == board[currRow][currCol + 2] && board[currRow][currCol] == board[currRow][currCol + 3]) {
                            return true;
                        }
                    }
                }
            }
        }
        // Check for vertical 4 in a row
        for (int currRow = 0; currRow < ROWS-3; currRow++) { // for each row except the last 3
            for (int currCol = 0; currCol < COLUMNS; currCol++) { // for each column
                if (board[currRow][currCol] != ' ') { // if the current spot is not empty
                        if (board[currRow][currCol] == board[currRow+1][currCol] && board[currRow][currCol] == board[currRow+2][currCol] && board[currRow][currCol] == board[currRow+3][currCol]) {                        
                            return true;
                    }
                }
            }
        }
        // Check for diagonal 4 in a row
        for (int currRow = 0; currRow < ROWS - 3; currRow++) {
            for (int currCol = 0; currCol < COLUMNS; currCol++) {
                if (board[currRow][currCol] != ' ') {
                    // Check diagonal \ up
                    if (
                            currCol < COLUMNS - 3 &&
                            board[currRow][currCol] == board[currRow + 1][currCol + 1] &&
                            board[currRow][currCol] == board[currRow + 2][currCol + 2] &&
                            board[currRow][currCol] == board[currRow + 3][currCol + 3]
                    ) {
                        return true;
                    }

                    // Check diagonal / up
                    if (
                            currCol >= 3 &&
                            board[currRow][currCol] == board[currRow + 1][currCol - 1] &&
                            board[currRow][currCol] == board[currRow + 2][currCol - 2] &&
                            board[currRow][currCol] == board[currRow + 3][currCol - 3]
                    ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public boolean checkDraw() {
        int topRow = 0;
        for (int topRowColumns = 0; topRowColumns < COLUMNS; topRowColumns++) {
            if (board[topRow][topRowColumns] == ' ') {
                return false;
            }
        }
        System.out.println("Draw! You're both losers!");
        return true;
    }
}