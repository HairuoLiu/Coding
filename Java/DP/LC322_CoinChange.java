
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

/**
 * You are given coins of different denominations and a total amount of money amount. Write a
 * function to compute the fewest number of coins that you need to make up that amount. If that
 * amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 *
 * Input: coins = [1, 2, 5], amount = 11 Output: 3 Explanation: 11 = 5 + 5 + 1 Example 2:
 *
 * Input: coins = [2], amount = 3 Output: -1 Note: You may assume that you have an infinite number
 * of each kind of coin.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC322_CoinChange{

  //{2,5,10,1}27->4 [186,419,83,408]6249->20
  public static void main(String[] args) {
    int[] coin = {1,2,5};

    int amount = 11;
    int ans = coinChangeDP(coin, amount);

//    int ans = coinChangeDFS(coin, amount);
//    int ans = coinChangeBFS(coin, amount);
    System.out.println(ans);
  }

  //回头看

  //DP 技巧 m[i] = Integer.MAX_VALUE-1; 因为比较 min 是时候如果MAX_VALUE加1会溢出
  public static int coinChangeDP(int[] coins, int amount) {
    //santiy check
    if (coins.length == 0 || amount == 0) {
      return 0;
    }

    Arrays.sort(coins);
    int[] m = new int[amount + 1];
    for (int i = 1; i <= amount; i++) {
      m[i] = Integer.MAX_VALUE-1;
    }
    m[0]=0;
    for (int i = 0; i < coins.length; i++) {
      for (int j = 0; j <= amount; j++) {
        if (j >= coins[i]) {
          m[j] = Math.min(m[j], m[j - coins[i]]+1);
        }
      }
    }
    return m[amount] == Integer.MAX_VALUE-1 ? -1 : m[amount];
  }

  //BFS打印路径模板
  public static int coinChangeBFS(int[] coins, int amount) {
    if (amount == 0) {
      return -1;
    }
    Deque<Integer> queue = new ArrayDeque<>();
    int step = 1;
    for (int i : coins) {
      if (i == amount) {
        return step;
      }
      queue.offerFirst(i);
    }
    while (!queue.isEmpty()) {
      int size = queue.size();
      step++;
      for (int i = 0; i < size; i++) {
        int cur = queue.pollLast();
        for (int coin : coins) {
          if (coin + cur == amount) {
            return step;
          }
          if (coin + cur < amount) {
            queue.offerFirst(coin + cur);
          }
        }
      }
    }
    return -1;
  }

  //DFS
  public static int coinChangeDFS(int[] coins, int amount) {
    //santiy check
    if (coins.length == 0 || amount == 0) {
      return 0;
    }
    int[] min = new int[]{Integer.MAX_VALUE};
    DFS(coins, amount, min, 0, 0);
    return min[0] == Integer.MAX_VALUE ? -1 : min[0];
  }

  public static void DFS(int[] coins, int amount, int[] min, int temp, int step) {
    if (temp > amount || min[0] < step) {
      return;
    }
    if (temp == amount) {
      min[0] = Math.min(min[0], step);
      return;
    }
    for (int i = coins.length - 1; i >= 0; i--) {
      DFS(coins, amount, min, temp + coins[i], step + 1);
    }
  }

}
