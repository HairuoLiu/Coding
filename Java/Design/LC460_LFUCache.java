
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 *
 *Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * Example:
 * LFUCache cache = new LFUCache( 2 capacity  )
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4,4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *
 * @author Liu.3502
 * @created 2018-09-10 下午1:17
 */
public class LC460_LFUCache{

    class LFUCache{
        Map<Integer,Integer> keyMap;
        Map<Integer,Integer>  freqMap;    // freq map for the key in vals
        Map<Integer, LinkedHashSet<Integer>> lists;
        int cap;
        int min = -1;

        public LFUCache (int capacity){
            cap = capacity;
            keyMap = new HashMap<>();
            freqMap=new HashMap<>();
            lists = new HashMap<>();
            lists.put  (1, new LinkedHashSet<>());
        }

        public int get(int key){
            if(!keyMap.containsKey(key)){
                return -1;
            }
            int count =  freqMap.get(key);
            freqMap.put(key,count + 1);
            lists.get(count).remove(key);
            if (count == min && lists.get(count).size() == 0) {
                ++min;
            }
            LinkedHashSet <Integer> set = lists.get(count + 1);

            if (set == null) {
                set = new LinkedHashSet<>();
                lists.put (count + 1, set);
            }
            set.add(key);
            return keyMap.get(key);
        }

        public void put (int key, int value){
            if  (cap  <= 0) {
                return;
            }
            if (keyMap.containsKey (key)){
                keyMap. put(key, value);
                get(key);
                return;
            }
            if (keyMap.size () == cap) {
                int evit = lists.get(min).iterator().next();
                lists.get (min).remove(evit);
                keyMap.remove(evit);
            }
            keyMap.put(key, value);
            freqMap.put(key, 1);
            lists.get(1).add(key);
        }
    }




    private final int capacity;
    private ListNode head, tail;
    private Map<Integer, ListNode> map;

    public LFUCache_460(int capacity){
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // first we get the node from the map
        // remove it from dll
        // append it at the beginning
        ListNode curr = map.get(key);
        delete(curr);
        append(curr);
        return curr.val;
    }

    public void put(int key, int value) {
        ListNode curr = new ListNode(key, value);
        // check if we have previous version of current node
        if (map.containsKey(key)) {
            ListNode prevVersion = map.get(key);
            delete(prevVersion);
            append(curr);
            return;
        }
        // check if current map reach the capacity
        if (map.size() < capacity) {
            append(curr);
        } else {
            delete(tail);
            append(curr);
        }
    }

    private void append(ListNode curr) {
        // add node to the map
        map.put(curr.key, curr);
        // check dll is null
        if (head == null) {
            head = curr;
            tail = curr;
            return;
        }
        // append it before the current head
        curr.next = head;
        head.prev = curr;
        head = curr;
    }

    private void delete(ListNode curr) {
        // delete from map
        map.remove(curr.key);
        if (curr == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            return;
        }
        if (curr == tail) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
            return;
        }
        // delete the curr node
        if (curr.prev != null) {
            curr.prev.next = curr.next;
        }
        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }
    }

    class ListNode {
        int key;
        int val;
        ListNode prev;
        ListNode next;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
