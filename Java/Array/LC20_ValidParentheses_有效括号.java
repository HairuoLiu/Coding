import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 * Input: "()"
 * Output: true
 * Example 2:
 *
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: "(]"
 * Output: false
 * Example 4:
 *
 * Input: "([)]"
 * Output: false
 * Example 5:
 *
 * Input: "{[]}"
 * Output: true
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC20_ValidParentheses_有效括号{

  public static void main(String[] args) {

    //System.out.println(ans + " / " + ans2);
  }

  public static boolean isValid(String s) {
    //sanity check
    if(s.length() == 0){
      return true;
    }
    Deque<Character> stack = new ArrayDeque<>();
    for(int i = 0; i < s.length(); i++){
      char c = s.charAt(i);
      if(c== '{' || c == '(' || c == '['){
        stack.offerFirst(c == '{' ? '}':  c == '(' ? ')':']');
      }else if(c== '}' || c == ')' || c == ']'){
        if(stack.isEmpty() || stack.poll() != c){
          return false;
        }
      }
    }
    return stack.isEmpty();
  }
}
