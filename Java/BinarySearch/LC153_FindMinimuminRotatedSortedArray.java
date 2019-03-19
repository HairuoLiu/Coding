import test.PracticeClass17.A;

/**
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 *
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 *
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 *
 * Input: [3,4,5,1,2]
 * Output: 1
 * Example 2:
 *
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC153_FindMinimuminRotatedSortedArray{

  public static void main(String[] args) {
    int[] letter = {4,5,6,7,0,1,2};
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
      }else{
        left = mid + 1;
      }
    }
    return nums[left];
  }

}

