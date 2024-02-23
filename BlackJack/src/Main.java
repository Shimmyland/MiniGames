import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // start a game
        Game game = new Game();

        do {
            // set up the game
            game.newGame();

            // distribution of cards
            game.distribution();

            // show hand
            game.showHands();

            // action
            game.action();

            // win condition
            game.winner();

            // prepare the next game
            game.prepare();

            // play again
        } while (game.playAgain());
    }


}
