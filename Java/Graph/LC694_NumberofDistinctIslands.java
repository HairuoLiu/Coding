
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
 *
 * Example 1:
 *
 * 11000
 * 11000
 * 00011
 * 00011
 * Given the above grid map, return 1.
 * Example 2:
 *
 * 11011
 * 10000
 * 00001
 * 11011
 * Given the above grid map, return 3.
 *
 * Notice that:
 * 11
 * 1
 * and
 *  1
 * 11
 * are considered different island shapes, because we do not consider reflection / rotation.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC694_NumberofDistinctIslands{


  static class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 };

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

  public static void main(String[] args) {
    int[][] n = {{1,1,0},{0,1,1},{0,0,0},{1,1,1},{0,1,0}};
    int num  = numDistinctIslands(n);

    System.out.println(num);

    //System.out.println(ans );
  }

  public static int numDistinctIslands(int[][] grid) {
    //sanity check
    if(grid == null || grid.length == 0 || grid[0].length == 0){
      return 0;
    }
    Set<String> set = new HashSet<>();
    for(int i = 0; i < grid.length; ++i){
      for(int j = 0; j < grid[0].length; ++j){
        if(grid[i][j] == 1){
          StringBuilder s = new StringBuilder("a");
          dfs(grid,i,j,s);
          set.add(s.toString());
        }
      }
    }
    return set.size();
  }

  public static final int[][] DIRS = {{1,0,1},{0,1,2},{-1,0,3},{0,-1,4}};
  public static void dfs(int[][] grid,int x,int y,StringBuilder s){
    grid[x][y] = 2;
    //visited
    for(int[] dir : DIRS){
      int nx = x + dir[0];
      int ny = y + dir[1];
      char d = (char)('a'+ dir[2]);
      if(nx >=0 && ny >=0 &&
          nx < grid.length && ny < grid[0].length &&
          grid[nx][ny] == 1){
        s.append(d);
        dfs(grid,nx,ny,s);
      }
    }
    s.append('z');
  }
}