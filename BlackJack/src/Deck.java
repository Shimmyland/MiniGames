import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    // fields
    private List<Card> deck;
    private int cardsInDeck;


    // constructor
    public Deck() {
        this.deck = new ArrayList<>();
        String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suitsOfCards = {"C", "D", "H", "S"};
        for (String s : suitsOfCards) {
            for (int i = 0; i < cards.length; i++) {
                this.deck.add(new Card(cards[i], s));
            }
        }
        this.cardsInDeck = deck.size();
    }


    // getter and setters
    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public int getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(int cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }


    // class methods
    public void shuffle() {
        Random random = new Random();
        for (int i = 0; i < deck.size(); i++) {
            if (deck.get(i) == null) {
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
                cardsInDeck--;
                return card;
            }
        }
        return null;    // deck is empty
    }
}
