package Controller;

import Model.*;
import Model.CardFactory.Card;
import View.GUI;
import View.Instructions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BlackJackLogic implements ActionListener {

    private DeckOfCards deckOfCards;
    private GUI gui;
    private Statistics stats;
    private final User user;
    private final House house;
    private String userName;
    private int currentBet;
    private int currentCapital;



    public BlackJackLogic() {
        setUserValues();

        deckOfCards = new DeckOfCards();
        user = new User(userName, currentCapital);
        house = new House();
        stats = new Statistics();

        gui = new GUI();
        gui.newGame.addActionListener(this);
        gui.noMoreCards.addActionListener(this);
        gui.newCard.addActionListener(this);
        gui.rules.addActionListener(this);
        gui.exit.addActionListener(this);
        gui.setTotalCapital(currentCapital);
        gui.setPlayerName(userName);
        gui.updateStatistics(stats.getStatsSummary());

        nextRound();

    }

    public void discardAllHands() {
        user.discardHand();
        house.discardHand();
    }

    public void updateAllHandImages() {
        gui.updateUserHandImages(getCardImages(getUser()));
        gui.updateHouseHandImages(getCardImages(getHouse()));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.newCard) {
            userDrawCard();
            gui.updateUserHandImages(getCardImages(getUser()));

            if (user.getHandValue() > 21) {
                gui.updateInstructions(Instructions.BUSTED.getInstruction());
                stats.incrementLosses();
                endRound();
            }



        } else if (e.getSource() == gui.noMoreCards) {
            gui.removeUpsideDownCard();
            while (getHouse().getHandValue() < 17 && getHouse().getHandValue() > 0) {
                houseDrawCard();
                gui.updateHouseHandImages(getCardImages(getHouse()));

            }
            endRound();

            switch (calculateWinner()) {
                case WIN -> JOptionPane.showMessageDialog(null, EndOfRound.WIN.getEndOfRound() + payOutWinnings() + "€");
                case LOSE -> JOptionPane.showMessageDialog(null, EndOfRound.LOSE.getEndOfRound());
                case DRAW -> JOptionPane.showMessageDialog(null, EndOfRound.DRAW.getEndOfRound());
            }

        } else if (e.getSource() == gui.newGame) {
            nextRound();

        } else if (e.getSource() == gui.rules) {
            gui.showRules();

        } else if (e.getSource() == gui.exit) {
            System.exit(0);
        }

    }

    private void nextRound() {
        gui.resetHandValues();
        if (deckOfCards.getDeckOfCards().size() > 15) {
            deckOfCards.createCardsFromFactory();
        }
        gui.updateInstructions(Instructions.PLACE_BET.getInstruction());
        gui.newRoundLayout();
        gui.newCard.setEnabled(true);
        gui.noMoreCards.setEnabled(true);
        discardAllHands();
        updateAllHandImages();
        placeBet();
        dealCardsAtStartOfRound();
        updateAllHandImages();

    }

    private void placeBet() {
        currentBet = 0;
        String answer = JOptionPane.showInputDialog(null, "Place your bet");
        gui.updateInstructions(Instructions.DECIDE_NEXT_MOVE.getInstruction());

        if (answer != null && !answer.isEmpty()) {
            try {
                int bet = Integer.parseInt(answer);

                if (bet <= currentCapital) {
                    currentBet = bet;
                    user.subractBetFromCapital(bet);
                    gui.setTotalCapital(user.getCurrentCapital());
                    gui.updateInstructions(Instructions.DECIDE_NEXT_MOVE.getInstruction());

                } else {
                    JOptionPane.showMessageDialog(null, "Insatsen kan inte vara större än ditt kapital.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ange en giltig summa att satsa.");
            }

            gui.setCurrentBet(currentBet);
            gui.updateInstructions(Instructions.DECIDE_NEXT_MOVE.getInstruction());
        }
    }

    public List<JLabel> getCardImages(Player player) {
        List<JLabel> cardImages = new ArrayList<>();
        for (Card card : player.getCurrentHand()) {
            cardImages.add(card.getImage());
        }

        return cardImages;
    }


    public void setUserValues() {
        userName = JOptionPane.showInputDialog("Enter player name: ");
        String capital = JOptionPane.showInputDialog("Enter capital: ");
        currentBet = 0;
        try {
            currentCapital = Integer.parseInt(capital);
        } catch (NumberFormatException e) {
            currentCapital = 1000;
        }
    }

    public EndOfRound calculateWinner() {
        boolean userBust = user.getHandValue() > 21;
        boolean houseBust = house.getHandValue() > 21;

        EndOfRound result;

        if (userBust) {
            gui.updateInstructions(Instructions.LOST_ROUND.getInstruction());
            stats.incrementLosses();// Lade till
            gui.newCard.setEnabled(false);
            gui.noMoreCards.setEnabled(false);
            result = EndOfRound.LOSE;
        }
        else if (houseBust) {
            gui.updateInstructions(Instructions.WON_ROUND.getInstruction());
            payOutWinnings();
            stats.incrementWins();// Lade till
            result = EndOfRound.WIN;
        }
        else if (user.getHandValue() > house.getHandValue()) {
            gui.updateInstructions(Instructions.WON_ROUND.getInstruction());
            payOutWinnings();
            stats.incrementWins();// Lade till
            result = EndOfRound.WIN;
        }
        else if (user.getHandValue() < house.getHandValue()) {
            gui.updateInstructions(Instructions.LOST_ROUND.getInstruction());
            stats.incrementLosses();// Lade till
            result = EndOfRound.LOSE;
        }
        else {
            gui.updateInstructions(Instructions.DRAW_ROUND.getInstruction());
            stats.incrementDraws();// Lade till
            result = EndOfRound.DRAW;
        }
        gui.setTotalCapital(user.getCurrentCapital());
        gui.resetCurrentBet();
        gui.updateStatistics(stats.getStatsSummary());
        return result;
    }


    public void houseDrawCard() {
        house.drawCard(deckOfCards.dealCard());
        gui.updateHouseHandValue(house.getHandValue());
    }


    public void userDrawCard() {
        user.drawCard(deckOfCards.dealCard());
        gui.updateUserHandValue(user.getHandValue());

    }


    public void dealCardsAtStartOfRound() {
        user.drawCard(deckOfCards.dealCard());
        house.drawCard(deckOfCards.dealCard());
        user.drawCard(deckOfCards.dealCard());
        gui.updateUserHandValue(user.getHandValue());
        gui.updateHouseHandValue(house.getHandValue());
    }

    public int payOutWinnings() {
        int winnings = currentBet * 2;
        user.addToTotalCapital(winnings);
        return winnings;
    }


    public User getUser() {
        return user;
    }


    public House getHouse() {
        return house;
    }


    public void endRound() {
        gui.newCard.setEnabled(false);
        gui.noMoreCards.setEnabled(false);
        gui.updateStatistics(stats.getStatsSummary());
    }


    public static void main(String[] args) {
        new BlackJackLogic();
    }
}
