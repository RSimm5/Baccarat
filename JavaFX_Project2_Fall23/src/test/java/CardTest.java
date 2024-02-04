import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void testCardConstructorAndGetters() {
        // Create a sample card
        String suites[] = {"hearts", "spades", "clubs", "diamonds"};

		for (String suite : suites) {
			for (int i = 0; i < 13; i++) {
				Card card = new Card(suite, i);

				// test getters
				Assertions.assertEquals(suite, card.getSuite(), "Suite should match");
        		Assertions.assertEquals(i, card.getValue(), "Value should match");

				//test calcValue
				if (i < 10) {
					Assertions.assertEquals(i, card.calcValue(), "calcValue should match");
				} else {
					Assertions.assertEquals(0, card.calcValue(), "calcValue should match");
				}
			}
		}
    }
}
