package Model.CardFactory;

import javax.swing.*;

public class Diamonds implements Card {

    private CardSuits suit;
    private int value;
    private JLabel cardImage;

    public Diamonds(CardSuits suit, int value) {
        this.suit = suit;
        this.value = value;
        this.cardImage = new JLabel();
    }

    @Override
    public String getSuit() {
        return suit.toString();
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        this.cardImage.setIcon(icon);
    }

    @Override
    public JLabel getImage() {
        return cardImage;
    }
}
