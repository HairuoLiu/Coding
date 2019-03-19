
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid
 * for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each
 * transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and
 * Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be
 * represented as [[0, 1, 10], [2, 0, 5]].
 *
 * Given a list of transactions between a group of people, return the minimum number of transactions
 * required to settle the debt.
 *
 * Note:
 *
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0. Person's IDs may not
 * be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 * Example 1:
 *
 * Input: [[0,1,10], [2,0,5]]
 *
 * Output: 2
 *
 * Explanation: Person #0 gave person #1 $10. Person #2 gave person #0 $5.
 *
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5
 * each. Example 2:
 *
 * Input: [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 *
 * Output: 1
 *
 * Explanation: Person #0 gave person #1 $10. Person #1 gave person #0 $1. Person #1 gave person #2
 * $5. Person #2 gave person #0 $5.
 *
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC465_OptimalAccountBalancing_收支平衡{

  public static void main(String[] args) {
    int[][] m = {{0, 3, 1}, {1, 3, 1}, {2, 3, 1}, {3, 4, 3}};
    int[][] m1 = {{0, 1, 10}, {1, 0, 1}, {1, 2, 5}, {2, 0, 5}, {2, 3, 5}, {3, 0, 5}};

    int res = minTransfers(m1);
    System.out.println(res);
  }

  public static int minTransfers(int[][] trans) {
    int[] res = {0};
    Map<Integer, Integer> net = new HashMap<>();
    for (int i = 0; i < trans.length; i++) {
      net.put(trans[i][0], net.getOrDefault(trans[i][0], 0) - trans[i][2]);
      net.put(trans[i][1], net.getOrDefault(trans[i][1], 0) + trans[i][2]);
    }
    res[0] = trans.length;
    List<Integer> pos = new ArrayList<Integer>();
    List<Integer> neg = new ArrayList<Integer>();

    for (int val : net.values()) {
      //把净值分类
      if (val > 0) {
        pos.add(val);
      }
      if (val < 0) {
        neg.add(-val);
      }
    }
    backtrack(pos, neg, 0,res);
    return res[0];
  }

  private static void backtrack(List<Integer> pos, List<Integer> neg, int p,int[] res) {    //p是有多少次交易的意思
    //base case
    if (pos.size() == 0 && neg.size() == 0) {
      res[0] = Math.min(res[0], p);
      return;
    }
    //每次都重复取size 因为neg和pos会改变
    int n1 = pos.size();
    int n2 = neg.size();

    //剪枝
    if (n1 + p >= res[0] || n2 + p >= res[0]) {
      return;
    }
    /**每层用每个pos尝试去付每个neg，尝试i*j种组合
     * 时间复杂度： n(n)* (n)(n) ...
     * ==> n^n * n^n   **/
    for (int i = 0; i < pos.size(); i++) {
      int posNum = pos.remove(i);
      for (int j = 0; j < neg.size(); j++) {
        int negNum = neg.remove(j);
        //移除要算的两笔账先 每次至少消掉一个人。
        if (posNum > negNum) {
          pos.add(posNum - negNum);
          backtrack(pos, neg, p + 1,res);//pos元素增多1个
          pos.remove(pos.size() - 1);

        } else if (posNum < negNum) {
          neg.add(negNum - posNum);//neg元素增多1个
          backtrack(pos, neg, p + 1,res);
          neg.remove(neg.size() - 1);

        } else {
          backtrack(pos, neg, p + 1,res);
          //如果是==0 这种情况 pos和neg就各减了一个元素 这样是最好的情况
        }
        neg.add(j, negNum);
      }
      pos.add(i, posNum);
    }
  }


}
