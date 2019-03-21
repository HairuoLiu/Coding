
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC112_PathSum {

  public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
  public static void main(String[] args) {
    ListNode root = new ListNode(0);
    ListNode ans = hasPathSum(root,0);
    System.out.println(ans);

  }

  //O(N)
  public boolean hasPathSum(TreeNode root, int sum) {
    //sanity check
    if(root == null){
      return false;
    }
    returen pathSum(root, 0, sum);
  }

  public boolean pathSum(TreeNode root, int cur, int sum){
    if(root == null){
      return cur == sum;
    }
    if(pathSum(root.left, cur + root.val, sum) || pathSum(root.right, cur + root.val, sum)){
      return true;
    }
    return false;
  }

  //切记 sum == 0 时候 root == null 不能判断
  //必须要把base case改好
  public boolean hasPathSum(TreeNode root, int sum) {
    if(root == null) return false;
    if(root.left == null && root.right == null && root.val == sum){
      return true;
    }
    return hasPathSum(root.left, sum - root.val)|| hasPathSum(root.right, sum - root.val);
  }
}
