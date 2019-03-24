
/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

 * You may assume no duplicates in the array.
 * Example 1:
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
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

public class LC35_SearchInsertPosition{

  public static void main(String[] args) {
    char[] letter = {'c', 'f', 'j'};
    char target = 'k';
    char ans = nextGreatestLetter(letter,target);
    System.out.println(ans);
  }
  
  //
  public int searchInsert(int[] nums, int target) {
    //sanity check
    if(nums == null || nums.length == 0) return 0;
    int left = 0, right = nums.length - 1 ;
    int res = -1;
    while(left <= right){
        int mid = left + (right - left) / 2;
        if(nums[mid] >= target){
            right = mid - 1;
            res = mid;
        }else{
            left = mid + 1;
        }
    }
    return res == -1 ? nums.length : res;
  }

  //变形题 
  public static char nextGreatestLetter(char[] letters, char target) {
      int left = 0, right = letters.length - 1;
      //sanity cheak
      if(right <= 0){
        return ' ';
      }
      while (left <= right){
        int mid = left + (right - left) / 2;
        if(letters[mid]  <= target){
          left = mid + 1;
        }else if(letters[mid] > target  ){
          right = mid-1;
        }
      }
      if(left == letters.length  ){
        return letters[0];
      }
      return letters[left];
  }

}
