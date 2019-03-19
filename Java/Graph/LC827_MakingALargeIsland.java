
/**
 *
 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
 *
 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
 *
 * Example 1:
 *
 * Input: [[1, 0], [0, 1]]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 * Example 2:
 *
 * Input: [[1, 1], [1, 0]]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 * Example 3:
 *
 * Input: [[1, 1], [1, 1]]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 * Notes:
 *
 * 1 <= grid.length = grid[0].length <= 50.
 * 0 <= grid[i][j] <= 1.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC827_MakingALargeIsland{

  public static class Pair<A, B> {
    public int first;
    public int second;
    public Pair(int first, int second) {
      this.first = first;
      this.second = second;
    }
  }

  public static void main(String[] args) {
//    int[][] grid = {{1,1,1,1,0,1}, {0,0,0,1,0,1}, {0,1,0,0,0,1}, {0,1,1,1,1,1}};
    int[][] grid = {{0,0,0,0,0,0,0},{0,1,1,1,1,0,0},{0,1,0,0,1,0,0},{1,0,1,0,1,0,0},{0,1,0,0,1,0,0},{0,1,0,0,1,0,0},{0,1,1,1,1,0,0}};

    int ans2 = largestIslandBFS(grid);
    int ans = largestIslandDFS(grid);
    System.out.println(ans + " / " + ans2);
  }

  //Diraction
  public static int[][] dirs ={{1,0},{0,1},{-1,0},{0,-1}};

  //BFS打印路径模板
  public static int largestIslandBFS(int[][] grid) {
    int[] res = new int[]{Integer.MAX_VALUE};
    int row = grid.length, col = grid[0].length;
    return 0;
  }

  //DFS
  public static final int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};

  public static int largestIslandDFS(int[][] grid) {
    if(grid == null || grid.length == 0 || grid[0].length == 0 ){
      return 0;
    }
    int row = grid.length;
    int col = grid[0].length;
    int[] res = {0};
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        res[0] = DFS(grid,res,1,i,j,true);
      }
    }
    return res[0];
  }

  public static int DFS(int[][] grid,int[] res,int size,int x,int y,boolean change){
    if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || (!change && grid[x][y] == 0)||grid[x][y] == -1){
      return size;
    }
    res[0] = size > res[0] ? size : res[0];
    int temp = grid[x][y];
    int max = 0;
    grid[x][y] = -1;
    for(int[] i:dir){
      int newX = x + i[0];
      int newY = y + i[1];
      max = Math.max(DFS(grid,res,size + 1,newX,newY,temp == 0 ? false:change),max);
    }
    grid[x][y] = temp;
    return max;
  }


  //DFS + BFS打印路径模板 (走长度层)
}
