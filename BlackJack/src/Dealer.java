import java.util.HashMap;

public class Dealer extends Person {

    // fields
    private boolean holeCard;   // known as the 2nd card, if true = able to print


    // constructor
    public Dealer() {
        super();
        this.holeCard = false;
    }

    // getter and setter
    public boolean isHoleCard() {
        return holeCard;
    }

    public void setHoleCard(boolean holeCard) {
        this.holeCard = holeCard;
    }


    // method for the Dealer
    @Override
    public void showHand() {
        StringBuilder message = new StringBuilder("Dealer has an ");
        HashMap<String, String> faceCards = new HashMap<>();
        faceCards.put("J", "a Jack");
        faceCards.put("Q", "a Queen");
        faceCards.put("K", "a King");
        faceCards.put("A", "an Ace");

        for (int i = 0; i < getHand().size(); i++) {
            // Check for face or value card
            if (faceCards.containsKey(getHand().get(i).getValue())) {
                message.append(faceCards.get(getHand().get(i).getValue()));
            } else {
                if (getHand().get(i).getValue().equals("8")) {
                    message.append("an ");
                } else {
                    message.append("a ");
                }
                message.append(getHand().get(i).getValue());
            }

            // suits
            switch (getHand().get(i).getSuit()) {
                case "C":
                    message.append(" of Clubs");
                    break;
                case "D":
                    message.append(" of Diamonds");
                    break;
                case "H":
                    message.append(" of Hearts");
                    break;
                default:
                    message.append(" of Spades");
                    break;
            }

            // show only 1st cards
            if (!holeCard) {
                message.append(" (and a hidden card)");
                break;
            } else {
                if (i < getHand().size() - 1) {
                    message.append(i == getHand().size() - 2 ? " and " : ", ");         // ternary conditional operator
                }
            }
        }
        System.out.println(message + ", with a total of " + showScore());
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
                System.out.println("Dealer's score is too low, he has to draw a card..");
                showHand();
            } else {
                break;
            }
        }
    }
}
