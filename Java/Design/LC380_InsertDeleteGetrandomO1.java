
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is
 * no middle value. So the median is the mean of the two middle value.
 *
 * For example, [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure. double
 * findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1) addNum(2) findMedian() -> 1.5 addNum(3) findMedian() -> 2
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it? If 99%
 * of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *
 * @author Liu.3502
 * @created 2018-09-10 下午1:17
 */
public class LC380_InsertDeleteGetrandomO1{

  class RandomizedSet {
    private List<Integer> list;
    private Map<Integer,Integer> map;

    /** Initialize your data structure here. */
    public RandomizedSet() {
      this.list = new ArrayList<>();
      this.map = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
      Integer cur = map.get(val);
      if(cur == null){
        list.add(val);
        map.put(val,list.size()-1);
        return true;
      }
      return false;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
      Integer cur = map.get(val);
      if(cur != null){
        int last = list.get(list.size()-1);
        list.set(cur,last);
        map.put(last,cur);
        map.remove(val);
        list.remove(list.size()-1);
        return true;
      }
      return false;
    }

    /** Get a random element from the set. */
    public int getRandom() {
      return list.get((int)(Math.random()*list.size()));
    }

  }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
