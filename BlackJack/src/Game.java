import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    // dependencies
    private Player player;
    private Dealer dealer;
    private Deck deck;

    // constructor
    public Game() {
        this.player = new Player();
        this.dealer = new Dealer();
        this.deck = new Deck();

        deck.shuffle();
    }


    // getters and setters
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }


    // class methods
    // distribution of cards method
    public void distribution() {
        for (int i = 0; i < 2; i++) {
            player.receiveACard(deck.getCard());
            dealer.receiveACard(deck.getCard());
        }
    }


    public void showHands() {
        player.showHand();
        dealer.showHand();
        System.out.println();
    }


    public void action() {
        System.out.println();
        System.out.println("Would you like to:");
        System.out.println("'Hit' (H): Take an additional card from the dealer.");
        System.out.println("'Stand' (S): Decline any additional cards and keep the current hand.");
        if (player.getHand().size() <= 2) {
            System.out.println("'Double Down' (D): Double the initial bet and receive exactly one more card. This option is often available only on the first two cards.");
        }
        if (player.getHand().stream().map(Card::getValue).distinct().count() == 1) {     // find out, if the cards are same by "distinct"
            System.out.println("'Split' (P): If the first two cards have the same value (e.g., two 8s), the player can split them into two separate hands, each with its own bet. " +
                    "Additional cards are dealt to each new hand. Some variations may have restrictions on which pairs can be split.");
        }
        System.out.print("Player's move: ");


        while (true) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next().toUpperCase();
            System.out.println();
            if (Character.isDigit(answer.charAt(0))) {
                System.out.println("Wrong input, try again.");
            } else {
                switch (answer) {
                    case "H":
                        if (!player.getSplitHand().isEmpty()){
                            while (true){
                                System.out.print("Do you want to draw a card for your main Hand?(y/n)");
                                Scanner drawCardForBothHands = new Scanner(System.in);
                                if (Character.isDigit(answer.charAt(0))) {
                                    System.out.println("Wrong input, try again.");
                                } else {
                                    if (drawCardForBothHands.equals("y")) {
                                        player.receiveACard(deck.getCard());
                                        player.showHand();
                                        break;
                                    } else if (drawCardForBothHands.equals("n")) {
                                        player.receiveACardSplit(deck.getCard());
                                        break;
                                    } else {
                                        System.out.println("Wrong input, try again.");
                                    }
                                }
                            }
                        } else {
                            player.receiveACard(deck.getCard());
                            player.showHand();
                        }
                        if (player.showScore() > 21) {
                            return;
                        }
                        action();
                        return;
                    case "S":
                        // nothing
                        return;
                    case "D":
                        player.receiveACard(deck.getCard());
                        // incorporate bidding!
                        break;
                    case "P":
                        // rewrite it
                        List<Card> tmp = player.getHand();
                        player.receiveACardSplit(player.getHand().get(1));
                        player.setHand(new ArrayList<>());
                        player.receiveACard(tmp.get(0));
                        player.receiveACard(deck.getCard());
                        player.receiveACardSplit(deck.getCard());
                        break;
                    default:
                        System.out.println("Wrong answer, try again.");
                        break;
                }
            }
        }
    }


    // Win conditions
    public void winner() {
        if (player.showScore() > 21) {
            System.out.println("Your score is higher then 21, dealer wins.");
            return;
        }

        dealer.setHoleCard(true);
        dealer.showHand();
        dealer.lessThen17(deck);    // Check dealer's hand

        System.out.println();
        if (player.showScore() <= 21 && player.showScore() > dealer.showScore() && dealer.showScore() > 21) {
            System.out.println("Player wins");
        } else {
            System.out.println("Dealer wins");
        }
    }


    // prepare for next game
    public void prepareForNewGame() {
        player.setHand(new ArrayList<>());
        dealer.setHand(new ArrayList<>());
        System.out.println("cards in deck after - " + deck.getCardsInDeck());
        dealer.setHoleCard(false);
        if (deck.getCardsInDeck() < 31) {  // 52 / 100 * 60 -> 31-32 cards (less than 60% available)
            this.deck = new Deck();
            System.out.println("shuffling..");
            deck.shuffle();
        }
    }

    public boolean playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to play again?(y/n)");

        while (true) {
            String answer = scanner.next().toLowerCase();
            if (Character.isDigit(answer.charAt(0))) {
                System.out.println("Wrong input, try again.");
            } else {
                if (answer.equals("y")) {
                    return true;
                } else if (answer.equals("n")) {
                    return false;
                } else {
                    System.out.println("Wrong input, try again.");
                }
            }
        }
    }
}
