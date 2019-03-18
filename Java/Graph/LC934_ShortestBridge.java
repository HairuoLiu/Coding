
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 *
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 *
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 *
 *
 * Example 1:
 *
 * Input: [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 *
 * Input: [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 *
 * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC934_ShortestBridge{

  public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) {
        start = s; end = e;
      }
  }

  public static void main(String[] args) {
    int [][] grid = {{1,0},{0,1}};
    System.out.println(shortestBridgeDi_DFS(grid));
  }

  private static final int[][] DIRS = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };


  //双端K_BFS
  public static int shortestBridgeDi_DFS(int[][] A) {
    if (A == null || A.length == 0 || A[0].length == 0){
      return 0;
    }
    int m = A.length, n = A[0].length;
    Set<String> queue1 = new HashSet<>();
    Set<String> queue2 = new HashSet<>();
    boolean[][] visit = new boolean[m][n];
    int stop = 0;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (A[i][j] == 1 && visit[i][j] == false) {
          if(stop == 0){
            dfs(i, j, m, n, A, visit, queue1);
            ++stop;
          }else if(stop == 1){
            dfs(i, j, m, n, A, visit, queue2);
            ++stop;
            break;
          }
        }
      }
      if (stop == 2){
        break;

      }
    }
    int level = 0;
    while (!queue1.isEmpty() && !queue2.isEmpty()){
      Set<String> nextLevel;
      if(queue1.size() > queue2.size()){
        nextLevel = queue2;
        queue2 = queue1;
        queue1 = nextLevel;
      }
      nextLevel = new HashSet<>();
      for (String c : queue1) {
        String[] curS = c.split(",");
        int [] cur = new int[]{Integer.parseInt(curS[0]), Integer.parseInt(curS[1])};
        for (int[] dir : DIRS) {
          int row = cur[0] + dir[0];
          int col = cur[1] + dir[1];
          if(queue2.contains(new String(row+","+ col))){
            return level;
          }
          if (valid(row, col, m, n, visit)) {
            nextLevel.add(new String(row+","+ col));
            visit[row][col] = true;
          }
        }
      }
      queue1 = nextLevel;
      level++;
    }
    return -1;
  }
  private static boolean valid(int row, int col, int m, int n, boolean[][] visit) {
    return row >= 0 && row < m && col >= 0 && col < n && !visit[row][col];
  }
  private static void dfs(int row, int col, int m, int n, int[][] A, boolean[][] visit,Set<String> queue) {
    queue.add(new String(row+","+col));
    visit[row][col] = true;
    for (int[] dir : DIRS) {
      int nextRow = row + dir[0];
      int nextCol = col + dir[1];
      if (valid(nextRow, nextCol, m, n, visit)) {
        if (A[nextRow][nextCol] == 1) {
          dfs(nextRow, nextCol, m, n, A, visit, queue);
        }
      }
    }
  }

}