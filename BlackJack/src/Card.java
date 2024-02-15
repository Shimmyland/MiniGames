// Cards
// 2-10 = face value
// J,Q,K = 10 points
// A = 1 or 11 points
public class Card {
    private String value;
    private String type;

    // constructor
    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    // getters
    public String getValue() {
        return value;
    }
    public String getType() {
        return type;
    }

    // setters
    public void setValue(String value) {
        this.value = value;
    }
    public void setType(String type) {
        this.type = type;
    }
}
