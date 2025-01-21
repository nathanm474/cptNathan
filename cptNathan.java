import arc.*;

public class cptNathan {
    // Game settings
    final int ROWS = 6;
    final int COLS = 7;
    int[][] board = new int[ROWS][COLS]; // Board represented by a 2D array
    int player1Wins = 0, player2Wins = 0;
    String player1Name, player2Name;
    String currentPlayer;
    int currentPlayerNum;
    
    // Console object
    Console con = new Console();

    public static void main(String[] args) {
         cptNathan game = new cptNathan();
        game.startGameLoop();
    }

    // Game loop method
    public void startGameLoop() {
        boolean exit = false;

        while (!exit) {
            displayMainMenu();
            String choice = con.readLine(); // Read user input

            if (choice.equals("1")) {
                startGame();
            } else if (choice.equals("2")) {
                viewHighScores();
            } else if (choice.equals("3")) {
                chooseTheme();
            } else if (choice.equals("4")) {
                con.println("Exiting program...");
                exit = true;
            } else {
                con.println("Invalid choice. Try again.");
            }
        }
    }

    //method to display main menu
    public void displayMainMenu() {
        con.println("\n----- Main Menu -----");
        con.println("1. Play Game");
        con.println("2. View Highscores");
        con.println("3. Choose Theme");
        con.println("4. Quit");
        con.print("Enter your choice: ");
    }

    // method to start a new game
    public void startGame() {
        resetBoard();
        getPlayerNames();
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            con.println("Current Scores: " + player1Name + " " + player1Wins + " - " + player2Name + " " + player2Wins);
            con.println(currentPlayer + "'s turn. Enter a column number (1-7): ");
            String colInput = con.readLine();
            int col = Integer.parseInt(colInput) - 1; // 0-based index for column

            if (col < 0 || col >= COLS || !dropPiece(col)) {
                con.println("Invalid move. Try again.");
                continue;
            }

            if (checkWinner()) {
                printBoard();
                con.println(currentPlayer + " wins!");
                if (currentPlayerNum == 1) player1Wins++;
                else player2Wins++;

                con.print("Do you want to play again? (y/n): ");
                String playAgain = con.readLine();
                if (playAgain.equalsIgnoreCase("n")) {
                    gameOver = true;
                } else {
                    resetBoard();
                }
            } else {
                switchPlayer();
            }
        }
    }

    //method to get player names
    public void getPlayerNames() {
        con.print("Enter Player 1's name: ");
        player1Name = con.readLine();
        con.print("Enter Player 2's name: ");
        player2Name = con.readLine();
        currentPlayer = player1Name;
        currentPlayerNum = 1;
    }

    // method to reset the game board
    public void resetBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = 0; // Fill the board with 0's (empty spots)
            }
        }
    }

    // method to print the game board
    public void printBoard() {
        con.println("-------------");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j] == 0) {
                    con.print("[ ] ");
                } else if (board[i][j] == 1) {
                    con.print("[X] ");
                } else {
                    con.print("[O] ");
                }
            }
            con.println();
        }
        con.println("-------------");
        con.print("  1   2   3   4   5   6   7");
        con.println();
    }

    // method to drop a piece in the chosen column
    public boolean dropPiece(int col) {
        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == 0) {
                board[row][col] = currentPlayerNum;
                return true;
            }
        }
        return false; // Column is full
    }

    // method to switch between players
    public void switchPlayer() {
        if (currentPlayerNum == 1) {
            currentPlayer = player2Name;
            currentPlayerNum = 2;
        } else {
            currentPlayer = player1Name;
            currentPlayerNum = 1;
        }
    }

    //method to check for a winner (horizontal, vertical, and diagonal)
    public boolean checkWinner() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] != 0) {
                    if (checkDirection(row, col, 1, 0) ||   // Horizontal
                        checkDirection(row, col, 0, 1) ||   // Vertical
                        checkDirection(row, col, 1, 1) ||   // Diagonal \
                        checkDirection(row, col, 1, -1)) {  // Diagonal /
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //method to check direction for 4 connected pieces
    public boolean checkDirection(int row, int col, int dRow, int dCol) {
        int player = board[row][col];
        for (int i = 1; i < 4; i++) {
            int newRow = row + dRow * i;
            int newCol = col + dCol * i;
            if (newRow < 0 || newRow >= ROWS || newCol < 0 || newCol >= COLS || board[newRow][newCol] != player) {
                return false;
            }
        }
        return true;
    }

    // method to view high scores (simply displays current players' scores)
    public void viewHighScores() {
        con.println("Highscores: ");
        con.println("Player 1: " + player1Name + " - Wins: " + player1Wins);
        con.println("Player 2: " + player2Name + " - Wins: " + player2Wins);
    }

    //method to choose theme (placeholder)
    public void chooseTheme() {
        con.println("Choose a theme:");
        con.println("1. Christmas");
        con.println("2. Basic");
        con.println("3. Custom");
        String themeChoice = con.readLine();
        if (themeChoice.equals("1")) {
            con.println("Christmas theme selected.");
        } else if (themeChoice.equals("2")) {
            con.println("Basic theme selected.");
        } else if (themeChoice.equals("3")) {
            con.println("Custom theme selected.");
        } else {
            con.println("Invalid choice, defaulting to Basic theme.");
        }
    }
}
