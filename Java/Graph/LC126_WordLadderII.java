
import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 *
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC126_WordLadderII{


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

  public static void main(String[] args) {
    String beginWord = "hit";
    String endWord = "cog";
    String [] list = {"hot","dot","dog","lot","log","cog"};
    // = Arrays.asList(list);
    List<String> wordList = new LinkedList<>();
    wordList.add("hot");
    wordList.add("dot");
    wordList.add("dog");
    wordList.add("lot");
    wordList.add("log");
    wordList.add("cog");

    List<List<String>> ans1 = findLadders(beginWord,endWord,wordList);
    //List<List<String>> ans2 = findLadders(beginWord,endWord,wordList);

    System.out.println(ans1 +" ");

    //System.out.println(ans );
  }

  //solution 1 BFS打印路径模板 + 每次生成新的string
  public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    wordList.add(beginWord);
    Set<String> list = new HashSet<>(wordList);
    Map<String, List<String>> prevMap = new HashMap<>();
    Map<String,Integer> levels = new HashMap<>();
    Queue<String> q = new LinkedList<>();
    q.offer(beginWord);
    prevMap.put(beginWord,new ArrayList<>());
    levels.put(beginWord,0);
    int level = 0;
    while(!q.isEmpty()){
      int size = q.size();
      for(int i = 0; i < size; ++i){
        String cur = q.poll();
        char[]  c = cur.toCharArray();
        for(int k = 0; k < cur.length(); ++k){
          char t = c[k];
          for(int j = 0; j < 26; ++j){
            c[k] = (char) ('a' + j);
            String newW = new String(c);
            if(list.contains(newW)) {
              if (!levels.containsKey(newW)) {
                levels.put(newW, level + 1);
                prevMap.put(newW, new ArrayList<>());
                prevMap.get(newW).add(cur);
                q.offer(newW);
              } else if (level + 1 == levels.get(newW)) {
                prevMap.get(newW).add(cur);
              }
            }
          }
          c[k] = t;
        }
      }
      if(levels.containsKey(endWord)) break;
      level++;
    }
    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();
    recoverP(endWord,beginWord,prevMap,path,res);
    return res;
  }

  public static void recoverP(String node, String init, Map<String,List<String>> prevMap, List<String> path, List<List<String>> res){
    if(node.equals(init)){
      path.add(init);
      List<String>tres = new ArrayList<>(path);
      Collections.reverse(tres);
      res.add(tres);
      path.remove(path.size()-1);
      return;
    }
    if(prevMap.containsKey(node)){
      path.add(node);
      for(String n: prevMap.get(node)){
        recoverP(n,init,prevMap,path,res);
      }
      path.remove(path.size() - 1);
    }
  }

  //solution 2.5 Double side BFS打印路径模板 + 记录一开始的换回去
  //char old = chrs[i];
  //  for (char c = 'a'; c <= 'z'; c++) {
  //    chrs[i] = c;
  //    String candidate = String.valueOf(chrs);
  //    ...
  //    chrs[i] = old;


}