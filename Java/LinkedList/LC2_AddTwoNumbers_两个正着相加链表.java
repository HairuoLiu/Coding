
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC2_AddTwoNumbers_两个正着相加链表{

  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  public static void main(String[] args) {
    ListNode head1 = new ListNode(1);
    ListNode head2 = new ListNode(0);
    head1.next = new ListNode(8);
    ListNode ans1 = addTwoNumbersBF(head1,head2);
    ListNode ans2 = addTwoNumbers(head1,head2);

    System.out.println(ans1 + " " + ans2);
  }

  //Assumtion what will happen when it is null  we need to return inplace or not

  //Solution 0:   BF with queue       Time 0(N), Spase 0(N)
  //we record all the sum in the begin and use create all of them
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

    int sum = 0;
    Deque<Integer> queue = new ArrayDeque<>();
    while (l1 != null && l2 != null){
      sum += (l1.val + l2.val);
      queue.offerFirst(sum%10);
      sum/= 10;
      l1 = l1.next;
      l2 = l2.next;
    }
    ListNode TempHead = l1 == null ? l2 :l1;
    while (TempHead != null){
      sum += TempHead.val;
      queue.offerFirst(sum%10);
      TempHead = TempHead.next;
      sum /= 10;
    }
    if(sum == 1){
      queue.offerFirst(1);
    }
    ListNode newHead = new ListNode(0);
    ListNode res = newHead;
    while (!queue.isEmpty()){
      newHead.next = new ListNode(queue.pollLast());
      newHead = newHead.next;
    }
    return res.next;
  }

  //Solution 1:   Time 0(N), Spase 0(1)
  public static ListNode addTwoNumbersBF(ListNode l1, ListNode l2) {
    ListNode prev = new ListNode(0);
    ListNode head = prev;
    int carry = 0;
    while (l1 != null || l2 != null || carry != 0) {
      ListNode cur = new ListNode(0);
      int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + carry;
      cur.val = sum % 10;
      carry = sum / 10;
      prev.next = cur;
      prev = cur;

      l1 = (l1 == null) ? l1 : l1.next;
      l2 = (l2 == null) ? l2 : l2.next;
    }
    return head.next;
  }

  //Solution 2: recursion
  public ListNode addTwoNumsHelper(ListNode l1, ListNode l2,int c){
    ListNode retList = null;
    int sum = ((l2 == null) ? 0 : l2.val) + ((l1 == null) ? 0 : l1.val) + c;
    if(l1 == null && l2 == null){
      if(c == 1){
        return new ListNode(1);
      }
      return null;
    }
    c = sum/10;
    retList = new ListNode(sum%10);
    if(l1 != null && l2 != null){
      retList.next = addTwoNumsHelper(l1.next, l2.next,c);
    }
    else if(l1 != null && l2 == null){
      retList.next = addTwoNumsHelper(l1.next,null,c);
    }
    else if(l1 == null && l2 != null){
      retList.next = addTwoNumsHelper(null,l2.next,c);
    }
    return retList;
  }
}
