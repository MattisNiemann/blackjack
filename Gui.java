import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class Gui {

    private JFrame frame;

    private JPanel housePanel;
    private JPanel playerPanel;
    private JPanel buttonPanel;

    private JLabel statusLabel;
    private JLabel houseValueLabel;
    private JLabel playerValueLabel;

    private JButton hitButton;
    private JButton standButton;
    private JButton restartButton;

    public Gui(ActionListener listener) {
        frame = new JFrame("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout(10, 10));

        // Überschrift
        JLabel titleLabel = new JLabel("Blackjack", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Mitte: Haus + Spieler
        JPanel gamePanel = new JPanel(new GridLayout(2, 1, 10, 10));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        housePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        housePanel.setBorder(BorderFactory.createTitledBorder("Haus"));

        playerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        playerPanel.setBorder(BorderFactory.createTitledBorder("Spieler"));

        gamePanel.add(housePanel);
        gamePanel.add(playerPanel);

        frame.add(gamePanel, BorderLayout.CENTER);

        // Unten: Punkte + Buttons + Status
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel valuePanel = new JPanel(new GridLayout(1, 2));
        houseValueLabel = new JLabel("Haus: ?", SwingConstants.CENTER);
        playerValueLabel = new JLabel("Spieler: 0", SwingConstants.CENTER);

        houseValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playerValueLabel.setFont(new Font("Arial", Font.BOLD, 16));

        valuePanel.add(houseValueLabel);
        valuePanel.add(playerValueLabel);

        buttonPanel = new JPanel(new FlowLayout());

        hitButton = new JButton("Karte ziehen");
        standButton = new JButton("Halten");
        restartButton = new JButton("Neustart");

        hitButton.setActionCommand("hit");
        standButton.setActionCommand("stand");
        restartButton.setActionCommand("restart");

        hitButton.addActionListener(listener);
        standButton.addActionListener(listener);
        restartButton.addActionListener(listener);

        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(restartButton);

        statusLabel = new JLabel("Wähle: Karte ziehen oder Halten", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 15, 0));

        bottomPanel.add(valuePanel, BorderLayout.NORTH);
        bottomPanel.add(buttonPanel, BorderLayout.CENTER);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void update(List<Card> playerHand, List<Card> houseHand, boolean hideHouseCard) {    //gui wird geupdated
        playerPanel.removeAll();
        housePanel.removeAll();

        for (Card card : houseHand) {               // Haus Karten werden geadded
            if (hideHouseCard && housePanel.getComponentCount() == 1) {
                housePanel.add(createBackLabel());
            } else {
                housePanel.add(createCardLabel(card));
            }
        }

        for (Card card : playerHand) {          // Spieler Karten werden geadded
            playerPanel.add(createCardLabel(card));
        }

        int playerValue = Main.calculateHandValue(playerHand);       // punkte werden berechnet und dargestellt
        playerValueLabel.setText("Spieler: " + playerValue);

        if (hideHouseCard) {
            houseValueLabel.setText("Haus: ?");
        } else {
            int houseValue = Main.calculateHandValue(houseHand);
            houseValueLabel.setText("Haus: " + houseValue);
        }

        frame.revalidate();
        frame.repaint();
    }

    public void setButtonsEnabled(boolean enabled) {
        hitButton.setEnabled(enabled);
        standButton.setEnabled(enabled);
    }

    public void setStatus(String text) {
        statusLabel.setText(text);
    }

    private JLabel createCardLabel(Card card) {
        String path = "cards/" + card.getColor() + "-" + card.getName() + ".png";
        return createImageLabel(path, card.toString());
    }

    private JLabel createBackLabel() {
        return createImageLabel("cards/BACK.png", "Verdeckte Karte");
    }

    private JLabel createImageLabel(String path, String fallbackText) {
        ImageIcon icon = new ImageIcon(path);

        if (icon.getIconWidth() == -1) {
            JLabel missingLabel = new JLabel(fallbackText, SwingConstants.CENTER);
            missingLabel.setPreferredSize(new Dimension(100, 150));
            missingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            return missingLabel;
        }

        Image image = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(image));
    }

    
}