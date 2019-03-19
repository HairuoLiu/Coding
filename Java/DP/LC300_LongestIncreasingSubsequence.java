
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC300_LongestIncreasingSubsequence {

  public static void main(String[] args) {
    int [] nums = {-2,-1};
    System.out.println(lengthOfLIS(nums));
  }

  public static int lengthOfLIS(int[] nums) {
    if(nums == null || nums.length == 0){
      return 0;
    }
    int max = 1;
    int m[] = new int[nums.length];
    m[0] = 1;
    for(int i = 0; i < nums.length;i++){
      m[i] = 1;
      for(int j = 0; j < i; j++){
        if(nums[j] < nums[i]){
          m[i] = Math.max(m[j] + 1,m[i]);
        }
      }
      max = Math.max(max,m[i]);
    }
    return max;
  }

}
