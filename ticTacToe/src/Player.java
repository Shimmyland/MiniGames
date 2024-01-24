
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    // Instance variables of this class
    private String name;
    private Character mark;

    // constructor
    public Player() {
    }
    // ---------------


    // getters
    public String getName() {
        return name;
    }

    public Character getMark() {
        return mark;
    }
    // ---------------

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setMark(Character mark) {
        this.mark = mark;
    }
    // ---------------


    // setting up players
    public Player setUpThePlayers(Scanner scanner, int i, ListOfPlayers listOfPlayers, Board board) {
        this.setName(setPlayersName(scanner, i, listOfPlayers));
        this.setMark(setPlayersMark(scanner, this, listOfPlayers, board));

        System.out.println();
        return this;
    }

    public String setPlayersName(Scanner scanner, int i, ListOfPlayers listOfPlayers) {
        String tmpName;
        String tmpOrder;
        if (i == 0) {
            tmpOrder = "1st";
        } else if (i == 1) {
            tmpOrder = "2nd";
        } else if (i == 2) {
            tmpOrder = "3rd";
        } else {
            tmpOrder = "4th";
        }

        // setting up names
        while (true) {
            try {
                System.out.print("Enter a name of the " + tmpOrder + " player: ");
                tmpName = scanner.next().toLowerCase();

                // validation
                if (isTheNameTaken(tmpName, listOfPlayers)) {
                    System.out.println("Name is already taken. Try again.");
                    continue;  // restart
                }
                if (isTheNameDigit(tmpName)) {
                    System.out.println("Name contain a number. Try again.");
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again.");
                scanner.next();
            }
        }

        tmpName = tmpName.substring(0, 1).toUpperCase() + tmpName.substring(1);
        return tmpName;
    }

    public Character setPlayersMark(Scanner scanner, Player player, ListOfPlayers listOfPlayers, Board board) {
        // print list of available marks = MAKE A METHOD IN BOARD
        System.out.print("Choose from one of those marks: ");
        int count = 0;
        for (char c : board.getListOfMarks()) {
            System.out.print(c);
            count++;
            if (count < board.getListOfMarks().length) {
                System.out.print(", ");
            }
        }
        System.out.println();

        // setting up player's marks
        char tmpChar;
        while (true) {
            try {
                System.out.print("Enter a " + player.getName() + "'s mark (one letter): ");
                tmpChar = scanner.next().toUpperCase().charAt(0);     // help from ChatGPT to use charAt(0)

                // validation
                if (isTheMarkOutOfTheList(tmpChar, board.getListOfMarks())) {
                    System.out.println("Mark is out of the list. Try again.");
                    continue;
                }
                if (isTheMarkTaken(tmpChar, listOfPlayers)) {
                    System.out.println("Mark is already taken. Try again.");
                    continue;
                }

                player.setMark(tmpChar);
                break;

            } catch (InputMismatchException e) {
                System.out.println("Wrong input, try again.");
                scanner.next();
            }
        }

        return tmpChar;
    }
    // -----------------


    // validation statements
    private boolean isTheNameTaken(String tmpName, ListOfPlayers listOfPlayers) {
        for (Player player : listOfPlayers.getPlayers()) {
            if (player.getName().equalsIgnoreCase(tmpName)) { // help from chatGPT
                return true;
            }
        }
        return false;
    }

    private boolean isTheNameDigit(String tmpName) {
        // if (player.getName().matches(".*\\d+.*") -> chatGPT
        // if player's name contains a number
        for (char c : tmpName.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTheMarkTaken(Character mark, ListOfPlayers listOfPlayers) {
        for (Player player : listOfPlayers.getPlayers()) {
            if (player.getMark() != null && player.getMark().equals(mark)) {
                return true;
            }
        }
        return false;
    }

    private boolean isTheMarkOutOfTheList(Character mark, char[] listOfMarks) {
        for (char c : listOfMarks) {
            if (mark == c) {
                return false;
            }
        }
        return true;
    }
    // -----------------


    // winner conditions
    public boolean isWinner(Board board) {
        if (checkRows(board) || checkColumns(board) ||
                checkDiagonal1(board) || checkDiagonal2(board) || checkDiagonal3(board) || checkDiagonal4(board)) {
            return true;
        }
        return false;
    }

    private boolean checkRows(Board board) {                // checks for a same marks in a line
        for (int i = 0; i < board.getRows(); i++) {
            int count = 0;
            for (int j = 0; j < board.getColumns(); j++) {
                if (board.getDimensions()[i][j] == this.mark) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumns(Board board) {             // checks for a same marks in a vertical line
        for (int j = 0; j < board.getColumns(); j++) {
            int count = 0;
            for (int i = 0; i < board.getRows(); i++) {
                if (board.getDimensions()[i][j] == this.mark) {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal1(Board board) {           // checks from top left to the bottom right
        int count = 0;
        for (int i = 0; i < board.getRows(); i++) {
            if (board.getDimensions()[i][i] == this.mark) {
                count++;
            } else {
                count = 0;
            }

            if (count == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonal2(Board board) {           // checks from bottom right to the top left
        int count = 0;
        int column = board.getColumns() - 1;
        for (int i = board.getRows() - 1; i >= 0; i--) {
            while (i > 0 && column > 0) {
                if (board.getDimensions()[i][column] == this.mark) {
                    count++;
                } else {
                    count = 0;
                }

                if (count == 3) {
                    return true;
                }
                i--;
                column--;
            }
        }
        return false;
    }

    private boolean checkDiagonal3(Board board) {           // checks from top right connor to bottom left
        int count = 0;
        int column = board.getColumns() - 1;
        for (int i = 0; i < board.getRows(); i++) {
            if (board.getDimensions()[i][column] == this.mark) {
                count++;
            } else {
                count = 0;
            }

            if (count == 3) {
                return true;
            }

            if (column == 0) {
                return false;
            }
            column--;
        }
        return false;
    }

    private boolean checkDiagonal4(Board board) {           // checks from bottom left connor to top right
        int count = 0;
        int column = 0;
        for (int i = board.getRows() - 1; i >= 0; i--) {
            while (i > 0 && column < board.getColumns()) {
                if (board.getDimensions()[i][column] == this.mark) {
                    count++;
                } else {
                    count = 0;
                }

                if (count == 3) {
                    return true;
                }
                i--;
                column++;
            }
        }
        return false;
    }
    // -----------------
}
