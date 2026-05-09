public class Card {
    public String color;
    public String value;


    public Card(String color, String value) {
        this.color = color;
        this.value = value;
  
    }


public String getColor() {
    return color;
}

public int getValue() {
if (value.equals("Bube") || value.equals("Dame") || value.equals("König")) {
    return 10;
}
if (value.equals("Ass") ) {
    return 11;
}

return Integer.parseInt(value);
}
@Override
public String toString() {
    return color + " " + value;
}
}