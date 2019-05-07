import java.util.*;

public enum Suit{
    Club,Diamond,Spade,Heart;
}

public class Card {
    private final Suit suit;
    private final int value;

    public Card(Suit suit, int value){
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit(){
        return suit;
    }

    public int getValue(){
        return value;
    }
}
