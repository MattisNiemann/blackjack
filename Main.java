import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hauptklasse des Blackjack-Spiels.
 * Hier werden Deck, Spielablauf und Gewinnerlogik verwaltet.
 */


public class Main {

    static List<Card> deck = new ArrayList<>();
    static List<Card> playerHand = new ArrayList<>();
    static List<Card> houseHand = new ArrayList<>();
    

    static Gui gui;
    public static void main(String[] args) {
        startGame(); // spiel startet
    }

    public static Card drawCard(List<Card> target) {

        Card drawnCard = deck.remove(deck.size() - 1);
        target.add(drawnCard);
        return drawnCard;

    }

/**
 * Erstellt ein vollständiges Kartendeck.
 */
    public static void createDeck() {
        String[] colors = { "Herz", "Karo", "Piek", "Kreuz" };
        String[] values = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass" };
        for (String color : colors) {
            for (String value : values) {
                Card card = new Card(color, value);
                deck.add(card);
            }
        }
    }
    public static void houseTurn() {
        while (calculateHandValue(houseHand) < 17) {    // haus zieht bis es auf min 17 steht
            drawCard(houseHand);
        }
        detectWinner();
    }

    public static void detectWinner() {
        int playerValue = calculateHandValue(playerHand);
        int houseValue = calculateHandValue(houseHand);
    
        gui.update(playerHand, houseHand, false);
        gui.setButtonsEnabled(false);
    
        if (playerValue > 21) {
            gui.setStatus("Du hast verloren!");
        } else if (houseValue > 21 || playerValue > houseValue) {
            gui.setStatus("Du hast gewonnen!");
        } else if (playerValue < houseValue) {
            gui.setStatus("Du hast verloren!");
        } else {
            gui.setStatus("Unentschieden!");
        }
    }

    public static int calculateHandValue(List<Card> hand) {
        // rechnet den wert aller Karten auf der Hand aus
        int sum = 0;
        int aces = 0;
        for (Card card : hand) {
            sum += card.getValue(); // Wert der Karte zur Summe hinzufügen
            if(card.getValue() == 11) { 
                aces++; 
            }
        }
        while (sum > 21 && aces > 0) {
    sum -= 10;
    aces--;
}
        return sum;
    }


    public static void startGame() {

// karten werden resetet
        deck.clear();
        playerHand.clear();
        houseHand.clear();
        // deck wird erstellt und gemischt
        createDeck();
        Collections.shuffle(deck);

        // spieler und haus bekommen jeweils 2 karten
        drawCard(playerHand);
        drawCard(playerHand);
        drawCard(houseHand);
        drawCard(houseHand);

        gui = new Gui(e -> {
            String command = e.getActionCommand();
        
            if (command.equals("hit")) {
                drawCard(playerHand);
                gui.update(playerHand, houseHand, true);
        
                if (calculateHandValue(playerHand) > 21) {
                    detectWinner();
                }
        
            } else if (command.equals("stand")) {
                houseTurn();
        
            } else if (command.equals("restart")) {
                startGame();
            }
        });
        
        gui.update(playerHand, houseHand, true);

    }
    
}