
/**
 *
 * Given a sorted array and a target value, return the index if the target is found.
 * If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC744_FindSmallestLetterGreaterThanTarget{

  public static void main(String[] args) {
    int [] letter = {1,3,5,6};
    int target = 7;
    int ans = searchInsert(letter,target);
    System.out.println(ans);
  }

  public static int searchInsert(int[] nums, int target) {
    //sanity check
    if(nums.length == 0 || target == 0){
      return 0;
    }

    int right = nums.length - 1, left = 0;
    while (left <= right){
      int mid = left + (right- left)/2;
      if(nums[mid] == target){
        return mid;
      }
      else if(nums[mid] > target){
        right = mid - 1;
      }else{
        left = mid + 1;
      }
    }
    return left;
  }

}
