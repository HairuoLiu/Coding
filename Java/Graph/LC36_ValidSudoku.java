

/**
 *
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
 *
 * Each row must contain the digits 1-9 without repetition.
 * Each column must contain the digits 1-9 without repetition.
 * Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * A partially filled sudoku which is valid.
 *
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 *
 * Example 1:
 *
 * Input:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: true
 * Example 2:
 *
 * Input:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * Output: false
 * Explanation: Same as Example 1, except with the 5 in the top left corner being
 *     modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 * Note:
 *
 * A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * Only the filled cells need to be validated according to the mentioned rules.
 * The given board contain only digits 1-9 and the character '.'.
 * The given board size is always 9x9.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC36_ValidSudoku{

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

  public static void main(String[] args) {
   char [][] grid = {
       {'8','3','.','.','7','.','.','.','.'},
       {'6','.','.','1','9','5','.','.','.'},
       {'.','9','8','.','.','.','.','6','.'},
       {'8','.','.','.','6','.','.','.','3'},
       {'4','.','.','8','.','3','.','.','1'},
       {'7','.','.','.','2','.','.','.','6'},
       {'.','6','.','.','.','.','2','8','.'},
       {'.','.','.','4','1','9','.','.','5'},
       {'.','.','.','.','8','.','.','7','9'}};
    System.out.println(isValidSudoku(grid));
  }

  public static boolean isValidSudoku(char[][] board) {

    boolean[][] setCub = new boolean[9][9];
    boolean[][] setRow = new boolean[9][9];
    boolean[][] setCol = new boolean[9][9];

    for(int i = 0; i < 9; ++i){
      for(int j = 0; j < 9; ++j){
        if(board[i][j] != '.'){
          int num = board[i][j] - '0' - 1;
          if(setRow[i][num]||setCol[j][num] || setCub[i/3 * 3 +j/3][num] ){
            return false;
          }
          setCub[i/3 * 3 +j/3][num] =  setRow[i][num] = setCol[j][num] =true;
        }
      }
    }
return true;

  }

}