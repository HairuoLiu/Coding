
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * Example 1:
 *
 * Input: [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum
 *              jump length is 0, which makes it impossible to reach the last index.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC55_JumpGame{

  public static void main(String[] args) {
    int [] nums = {0,1,2,3,4,8,9,11};
    System.out.println(canJump(nums));
  }

  //DP use m[] to check if cur is vaild
  public static boolean canJump(int[] nums) {
    boolean [] m = new boolean[nums.length];
    m[0] = true;
    for(int i = 0 ; i < nums.length; i++){
      if(m[i]){
        int jump = nums[i];
        for(int j = 0 ; j + i < nums.length && j <= jump; j++){
          m[i+j] = true;
        }
      }
    }
    return m[nums.length -1];
  }


  //Greedy 正向
  //如果
  public static boolean canJumpG(int[] nums) {
    int max = 0;
    for(int i = 0  ; i <nums.length ;++i){
      if(i > max){
        return false;
      }
      max = Math.max(max,i + nums[i]);
    }
    return true;
  }


  //Greedy 反向
  //如果
  public static boolean canJumpGN(int[] nums) {
    int l = nums.length -1;
    for(int i = nums.length - 1; i >= 0 ;--i){
      if(nums[i] + i == l){
        l = i;
      }
    }
    return l == 0;
  }

}
