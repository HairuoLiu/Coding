
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to
 * adjacent numbers on the row below.
 *
 * For example, given the following triangle
 *
 * [ [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3] ] The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5
 * + 1 = 11).
 *
 * Note:
 *
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number
 * of rows in the triangle.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */


public class LC120_Triangle{

  public static void main(String[] args) {

    List<List<Integer>> a = new ArrayList<>();
    List<Integer> a0 = new ArrayList<>();
    List<Integer> a1 = new ArrayList<>();
    List<Integer> a2 = new ArrayList<>();
    List<Integer> a3 = new ArrayList<>();
    List<Integer> a4 = new ArrayList<>();
    a0.add(2);
    a1.add(3);
    a1.add(4);
    a2.add(6);
    a2.add(5);
    a2.add(7);
    a3.add(4);
    a3.add(1);
    a3.add(8);
    a3.add(3);
    a.add(a0);
    a.add(a1);
    a.add(a2);
    a.add(a3);
    //a.add(a4);

    //boolean ans1 = isInterleave(s1,s2,s3);
    int ans2 = minimumTotal(a);

    System.out.println(ans2);
  }

  //REC


  //REC + MEMO
  public static int[][] m;

  public static int minimumTotal(List<List<Integer>> triangle) {
    int row = triangle.size();
    int col = triangle.get(row-1).size();
    m = new int[row][col];
    for (int[] i : m) {
      Arrays.fill(i, Integer.MAX_VALUE);
    }

    return dfs(triangle, 0, 0);
  }

  public static int dfs(List<List<Integer>> triangle, int x, int y) {
    if (triangle.size() <= x || triangle.get(x).size() <= y) {
      return Integer.MAX_VALUE;
    }
    if (m[x][y] != Integer.MAX_VALUE) {
      return m[x][y];
    }
    List<Integer> cur = triangle.get(x);
    int ans = cur.get(y);
    if (triangle.size() - 1 == x) {
      m[x][y] = ans;
      return m[x][y];
    }

    int lr = Math.min(dfs(triangle, x + 1, y), dfs(triangle, x + 1, y + 1));

    if (lr != Integer.MAX_VALUE) {
      m[x][y] = ans + lr;
    }
    return m[x][y];
  }
}
