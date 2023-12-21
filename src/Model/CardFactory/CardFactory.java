package Model.CardFactory;

public class CardFactory {

    public Card createCard(CardSuits cardType, int value) {
        if (cardType == null) {
            return null;
        }

        switch (cardType) {
            case SPADE -> {
                return new Spades(CardSuits.SPADE, value);
            }
            case HEART -> {
                return new Hearts(CardSuits.HEART, value);
            }
            case CLUB -> {
                return new Clubs(CardSuits.CLUB, value);
            }
            case DIAMOND -> {
                return new Diamonds(CardSuits.DIAMOND, value);
            }
            default -> throw new IllegalArgumentException();

        }
    }
}
