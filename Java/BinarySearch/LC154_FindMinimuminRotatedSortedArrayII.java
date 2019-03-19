
/**
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * The array may contain duplicates.
 *
 * Example 1:
 *
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 *
 * Input: [2,2,2,0,1]
 * Output: 0
 * Note:
 *
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC154_FindMinimuminRotatedSortedArrayII {

  public static void main(String[] args) {
    int[] letter = {2,0,1,1,1};
    int ans1 = findMinBF(letter);
    int ans2 = findMinBS(letter);

    System.out.println(ans1);
    System.out.println(ans2);

  }

  //Solution 0: BF    Time: O(n) Space O(1)
  public static int findMinBF(int[] nums) {
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      min = Math.min(min,nums[i]);
    }
    return min;
  }

  //Solution 1: Binary Search   Time: O(logn) Space O(1)
  public static int findMinBS(int[] nums) {
    int left = 0,right = nums.length - 1;
    while (left + 1 < right){
      int mid = left + (right - left)/2;
      if(nums[mid] < nums[right]){
        right = mid;
      }else if(nums[mid] > nums[right]){
        left = mid;
      }else{
        right--;
      }
    }
    return Math.min(nums[left],nums[right]);
  }

}

