import java.util.HashMap;

public class Player extends Person {

    // add money as a local field
    public Player() {
        super();
    }


    // method for the Player
    @Override
    public void showHand() {
        System.out.print("You have ");
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

            //
            if (i < getHand().size() - 1) {
                if (i < getHand().size() - 2){
                    System.out.print(", ");
                } else {
                    System.out.print(" and ");
                }
            }
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

    public boolean autoWin() {
        return showScore() == 21;
    }


}
