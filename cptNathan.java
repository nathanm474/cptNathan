import arc.*;
public class cptNathan{
	public static void main(String[] args){
	Console con = new Console();
	con.println("test");
	 int ROWS = 6;
     int COLS = 7;
     int[][] board = new int[ROWS][COLS]; // Board represented by a 2D array
     int player1Wins = 0, player2Wins = 0;
     String player1Name, player2Name;
     String currentPlayer;
     int currentPlayerNum;

    
  
        }
   
    // Display main menu
    private static void displayMainMenu() {
        con.println("----- Main Menu -----");
        con.println("1. Play Game");
        con.println("2. View Highscores");
        con.println("3. Choose Theme");
        con.println("4. Quit");

        
    }
  // Start a new game
    private static void startGame(Scanner sc) {
        resetBoard();
        getPlayerNames(sc);
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            con.println("Current Scores: " + player1Name + " " + player1Wins + " - " + player2Name + " " + player2Wins);
            con.println(currentPlayer + "'s turn. Enter a column number (1-7): ");
            int col = sc.nextInt() - 1; // 0-based for column
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
                String playAgain = sc.next();
                if (playAgain.equalsIgnoreCase("n")) {
                    gameOver = true;
                    saveHighScores();
                } else {
                    resetBoard();
                }
            } else {
                switchPlayer();
            }
        }
	}



}
