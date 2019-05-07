import java.util.*;
//Deck
//  Action:
//  createNewDeck() 新建牌堆
//  shuffle()  洗乱牌堆
//  dealCard()  拿1张牌
//  dealHand()  拿num牌
//  
//  State
//  remainingCards()
//  ...

public class Deck {
    private Card[] cards;
    private int dealIndex = 0;

    public Deck(){
        this.cards = createNewDeck(13);
    }

    private Card[] createNewDeck(int largestCardsNumber){
        cards = new Card[largestCardsNumber];
        int c = 0;
        for (int i = 1; i <= largestCardsNumber; ++i) {
            for (Suit e : Suit.values()) {
                cards[c++] = new Card(e, i);
            }
        }
        return cards;
    }

    public void shuffle(){
        Random random = new Random();
        for (int i = 0; i < cards.length; ++i) {
            int r = random.nextInt(cards.length - i) + i;
            Card tmp = cards[i];
            cards[i] = cards[r];
            cards[r] = tmp;
        }
    }

    private int remainingCards() {
        return cards.length - dealIndex;
    }

    public Card[] dealHand(int number) {
        if(number > remainingCards()){
            return null;                // or create new one, dealIndex 初始化
        }
        Card[] cards = new Card[number];
        for (int i = 0; i < number; i++) {
            cards[i] = dealCard();
        }
        return cards;
    }

    public Card dealCard() {
        return remainingCards() == 0 ? null : cards[dealIndex++];
    }
}
