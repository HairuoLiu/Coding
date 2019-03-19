
/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position
 * of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8 Output: [3,4] Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6 Output: [-1,-1]
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC34_SearchforaRange{

  public static void main(String[] args) {
    int[] letter = {1,1,1};
    int target = 1;
    int[] ans1 = searchRangeBF(letter, target);
    int[] ans2 = searchRange(letter, target);

    int[] ans3 = searchRangeBS(letter, target);

    System.out.println(ans1[0]+ " | " + ans1[1]);
    System.out.println(ans2[0]+ " | " + ans2[1]);
    System.out.println(ans3[0]+ " | " + ans3[1]);


  }

  //Solution 1: BF
  public static int[] searchRangeBF(int[] nums, int target) {
    //sanity check
    if(nums.length == 0){
      return new int[]{-1, -1};
    }
    int min = -1,max = -1;
    int pointer = 0;
    while (pointer < nums.length){
      if (min == -1 && nums[pointer] == target){
          min = pointer;
      }
      max = nums[pointer] == target ? pointer: max;
      pointer++;
    }
    return new int[]{min,max};
  }

  //Solution 2: Binary Search
  public static int[] searchRange(int[] nums, int target) {
    if(nums == null || nums.length == 0){
      return new int[]{-1,-1};
    }
    int l = findL(nums,target);
    int r = findR(nums,target);
    return new int[]{l,r};
  }

  public static int findL(int[] nums, int target){
    int left = 0;
    int right = nums.length - 1;
    int res= -1;
    while(left <= right){
      int mid = left+ (right - left)/2;
      if(nums[mid] <= target){
        res = mid;
        left = mid + 1;
      }else{
        right = mid - 1;
      }
    }
    return res;
  }

  public static int findR(int[] nums, int target){
    int left = 0;
    int right = nums.length - 1;
    int res= -1;
    while(left <= right){
      int mid = left+ (right - left)/2;
      if(nums[mid] >= target){
        res = mid;
        right = mid - 1;
      }else{
        left = mid + 1;
      }
    }
    return res;
  }

  //
  public static int[] searchRangeBS(int[] nums, int target) {
    //sanity check
    if (nums.length == 0) {
      return new int[]{-1, -1};
    }
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < target) {
        left = mid + 1;
      }else{
        right = mid;
      }
    }
    int min = left;
    left = 0; right = nums.length - 1;
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] <= target) {
        left = mid;
      } else {
        right = mid;

      }
    }
    if (nums[min] != target) {
      return new int[]{-1,-1};
    }
    int max = -1;
    if(left > right){
       max = nums[left] == target ? left : right;
    }else{
      max = nums[right] == target ? right : left ;
    }

    return new int[]{min, max};
  }
}
