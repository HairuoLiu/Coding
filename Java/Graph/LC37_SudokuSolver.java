
/**
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 *
 *
 * A sudoku puzzle...
 *
 *
 * ...and its solution numbers marked in red.
 *
 * Note:
 *
 * The given board contain only digits 1-9 and the character '.'.
 * You may assume that the given Sudoku puzzle will have a single unique solution.
 * The given board size is always 9x9.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC37_SudokuSolver{

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
    solveSudoku(grid);
    System.out.println(grid);
  }

  public static void solveSudoku(char[][] board) {
    if(board == null || board.length == 0|| board[0].length == 0){
      return;
    }

  }

  public void backT(char[][] board,int i, int j){

    if(board[i][j] == '.'){

    }

  }

}