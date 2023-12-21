package Model;

import Model.CardFactory.Card;
import Model.CardFactory.CardFactory;
import Model.CardFactory.CardSuits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckOfCards {

    private List<Card> deckOfCards = new ArrayList<>();

    public DeckOfCards() {
        createCardsFromFactory();
    }


    public void createCardsFromFactory() {
        CardFactory factory = new CardFactory();
        List<Integer> listOfValues = Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
        List<CardSuits> listOfSuits = Arrays.asList(
                CardSuits.CLUB,
                CardSuits.DIAMOND,
                CardSuits.HEART,
                CardSuits.SPADE);

        deckOfCards.clear();

        for (int i = 0; i < listOfSuits.size(); i++) {
            for (int j = 0; j < listOfValues.size(); j++) {
                Card card = factory.createCard(listOfSuits.get(i), listOfValues.get(j));
                deckOfCards.add(card);
            }
        }
        ImagePaths imagePaths = new ImagePaths();
        for (int i = 0; i < 52; i++) {
            deckOfCards.get(i).setImage(imagePaths.getImagePath(i));
        }


        Collections.shuffle(deckOfCards);

    }

    public Card dealCard() {
        Card tempCard = deckOfCards.get(0);
        System.out.println(deckOfCards.get(0).getValue());
        System.out.println(deckOfCards.get(0).toString());
        deckOfCards.remove(0);
        return tempCard;
    }

    public List<Card> getDeckOfCards(){
        return deckOfCards;
    }
}

