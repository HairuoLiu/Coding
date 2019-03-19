
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */


//Time O(N);


public class LC22_GenerateParentheses_输出所有有效括号{

  public static void main(String[] args) {
    int n = 3;
    List<String> ans1 = generateParenthesis(n);
    //boolean ans2 = isValid(num);

    System.out.println(ans1 + " / " );
  }

  public static List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList();
    //sanity check
    if(n == 0){
      return res;
    }
    StringBuilder temp = new StringBuilder();
    DFS(n,0,res,temp,0,0);
    return res;
  }

  // Time: O(2^N)
  public static void DFS(int n, int level, List<String> res, StringBuilder temp,int left,int right){
    //base case
    if( 2 * n == level){
      if(left == right){
        res.add(new String(temp));
      }
      return;
    }
    //recursive rule
    if(left == right){
      DFS(n,level+1,res,temp.append('('),left+1,right);
      temp.deleteCharAt(temp.length()-1);
    }else if(left > right){
      DFS(n,level+1,res,temp.append(')'),left,right+1);
      temp.deleteCharAt(temp.length()-1);
      DFS(n,level+1,res,temp.append('('),left+1,right);
      temp.deleteCharAt(temp.length()-1);
    }
  }

  //附加题
  public List<String> removeInvalidParentheses(String s) {
    List<String> res = new LinkedList<>();
    //sanity
    if(s == null){
      return res;
    }
    int n = s.length();
    int l = 0;
    int r = 0;
    //求出需要移除的左右括号数目，即为不规范的( 和 )数目
    for(int i = 0; i < s.length(); i++){
      if(s.charAt(i) == '('){
        l++;
      }
      if(s.charAt(i) == ')'){
        if(l > 0){
          l--;
        }
        else{
          r++;
        }
      }
    }
    DFSplus(new StringBuilder(s), 0, l, r, res);
    return res;
  }

  private void DFSplus(StringBuilder sb, int index, int l, int r, List<String> results){
    if(l == 0 && r == 0){
      if(isValid(sb)){
        results.add(sb.toString());
      }
      return;
    }

    for(int i = index; i < sb.length(); i++){
      if(i > index && sb.charAt(i) == sb.charAt(i - 1)){
        continue;
      }
      if(sb.charAt(i) == '(' && l > 0){
        sb.deleteCharAt(i);
        DFSplus(sb, i, l - 1, r, results);
        sb.insert(i, '(');
      }
      if(sb.charAt(i) == ')' && r > 0){
        sb.deleteCharAt(i);
        DFSplus(sb, i, l, r - 1, results);
        sb.insert(i, ')');
      }
    }
  }

  private boolean isValid(StringBuilder sb){
    int delta = 0;
    for(int i = 0; i < sb.length(); i++){
      if(sb.charAt(i) == '('){
        delta++;
      }
      if(sb.charAt(i) == ')'){
        delta--;
      }
      if(delta < 0){
        return false;
      }
    }
    return delta == 0;
  }


}
