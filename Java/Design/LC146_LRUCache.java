
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *
 * Design and implement a data structure for Least Recently Used (LRU_Cash) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 capacity );
 *
 *  cache.put(1,1);
 *  cache.put(2,2);
 *  cache.get(1);      // returns 1
 *  cache.put(3,3);    // evicts key 2
 *  cache.get(2);      // returns -1 (not found)
 *  cache.put(4,4);    // evicts key 1
 *  cache.get(1);      // returns -1 (not found)
 *  cache.get(3);      // returns 3
 *  cache.get(4);      // returns 4
 *
 * @author Liu.3502
 * @created 2018-09-10 下午1:17
 */
public class LC146_LRUCache{

    class LRU_F{
        private Map<Integer, Node> map;
        private final int capacity;
        private Node head;
        private Node tail;
        int size;

        public LRU_F(int capacity){
            map = new HashMap<>();
            this.capacity = capacity;
            size = 0;
            //build dammyhead and tail
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key){
            Node node = map.get(key);
            if(node != null){
                removeNode(node);
                addToHead(node);
                return node.val;
            }
            return -1;
        }

        public void put(int key, int value){
            Node cur = map.get(key);
            if(cur != null){
                cur.val = value;
                removeNode(cur);
            }else{
                cur = new Node(key,value);
                map.put(key,cur);
                if(size == capacity){
                    map.remove(tail.prev.key);
                    removeNode(tail.prev);
                }else {
                    ++size;
                }
            }
            addToHead(cur);
        }

        private void addToHead(Node node){
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
        }

        private void removeNode(Node node){
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }

    }

    class LRU{
        private Map<Integer, Node> map;
        private final int capacity;
        private Node head;
        private Node tail;

        public LRU(int capacity){
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
            Node curr = map.get(key);
            delete(curr);
            append(curr);
            return curr.val;
        }

        public void put(int key, int value) {
            Node curr = new Node(key, value);
            // check if we have previous version of current node
            if (map.containsKey(key)) {
                Node prevVersion = map.get(key);
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

        private void append(Node curr) {
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

        private void delete(Node curr) {
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
    }

    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
