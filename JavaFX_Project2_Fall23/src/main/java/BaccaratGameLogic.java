import java.util.ArrayList;

public class BaccaratGameLogic {
    // returns if banker or player won
	// hand1 is player, hand2 is banker
    public String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
    	int total1 = handTotal(hand1);
    	int total2 = handTotal(hand2);
    	
    	if (total1 == total2) {
    		return "Draw";
    	}
    	else if (total1 > total2) {
    		return "Player";
    	}
    	else {
    		return "Banker";
    	}
    	
    	
    }

    // calculates total value of all cards in hand
    public int handTotal(ArrayList<Card> hand) {
        int total = 0;
        
        for (Card card: hand) {
            total += card.calcValue();
        }
        
        return total % 10; // removes first digit if > 9
    }

	// evaluates whether banker draws
    public boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
    	int total = handTotal(hand);
    	int playerVal;
    	
    	if (playerCard == null) {
    		playerVal = -1;
    	}
    	else {
    		playerVal = playerCard.calcValue();
    	}
    	
    	// for 3,4,5,6,
    	// whether the banker draws is determined by the whether the player drew, and if so the value of the
    	// player's draw card, as shown in the game table
    	if (total >= 7) {
    		return false;
    	}
    	else if (total <= 2) {
    		return true;
    	}
    	else if (total == 3) { 
    		if (playerVal != 8) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else if (total == 4) {
    		if ((playerVal) == 0 || (playerVal == 1) || (playerVal == 8) || (playerVal == 9)) {
    			return false;
    		}
    		else {
    			return true;
    		}
    	}
    	else if (total == 5) {
    		if ((playerVal == -1) || (playerVal == 4) || (playerVal == 5) || (playerVal == 6) || (playerVal ==7)) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    	else {
    		if ((playerVal == 6) || (playerVal == 7)) {
    			return true;
    		}
    		else {
    			return false;
    		}
    	}
    }
    
	// evaluates whether player can draw
    public boolean evaluatePlayerDraw(ArrayList<Card> hand) {
    	int playerTotal = handTotal(hand);
    	
    	if (playerTotal < 6) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
