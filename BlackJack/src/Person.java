import java.util.ArrayList;
import java.util.List;

public class Person {
    private List<Card> hand;

    // constructor
    public Person() {
        this.hand = new ArrayList<>();
    }

    // getter
    public List<Card> getHand() {
        return this.hand;
    }

    // setter
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }


    // methods
    public void receiveACard(Card card) {
        hand.add(card);
    }


    // delete?
    public void showHand() {
        System.out.print("Person's hand: ");
        int count = 0;
        for (Card c : hand) {
            count++;
            System.out.print(c.getValue() + "-" + c.getType());
            if (count < hand.size()) {
                System.out.print(" and ");
            }
        }
        System.out.println();
    }


    public int showScore() {
        int score = 0;
        String[] listOfValues = {"J", "Q", "K"};

        for (Card c : hand) {
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
        return score;
    }
}
