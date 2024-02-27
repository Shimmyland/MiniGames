public class Main {
    public static void main(String[] args) {

        // start and set up the game
        Game game = new Game();

        do {
            // place a bet
            game.getPlayer().placeABet();

            // distribution of cards
            game.distribution();

            // show hands
            game.showHands();

            // "auto win" for player if he has a BlackJack
            if (!game.getPlayer().hasBlackJack()){
                // action
                game.action();

                // win condition
                game.winner();

            } else {
                System.out.println();
                System.out.println("You have a BlackJack!");
            }
            // prepare for the next game
            game.prepareForNewGame();

            // play again
        } while (game.playAgain());
    }
}
