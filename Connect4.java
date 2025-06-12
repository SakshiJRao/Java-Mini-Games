import java.util.Scanner;

public class Connect4 {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private char[][] board;
    private char currentPlayer;
    private int redScore;
    private int yellowScore;

    public Connect4() {
        board = new char[ROWS][COLUMNS];
        currentPlayer = 'R'; // 'R' for Red and 'Y' for Yellow
        redScore = 0;
        yellowScore = 0;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = '.';
            }
        }
    }

    private void printBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
        System.out.println("0 1 2 3 4 5 6");
    }

    public boolean placePiece(int column) {
        if (column < 0 || column >= COLUMNS || board[0][column] != '.') {
            return false; // Invalid move
        }

        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][column] == '.') {
                board[i][column] = currentPlayer;
                return true;
            }
        }
        return false; // This should never happen if checked correctly
    }

    public boolean checkWin() {
        return checkHorizontal() || checkVertical() || checkDiagonal();
    }

    private boolean checkHorizontal() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                        board[row][col + 1] == currentPlayer &&
                        board[row][col + 2] == currentPlayer &&
                        board[row][col + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical() {
        for (int col = 0; col < COLUMNS; col++) {
            for (int row = 0; row < ROWS - 3; row++) {
                if (board[row][col] == currentPlayer &&
                        board[row + 1][col] == currentPlayer &&
                        board[row + 2][col] == currentPlayer &&
                        board[row + 3][col] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal() {
        // Check for diagonal (bottom-left to top-right)
        for (int row = 3; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                        board[row - 1][col + 1] == currentPlayer &&
                        board[row - 2][col + 2] == currentPlayer &&
                        board[row - 3][col + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        // Check for diagonal (top-left to bottom-right)
        for (int row = 0; row < ROWS - 3; row++) {
            for (int col = 0; col < COLUMNS - 3; col++) {
                if (board[row][col] == currentPlayer &&
                        board[row + 1][col + 1] == currentPlayer &&
                        board[row + 2][col + 2] == currentPlayer &&
                        board[row + 3][col + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        return false;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 'R') ? 'Y' : 'R';
    }

    public void displayScores() {
        System.out.println("Scores:");
        System.out.println("Red (R): " + redScore);
        System.out.println("Yellow (Y): " + yellowScore);
    }

    public void updateScore() {
        if (currentPlayer == 'R') {
            redScore++;
        } else {
            yellowScore++;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do {
            Connect4 game = new Connect4();
            boolean isGameOver = false;

            while (!isGameOver) {
                game.printBoard();
                System.out.print("Player " + game.currentPlayer + ", choose a column (0-6): ");
                int column = scanner.nextInt();

                if (game.placePiece(column)) {
                    if (game.checkWin()) {
                        game.printBoard();
                        System.out.println("Player " + game.currentPlayer + " wins!");
                        game.updateScore();
                        isGameOver = true;
                    } else {
                        game.switchPlayer();
                    }
                } else {
                    System.out.println("Invalid move, try again.");
                }
            }
            game.displayScores();

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");

        } while (playAgain);

        scanner.close();
    }
}
