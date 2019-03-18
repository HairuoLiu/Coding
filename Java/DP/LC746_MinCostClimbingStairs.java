

/**
 *
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
 *
 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor,
 * and you can either start from the step with index 0, or the step with index 1.
 *
 * Example 1:
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 * Example 2:
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 * Note:
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999]
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC746_MinCostClimbingStairs {

  public static void main(String[] args) {
    int[] n = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
    int result =  minCostClimbingStairs(n);
    System.out.println("result的值是：" + result);

  }

  public static int minCostClimbingStairs(int[] cost) {
    int len = cost.length;
    if(len == 0){
      return 0;
    }
    int firststep = cost[0];
    if(len == 1 ){
      return 1;
    }
    int secondstep = cost[1], i = 2;
    while(i < len){
      int temp = Math.min(firststep,secondstep);
      firststep = secondstep;
      secondstep = temp + cost[i];
      i++;
    }
    return secondstep > firststep ? firststep:secondstep;
  }

}
