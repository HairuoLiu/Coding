
/**
 *
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
 *
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.
 *
 * Example:
 *
 * Input:
 * [
 *   [0,1,0],
 *   [0,0,1],
 *   [1,1,1],
 *   [0,0,0]
 * ]
 * Output:
 * [
 *   [0,0,0],
 *   [1,0,1],
 *   [0,1,1],
 *   [0,1,0]
 * ]
 * Follow up:
 *
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC289_GameofLife{

  public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) {
        start = s; end = e;
      }
  }

  public static void main(String[] args) {
    int [][] grid = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
    gameOfLife(grid);
    for(int[] i:grid){
      for(int j : i){
        System.out.print(j + " ");

      }
      System.out.println();
    }

    //System.out.println(ans );
  }

  //dirs
  static final int[][] DIRS = {{1,-1},{1,0},{1,1},{0,-1},{0,1},{-1,-1},{-1,0},{-1,1}};

  // Solution 0:   Time : O(N)
  public static void gameOfLife(int[][] board) {
    if(board == null || board.length == 0 ||board[0].length == 0){
      return;
    }
    int row = board.length;
    int col = board[0].length;
    int[][] res = new int [row][col];
    for(int i = 0; i < row ;++i){
      for(int j = 0; j < col ;++j){
        res[i][j] = dfs(board,i,j,row,col);
      }
    }
    for(int i = 0; i < row ; ++i){
      for(int j = 0; j < col; ++j){
        board[i][j] = res[i][j];
      }
    }
  }

  public static int dfs(int[][] b,int x,int y,int row,int col){
    int count = 0;
    for(int[] dir : DIRS){
      int newX = x + dir[0];
      int newY = y + dir[1];
      if(newX >= 0 && newY >= 0 && newX < row && newY < col && b[newX][newY] == 1){
        count++;
      }
    }
    if( b[x][y] == 1){
      if(count < 2 || count > 3){
        return 0;
      }
    }else{
      if(count == 3){
        return 1;
      }
    }
    return b[x][y];
  }

}