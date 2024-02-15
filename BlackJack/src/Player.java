public class Player extends Person {

    // add money as a local field
    public Player() {
        super();
    }

    // worth to overwrite?
    public void showHand() {
        System.out.print("Player's hand: ");
        int count = 0;
        for (Card c : getHand()) {
            count++;
            System.out.print(c.getValue() + "-" + c.getType());
            if (count < getHand().size()) {
                System.out.print(" and ");
            }
        }
        System.out.println();
    }

    // only for player
    public boolean autoWin() {
        return showScore() == 21;
    }


}
