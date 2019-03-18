
/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC200_NumberofIslands{

  public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) {
        start = s; end = e;
      }
  }

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

  public static void main(String[] args) {
    char[][] grid = {{'1','1','1','1','0'},{}};
    int ans = numIslands(grid);
    System.out.println(1 > Integer.MAX_VALUE);

    //System.out.println(ans );
  }

  //dirs
  static final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

  // Solution 0:   Time : O(N)
  public static int numIslands(char[][] grid) {
    //sanity check
    if(grid == null || grid.length == 0 || grid[0].length == 0){
      return 0;
    }
    int row = grid.length;
    int col = grid[0].length;
    int count = 0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if(grid[i][j] == '1'){
          isLandDFS(grid,0,0,row,col);
          count++;
        }
      }
    }
    return count;
  }

  public static void isLandDFS(char[][] grid, int x, int y,int row, int col){
    // recursive rule
    grid[x][y] = '2';
    for(int[] dir :dirs){
      int X = dir[0] + x;
      int Y = dir[1] + y;
      // base case
      if(X >= 0 && X < row && Y >= 0 && Y < col && grid[X][Y] != '2' && grid[X][Y] != '0'){
        isLandDFS(grid,X,Y,row,col);
      }
    }
  }

}