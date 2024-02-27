import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    // dependencies
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private BasicStrategy basicStrategy;

    // constructor
    public Game() {
        this.player = new Player();
        this.dealer = new Dealer();
        this.deck = new Deck();
        this.basicStrategy = new BasicStrategy();

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

    public BasicStrategy getBasicStrategy() {
        return basicStrategy;
    }

    public void setBasicStrategy(BasicStrategy basicStrategy) {
        this.basicStrategy = basicStrategy;
    }


    // class methods
    public void distribution() {
        for (int i = 0; i < 2; i++) {
            player.receiveACard(deck.getCard());
            dealer.receiveACard(deck.getCard());
        }
    }

    public void showHands() {
        player.showHand();
        dealer.showHand();
    }

    public void action() {
        List<String> options = new ArrayList<>();
        options.add("H");
        options.add("S");
        System.out.println();
        System.out.println("Action:");
        System.out.println("'Hit' (H): Take an additional card from the dealer.");
        System.out.println("'Stand' (S): Decline any additional cards and keep the current hand.");
        if (player.getHand().size() == 2 || player.getSplitHand().size() == 2) {    // available only on the first two cards
            System.out.println("'Double Down' (D): Double the initial bet and receive exactly one more card.");
            options.add("D");
        }
        if (player.getHand().stream().map(Card::getValue).distinct().count() == 1 || player.getSplitHand().stream().map(Card::getValue).distinct().count() == 1) {     // find out, if the cards are same by "distinct"
            System.out.println("'Split' (P): If the first two cards have the same value (e.g., two 8s), the player can split them into two separate hands, each with its own bet. " +
                    "Additional cards are dealt to each new hand. Some variations may have restrictions on which pairs can be split.");
            options.add("P");
        }

        basicStrategy.getAdvice(player, dealer);

        while (true) {
            System.out.print("Player's move: ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next().toUpperCase();
            if (!options.contains(answer)) {
                System.out.println("Wrong input or the chosen option is not available, please try again.");
            } else {
                switch (answer) {
                    case "H":
                        if (!player.getSplitHand().isEmpty()) {
                            while (true) {
                                System.out.print("Draw a card: Hand (a), split Hand (b), both of them (c).");
                                String drawCardForBothHands = scanner.next();
                                switch (drawCardForBothHands) {
                                    case "a":
                                        player.receiveACard(deck.getCard());
                                        break;
                                    case "b":
                                        player.receiveACardSplit(deck.getCard());
                                        break;
                                    case "c":
                                        player.receiveACard(deck.getCard());
                                        player.receiveACardSplit(deck.getCard());
                                        break;
                                    default:
                                        System.out.println("Wrong input, try again.");
                                        break;
                                }
                                break;
                            }
                        } else {
                            player.receiveACard(deck.getCard());
                            player.showHand();
                            if (player.showScore() > 21) {
                                return;
                            }
                        }
                        action();
                        return;
                    case "S":
                        // nothing
                        return;
                    case "D":
                        player.receiveACard(deck.getCard());
                        // incorporate bidding!, if you split you can then double
                        return;
                    case "P":
                        player.receiveACardSplit(player.getHand().get(1));
                        player.getHand().set(1, deck.getCard());
                        player.receiveACardSplit(deck.getCard());
                        player.showHand();
                        break;
                    // default: not needed, condition will take care of all inputs
                }
            }
        }
    }


    // Win conditions
    public void winner() {
        System.out.println();

        if (!player.getSplitHand().isEmpty()) {
            if (player.showScore() > 21) {
                System.out.println("Your score in your Hand is higher then 21, dealer wins.");
            }
            if (player.showSplitScore() > 21) {
                System.out.println("Your score in your Split Hand is higher then 21, dealer wins.");
            }
        } else {
            if (player.showScore() > 21) {
                System.out.println("Your score is higher then 21, dealer wins.");
                player.setMoneyAccount(player.getMoneyAccount() - player.getBet());
                return;
            }
        }

        dealer.setHoleCard(true);
        dealer.showHand();
        dealer.lessThen17(deck);    // Check dealer's hand
        System.out.println();

        if (!player.getSplitHand().isEmpty()) {
            if (player.showSplitScore() <= 21 && player.showSplitScore() > dealer.showScore() || dealer.showScore() > 21) {
                System.out.println("Player wins with Split Hand.");
            } else {
                System.out.println("Dealer wins over Player's Split Hand.");
            }
        }

        if (player.showScore() <= 21 && player.showScore() > dealer.showScore() || dealer.showScore() > 21) {
            System.out.println("Player wins");
            player.setMoneyAccount(player.getMoneyAccount() + player.getBet());
        } else if (player.showScore() == dealer.showScore()) {
            System.out.println("It's draw!");
        } else {
            System.out.println("Dealer wins");
            player.setMoneyAccount(player.getMoneyAccount() - player.getBet());

        }
    }


    // prepare for next game
    public void prepareForNewGame() {
        player.setHand(new ArrayList<>());
        player.setBet(0);
        if (!player.getSplitHand().isEmpty()) {
            player.setSplitHand(new ArrayList<>());
        }
        dealer.setHand(new ArrayList<>());
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
            if (answer.equals("y")) {
                System.out.println();
                return true;
            } else if (answer.equals("n")) {
                return false;
            } else {
                System.out.println("Wrong input, try again.");
            }
        }
    }
}
