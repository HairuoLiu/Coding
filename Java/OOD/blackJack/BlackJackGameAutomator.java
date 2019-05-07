import java.util.*;

//BlackJackGameAutomator
//  
//  BlackJackGameAutomator(int numPlayer)
//  boolean initialDeal()
//  List<Integer> getBlackJacks()
//  boolean playHand(Hand hand)
//  boolean playAllHands() 
//  List<Integer> getWinners() 
//  printHandAndScores()
//  simulate

public class BlackJackGameAutomator {
    private Deck deck;
    private Hand[] hands;
    private static final int HIT_UNTIL = 16;

    public BlackJackGameAutomator(int numPlayer) {
        hands = new Hand[numPlayer];
        for (int i = 0; i < numPlayer; i++) {
            hands[i] = new Hand();
        }
        deck = new Deck();
        deck.shuffle();
    }

    private boolean initialDeal() {
        for (Hand h : hands) {
            Card[] cards = deck.dealHand(2);
            if (cards == null) return false;
            h.addCards(cards);
        }
        return true;
    }

    List<Integer> getBlackJacks(){
        List<Integer> winners = new ArrayList<>();
        for (int i = 0; i < hands.length; i++) {
            if (hands[i].isBlackJack()) winners.add(i);
        }
        return winners;
    }

    boolean playHand(Hand hand){
        while (hand.score() < HIT_UNTIL && !hand.isBusted()) {
            Card card = deck.dealCard();
            if (card == null) return false;
            hand.addCards(new Card[]{card});
        }
        return true;
    }

    boolean playAllHands() {
        for (Hand h : hands) {
            if (!playHand(h)) return false;
        }
        return true;
    }

    List<Integer> getWinners() {
        List<Integer> winners = new ArrayList<>();
        int winnerScore = 0;
        for (int i = 0; i < hands.length; i++) {
            Hand h = hands[i];
            if (!h.isBusted()) {
                if (h.score() >= winnerScore) {
                    if (h.score() > winnerScore) {
                        winners.clear();
                        winnerScore = h.score();
                    }
                    winners.add(i);
                }
            }
        }
        return winners;
    }

    void printHandAndScores() {
        for (int i = 0; i < hands.length; i++) {
            if (hands[i].isBusted()) {
                System.out.print("hand" + i + " is busted! Cards: ");
            } else {
                System.out.print("hand" + i + " score: " + hands[i].score() + " Cards: ");
            }
            hands[i].print();
            System.out.println();
        }
    }

    public void simulate() {
        boolean success = initialDeal();

        if (!success) {
            System.err.println("Error. Out of cards at initial");
        } else {
            System.out.println("-----Initial-----");
            printHandAndScores();
            List<Integer> blackJacks = getBlackJacks();
            if (blackJacks.size() > 0) {
                System.out.print("Blackjack at ");
                for (int i : blackJacks) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }

        success = playAllHands();
        if (!success) {
            System.err.println("Error. Out of cards");
        } else {
            System.out.println("\n ---Completed Game---");
            printHandAndScores();
            List<Integer> winners = getWinners();
            if (winners.size() > 0) {
                System.out.print("winners: ");
                for(int i : winners){
                    System.out.print(i + " ");
                }
            } else {
                System.out.print("All players are busted.");
            }
        }
    }
}