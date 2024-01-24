import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Character player = 'X';
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        System.out.println("Welcome to Tic Tac Toe game!");

        while (true) {
            if (isFull(board)) {
                boardSetUp(board);
                System.out.println("We have a draw! Board is full.");
                break;
            }
            boardSetUp(board);
            board = playerMove(board, player, scanner);
            if (isWinner(board, player)) {
                System.out.println("---------------------");
                System.out.println(player + " is the winner!");
                boardSetUp(board);
                System.out.println("---------------------");
                break;
            }
            player = switchPlayer(player);
        }
        playAgain(scanner);
    }

    public static void boardSetUp(char[][] board) {
        System.out.println("Board: ");
        System.out.println("  0 1 2");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (col != 0) {
                    System.out.print("|");
                } else {
                    System.out.print(row + " ");
                }
                System.out.print(board[row][col]);
            }
            if (row != board.length - 1) {
                System.out.println("\n  -+-+-");
            }
        }
        System.out.println();
    }

    public static Character switchPlayer(Character player) { // this.player = (this.player == 'X') ? 'O' : 'X';
        if (player == 'X') {
            player = 'O';
        } else {
            player = 'X';
        }
        return player;
    }

    public static char[][] playerMove(char[][] board, Character player, Scanner scanner) {
        boolean inputIsLegit = false;
        int rows = 0, column = 0;

        while (!inputIsLegit) {
            try {
                System.out.println("Player " + player + "'s move. Enter your move (row, column): ");
                rows = scanner.nextInt();
                column = scanner.nextInt();
                inputIsLegit = isValid(rows, column, board);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again please.");
                scanner.next();
            }
        }
        board[rows][column] = player;

        return board;
    }

    public static boolean isValid(int rows, int column, char[][] board) {
        if (rows >= 0 && rows < 3) {
            if (column >= 0 && column < 3) {
                if (board[rows][column] == ' ') {
                    return true;
                }
            }
        }
        System.out.println("Invalid move. Try again.");
        return false;
    }

    public static boolean isWinner(char[][] board, Character player) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return false;
    }

    public static boolean isFull(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void playAgain(Scanner scanner) {
        try {
            while (true) {
                System.out.print("Do you want to play again? (y/n): ");
                String answer = scanner.next().toLowerCase();

                if ("y".equals(answer)) {
                    main(null); // reset the game
                    break;
                } else if ("n".equals(answer)) {
                    System.out.println("Thank you for playing!");
                    break;
                } else { // other character
                    System.out.println("Wrong input.");
                }
            }
        } catch (InputMismatchException e) { // type integer
            System.out.println("Wrong input! Try again.");
            scanner.nextInt();
        }
    }

}
