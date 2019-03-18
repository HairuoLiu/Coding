
/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 * Input: 2 Output: 2 Explanation: There are two ways to climb to the top. 1. 1 step + 1 step 2. 2
 * steps Example 2:
 *
 * Input: 3 Output: 3 Explanation: There are three ways to climb to the top. 1. 1 step + 1 step + 1
 * step 2. 1 step + 2 steps 3. 2 steps + 1 step
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC70_ClimbingStairs {

  public static void main(String[] args) {
    int n = 6;
    int result = climbStairs(n);
    System.out.println("result的值是：" + result);

  }

  public static int climbStairs(int n) {
    int firstStep = 1;
    if(n == 1){
      return firstStep;
    }
    int secondStep = 2;
    while( n - 2 > 0){
      int temp = firstStep;
      firstStep = secondStep;
      secondStep = temp + firstStep;
      n--;
    }
    return secondStep;
  }

}
