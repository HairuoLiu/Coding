package LeetPlan;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC78_Subsets{

  public static void main(String[] args) {
    int[] n = {1,2,3};
    List<List<Integer>>  ans1 = subsetsBU(n,0);
    List<List<Integer>>  ans2 = subsetsBUplus(n);

    for(List<Integer> i :ans1){
      System.out.print(i);
    }
    for(List<Integer> i :ans2){
      System.out.print(i);
    }
  }

  //Differnt BF thinking
  //S1: 我们看待问题的方法是划分子问题
  //S1.1: 当我们解决了一个子问题之后我们应该看如何优化这个已经解决的问题

  //Solution 1:   Pure recursion (bottom up)
  //Benefit:  Easy to write
  //Weakness: Handle duplicate
  public static List<List<Integer>> subsetsBU(int[] nums,int level) {
    if(level == nums.length){
      List<Integer> tempList = new ArrayList<>();
      List<List<Integer>> res = new ArrayList<>();
      res.add(tempList);
      return res;
    }
    List<List<Integer>> oldres = subsetsBU(nums,level + 1);
    List<List<Integer>> res = new ArrayList<>();
    for(List<Integer> i:oldres){
      List<Integer> temp = new ArrayList<>(i);
      temp.add(nums[level]);
      res.add(temp);
    }
    res.addAll(oldres);
    return res;
  }

  //Solution 1.1:   Pure recursion (bottom up) -> DP
  //Benefit:  Easy to write
  //Weakness: More space
  public static List<List<Integer>> subsetsBUplus(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    //base case
    res.add(new ArrayList<>());
    for (int i = 0; i < nums.length; i++) {
      int size = res.size();
      for(int j = 0 ; j < size ; j++){
        List<Integer> temp = new ArrayList<>(res.get(j));
        temp.add(nums[i]);
        res.add(temp);
      }
    }
    return res;
  }

  //Solution  2:    DFS (top down)
  //我们每层要干什么? 每层有多少个状态? (搜索到当前层时所有可能的解:这里难道不应该是bfs所有可能吗)
  //从图上体现
  public static List<List<Integer>> subsetsDFS1(int[] nums){
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    subsetsDFS1helper(nums,0,temp,res);
    return res;
  }
  public static void subsetsDFS1helper(int[] nums, int level,List<Integer> temp,List<List<Integer>> res){
      if(nums.length == level){
        res.add(new ArrayList<>(temp));
        return;
      }
      //对于subsets有两个状态：加 或 不加 当前元素
      temp.add(nums[level]);
      subsetsDFS1helper(nums,level+1,temp,res);
      temp.remove(temp.size()-1);
      subsetsDFS1helper(nums,level+1,temp,res);
  }

  //Solution  2.1:    DFS位搜索
  //我们每层代表什么: 长度相等的所有可能性
  //每层有多少个状态需要尝试：每一层新增的状态根据上一层来决定，如果上一层打印到了nums[st], 则这一层新增的状态是从 st + 1 到 nums.length - 1
  public static List<List<Integer>> subsetsDFS2(int[] nums){
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    subsetsDFS2helper(nums,0,temp,res);
    return res;
  }
  //我们在构建subset的过程当中要避免出现重复，比如：'123'， '132'， '213'，'231'，'312'，'321'这几个都是相同的subset，
  //我们只应该让它在我们的构建方法中出现一次，否则会有重复
  //常见的Intuition是“如果我们在构建subset的过程中保持一定的顺序，那么上述这些可能出现的subset只会出现其中的唯一一个！'，
  //那么我们可以选用的方法是，在构建subset的过程中，每次新添加的元素保持递增的顺序，
  public static void subsetsDFS2helper(int[] nums, int level,List<Integer> temp,List<List<Integer>> res){
    res.add(new ArrayList<>(temp));
    //对于每个size为k的subset都是由一个size为k-1的subset加上一个元素得到的
    for (int i = level; i < nums.length; i++) {
      subsetsDFS2helper(nums,level+1,temp,res);
      temp.remove(temp.size() - 1);
    }
  }
}
