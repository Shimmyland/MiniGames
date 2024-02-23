import java.util.HashMap;

public class Dealer extends Person {

    private boolean holeCard;   // known as the 2nd card, if true = able to print

    // create a bank?

    // constructor
    public Dealer() {
        super();
        this.holeCard = false;
    }

    // setter and getter
    public boolean isHoleCard() {
        return holeCard;
    }
    public void setHoleCard(boolean holeCard) {
        this.holeCard = holeCard;
    }


    // method for the Dealer
    @Override
    public void showHand() {
        System.out.print("Dealer has an ");
        for (int i = 0; i < getHand().size(); i++) {

            HashMap<String, String> faceCards = new HashMap<>();
            faceCards.put("J", "a Jack");
            faceCards.put("Q", "a Queen");
            faceCards.put("K", "a King");
            faceCards.put("A", "an Ace");

            // Check for face or value card
            if (faceCards.containsKey(getHand().get(i).getValue())) {
                System.out.print(faceCards.get(getHand().get(i).getValue()));
            } else {
                if (getHand().get(i).getValue().equals("8")) {
                    System.out.print("an ");
                } else {
                    System.out.print("a ");
                }
                System.out.print(getHand().get(i).getValue());
            }

            System.out.print(" of ");

            // suits
            switch (getHand().get(i).getSuit()) {
                case "C":
                    System.out.print("Clubs");
                    break;
                case "D":
                    System.out.print("Diamonds");
                    break;
                case "H":
                    System.out.print("Hearts");
                    break;
                default:
                    System.out.print("Spades");
                    break;
            }

            // show only 1st cards
            if (!holeCard) {
                break;
            } else {
                if (i < getHand().size() - 1) {
                    System.out.print(i == getHand().size() - 2 ? " and " : ", ");       // ternary conditional operator
                }
            }
        }
    }

    @Override
    public int showScore() {
        int score = 0;
        String[] listOfValues = {"J", "Q", "K"};

        if (holeCard) {
            // show all cards
            for (Card c : getHand()) {
                // Check if the card value is one of the J, Q, K
                boolean isSpecialValue = false;
                for (String s : listOfValues) {
                    if (c.getValue().equals(s)) {
                        score += 10;
                        isSpecialValue = true;
                        break;
                    }
                }

                // Face value and check for Ace
                if (!isSpecialValue) {
                    if (c.getValue().equals("A")) {
                        score += 11;
                    } else {
                        score += Integer.parseInt(c.getValue());
                    }
                }
            }
        } else {
            // show score only the 1st one
            boolean isSpecialValue = false;
            for (String s : listOfValues) {
                if (getHand().get(0).getValue().equals(s)) {
                    score += 10;
                    isSpecialValue = true;
                    break;
                }
            }

            // Face value and check for Ace
            if (!isSpecialValue) {
                if (getHand().get(0).getValue().equals("A")) {
                    score += 11;
                } else {
                    score += Integer.parseInt(getHand().get(0).getValue());
                }
            }
        }
        return score;
    }

    public void lessThen17(Deck deck) {
        while (true) {
            if (showScore() < 17) {
                receiveACard(deck.getCard());
                System.out.println();
                System.out.println("Dealer's score is too low, he draws a card..");
                showHand();
            } else {
                break;
            }
        }
        System.out.print(", with a total of " + showScore());
    }
}
