
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 *
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: true
 * Example 2:
 *
 * Input: A = "ab", B = "ab"
 * Output: false
 * Example 3:
 *
 * Input: A = "aa", B = "aa"
 * Output: true
 * Example 4:
 *
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 * Example 5:
 *
 * Input: A = "", B = "aa"
 * Output: false
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC859_BuddyStrings{

    public static void main(String[] args) {
       String a ="abc";
       String b ="abc";
        boolean result = buddyStrings(a,b);
        System.out.println("result的值是：" + result);
    }

    public static boolean buddyStrings(String A, String B) {
        if(A.length() != B.length()){
            return false;
        }
        if(A.equals(B)){
            Set<Character> set =new HashSet<>();
            for(char i : A.toCharArray()){
                set.add(i);
            }
            return set.size() < A.length();
        }
        char AF='-',BF='+',AS='?',BS='/';
        int diff = 0;
        for(int i = 0; i < A.length(); ++i){
            if(diff >= 2){
                return false;
            }
            if(diff == 0 && A.charAt(i) != B.charAt(i)){
                AF = A.charAt(i);
                BF = B.charAt(i);
                diff++;
            }else if(diff ==1 && A.charAt(i) != B.charAt(i)){
                AS = A.charAt(i);
                BS = B.charAt(i);
                diff++;
            }
        }
        return AF == BS && AS == BF;
    }



}
