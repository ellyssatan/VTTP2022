package unodeck;

public class  Cards {
    // implicit static (CONSTANT) - CLASS member, not instance member
    public enum Colour {Red, Green, Blue, Yellow}
    // card names
    public static final String[] CARD_NAMES = {
        "1", "2", "3", "4", "5", "6",
        "7", "8", "9", "skip", "shuffle", "+2"
    };

    
    private final Colour colour;
    private final String name;
    //private final int value;

    public Cards(Colour s, String n, int v) {
        colour = s;
        name = n;
        //value = v;
    }

    public Suit getSuit() {return suit;}
    public String getName() {return name;}
    public int getValue() {return value;}

    @Override
    public String toString() {
        return "%s-%s-%d".formatted(suit, name, value);
    }
}
