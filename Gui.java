import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Gui {

    private static JLabel getCardLabel() {
        // Ensure the path is correct and relative to the classpath
        ImageIcon cardIcon = new ImageIcon("cards/7-C.png");
        // Scale the image to make it smaller
        Image scaledImage = cardIcon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return new JLabel(scaledIcon);
    }

    public static void main(String[] args) {
        // Example usage of getCardLabel
        JFrame frame = new JFrame("Card Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 450);
        JLabel cardLabel = getCardLabel();
        frame.add(cardLabel);
        frame.setVisible(true);
        System.out.println("Card label created: " + cardLabel);
    }
}
