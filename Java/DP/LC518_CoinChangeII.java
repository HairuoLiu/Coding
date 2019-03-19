
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 *
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that amount.
 * You may assume that you have infinite number of each kind of coin.
 *
 * Note: You can assume that
 *
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 * Example 1:
 *
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC518_CoinChangeII{

  //{2,5,10,1}27 -> 4
  public static void main(String[] args) {
    int[] coin = { 1,2, 5};

    int amount = 100;
    int ans = change(amount,coin);

//    int ans = coinChangeDFS(coin, amount);
//    int ans = coinChangeBFS(coin, amount);
    System.out.println(ans);
  }


  //DP
  public static int change(int amount, int[] coins) {
    if(amount == 0){
      return 1;
    }
    int[] m = new int[amount+1];
    m[0] = 1;
    for (int i = 1; i <= coins.length; i++) {
      for (int j = 0; j <= amount; j++) {
        if(coins[i-1] <= j){
          m[j] += m[j - coins[i - 1]];
        }
      }
    }
    return m[amount];
  }



}
