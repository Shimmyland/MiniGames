
import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    // Instance variables of this class
    private char[][] dimensions;
    private int rows;
    private int columns;
    private char[] listOfMarks;
    // ---------------


    // constructor
    public Board() {
        this.listOfMarks = new char[]{'X', 'O', 'Y', 'Z'};
    }
    // ---------------


    // getters
    public char[][] getDimensions() {
        return dimensions;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public char[] getListOfMarks() {
        return listOfMarks;
    }
    // ---------------

    // setters
    public void setBoard() {
        this.dimensions = new char[this.rows][this.columns];
        for (int i = 0; i < dimensions.length; i++) {          // rows
            for (int j = 0; j < dimensions[i].length; j++) {   // columns
                dimensions[i][j] = ' ';
            }
        }
    }

    public void setRows(Scanner scanner) {
        int rows;
        while (true) {
            try {
                System.out.println("How many rows should the board have? (The minimum is three):");
                rows = scanner.nextInt();
                if (rows >= 3) {
                    break;
                } else {
                    System.out.println("Wrong input. Try it again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again.");
                scanner.next();
            }
        }
        this.rows = rows;
    }

    public void setColumns(Scanner scanner) {
        int columns;
        while (true) {
            try {
                System.out.println("How many columns should the board have? (The minimum is three):");
                columns = scanner.nextInt();
                if (columns >= 3) {
                    break;
                } else {
                    System.out.println("Wrong input. Try it again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again.");
                scanner.next();
            }
        }
        this.columns = columns;
    }
    // ---------------


    // setting up the board
    public void setUpTheBoard(Scanner scanner, Board board) {
        board.setRows(scanner);
        board.setColumns(scanner);
        board.setBoard();
    }

    public void showBoard() {
        System.out.println("Board: ");
        System.out.print(" ");                                      // space for listing numbers above the empty chars
        for (int i = 0; i < dimensions[0].length; i++) {                 // listing numbers of columns
            System.out.print(" " + i);
        }
        System.out.println();

        for (int row = 0; row < dimensions.length; row++) {             // printing board itself starting with number of row
            for (int col = 0; col < dimensions[row].length; col++) {
                if (col != 0) {
                    System.out.print("|");
                } else {
                    System.out.print(row + " ");
                }
                System.out.print(dimensions[row][col]);
            }
            if (row != dimensions.length - 1) {                                 // lines between the rows
                System.out.print("\n  ");
                for (int i = 0; i < (dimensions[0].length * 2) - 1; i++) {
                    if (i % 2 == 0) {
                        System.out.print("-");
                    } else {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public boolean isFull() {                // tests board if is full of marks or not
        for (int row = 0; row < dimensions.length; row++) {
            for (int col = 0; col < dimensions[row].length; col++) {
                if (dimensions[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void addPlayersMove(Scanner scanner, Player player) {
        while (true) {
            try {
                showBoard();
                System.out.println();
                System.out.println(player.getName() + ", enter your move (number of row, then number of column): ");
                int row = scanner.nextInt();
                int column = scanner.nextInt();
                if (isValid(row, column)) {
                    dimensions[row][column] = player.getMark();
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Try again please.");
                scanner.next();     // remove the in incorrect inputs
            }
        }
        System.out.println();
    }

    private boolean isValid(int row, int column) {           // tests players' input
        if (row >= 0 && row < dimensions.length && column >= 0 && column < dimensions[0].length) {
            if (dimensions[row][column] == ' ') {
                return true;
            } else {
                System.out.println("Wrong move, the field is already taken. Try it again.");
            }
        } else {
            System.out.println("Wrong move outside of the field of the board. Try it again.");
        }
        return false;
    }
    // ---------------

}
