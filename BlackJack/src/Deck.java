import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> deck;
    private int cardsInDeck;
    private List<Card> discardPile;

    // constructor
    public Deck() {
        this.deck = new ArrayList<>();
        int index = 2;
        int counts = 0; // four special cards - A, J, Q, K
        // String[] cards = {"A", "2","3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] typesOfCard = {"C", "D", "H", "S"};    // four types of cards
        for (String s : typesOfCard) {
            while (true) {
                if (index > 10) {
                    if (counts == 0) {
                        this.deck.add(new Card("J", s));
                        counts++;
                    } else if (counts == 1) {
                        this.deck.add(new Card("Q", s));
                        counts++;
                    } else if (counts == 2) {
                        this.deck.add(new Card("K", s));
                        counts++;
                    } else if (counts == 3) {
                        this.deck.add(new Card("A", s));
                        index = 2;
                        counts = 0;
                        break;
                    }
                } else {
                    this.deck.add(new Card(Integer.toString(index), s));
                    index++;
                }
            }
        }
        this.cardsInDeck = deck.size();
        this.discardPile = new ArrayList<>();
    }

    // getter
    public List<Card> getDeck() {
        return deck;
    }


    // class methods
    public void shuffle(){
        Random random = new Random();
        for (int i=0; i < deck.size(); i++){
            if (deck.get(i) == null){
                continue;
            }
            Card currentCard = deck.get(i);
            int randomValue = random.nextInt(deck.size());
            Card randomCard = deck.get(randomValue);
            deck.set(i, randomCard);
            deck.set(randomValue, currentCard);
        }
    }

    public Card getCard() {
        for (int i = 0; i < deck.size(); i++) {
            Card card = deck.get(i);
            if (card != null) {
                deck.set(i, null);
                return card;
            }
        }
        return null;    // deck is empty
    }



}
