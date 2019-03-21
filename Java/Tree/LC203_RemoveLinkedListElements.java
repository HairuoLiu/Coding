
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 *
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC203_RemoveLinkedListElements {

  public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
  public static void main(String[] args) {
    ListNode root = new ListNode(0);
    ListNode ans = removeElements(root,0);
    System.out.println(ans);

  }

  //O(N)
  public static ListNode removeElements(ListNode head, int val) {
    if(head == null){
      return null;
    }
    ListNode dammyHead = new ListNode(0);
    dammyHead.next = head;
    ListNode newHead = dammyHead;

    while(dammyHead.next != null){
      if(dammyHead.next.val == val){
        dammyHead.next = dammyHead.next.next;
      }else{
        dammyHead = dammyHead.next;
      }
    }
    return newHead.next;
  }
}
