import java.util.ArrayList;
import java.util.Collections;

public class BaccaratDealer {
    ArrayList<Card> deck = new ArrayList<Card>();

    // generate a deck of cards.
    public void generateDeck() {
        deck.clear();
        String suites[] = {"hearts", "diamonds", "spades", "clubs"};
        for (String suite : suites) { // for suites
            for (int j = 1; j <= 13; j++) { // for value
                Card card = new Card(suite, j);
                deck.add(card);
            }
        }
    }

    // deals hands from deck
    public ArrayList<Card> dealHand() {
        ArrayList<Card> hand = new ArrayList<Card>();
        if (deckSize() > 0) hand.add(drawOne());
        if (deckSize() > 0) hand.add(drawOne());
        return hand;
    }

    // draws one card from the bottom of deck
    public Card drawOne() {
        if (deck.size() == 0) return null;
        return deck.remove(deckSize() - 1);
    }

    // shuffles deck
    public void shuffleDeck() {
        generateDeck();
        Collections.shuffle(deck);
    }

    // returns size of deck
    public int deckSize() {
        return deck.size();
    }
}
