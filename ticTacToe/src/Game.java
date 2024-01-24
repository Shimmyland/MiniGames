
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    public Game() {
    }

    public void turn(Scanner scanner, ListOfPlayers listOfPlayers, Board board) {
        boolean theGameIsOn = true;
        while (theGameIsOn) {
            for (Player currentPlayer : listOfPlayers.getPlayers()) {
                if (!board.isFull()) {
                    // game turn
                    board.addPlayersMove(scanner, currentPlayer);
                    if (currentPlayer.isWinner(board)) {
                        System.out.println();
                        System.out.println("---------------");
                        board.showBoard();
                        System.out.println(currentPlayer.getName() + " is the Winner! Congrats!");
                        System.out.println("---------------");
                        System.out.println();
                        theGameIsOn = false;
                        break;
                    }
                } else {
                    board.showBoard();
                    System.out.println("Board is full!");
                    theGameIsOn = false;
                    break;
                }
            }
        }
    }

    public void newGame(Scanner scanner, Board board, ListOfPlayers listOfPlayers) {
        if (keepTheBoard(scanner)) {
            board.setBoard();
        } else {
            board.setUpTheBoard(scanner, board);
        }
        if (!keepThePlayers(scanner)) {
            System.out.println();
            listOfPlayers.removeAll();
            int numberOfPlayers = listOfPlayers.setNumberOfPlayers(scanner);
            for (int i = 0; i < numberOfPlayers; i++) {
                Player player = new Player();
                listOfPlayers.add(player.setUpThePlayers(scanner, i, listOfPlayers, board));
            }
        }
        this.turn(scanner, listOfPlayers, board);
    }

    public boolean playAgain(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Do you want to play again? (y/n): ");
                String answer = scanner.next().toLowerCase();
                if ("y".equals(answer)) {
                    return true;
                } else if ("n".equals(answer)) {
                    System.out.println("Thank you for playing!");
                    return false;
                } else { // other character
                    System.out.println("Wrong input.");
                }
            } catch (InputMismatchException e) { // type integer
                System.out.println("Wrong input! Try again.");
                scanner.nextInt();
            }
        }
    }

    public boolean keepTheBoard(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Do you want to keep the board? (y/n): ");
                String answer = scanner.next().toLowerCase();
                if ("y".equals(answer)) {
                    return true;
                } else if ("n".equals(answer)) {
                    return false;
                } else { // other character
                    System.out.println("Wrong input.");
                }
            } catch (InputMismatchException e) { // type integer
                System.out.println("Wrong input! Try again.");
                scanner.nextInt();
            }
        }
    }

    public boolean keepThePlayers(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Do you want to keep the players and their marks? (y/n): ");
                String answer = scanner.next().toLowerCase();
                if ("y".equals(answer)) {
                    return true;
                } else if ("n".equals(answer)) {
                    return false;
                } else { // other character
                    System.out.println("Wrong input.");
                }
            } catch (InputMismatchException e) { // type integer
                System.out.println("Wrong input! Try again.");
                scanner.nextInt();
            }
        }
    }
}
