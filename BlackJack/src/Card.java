// Cards
// 2-10 = face value
// J,Q,K = 10 points
// A = 1 or 11 points
public class Card {
    private String value;
    private String suit;        // color of a card

    // constructor
    public Card(String value, String type) {
        this.value = value;
        this.suit = type;
    }

    // getters
    public String getValue() {
        return value;
    }
    public String getSuit() {
        return suit;
    }

    // setters
    public void setValue(String value) {
        this.value = value;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
}
