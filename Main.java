import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
public static void menu() {
    update();
    System.out.println("1. Karte ziehen");
    System.out.println("2. Hand anzeigen");
    System.out.println("3. Beenden");
    Scanner scanner = new Scanner(System.in);
    int choice = scanner.nextInt();
    System.out.println(choice);

    
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
menu();

    

    

}
public static void update() {
    System.out.println("Spieler Karten: " + playerHand);
    System.out.println("Haus Karten: " + houseHand.get(0) + " ??");
}
}