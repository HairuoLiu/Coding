
/**
 *
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC25_ReverseNodesinkGroup{

  public static void main(String[] args) {
    ListNode head1 = new ListNode(1);
    head1.next = new ListNode(10);
    head1.next.next = new ListNode(8);
    head1.next.next.next = new ListNode (9);
    head1.next.next.next.next = new ListNode (2);
    ListNode ans1 = reverseKGroup(head1,3);
   // ListNode ans2 = addTwoNumbers(head1,head2);
  while (ans1!=null){
    System.out.println(ans1.val);
    ans1 = ans1.next;
  }
    //System.out.println(ans1 + " ");
  }

  //Solution 0:   BF with queue       Time 0(N), Spase 0(N)
  //we record all the sum in the begin and use create all of them
  public static ListNode reverseKGroup(ListNode head, int k) {
  ListNode curr = head;
  int count = 0;
    while (curr != null && count != k) { // find the k+1 node
    curr = curr.next;
    count++;
  }
    if (count == k) { // if k+1 node is found
    curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
    // head - head-pointer to direct party,
    // curr - head-pointer to reversed party;
    while (count-- > 0) { // reverse current k-group:
      ListNode tmp = head.next; // tmp - next head in direct party
      head.next = curr; // preappending "direct" head to the reversed list
      curr = head; // move head of reversed party to a new node
      head = tmp; // move "direct" head to the next node in direct party
    }
    head = curr;
  }
    return head;
  }

  //Assumtion what will happen when it is null  we need to return inplace or not

  //无论如何都前后翻转
  public static ListNode reverseKGroupH(ListNode head, int k) {
    if(head == null|| head.next == null){
      return head;
    }
    ListNode back = head;
    ListNode pre = null;
    ListNode cur = head;
    ListNode next = head.next;
    for(int i = 0; i < k;++i){
      if(cur != null){
        next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
      }
    }
    back.next= reverseKGroupH(next,k);
    return pre;
  }

  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }


}
