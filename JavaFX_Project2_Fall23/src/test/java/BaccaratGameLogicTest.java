import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BaccaratGameLogicTest {
    BaccaratGameLogic testLogic = new BaccaratGameLogic();
    ArrayList<Card> cards1 = new ArrayList<>();
    ArrayList<Card> cards2 = new ArrayList<>();
    ArrayList<Card> cards3 = new ArrayList<>();
    ArrayList<Card> cards4 = new ArrayList<>();

    @BeforeEach
    void setup() {
        cards1.add(new Card("Hearts", 2));
        cards1.add(new Card("Spades", 7));
        cards1.add(new Card("Diamonds", 10));
        
        cards2.add(new Card("Clubs", 4));
        cards2.add(new Card("Hearts", 12));

        cards3.add(new Card("Diamonds", 13));
        cards3.add(new Card("Spades", 10));

        cards4.add(new Card("Diamonds", 6));
        cards4.add(new Card("Spades", 2));
    }

    @Test
    void handTotalTest() {
        assertEquals(9, testLogic.handTotal(cards1), "Total values should be equal");
        assertEquals(4, testLogic.handTotal(cards2), "Total values should be equal");
        assertEquals(0, testLogic.handTotal(cards3), "Total values should be equal");
        assertEquals(8, testLogic.handTotal(cards4), "Total values should be equal");
    }

    @Test
    void evaluatePlayerDrawTest() {
        assertEquals(false, testLogic.evaluatePlayerDraw(cards1), "Boolean values should be equal");
        assertEquals(true, testLogic.evaluatePlayerDraw(cards2), "Boolean values should be equal");
        assertEquals(true, testLogic.evaluatePlayerDraw(cards3), "Boolean values should be equal");
        assertEquals(false, testLogic.evaluatePlayerDraw(cards4), "Boolean values should be equal");

    }
    @Test
    void evaluateBankerDrawTest() {
        assertEquals(false, testLogic.evaluateBankerDraw(cards1, cards2.get(0)), "Boolean values should be equal");
        assertEquals(true, testLogic.evaluateBankerDraw(cards3, cards2.get(0)), "Boolean values should be equal");
        assertEquals(true, testLogic.evaluateBankerDraw(cards2, cards2.get(0)), "Boolean values should be equal");
        assertEquals(false, testLogic.evaluateBankerDraw(cards2, cards2.get(1)), "Boolean values should be equal");
    }

    @Test
    void whoWonTest() {
        assertEquals("Draw", testLogic.whoWon(cards1, cards1), "String values should be equal");
        assertEquals("Player", testLogic.whoWon(cards1, cards2), "String values should be equal");
        assertEquals("Player", testLogic.whoWon(cards1, cards3), "String values should be equal");
        assertEquals("Player", testLogic.whoWon(cards1, cards4), "String values should be equal");

        assertEquals("Banker", testLogic.whoWon(cards2, cards1), "String values should be equal");
        assertEquals("Draw", testLogic.whoWon(cards2, cards2), "String values should be equal");
        assertEquals("Player", testLogic.whoWon(cards2, cards3), "String values should be equal");
        assertEquals("Banker", testLogic.whoWon(cards2, cards4), "String values should be equal");

        assertEquals("Banker", testLogic.whoWon(cards3, cards1), "String values should be equal");
        assertEquals("Banker", testLogic.whoWon(cards3, cards2), "String values should be equal");
        assertEquals("Draw", testLogic.whoWon(cards3, cards3), "String values should be equal");
        assertEquals("Banker", testLogic.whoWon(cards3, cards4), "String values should be equal");

        assertEquals("Banker", testLogic.whoWon(cards4, cards1), "String values should be equal");
        assertEquals("Player", testLogic.whoWon(cards4, cards2), "String values should be equal");
        assertEquals("Player", testLogic.whoWon(cards4, cards3), "String values should be equal");
        assertEquals("Draw", testLogic.whoWon(cards4, cards4), "String values should be equal");
    }
}
