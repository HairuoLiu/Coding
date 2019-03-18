

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 *
 * Return 0 if there is no such transformation sequence.
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
 * Output: 5
 *
 * Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 * return its length 5.
 * Example 2:
 *
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: 0
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC127_WordLadder{


/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */

  public static void main(String[] args) {
    String beginWord = "hit";
    String endWord = "cog";
    String [] list = {"hot","dot","dog","lot","log","cog"};
    List<String> wordList = Arrays.asList(list);
    int ans1 = ladderLengthBFS(beginWord,endWord,wordList);
    int ans2 = ladderLengthDoubleBFS(beginWord,endWord,wordList);

    System.out.println(ans1 +" "+ans2);

    //System.out.println(ans );
  }

  //solution 1 BFS打印路径模板 + 每次生成新的string
  public static int ladderLengthBFS(String beginWord, String endWord, List<String> wordList) {
    if(wordList.size() == 0 || beginWord.equals(endWord)){
      return 0;
    }
    // add Set
    Set<String> set = new HashSet<>();
    for(String i:wordList){
      set.add(i);
    }

    Set<String> queue= new HashSet<>();
    queue.add(beginWord);
    int num = 1;
    while(!queue.contains(endWord)){
      Set<String> toAdd = new HashSet<String>();
      for (String each : queue) {
        for (int i = 0; i < each.length(); i++) {
          char[] chars = each.toCharArray();
          for (char ch = 'a'; ch <= 'z'; ch++) {
            chars[i] = ch;
            String word = new String(chars);
            if (set.contains(word)) {
              toAdd.add(word);
              set.remove(word);
            }
          }
        }
      }
      num++;
      if(toAdd.size() == 0){
        return 0;
      }
      queue = toAdd;
    }
    return num;
  }


  //solution 2 Double side BFS打印路径模板 + 每次生成新的string
  public static int ladderLengthDoubleBFS(String beginWord, String endWord, List<String> wordList) {
    // sanity check
    if(wordList.size() == 0 || beginWord.equals(endWord)){
      return 0;
    }
    int num = 1;
    // add Set
    Set<String> set = new HashSet<>();
    for(String i:wordList){
       set.add(i);
    }

    // two side set
    Set<String> smallSet = new HashSet<String>(), bigSet = new HashSet<String>();
    smallSet.add(beginWord);
    bigSet.add(endWord);
    while (!smallSet.isEmpty() && !bigSet.isEmpty()){
      autoSwap(smallSet,bigSet);
      Set<String> newSet = new HashSet<>();
      for(String cur : smallSet){
        for(int i = 0; i < cur.length(); i++){
          char [] curChar = cur.toCharArray();
          for(char k = 'a'; k <= 'z'; k++){
            curChar[i] = k;
            String temp = new String(curChar);
            if(set.contains(temp)){
              if(bigSet.contains(temp)){
                return num + 1;           //多加一步 因为第一次是吧两边都放进去了
              }
              newSet.add(temp);
              set.remove(temp);
            }
          }
        }
      }
      num++;
      if(newSet.size() == 0){
        return 0;
      }
      smallSet = newSet;
    }
    return num;
  }

  public static void autoSwap(Set<String> a,Set<String> b){
    if(a.size() <= b.size()){
      return;
    }
    Set<String> temp = a;
    a = b;
    b = temp;
  }

  //solution 2.5 Double side BFS打印路径模板 + 记录一开始的换回去
  //char old = chrs[i];
  //  for (char c = 'a'; c <= 'z'; c++) {
  //    chrs[i] = c;
  //    String candidate = String.valueOf(chrs);
  //    ...
  //    chrs[i] = old;


}