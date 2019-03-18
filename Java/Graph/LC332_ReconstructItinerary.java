
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from,
 * to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
 * Thus, the itinerary must begin with JFK.
 *
 * Note:
 *
 * If there are multiple valid itineraries, you should return the itinerary that has the smallest
 * lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a
 * smaller lexical order than ["JFK", "LGB"]. All airports are represented by three capital letters
 * (IATA code). You may assume all tickets form at least one valid itinerary. Example 1:
 *
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]] Output: ["JFK", "MUC",
 * "LHR", "SFO", "SJC"] Example 2:
 *
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]] Output:
 * ["JFK","ATL","JFK","SFO","ATL","SFO"] Explanation: Another possible reconstruction is
 * ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC332_ReconstructItinerary{

  public static class Interval {

    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }

  public static void main(String[] args) {
    // char [][] grid = {{'A'}};
    String[][] tickets = {{"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"}};

    System.out.println(findItinerary(tickets));
  }
  public static List<String> findItinerary(String[][] tickets) {
    List<String> res = new ArrayList<>();
    Map<String,List<String>> map = new HashMap<>();
    for(String[] i : tickets){
      List<String> cur = map.getOrDefault(i[0],new ArrayList<>());
    cur.add(i[1]);
      map.put(i[0],cur);
    }
    return res;
  }

  public static List<String> dfs(String str,Set<String> visit, Map<String,List<String>> map){
      List<String> res = new ArrayList<>();
      res.add(str);
      //visit
      List<String> curLevel = map.get(str);
      List<String> t = new ArrayList<>();

      for(String  i :curLevel){
        t = dfs(i,visit,map);
      }
      return res;
  }


  public static List<String> findItineraryOld(String[][] tickets) {
    Map<String, List<String>> map = new HashMap<>();
    // Map<String,Integer> numSet = new HashMap<>();
    for (String[] i : tickets) {
      List<String> Ncur = map.getOrDefault(i[0], new ArrayList<>());
      // Integer t = numSet.getOrDefault(i[0],0);
      //add to map
      Ncur.add(i[1]);
      map.put(i[0], Ncur);
      //add to numSet
      // numSet.put(i[1],numSet.getOrDefault(i[1],t) + 1);
      // numSet.put(i[0],t);
    }

    PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> a.charAt(0) - b.charAt(0));
    List<String> res = new ArrayList<>();
    pq.offer("JFK");
    // for(String i : numSet.keySet()){
    //     Integer cur = numSet.get(i);
    //     if(cur == 0){
    //         pq.offer(i);
    //     }
    // }

    while (!pq.isEmpty()) {
      String s = pq.poll();
      res.add(s);
      List<String> tmap = map.get(s);
      if (tmap == null) {
        continue;
      }
      for (String i : tmap) {
        pq.offer(i);
        // Integer iNum = numSet.get(i);
        // if(iNum  == 1){
        //     pq.offer(i);
        // }
        // numSet.put(i,iNum - 1);
      }
      map.remove(s);
    }
    return res;
  }


}