package Model.CardFactory;

import javax.swing.*;

public interface Card {
    String getSuit();
    int getValue();
    void setImage(String path);
    JLabel getImage();



}
