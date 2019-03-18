

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Given the head of a graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a label (int) and a list (List[UndirectedGraphNode]) of its neighbors.
 * There is an edge between the given node and each of the nodes in its neighbors.
 *
 *
 * OJ's undirected graph serialization (so you can understand error output):
 * Nodes are labeled uniquely.
 *
 * We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
 *
 *
 * As an example, consider the serialized graph {0,1,2#1,2#2,2}.
 *
 * The graph has a total of three nodes, and therefore contains three parts as separated by #.
 *
 * First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
 * Second node is labeled as 1. Connect node 1 to node 2.
 * Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
 *
 *
 * Visually, the graph looks like the following:
 *
 *        1
 *       / \
 *      /   \
 *     0 --- 2
 *          / \
 *          \_/
 *
 * Note: The information about the tree serialization is only meant so that you can understand error output if you get a wrong answer. You don't need to understand the serialization to solve the problem.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC133_CloneGraph{


  static class UndirectedGraphNode {
      int label;
      List<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 };

/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

  public static void main(String[] args) {
    UndirectedGraphNode node = new UndirectedGraphNode(0);
    UndirectedGraphNode ans1 = cloneGraphDFS(node);
    UndirectedGraphNode ans2 = cloneGraphBFS(node);

    System.out.println(ans1.label + " " + ans2.label);

    //System.out.println(ans );
  }

  // Solution 0:   Time : O(N)
  public static UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) {
    if(node == null){
      return node;
    }
    Map<Integer,UndirectedGraphNode> visit = new HashMap<Integer,UndirectedGraphNode>();
    return dfs(node,visit);
  }

  public static UndirectedGraphNode dfs(UndirectedGraphNode node,Map<Integer,UndirectedGraphNode> map){
    UndirectedGraphNode cur = new UndirectedGraphNode(node.label);
    map.put(cur.label, cur);
    for(UndirectedGraphNode temp : node.neighbors){
      UndirectedGraphNode curMapEntry = map.get(temp.label);
      if(curMapEntry == null){
        cur.neighbors.add(dfs(temp,map));
      }else {
        cur.neighbors.add(curMapEntry);
      }
    }
    return cur;
  }

  public static UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node){
    if(node == null){
      return node;
    }
    Map<Integer,UndirectedGraphNode> visit = new HashMap<Integer,UndirectedGraphNode>();
    Queue<UndirectedGraphNode> queue = new ArrayDeque();

    UndirectedGraphNode res = new UndirectedGraphNode(node.label);
    visit.put(res.label, res);
    queue.offer(node);
    while(!queue.isEmpty()){
      int size = queue.size();
      for(int i = 0 ; i < size; i++){
       UndirectedGraphNode cur = queue.poll();
       UndirectedGraphNode tempNode = new UndirectedGraphNode(cur.label);

       for(UndirectedGraphNode temp : cur.neighbors){
         UndirectedGraphNode curMapEntry = visit.get(temp.label);

         if(curMapEntry == null){
           queue.offer(temp);
           visit.put(temp.label,temp);
         }
        tempNode.neighbors.add(curMapEntry);
       }
      }
    }
    return res;
  }

}