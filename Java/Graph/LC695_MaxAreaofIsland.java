
/**
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 *
 * Example 1:
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
 * Example 2:
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * Note: The length of each dimension in the given grid does not exceed 50.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC695_MaxAreaofIsland{

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
    int [][] grid = {
        {0,0,1,0,0,0,0,1,0,0,0,0,0},
        {0,0,1,0,0,0,0,1,0,0,0,0,0},
        {0,0,1,0,0,0,0,1,0,0,0,0,0},
        {0,0,1,0,0,0,0,1,0,0,0,0,0}};
    System.out.println(maxAreaOfIsland(grid));
  }

  public static int maxAreaOfIsland(int[][] grid) {
    if(grid == null || grid.length == 0 || grid[0].length == 0){
      return 0;
    }
    int row = grid.length, col = grid[0].length;
    int max = 0;
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col ; j++){
        int[] temp = {0};
        if(grid[i][j] == 1){
          dfs(grid,i,j,row,col,temp);
          max = Math.max(max,temp[0]);
        }
      }
    }
    return max;
  }

  public static final int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

  public static void dfs(int[][] grid,int x,int y,int row,int col,int[] max){
    max[0]++;
    grid[x][y] = 2;
    for(int[] dir:dirs){
      int newX = dir[0] + x;
      int newY = dir[1] + y;
      if(newX >= 0 && newX < row && newY >= 0 && newY < col && grid[newX][newY] == 1){
        dfs(grid,newX,newY,row,col,max);
      }
    }
  }

}