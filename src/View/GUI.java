package View;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GUI extends JFrame {

    public JButton newGame;
    public JButton noMoreCards;
    public JButton newCard;
    public JButton rules;
    public JButton exit;

    private ImageIcon cardBack;

    private JLabel cardBackLabel;
    private JLabel totalCapital;
    private JLabel currentBet;
    private JTextArea statistics;

    private JLabel instructions;

    private JLabel userHandValueText;
    private JLabel userHandValue;
    private JLabel emptySpace;
    private JLabel houseHandValueText;
    private JLabel houseHandValue;

    private JPanel buttonPanel;
    private JPanel userHandPanel;
    private JPanel houseHandPanel;
    private JPanel betAndCapitalPanel;
    private JPanel centerPanel;
    private JPanel handValuePanel;

    private JDialog dialog;
    private JScrollPane scrollPane;
    private JTextArea rulesText;

    private JPanel mainPanel;

    private Color brightYellow = new Color(255, 200, 0);
    private Color lightYellow = new Color(255, 235, 150);

    private Font handValueFont = new Font("Rockwell Condensed", Font.BOLD, 24);
    private Font instructionsFont = new Font("Rockwell Condensed", Font.BOLD, 18);
    private Font labelFont = new Font("Rockwell Condensed", Font.BOLD, 14);
    private Font rulesFont = new Font("Arial", Font.BOLD, 14);


    public GUI() {
        initiateComponents();
        addComponents();
        this.setLayout(new BorderLayout());
        this.add(mainPanel,BorderLayout.CENTER);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Black Jack");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }


    private void addComponents() {
        centerPanel.add(buttonPanel);
        centerPanel.add(instructions);
        buttonPanel.add(newCard);
        buttonPanel.add(noMoreCards);
        buttonPanel.add(newGame);
        buttonPanel.add(rules);
        buttonPanel.add(exit);
        buttonPanel.setOpaque(false);
        centerPanel.setOpaque(false);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrera instruktionerna

        centerPanel.add(buttonPanel);
        centerPanel.add(instructions);

        houseHandPanel.add(cardBackLabel);
        houseHandPanel.setOpaque(false);

        betAndCapitalPanel.add(statistics);
        betAndCapitalPanel.add(totalCapital);
        betAndCapitalPanel.add(currentBet);
        betAndCapitalPanel.setOpaque(false);

        handValuePanel.add(houseHandValueText);
        handValuePanel.add(houseHandValue);
        handValuePanel.add(emptySpace);
        handValuePanel.add(userHandValueText);
        handValuePanel.add(userHandValue);
        handValuePanel.setOpaque(false);

        userHandPanel.setOpaque(false);

        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(userHandPanel, BorderLayout.SOUTH);
        mainPanel.add(houseHandPanel,BorderLayout.NORTH);
        mainPanel.add(betAndCapitalPanel,BorderLayout.WEST);
        mainPanel.add(handValuePanel, BorderLayout.EAST);
    }


    private void createMainPanel() {
        ImageIcon backgroundImage = new ImageIcon("src/Background/background.jpg");
        mainPanel = new JPanel(new BorderLayout()){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        mainPanel.setOpaque(false);
    }


    private void initiateComponents() {
        newCard = new JButton("Hit me!");
        noMoreCards = new JButton("Stop!");
        newGame = new JButton("New game");
        rules = new JButton("Rules");
        exit = new JButton("Exit");

        instructions = new JLabel(" ");
        instructions.setFont(instructionsFont);
        instructions.setForeground(brightYellow);
        instructions.setHorizontalAlignment(JLabel.CENTER);

        cardBack = new ImageIcon("src/Cards/Background/cardBack_blue2.png");
        cardBackLabel = new JLabel(cardBack);

        userHandValueText = new JLabel("Player name: ");
        userHandValueText.setForeground(brightYellow);
        userHandValueText.setFont(labelFont);

        statistics = new JTextArea("");
        statistics.setForeground(brightYellow);
        statistics.setFont(labelFont);
        //statistics.setEditable(false);
        statistics.setOpaque(false);
       // statistics.setBorder(null);

        totalCapital = new JLabel("Total Capital: ");
        totalCapital.setForeground(brightYellow);
        totalCapital.setFont(labelFont);

        currentBet = new JLabel("Current Bet: ");
        currentBet.setForeground(brightYellow);
        currentBet.setFont(labelFont);

        houseHandValueText = new JLabel("Huset ");
        houseHandValueText.setForeground(brightYellow);
        houseHandValueText.setFont(handValueFont);
        houseHandValue = new JLabel("");
        houseHandValue.setForeground(brightYellow);
        houseHandValue.setFont(handValueFont);

        emptySpace = new JLabel();

        userHandValueText = new JLabel();
        userHandValueText.setForeground(brightYellow);
        userHandValueText.setFont(handValueFont);
        userHandValue = new JLabel("");
        userHandValue.setForeground(brightYellow);
        userHandValue.setFont(handValueFont);

        buttonPanel = new JPanel(new FlowLayout());
        userHandPanel = new JPanel();
        houseHandPanel = new JPanel();
        betAndCapitalPanel = new JPanel(new GridLayout(3, 1));
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        handValuePanel = new JPanel(new GridLayout(5, 1));

        createMainPanel();
    }


    public void updateUserHandImages(List<JLabel> cardImages) {
        userHandPanel.removeAll();
        for (JLabel cardImage : cardImages) {
            userHandPanel.add(cardImage);
        }
        revalidate();
        repaint();
    }


    public void removeUpsideDownCard() {
        houseHandPanel.removeAll();
    }

    public void updateHouseHandImages(List<JLabel> cardImages) {
        for (JLabel cardImage : cardImages) {
            houseHandPanel.add(cardImage);
        }
        revalidate();
        repaint();
    }

    public void updateUserHandValue(int handValue){
        userHandValue.setText(Integer.toString(handValue));
    }

    public void updateHouseHandValue(int handValue){
        houseHandValue.setText(Integer.toString(handValue));
    }

    public void resetHandValues(){
        userHandValue.setText("");
        houseHandValue.setText("");
    }


    public void newRoundLayout() {
        houseHandPanel.removeAll();
        houseHandPanel.add(cardBackLabel);
    }


    public void setTotalCapital(int capital) {
        String s = "Total Capital: ";
        totalCapital.setText(s + capital);
    }

    public void setCurrentBet(int bet) {
        String s = "Current Bet: ";
        currentBet.setText(s + bet);
    }

    public void resetCurrentBet(){
        currentBet.setText("");
    }


    public void setPlayerName(String userName) {
        userHandValueText.setText(userName);
    }


    public String readRulesFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "Kunde inte läsa in reglerna.";
        }
    }


    public void showRules() {
        String rulesText = readRulesFromFile("src/rules.txt");

        this.rulesText = new JTextArea(rulesText);
        this.rulesText.setEditable(false);
        this.rulesText.setWrapStyleWord(true);
        this.rulesText.setLineWrap(true);
        this.rulesText.setCaretPosition(0);
        this.rulesText.setBackground(lightYellow);
        this.rulesText.setFont(rulesFont);

        scrollPane = new JScrollPane(this.rulesText);
        scrollPane.setPreferredSize(new Dimension(500, 350));

        dialog = new JDialog();
        dialog.setTitle("Black Jack-regler");
        dialog.add(scrollPane);

        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void updateInstructions(String instruction){
       instructions.setText(instruction);
    }

    public void updateStatistics(String statsSummary){
        statistics.setText(statsSummary);
    }


}


