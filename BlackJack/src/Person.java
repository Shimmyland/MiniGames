import java.util.ArrayList;
import java.util.List;

public abstract class Person {
    private List<Card> hand;        // ArrayList?

    // constructor
    public Person() {
        this.hand = new ArrayList<>();
    }

    // setter and getter
    public List<Card> getHand() {
        return this.hand;
    }
    public void setHand(List<Card> hand) {
        this.hand = hand;
    }


    // methods
    public void receiveACard(Card card) {
        hand.add(card);
    }

    public abstract void showHand();
    public abstract int showScore();
}
