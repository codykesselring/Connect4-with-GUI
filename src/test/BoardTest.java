public class BoardTest {
    public static void main(String[] args) {
        testInitialization();
        testMakeMove();
        testCheckWin();
        testTieGame();
        testHorizontalWin();
        testVerticalWin();
        testDiagonalWin();
        testInvalidMoves();
    }

    private static void testInitialization() {
        Board board = new Board();
        board.initBoard();

        // add assertions or print statements to check the initial state of the board
        System.out.println("Initial Board:");
        board.printBoard();
        System.out.println();
    }

    private static void testMakeMove() {
        Board board = new Board();
        board.initBoard();

        // test making moves and print the board after each move
        board.makeMove(0, 'X');
        System.out.println("After Player X makes a move:");
        board.printBoard();
        System.out.println();

        board.makeMove(1, 'O');
        System.out.println("After Player O makes a move:");
        board.printBoard();
        System.out.println();
    }

    private static void testCheckWin() {
        Board board = new Board();
        board.initBoard();

        // test a winning scenario and print the board after each move
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(0, 'X'); // player X wins vertically

        System.out.println("After Player X wins vertically:");
        board.printBoard();
        System.out.println();

        // reset the board after the specific test
        board.initBoard();
    }

    private static void testTieGame() {
        Board board = new Board();
        board.initBoard();

        // play a sequence of moves that results in a tie game
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(2, 'X');
        board.makeMove(3, 'O');
        board.makeMove(4, 'X');
        board.makeMove(5, 'O');
        board.makeMove(6, 'X');
        board.makeMove(0, 'O');
        board.makeMove(1, 'X');
        board.makeMove(2, 'O');
        board.makeMove(3, 'X');
        board.makeMove(4, 'O');
        board.makeMove(5, 'X');
        board.makeMove(6, 'O');
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(2, 'X');
        board.makeMove(3, 'O');
        board.makeMove(4, 'X');
        board.makeMove(5, 'O');
        board.makeMove(6, 'X');

        System.out.println("After a tie game:");
        board.printBoard();
        System.out.println();
    }
    private static void testHorizontalWin() {
        Board board = new Board();
        board.initBoard();

        // test a winning scenario horizontally
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(2, 'X');
        board.makeMove(3, 'O');
        board.makeMove(4, 'X');
        board.makeMove(5, 'O');
        board.makeMove(6, 'X'); // player X wins horizontally

        System.out.println("After Player X wins horizontally:");
        board.printBoard();
        System.out.println();

        // reset the board after the specific test
        board.initBoard();
    }

    private static void testVerticalWin() {
        Board board = new Board();
        board.initBoard();

        // test a winning scenario vertically
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(0, 'X'); // player X wins vertically

        System.out.println("After Player X wins vertically:");
        board.printBoard();
        System.out.println();

        // reset the board after the specific test
        board.initBoard();
    }

    private static void testDiagonalWin() {
        Board board = new Board();
        board.initBoard();

        // test a winning scenario diagonally
        board.makeMove(0, 'X');
        board.makeMove(1, 'O');
        board.makeMove(1, 'X');
        board.makeMove(2, 'O');
        board.makeMove(2, 'X');
        board.makeMove(3, 'O');
        board.makeMove(2, 'X');
        board.makeMove(3, 'O');
        board.makeMove(3, 'X');
        board.makeMove(4, 'O');
        board.makeMove(3, 'X'); // player X wins diagonally

        System.out.println("After Player X wins diagonally:");
        board.printBoard();
        System.out.println();

        // reset the board after the specific test
        board.initBoard();
    }

    private static void testInvalidMoves() {
        Board board = new Board();
        board.initBoard();

        // test making invalid moves and print the board after each move
        board.makeMove(7, 'X'); // invalid move
        board.makeMove(-1, 'O'); // invalid move
        board.makeMove(0, 'X');
        board.makeMove(0, 'O');
        board.makeMove(0, 'X');
        board.makeMove(0, 'O');
        board.makeMove(0, 'X');
        board.makeMove(0, 'O'); // column 0 is now full
        board.makeMove(0, 'X'); // invalid move

        System.out.println("After testing invalid moves:");
        board.printBoard();
        System.out.println();
    }
    
    private static void resetBoardAfterAllTests() {
        // reset the board after all tests (you can call this funcion.)
        Board board = new Board();
        board.initBoard();
        System.out.println("Board reset after all tests:");
        board.printBoard();
    }
}

