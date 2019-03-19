
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Given an undirected graph, return true if and only if it is bipartite.
 *
 * Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.
 *
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * Input: [[1,3], [0,2], [1,3], [0,2]]
 * Output: true
 * Explanation:
 * The graph looks like this:
 * 0----1
 * |    |
 * |    |
 * 3----2
 * We can divide the vertices into two groups: {0, 2} and {1, 3}.
 * Example 2:
 * Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
 * Output: false
 * Explanation:
 * The graph looks like this:
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * We cannot find a way to divide the set of nodes into two independent subsets.
 *
 *
 * Note:
 *
 * graph will have length in range [1, 100].
 * graph[i] will contain integers in range [0, graph.length - 1].
 * graph[i] will not contain i or duplicate values.
 * The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC785_IsGraphBipartite {

  public static void main(String[] args) {
    int[][] grid = {{1,3},{0,2},{0,3},{1,2}};

    boolean ans = isBipartite(grid);
    System.out.println(ans);
  }

  public static boolean isBipartite(int[][] graph) {
    Map<Integer, Set<Integer>> map = buildGraph(graph);
    Set<Integer> firstLevel = new HashSet<>();
    Set<Integer> secondLevel = new HashSet<>();
    boolean isFirst = false;
    Queue<Integer> queue = new ArrayDeque<Integer>();
    queue.offer(0);
    firstLevel.add(0);
    while (!queue.isEmpty()){
      int size = queue.size();
      for(int i = 0; i < size; i++){
        Integer cur = queue.poll();
        Set<Integer> curSet = map.get(cur);
        for(Integer j : curSet){
          if(!isFirst && !firstLevel.contains(j)){
            secondLevel.add(j);
          }else if(isFirst && !secondLevel.contains(j)){
            firstLevel.add(j);
          }
          if(isFirst && firstLevel.contains(j)){
            return false;
          }
          if(!isFirst && secondLevel.contains(j)){
            return false;
          }
          queue.offer(j);
        }
        isFirst = !isFirst;
      }
    }
    return true;
  }


  public static  Map<Integer, Set<Integer>> buildGraph(int[][] graph){
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for(int i = 0; i < graph.length; i++){
      int root = i;
      for(int j = 0; j < graph[i].length; j++){
        Set<Integer> cur = map.getOrDefault(root,new HashSet<>());
        cur.add(j);
        map.put(root,cur);
      }
    }
    return map;
  }


}
