
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ].
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC47_PermutationsII{

  public static void main(String[] args) {

   int [] nums = {1,1,2};
    System.out.println(permuteUnique(nums));
  }

  //Dirs
  public static List<List<Integer>> permuteUnique(int[] nums) {

    List<List<Integer>> res = new ArrayList<>();
    if(nums == null || nums.length == 0){
      return res;
    }
    dfshelper(res,0,nums);
    return res;
  }
  public static void dfshelper(List<List<Integer>> res ,int index,int[] nums){
    //base case
    if(index == nums.length - 1){
      List<Integer> temp = new ArrayList<>();
      for (int num: nums) { temp.add(num); }
      res.add(temp);
      return;
    }
    Set<Integer> set = new HashSet<Integer>();
    for(int i = index + 1; i < nums.length; ++i){
      if(set.add(nums[i])){
        swap(nums,index,i);
        dfshelper(res,index + 1,nums);
        swap(nums,index,i);
      }
    }

  }

  public static void swap(int[] nums, int i ,int j){
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

}