import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 *
 *
 * Algorithm of Insertion Sort:
 *
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 *
 * @authorLiu.3502
 * @created2018-01-29 下午7:56
 */
public class LC147_InsertionSortList{
    static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode list= new ListNode(5);
        list.next=new ListNode(2);
        list.next.next=new ListNode(3);

        list.next.next.next=new ListNode(4);
        list.next.next.next.next=new ListNode(1);
        ListNode newList = insertionSortList(list);
        while (newList!=null){
            System.out.println("newList的值是：" + newList.val);
            newList=newList.next;
        }

    }

    public static ListNode insertionSortList(ListNode head) {
        //sanity check
        if(head == null){
            return null;
        }
        ListNode dammyHead = new ListNode(0); //new starter of the sorted list
        ListNode cur = head; //the node will be inserted
        ListNode pre = dammyHead; //insert node between pre and pre.next
        ListNode next = null; //the next node will be inserted
        //not the end of input list
        while(cur != null){
            next = cur.next;
            //find the right place to insert
            while (pre.next != null && pre.next.val < cur.val) {
                pre = pre.next;
            }
            //insert between pre and pre.next
            cur.next = pre.next;
            pre.next = cur;
            pre = dammyHead;
            cur = next;
        }
        return dammyHead.next;
    }

}
