public class Card {
    private String suite;
    private int value;

    //constructor
    public Card(String theSuite, int theValue) {
        this.suite = theSuite;
        this.value = theValue;
    }

    //getters
    public String getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }

    //10 and face cards are 0 points
    public int calcValue() {
        if (value >= 10) return 0;
        return value;
    }
    
}
