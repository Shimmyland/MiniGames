import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player extends Person {

    // add money as a local field
    private List<Card> splitHand;


    // constructor
    public Player() {
        super();
        this.splitHand = new ArrayList<>();
    }


    // getters and setters
    public List<Card> getSplitHand() {
        return splitHand;
    }
    public void setSplitHand(List<Card> splitHand) {
        this.splitHand = splitHand;
    }


    // method for the Player
    @Override
    public void showHand() {
        StringBuilder message = new StringBuilder("You have ");
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

            //
            if (i < getHand().size() - 1) {
                if (i < getHand().size() - 2){
                    message.append(", ");
                } else {
                    message.append(" and ");
                }
            }
        }
        System.out.println(message + ", with a total of " + showScore());

        if (!getSplitHand().isEmpty()){
            StringBuilder messageSplitHand = new StringBuilder("and in Split Hand you have ");
            for (int i = 0; i < getSplitHand().size(); i++) {
                // Check for face or value card
                if (faceCards.containsKey(getSplitHand().get(i).getValue())) {
                    messageSplitHand.append(faceCards.get(getSplitHand().get(i).getValue()));
                } else {
                    if (getSplitHand().get(i).getValue().equals("8")) {
                        messageSplitHand.append("an ");
                    } else {
                        messageSplitHand.append("a ");
                    }
                    messageSplitHand.append(getSplitHand().get(i).getValue());
                }

                // suits
                switch (getSplitHand().get(i).getSuit()) {
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

                //
                if (i < getSplitHand().size() - 1) {
                    if (i < getSplitHand().size() - 2){
                        messageSplitHand.append(", ");
                    } else {
                        messageSplitHand.append(" and ");
                    }
                }
            }
            System.out.println(messageSplitHand + ", with a total of " + showScore());
        }
    }

    @Override
    public int showScore() {
        int score = 0;
        String[] listOfValues = {"J", "Q", "K"};

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

            // Check for Ace
            if (!isSpecialValue) {
                if (c.getValue().equals("A")) {
                    if (score > 10){
                        score += 1;
                    } else {
                        score += 11;
                    }
                } else {
                    score += Integer.parseInt(c.getValue());
                }
            }
        }
        return score;
    }

    public boolean autoWin() {
        return showScore() == 21;
    }

    public void receiveACardSplit(Card card) {
        splitHand.add(card);
    }



}
