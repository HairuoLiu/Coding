
/**
 *
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
 *
 * Example:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * Given word = "ABCCED", return true.
 * Given word = "SEE", return true.
 * Given word = "ABCB", return false.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC79_WordSearch{

  public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) {
        start = s; end = e;
      }
  }

  public static void main(String[] args) {
   // char [][] grid = {{'A'}};
    char [][] grid = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};

    System.out.println(existDFS(grid,"SEE"));
  }

  //Dirs
  final static int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1}};

  //DFS 用
  public static boolean existDFS(char[][] board, String word) {
    //sanity check
    if(board == null){
      return false;
    }

    int row = board.length;
    int col = board[0].length;

    for(int i = 0 ;i < row; i++){
      for(int j = 0 ; j < col; j++){
        if(board[i][j] == word.charAt(0) && dfs(board,i,j,row,col,word,1)){
          return true;
        }
      }
    }
    return false;
  }

  public static boolean dfs(char[][] board ,int x ,int y ,int row , int col, String word, int len){
    char cur = board[x][y];
    if(len == word.length()){
     return true;
    }
    board[x][y] = ' ';
    for(int[] dir : dirs){
      int newX = dir[0]+x;
      int newY = dir[1]+y;
      if( newX >= 0 && newX < row &&
          newY >= 0 && newY < col &&
          board[newX][newY] != ' ' &&
          word.charAt(len) == board[newX][newY]){
        if(dfs(board,newX,newY,row,col,word,len + 1)){
          return true;
        }
      }
    }
    board[x][y] = cur;
    return false;
  }

  public boolean exist(char[][] board, String word) {
    char[] wa = word.toCharArray();
    for(int i=0; i<board.length; ++i){
      for(int j=0; j<board[0].length; ++j){
        if(existHelper(board, i, j, wa, 0)){
          return true;
        }
      }
    }
    return false;
  }

  private boolean existHelper(char[][] board, int i, int j, char[] wa, int cur) {
    if(cur == wa.length){
      return true;
    }
    if(i < 0 || j < 0 || i >= board.length || j >= board[0].length){
      return false;
    }
    if(board[i][j] != wa[cur]){
      return false;
    }
    board[i][j] = '.';
    boolean exists = existHelper(board, i-1, j, wa, cur+1)
        || existHelper(board, i, j+1, wa, cur+1)
        || existHelper(board, i+1, j, wa, cur+1)
        || existHelper(board, i, j-1, wa, cur+1);
    board[i][j] = wa[cur];

    return exists;
  }

}