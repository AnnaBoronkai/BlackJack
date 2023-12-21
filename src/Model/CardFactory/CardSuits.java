package Model.CardFactory;

public enum CardSuits {

    CLUB(0),
    DIAMOND(1),
    HEART(2),
    SPADE(3);

    private final int suit;

    CardSuits(int suit) {
        this.suit = suit;
    }

    public int getSuit() {
        return suit;
    }


}
