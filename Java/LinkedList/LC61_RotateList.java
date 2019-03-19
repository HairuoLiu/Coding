
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 * Example 2:
 *
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC61_RotateList{

  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  public static void main(String[] args) {
    ListNode lists = new ListNode(1);
    lists.next = new ListNode(2);
    lists.next.next = new ListNode(3);
    lists.next.next.next = new ListNode(4);
    lists.next.next.next.next = new ListNode(5);

    ListNode ans = rotateRight(lists,2);
    while (ans != null){
      System.out.println(ans.val);
      ans = ans.next;
    }
  }

  //Start from back because we need
  public static ListNode rotateRight(ListNode head, int k) {
    if (head==null||head.next==null) return head;
    ListNode dummy=new ListNode(0);
    dummy.next=head;
    ListNode fast = dummy,slow = dummy;
    int i;
    for ( i = 0;fast.next!=null;i++){
      //Get the total length
      fast=fast.next;
    }
    for (int j = i-k%i; j>0 ; j--){
      //Get the i-n%i th node
      slow=slow.next;
    }
    fast.next = dummy.next; //Do the rotation
    dummy.next = slow.next;
    slow.next=null;
    return dummy.next;
  }

}