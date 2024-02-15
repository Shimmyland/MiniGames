public class Dealer extends Person {

    // create a bank?
    public Dealer() {
        super();
    }

    // worth to overwrite?
    public void showHand(boolean showAllCards) {
        System.out.print("Dealer's hand: ");
        for (int i = 0; i < getHand().size(); i++) {
            if (showAllCards) {
                System.out.print(getHand().get(i).getValue() + "-" + getHand().get(i).getType());
                if (i < getHand().size()) {
                    System.out.print(" and ");
                }
            } else {
                System.out.print(getHand().get(i).getValue() + "-" + getHand().get(i).getType());
                break;
            }
        }
        System.out.println();
    }

    // method for Dealer
    public void lessThen17(Deck deck) {
        while (true) {
            if (showScore() < 17) {
                receiveACard(deck.getCard());
                System.out.println("Dealer's score is too low, he draws a card..");
                showHand(true);
            } else {
                break;
            }
        }
        System.out.println("Dealer's score: " + showScore());
    }
}
