
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import test.PracticeClass17.B;

/**
 *
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 *
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 *
 * Example:
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Note:
 *
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 *
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC815_BusRoutes {

  public static void main(String[] args) {
   // char [][] grid = {{'A'}};
    int [][] grid = {{1, 2, 7},{3, 6,7}};

    System.out.println(numBusesToDestination(grid,1,6));
  }

  public static int numBusesToDestination(int[][] routes, int S, int T) {
    //pre
    Map<Integer,Set<Integer>> midBus = new HashMap<>();
    for(int i = 0; i < routes.length; ++i){
      for(int j : routes[i]){
        Set<Integer> curBus =  midBus.getOrDefault(j, new HashSet<>());
        curBus.add(i);
        midBus.put(j,curBus);
      }
    }
    return BusLevel(routes,midBus,S,T);
  }

  public static int BusLevel(int[][] routes, Map<Integer,Set<Integer>> bus, int S,int T){
    Set<Integer> visit = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(T);
    visit.add(T);
    int res = 0;
    while(!queue.isEmpty()){
      int size = queue.size();
      ++res;
      for(int i = 0; i < size; ++i){
        Set<Integer> cur = bus.get(queue.poll());
        if(cur == null){continue;}
        for(Integer stop:cur){
          for(int b : routes[stop]){
            if(S == b){
              return res;
            }
            if(visit.add(b)){
              queue.offer(b);
            }
          }

        }
      }
    }
    return -1;
  }

}