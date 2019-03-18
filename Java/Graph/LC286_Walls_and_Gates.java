

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * You are given a m x n 2D grid initialized with these three possible values.
 *
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 * Example:
 *
 * Given the 2D grid:
 *
 * INF  -1  0  INF
 * INF INF INF  -1
 * INF  -1 INF  -1
 *   0  -1 INF INF
 * After running your function, the 2D grid should be:
 *
 *   3  -1   0   1
 *   2   2   1  -1
 *   1  -1   2  -1
 *   0  -1   3   4
 *
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC286_Walls_and_Gates{

  public static void main(String[] args) {
   // char [][] grid = {{'A'}};
    int [][] grid = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},{2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}
    };
    wallsAndGates(grid);
    System.out.println();
  }

  public static void wallsAndGates(int[][] rooms) {
    //santiy check
    if(rooms == null || rooms.length == 0||rooms[0].length == 0){
      return;
    }
    Queue<int[]> q = new LinkedList<>();
    Set<String> visit = new HashSet<>();
    //use K - BFS打印路径模板
    for(int i = 0 ; i < rooms.length; ++i){
      for(int j = 0; j < rooms[0].length; ++j){
        if(rooms[i][j] == 0){
          q.offer(new int[]{i,j});
        }
      }
    }
    KBFSINT(q,new boolean[rooms.length][rooms[0].length],rooms);
  }

  public static final int[][] DIRS = {{1,0},{0,1},{-1,0},{0,-1}};

  //String 慢
  public static void KBFS(Queue<int[]> q,Set<String> visit,int[][] rooms){
    int step = 0;
    while(!q.isEmpty()){
      int size = q.size();
      for(int i = 0; i < size;++i){
        int[] cur = q.poll();
        for(int[] dir : DIRS){
          int newX = cur[0] + dir[0];
          int newY = cur[1] + dir[1];
          if(!visit.add(new String(newX + "," + newY))){
            continue;
          }
          if(newX >= 0 && newX < rooms.length &&
              newY >= 0 && newY < rooms[0].length &&  rooms[newX][newY] != -1
          ){
            if(rooms[newX][newY] == 2147483647){
              rooms[newX][newY] = step + 1;
            }
            q.offer(new int[]{newX,newY});
          }
        }
      }
      step++;
    }
  }

  //boolean 快
  public static void KBFSINT(Queue<int[]> q, boolean[][] visit, int[][] rooms){
    int step = 0;
    while(!q.isEmpty()){
      int size = q.size();
      for(int i = 0; i < size;++i){
        int[] cur = q.poll();
        for(int[] dir : DIRS){
          int newX = cur[0] + dir[0];
          int newY = cur[1] + dir[1];
          if(visit[newX][newY]){
            continue;
          }
          if(newX >= 0 && newX < rooms.length &&
              newY >= 0 && newY < rooms[0].length &&  rooms[newX][newY] != -1
          ){
            if(rooms[newX][newY] == 2147483647){
              rooms[newX][newY] = step + 1;
            }
            q.offer(new int[]{newX,newY});
            visit[newX][newY] = true;
          }
        }
      }
      step++;
    }
  }
}