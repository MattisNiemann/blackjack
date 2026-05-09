import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main{
    
    static List<Card> deck = new ArrayList<>();
    static List<Card> playerHand = new ArrayList<>();
    static List<Card> houseHand = new ArrayList<>();
    
    public static void main(String[] args) {


        startGame();


        


    }


public static Card drawCard(List<Card> target){

    Card drawnCard = deck.remove(deck.size() - 1);
    target.add(drawnCard);
    return drawnCard;

}
public static void createDeck() {
    String[] colors = {"Herz", "Karo", "Piek", "Kreuz"};
    String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass"};
    for (String color : colors) {
        for (String value : values) {
            Card card = new Card(color, value);
            deck.add(card);
        }
    }
}
public static void playerTurn() {
    update();
    System.out.println("1. Karte ziehen");
    System.out.println("2. Karte halten");
    int choice;
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            choice = scanner.nextInt();
        }
    
    if (choice == 1) {
        drawCard(playerHand);
        if (calculateHandValue(playerHand) > 21) {          // wenn über 21 punkte -> verloren
            System.out.println("Du hast verloren!");
            System.out.println("Deine Karten: " + playerHand);1
            return;
        }
        playerTurn();
    } else if (choice == 2) {
        houseTurn();
    }
    

    
}
public static void houseTurn() {
    while (calculateHandValue(houseHand) < 17) {
        drawCard(houseHand);
    }
    update();
    int playerValue = calculateHandValue(playerHand);
    int houseValue = calculateHandValue(houseHand);
    if (houseValue > 21 || playerValue > houseValue) {
        System.out.println("Du hast gewonnen!");
    } else if (playerValue < houseValue) {
        System.out.println("Du hast verloren!");
    } else {
        System.out.println("Unentschieden!");
    }
}
public static int calculateHandValue(List <Card> hand){
// rechnet den wert aller Karten auf der Hand aus
int sum = 0;
    for (Card card : hand) {
        sum += card.getValue(); // Wert der Karte zur Summe hinzufügen
    }
    return sum;
}
public static void startGame() {
   // deck wird erstellt und gemischt
    createDeck();
    Collections.shuffle(deck);

    // spieler und haus bekommen jeweils 2 karten
            drawCard(playerHand);
            drawCard(playerHand);
            drawCard(houseHand);
            drawCard(houseHand);
        
update();
playerTurn();

}
public static void update() {
    System.out.println("Spieler Karten: " + playerHand);
    System.out.println("Haus Karten: " + houseHand.get(0) + " ??");
}
}