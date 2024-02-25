public class Main {
    public static void main(String[] args) {

        // start a game
        // set up the game
        Game game = new Game();

        do {

            // distribution of cards
            game.distribution();

            // show hand
            game.showHands();

            // "auto win" for player
            if (!game.getPlayer().autoWin()){
                // action
                game.action();

                // win condition
                game.winner();

                // prepare the next game
                game.prepareForNewGame();
            }

            // play again
        } while (game.playAgain());
    }


}
