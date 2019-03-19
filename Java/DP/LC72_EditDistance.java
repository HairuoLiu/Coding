
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 *
 * You have the following 3 operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC72_EditDistance {

  //"intention" "horse"
  //"execution" "ros"
  public static void main(String[] args) {
    String word1 = "intention";
    String word2 = "execution";

    int ans = minDistance(word1,word2);
    int ans2 = minDistanceDP(word1,word2);
    System.out.println(ans + " / " + ans2);
  }

  //Assumtion BF Time:
  public static int minDistance(String word1, String word2) {
      return 0;
  }

  //DP Space O(M*N) Time O(M*N)
  public static int minDistanceDP(String word1, String word2) {
    int len1 = word1.length(), len2 = word2.length();
    //corner case
    if(len1 == 0 || len2 ==0){
      return len1 == 0 ? len2: len1;
    }
    //M use to record
    int[][] m = new int [len1 + 1][len2 + 1];
    for(int i = 1; i <=len2;i++){
      m[0][i] = i;
    }
    for(int i = 1; i <=len1;i++){
      m[i][0] = i;
    }

    for (int i = 1; i <= len1; i++){
      for (int j = 1; j <= len2; j++) {
        if(word1.charAt(i-1) == word2.charAt(j-1)){
          m[i][j] = m[i-1][j-1];
        }else {
          m[i][j] = Math.min(m[i-1][j],m[i][j-1]) + 1;
        }
      }
    }
    return m[len1][len2];
  }

  //DP Space O(M + N) Time O(M * N) 最长公共子序列
}

