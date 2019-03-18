
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC131_palindrome_partitioning{

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

  public static void main(String[] args) {
  String s ="aab";
    List<List<String>> res = partition(s);
    System.out.println(res);
  }

  public static List<List<String>> partition(String s) {
    List<List<String>> res = new ArrayList<>();
    if(s == null || s.length() == 0) return res;
    char[ ] str = s.toCharArray();
    backT(str, 0, new ArrayList<>(),res);
    return res;
  }

  public static void backT(char[ ] str,int index, List<String> list, List<List<String>> res){
    if(index == str.length){

      res.add(new ArrayList<>(list));
      return;
    }
    for(int i = index; i < str.length; ++i){
      if(isPalindrome(str,index,i)){
        StringBuilder s = new StringBuilder();
        for(int j = index; j <= i; ++j){
          s.append(str[j]);
        }
        list.add(s.toString());
        backT(str,i+1,list,res);
        list.remove(list.size() - 1);
      }
    }

  }

  public static boolean isPalindrome(char[] str,int i,int r){
    while(i < r){
      if(str[i] != str[r]){
        return false;
      }
      i++;r--;
    }
    return true;
  }

}