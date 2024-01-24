
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // preparation for the game
        Scanner scanner = new Scanner(System.in);
        ListOfPlayers listOfPlayers = new ListOfPlayers();
        Board board = new Board();
        Game game = new Game();

        System.out.println("Welcome to Tic Tac Toe game!");
        System.out.println();
        int numberOfPlayers = listOfPlayers.setNumberOfPlayers(scanner);
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = new Player();
            listOfPlayers.add(player.setUpThePlayers(scanner, i, listOfPlayers, board));
        }

        board.setUpTheBoard(scanner, board);
        game.turn(scanner, listOfPlayers, board);

        while (game.playAgain(scanner)) {
            game.newGame(scanner, board, listOfPlayers);
        }
    }
}
