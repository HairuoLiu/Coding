package LeetPlan.BinarySearch.Plan1;


/**
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 *
 * You are given a target value to search. If found in the array return true, otherwise return false.
 *
 * Example 1:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 *
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC81_SearchinRotatedSortedArrayII{

  public static void main(String[] args) {
    int[] letter = {2,5,6,0,0,1,2};
    int target = 1;
    boolean ans1 = searchBF(letter, target);
    boolean ans2 = searchBS(letter, target);

    System.out.println(ans1);
    System.out.println(ans2);

  }

  //Solution 1: BF     Time: O(n) Space: O(1)
  public static boolean searchBF(int[] nums, int target) {
    boolean ans = false;
    for (int i = 0; i < nums.length; i++) {
      if(nums[i] == target){
        return true;
      }
    }
    return ans;
  }

  //Solution 2: Binary Search
  public static boolean  searchBS(int[] nums, int target) {
    //sanity check
    int left = 0, right = nums.length - 1;
    while(left < right){
      int mid = left+ (right-left)/2;
      if(nums[mid] == target){
        return true;
      }
      if(nums[mid] < nums[right]){
          
      }else if(nums[mid] > nums[right]){

      }else{
        right--;
      }
    }
    return false;
  }
}
