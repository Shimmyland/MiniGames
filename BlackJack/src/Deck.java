import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    // fields
    private List<Card> deck;
    private int cardsInDeck;
    private List<Card> discardPile;


    // constructor
    public Deck() {
        this.deck = new ArrayList<>();
        int index = 2;
        int counts = 0; //  face cards - A, J, Q, K
        // String[] cards = {"A", "2","3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suitsOfCards = {"C", "D", "H", "S"};
        for (String s : suitsOfCards) {
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


    // setters and getter
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
    public List<Card> getDiscardPile() {
        return discardPile;
    }
    public void setDiscardPile(List<Card> discardPile) {
        this.discardPile = discardPile;
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
                return card;
            }
        }
        return null;    // deck is empty
    }

    public void discardHand(List<Card> cards) {
        discardPile.addAll(cards);
        cardsInDeck -= discardPile.size();
    }

    public void returnCardsIntoDeck(){
        for (Card card : discardPile){
            for (Card position : deck){
                if (position == null){
                    position.setValue(card.getValue());
                    position.setSuit(card.getSuit());
                    break;
                }
            }
        }
        discardPile.clear();
    }
}
