
import java.util.ArrayList;
import java.util.List;

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

public class LC113_PathSumI{

  public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
  public static void main(String[] args) {
    ListNode root = new ListNode(0);
    ListNode ans = removeElements(root,0);
    System.out.println(ans);

  }

  //O(N)
public List<List<Integer>> pathSum(TreeNode root, int sum) {
	List<List<Integer>> res = new ArrayList<>();
	//sanity check
	if(root == null){
		return res;
	}
  pathTraversal(res, new ArrayList<>(),root, sum,0);
	return res;
}

  public void pathTraversal(List<List<Integer>> res, List<Integer> curPath, TreeNode root, int sum, int curSum ){
    //base case 1 return nothing
    if(root == null){
      return;
    }
    //base case 2 touch the leaf and check if the sum is equal to
    if(root.left == null && root.right == null){
      if(sum == curSum + root.val){
        curPath.add(root.val);
	res.add(new ArrayList<>(curPath));
	curPath.remove(curPath.size() - 1);
      }
      return;
    }
    //induction rule
    curPath.add(root.val);
    pathTraversal(res, curPath, root.right, sum, curSum + root.val);
    pathTraversal(res, curPath, root.left, sum, curSum + root.val);
    curPath.remove(curPath.size() - 1);
  }
}
