import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    static List<Card> deck = new ArrayList<>();
    static List<Card> playerHand = new ArrayList<>();
    static List<Card> houseHand = new ArrayList<>();

    public static void main(String[] args) {

        startGame(); // spiel startet

    }

    public static Card drawCard(List<Card> target) {

        Card drawnCard = deck.remove(deck.size() - 1);
        target.add(drawnCard);
        return drawnCard;

    }

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

    public static void playerTurn() {
            // der Spieler sieht seine Karten und kann entweder noch eine ziehen oder halten
        System.out.println("Spieler Karten: " + playerHand);
        System.out.println("Haus Karten: " + houseHand.get(0) + " ??");
        System.out.println("1. Karte ziehen");
        System.out.println("2. Karte halten");

        int choice = 0;
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        while (choice != 1 && choice != 2) { // bis er 1 oder 2 wählt 
            try {
                choice = scanner.nextInt(); // Eingabe des spielers wird gelesen
            } catch (java.util.InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte eine Zahl eingeben.");
                scanner.next();
            }
        }

        if (choice == 1) {  // weitere Karte ziehen
            drawCard(playerHand);
            if (calculateHandValue(playerHand) > 21) { // wenn über 21 punkte -> spiel vorbei
                detectWinner();
                return;
            }
            playerTurn();
        } else if (choice == 2) { // halten -> haus ist am zug
            houseTurn();
        }

    }

    public static void houseTurn() {
        while (calculateHandValue(houseHand) < 17) {    // haus zieht bis es auf min 17 steht
            drawCard(houseHand);
        }
        detectWinner();
    }

    public static void detectWinner() { // gewinner wird ermittelt und das ergebnis wird ausgegeben

        int playerValue = calculateHandValue(playerHand);
        int houseValue = calculateHandValue(houseHand);
        System.out.println("Spieler Karten: " + playerHand + playerValue + " Punkte");
        System.out.println("Haus Karten: " + houseHand + houseValue + " Punkte");
        if (playerValue > 21) {
            System.out.println("Du hast verloren!");
        }
         else if (houseValue > 21 || playerValue > houseValue) {
            System.out.println("Du hast gewonnen!");
        } else if (playerValue < houseValue) {
            System.out.println("Du hast verloren!");
        } else {
            System.out.println("Unentschieden!");
        }
        endGame();
    }

    public static int calculateHandValue(List<Card> hand) {
        // rechnet den wert aller Karten auf der Hand aus
        int sum = 0;
        for (Card card : hand) {
            sum += card.getValue(); // Wert der Karte zur Summe hinzufügen
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

        playerTurn(); // spieler ist am Zug

    }
    public static void endGame() {
        System.out.println("Spiel beendet. Zum neustart Enter drücken.");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        scanner.nextLine(); // Warten auf Eingabe
        startGame(); // Spiel neu starten
    }
}