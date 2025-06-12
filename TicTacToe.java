import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    private char player1Symbol;
    private char player2Symbol;
    private static final int TIME_LIMIT = 10; // seconds

    public TicTacToe(char player1Symbol, char player2Symbol) {
        this.board = new char[3][3];
        this.player1Symbol = player1Symbol;
        this.player2Symbol = player2Symbol;
        this.currentPlayer = player1Symbol;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == player1Symbol) ? player2Symbol : player1Symbol;
    }

    public boolean placeMark(int row, int col) {
        if (row >= 0 && col >= 0 && row < 3 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            return true;
        }
        return false;
    }

    public boolean checkForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2]) ||
                    checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]);
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return (c1 != '-' && c1 == c2 && c1 == c3);
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter symbol for Player 1 (X or O): ");
        char player1Symbol = scanner.next().toUpperCase().charAt(0);
        System.out.print("Enter symbol for Player 2 (X or O): ");
        char player2Symbol = scanner.next().toUpperCase().charAt(0);

        while (player1Symbol == player2Symbol || (player1Symbol != 'X' && player1Symbol != 'O') || (player2Symbol != 'X' && player2Symbol != 'O')) {
            System.out.println("Invalid symbols. Players must choose different symbols: X or O.");
            System.out.print("Enter symbol for Player 1 (X or O): ");
            player1Symbol = scanner.next().toUpperCase().charAt(0);
            System.out.print("Enter symbol for Player 2 (X or O): ");
            player2Symbol = scanner.next().toUpperCase().charAt(0);
        }

        TicTacToe game = new TicTacToe(player1Symbol, player2Symbol);
        boolean play = true;

        while (play) {
            System.out.println("Current board layout:");
            game.printBoard();
            System.out.println("Player " + game.currentPlayer + ", enter your move (row and column): ");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up for Player " + game.currentPlayer + "!");
                    System.exit(0); // End game if time runs out
                }
            }, TIME_LIMIT * 1000); // Set time limit

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            timer.cancel(); // Cancel the timer if the player makes a move

            if (game.placeMark(row, col)) {
                if (game.checkForWin()) {
                    System.out.println("Player " + game.currentPlayer + " wins!");
                    play = false;
                } else if (game.isBoardFull()) {
                    System.out.println("The game is a draw!");
                    play = false;
                } else {
                    game.changePlayer();
                }
            } else {
                System.out.println("This move is not valid.");
            }
        }
        game.printBoard();
        scanner.close();
    }
}
