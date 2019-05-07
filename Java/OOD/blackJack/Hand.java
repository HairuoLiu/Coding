import java.util.*;

//Hand
//  State:
//  score()
//  size() -> 判断五个时候用
//  isBlackJack() -> 判断是不是已经完美
//  isBusted()
//  
//  Action
//  updateScores()
//  addCards()
//  print()
//  
public class Hand {
    protected final List<Card> cards = new ArrayList<>();
    private boolean busted;

    public int score() {
        List<Integer> scores = possibleScores();
        if (scores.size() == 0) {
            busted = true;
            return 0;
        }
        return getMaxPossible(scores);       //max
    }

    private int getMaxPossible(List<Integer> scores){
        int max = Integer.MIN_VALUE;
        for(int cur : scores){
            max = Math.max(max,cur);
        }
        return max;
    }

    private List<Integer> possibleScores() {
        Set<Integer> res = new HashSet<>();
        updateScores(0, new int[1], res);
//        Iterator<Integer> it = res.iterator();
//        while (it.hasNext()) {
//            if (it.next() > 21) {
//                it.remove();
//            }
//        }
        //or use lambda to do remove in iterator
        res.removeIf( (Integer i) -> i > 21);
        return new ArrayList<>(res);
    }

    private void updateScores(int i, int[] cur, Set<Integer> res) {
        if (i == cards.size()) {
            res.add(cur[0]);
            return;
        }
        Integer curValue = cards.get(i).getValue();
        if (curValue == 1) {
            cur[0] += 1;
            updateScores(i + 1, cur, res);
            cur[0] -= 1;

            cur[0] += 11;
            updateScores(i + 1, cur, res);
            cur[0] -= 11;
        } else {
            cur[0] += curValue >= 10 ? 10 : curValue;
            updateScores(i + 1, cur, res);
            cur[0] -= curValue >= 10 ? 10 : curValue;
        }
    }

    public void addCards(Card[] c){
        Collections.addAll(cards, c);
    }

    public boolean isBlackJack(){
        return cards.size() == 2 && score() == 21;
    }

    public boolean isBusted() {
        return busted;
    }

    public int size(){
        return cards.size();
    }

    public void print() {
        for (Card c: cards) {
            System.out.print(c.getSuit() + "" + c.getValue() + " ");
        }
    }
}
