
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 *
 *
 * Follow up:
 *
 * If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 * If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 *
 * @author Liu.3502
 * @created 2018-09-10 下午1:17
 */
public class LC295_FindMedianFromDataStream{

    public class Node{
        int val;
        Node pre;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }

    private HashMap<Integer,Node> map;
    private Node middle;
    private Integer size;
    private Integer lsize;
    private Integer rsize;

    public FindMedianfromDataStream_295(int capacity){

    }

    class MedianFinder {

        PriorityQueue<Integer> l;
        PriorityQueue<Integer> r;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            l = new PriorityQueue<>(Collections.reverseOrder());       //!!!
            r = new PriorityQueue<>();       //!!!
        }

        public void addNum(int num) {
            //swap if the party we want to swich is bigger
            if (l.size() > 0 && l.size() >= r.size() && l.peek() > num) {
                r.add(l.peek());
                l.remove(l.peek());
                l.add(num);
            } else if (r.size() > 0 && l.size() <= r.size() && r.peek() < num) {
                l.add(r.peek());
                r.remove(r.peek());
                r.add(num);
            } else if (l.size() > r.size()) {
                r.add(num);
            } else {
                l.add(num);
            }
        }

        public double findMedian() {
            if (r.size() == l.size()) {
                return (((double) r.peek() + (double) l.peek()) / 2);
            } else {
                return r.size() > l.size() ? r.peek() : l.peek();
            }
        }
    }
//    public MedianFinder() {
//        this.map = new HashMap<>();
//        this.size = 0;
//        this.lsize = 0;
//        this.rsize = 0;
//    }
//
//    public void addNum(int num) {
//        addNode(num);
//        if(middle.val > num){
//            lsize++;
//        }else{
//            rsize++;
//        }
//
//        if(size%2 == 0){
//
//        }else{
//
//        }
//    }
//
//    public void addNode(int num){
//        Node cur = map.getOrDefault(num,new Node(num));
//        if(){
//
//        }
//    }
//
//
//    public double findMedian() {
//        if(size %2 == 0){
//
//        }
//    }
}
