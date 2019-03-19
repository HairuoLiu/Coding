
/**
 * In the "100 game," two players take turns adding, to a running total,
 * any integer from 1..10.
 * The player who first causes the running total
 * to reach or exceed 100 wins.  What if we change the game so that players
 * cannot re-use integers?  For example, two players might take turns drawing
 * from a common pool of numbers of 1..15 without replacement until they reach
 * a total >= 100.  Given an integer maxChoosableInteger and another integer
 * desiredTotal, determine if the first player to move can force a win, assuming
 * both players play optimally.  You can always assume that maxChoosableInteger
 * will not be larger than 20 and desiredTotal will not be larger than 300.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC464_CanIWin {

    public static void main(String[] args) {
        int C = 0;
        int D = 10;
        boolean result = LC464_CanIWin(C,D,false);

        System.out.println("result的值是：" + result);

    }

    public static boolean canIWin(int maxChoosableInteger, int desiredTotal,boolean isB){
        if(desiredTotal <= 0){
            return isB ? true:false;
        }
        for(int i = 1 ; i <= maxChoosableInteger; i++){
            if(!canIWin(maxChoosableInteger,desiredTotal - i,!isB)){
                 return true;
            }
        }
        return false;
    }



}
