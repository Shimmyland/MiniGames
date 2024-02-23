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

    public void newGame(){
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
        System.out.print(", with a total of " + player.showScore());
        System.out.println();
        if (player.autoWin()){      // solve it! - have to break game loop -> make it boolean! make a condition in main
            return;
        }

        dealer.showHand();
        System.out.print(", with a total of " + dealer.showScore());
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

        label:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next().toUpperCase();
            System.out.println();
            if (Character.isDigit(answer.charAt(0))) {
                System.out.println("Wrong input, try again.");
            } else {
                switch (answer) {
                    case "H":
                        player.receiveACard(deck.getCard());
                        player.showHand();
                        System.out.print(", with a total of " + player.showScore());
                        System.out.println();
                        if (player.showScore() > 21) {
                            return;
                        }
                        action();
                        return;
                    case "S":
                        // nothing
                        break label;
                    case "D":
                        player.receiveACard(deck.getCard());
                        // incorporate bidding!
                        break label;
                    case "P":
                        // ?!
                        break label;
                    default:
                        System.out.println("Wrong answer, try again.");
                        break;
                }
            }
        }
    }


    // Win conditions
    public void winner() {
        dealer.setHoleCard(true);
        dealer.showHand();
        dealer.lessThen17(deck);    // Check dealer's hand

        System.out.println();
        if (player.showScore() <= 21 && player.showScore() > dealer.showScore()) {
            System.out.println("Player wins");
        } else if (player.showScore() == dealer.showScore()) {
            System.out.println("Dealer wins");
        } else {
            System.out.println("Dealer wins");
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


    // prepare for next game
    public void prepare(){
        deck.discardHand(player.getHand());
        player.setHand(null);
        deck.discardHand(dealer.getHand());
        dealer.setHand(null);
        dealer.setHoleCard(false);
        if (deck.getCardsInDeck() < 31){  // 52 / 100 * 60 -> 31-32 cards (less than 60% available)
            deck.returnCardsIntoDeck();
        }
        deck.shuffle();
    }
}
