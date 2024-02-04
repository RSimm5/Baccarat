import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BaccaratDealerTest {
    BaccaratDealer testDealer = new BaccaratDealer();

    ArrayList<Card> getDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        while (testDealer.deckSize() != 0) {
            deck.add(testDealer.drawOne());
        }
        return deck;
    }

    @Test
    void testDrawEmpty() {
        ArrayList<Card> empty = new ArrayList<Card>();
        ArrayList<Card> hand = new ArrayList<Card>(testDealer.dealHand());
        Assertions.assertEquals(empty, hand, "Arrays should match");

        Card card = testDealer.drawOne();
        Assertions.assertEquals(null, card, "Cards should match");
    }

    @Test
    void testGenerateDeck() {
        testDealer.generateDeck();
        ArrayList<Card> deck = getDeck();
        int count = 0;

        String suites[] = {"clubs","spades","diamonds","hearts"};
        for (String suite : suites) {
            for (int i = 13; i > 0; i--) {
                Card card = deck.get(count);
                Assertions.assertEquals(suite, card.getSuite(), "Suite should match");
                Assertions.assertEquals(i, card.getValue(), "Value should match");
                count++;
            }
        }
    }

    @Test
    void testShuffle() {
        testDealer.generateDeck();
        ArrayList<Card> deck1 = getDeck(); 

        testDealer.shuffleDeck();
        ArrayList<Card> deck2 = getDeck();

        Assertions.assertNotEquals(deck1, deck2, "Deck should be shuffled");
    }

    @Test
    void testDraw() {
        testDealer.shuffleDeck();
        ArrayList<Card> hand = new ArrayList<Card>();
        hand = testDealer.dealHand();
        Assertions.assertEquals(2, hand.size(), "hand shouldn't be empty");
        Assertions.assertEquals(50, testDealer.deckSize(), "Deck should change size");

        hand.add(testDealer.drawOne());
        Assertions.assertEquals(49, testDealer.deckSize(), "Deck should change size");

        for (int i = 0; i < hand.size(); i++) {
            Assertions.assertNotEquals(null, hand.get(i), "Card should exist");
        }
    }
}
