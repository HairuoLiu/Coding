
/**
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * Example 1:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC33_SearchinRotatedSortedArray{

  public static void main(String[] args) {
    int[] letter = {3,5,1};
    int target = 1;
    int ans1 = searchBF(letter, target);
    int ans2 = searchBS(letter, target);

    System.out.println(ans1);
    System.out.println(ans2);

  }

  //Solution 1: BF     Time: O(n) Space: O(1)
  public static int searchBF(int[] nums, int target) {
    int ans = -1;
    //sanity check
    if(nums.length == 0){
      return ans;
    }

    int pointer = 0;
    while (pointer < nums.length){
      if (nums[pointer] == target){
          return pointer;
      }
      pointer++;
    }
    return ans;
  }

  //Solution 2: Binary Search
  public static int searchBS(int[] nums, int target) {
    //sanity check
    if (nums.length == 0) {
      return  -1;
    }
    int left = 0, right = nums.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if(nums[mid] == target){
        return mid;
      }else if(nums[mid] < nums[right]){      //    1 2 3 4 5
        if(nums[mid] > target){
          right = mid ;
        }else {
          left = mid ;
        }
      }else{
        if(nums[mid] < target){
          left = mid;
        }else{
          if(nums[right] > target){
            left = mid;
          }else{
            right = mid;
          }
        }
      }
    }
    if(left < nums.length  && nums[left] == target ){
      return left;
    }else if( right >= 0 && nums[right] == target){
      return right;
    }
    return -1;
  }
}
