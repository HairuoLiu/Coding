
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 *
 *
 * For example, given a 3-ary tree:
 *
 *
 *
 *
 * Return its preorder traversal as: [1,3,5,6,2,4].
 *
 *
 * Note: Recursive solution is trivial, could you do it iteratively?
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC589_NaryTreePreorderTraversal{

  static class Node {

    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }
  public static void main(String[] args) {
    Node root = new Node(1,null);
    List<Integer> ans = preorder(root);
    System.out.println(ans);

  }

  //O(N)
  public static List<Integer> preorder(Node root) {
    List<Integer> list = new ArrayList<>();
    helper(list,root);
    return list;
  }

  public static void helper(List<Integer> list,Node root){
    if(root == null){
      return;
    }
    list.add(root.val);
    for(Node i : root.children){
      helper(list,i);
    }
  }
}
