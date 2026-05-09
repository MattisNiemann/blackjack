import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gui {

    private static JLabel getCardLabel(Card card) {
        // Der Pfad wird aus der Farbe und dem Wert der Karte generiert
        // Beispiel: "cards/" + "Herz" + "-" + "Ass" + ".png"
        String path = "cards/" + card.getColor() + "-" + card.getValue() + ".png";
        ImageIcon cardIcon = new ImageIcon(path);

        // Falls das Bild nicht gefunden wurde (Fehlerschutz)
        if (cardIcon.getIconWidth() == -1) {
            System.out.println("Bild nicht gefunden: " + path);
            return new JLabel("Bild fehlt"); 
        }
    
        Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaledImage));
    }



    public static void main(String[] args) {
        // Example usage of getCardLabel
        JFrame frame = new JFrame("Card Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        JLabel cardLabel = getCardLabel(Main.playerHand.get(0)); // Beispiel: die erste Karte der Spielerhand anzeigen
        frame.add(cardLabel);
        frame.setVisible(true);
        System.out.println("Card label created: " + cardLabel);
    }
}
