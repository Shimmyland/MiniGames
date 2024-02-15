import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    // dependencies
    private Player player;
    private Dealer dealer;
    private Deck deck;

    public Game() {
        this.player = new Player();
        this.dealer = new Dealer();
        this.deck = new Deck();

        deck.shuffle();
    }

    // distribution of cards method
    public void distribution() {
        for (int i = 0; i < 2; i++) {
            player.receiveACard(deck.getCard());
            dealer.receiveACard(deck.getCard());
        }
    }


    public void showHands() {
        player.showHand();
        System.out.println("Player's score: " + player.showScore());
        if (player.autoWin()){      // solve it! - have to break game loop
            return;
        }

        dealer.showHand(false);
        System.out.println("Dealer's score: " + dealer.showScore());

        System.out.println();
    }


    public void action() {
        System.out.println("'Hit' (H): Take an additional card from the dealer.");
        System.out.println("'Stand' (S): Decline any additional cards and keep the current hand.");
        if (player.getHand().size() <= 2) {
            System.out.println("'Double Down' (D): Double the initial bet and receive exactly one more card. This option is often available only on the first two cards.");
        }
        if (player.getHand().stream().map(Card::getValue).distinct().count() == 1) {     // find out, if the cards are same by "distinct"
            System.out.println("'Split' (P): If the first two cards have the same value (e.g., two 8s), the player can split them into two separate hands, each with its own bet. " +
                    "Additional cards are dealt to each new hand. Some variations may have restrictions on which pairs can be split.");
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next().toUpperCase();
            if (Character.isDigit(answer.charAt(0))) {
                System.out.println("Wrong input, try again.");
            } else {
                if (answer.equals("H")) {
                    player.receiveACard(deck.getCard());
                    player.showHand();
                    System.out.println("Player's score: " + player.showScore());
                    if (player.showScore() > 21){
                        return;
                    }
                    action();
                    return;         // return or break?
                } else if (answer.equals("S")) {
                    // nothing
                    break;
                } else if (answer.equals("D")) {
                    player.receiveACard(deck.getCard());
                    // incorporate bidding!
                    break;
                } else if (answer.equals("P")) {
                    // ?!
                    break;
                } else {
                    System.out.println("Wrong answer, try again.");
                }
            }
        }
    }


    // Win conditions
    public void winner() {
        dealer.showHand(true);
        dealer.lessThen17(deck);    // Check dealer's hand
        if (player.showScore() <= 21 && player.showScore() > dealer.showScore()) {
            System.out.println("Player wins");
        } else if (player.showScore() == dealer.showScore()) {
            System.out.println("Dealer wins");
        } else {
            System.out.println("Dealer wins");
        }
    }


    // prepare next game
    public void prepare(){
        // players and dealers cards = remove their hands -> make them empty
        // -> deck.discardPile, deck.cardsInDeck
        // shuffle
    }
}
