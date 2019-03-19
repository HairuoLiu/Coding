
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * Example:
 *
 * Input: [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2.
 *     Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Note:
 *
 * You can assume that you can always reach the last index.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC45_JumpGameII {

  public static void main(String[] args) {
    int [] nums = {0,1,2,3,4,8,9,11};
    System.out.println( jump(nums));
  }

  public static int jump(int[] nums) {
    int[] m = new int[nums.length];
    Arrays.fill(m,Integer.MAX_VALUE);
    m[0] = 0;
    for(int i = 0; i < nums.length; ++i){
      for(int j = i-1; j >= 0; --j){
        if(nums[j] + j > i){
          m[i] = Math.min(m[i], m[j]+1);
        }
      }

    }
    return m[nums.length-1];
  }


  public static int jumpG(int[] nums) {
    int max = 0;
    int curEnd = 0;
    int curFarthest = 0;
    for(int i = 0; i < nums.length - 1; ++i){ //注意这里是nums - 1
      curFarthest = Math.max(curFarthest,i+nums[i]);
      if(i >= curEnd){
          max++;
          curEnd = curFarthest;
      }
    }

    return max;
  }

}
