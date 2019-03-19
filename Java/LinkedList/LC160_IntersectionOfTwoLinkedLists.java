
/**
 *
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * pointer: a, b
 * A:          a1 → a2
 *                     ↘
 *                       c1 → c2 → c3
 *                     ↗
 * B:     b1 → b2 → b3
 *
 *
 * begin to intersect at node c1.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC160_IntersectionOfTwoLinkedLists{

  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  public static void main(String[] args) {
    ListNode head1 = new ListNode(1);
    ListNode head2 = new ListNode(1);
    ListNode ans1 = getIntersectionNodeBF(head1,head2);
    ListNode ans2 = getIntersectionNode(head1,head2);
    System.out.println(ans1 + " " + ans2);
  }

  //Assumtion what will happen when it is null  we need to return inplace or not

  //Solution 1:BF Time: O(n)  Space: O(1)
  //Get the diff of two lists’ length, traverse the longer one with diff steps first,
  //Then traverse both to get the intersection
  public static ListNode getIntersectionNodeBF(ListNode headA, ListNode headB) {
    int lengthA = getLength(headA);
    int lengthB = getLength(headB);
    int diff = 0;
    if(lengthA < lengthB){
      return getIntersection(headA, headB, lengthB - lengthA);
    } else {
      return getIntersection(headB, headA, lengthA - lengthB);
    }
  }
  public static int getLength(ListNode head){
    int length = 0;
    ListNode dummy = head;
    while(dummy != null){
      dummy = dummy.next;
      length ++;
    }
    return length;
  }
  public static ListNode getIntersection(ListNode headA, ListNode headB, int diff){
    for(int i = 0; i < diff; i++){
      headB = headB.next;
    }
    while(headA != headB){
      headA = headA.next;
      headB = headB.next;
    }
    return headA;
  }

  //Solution 2: Not using length Time: O(n)  Space: O(1)
  //Assume len(A) < len(B)
  //Let a be the head of A and b be the head of B
  //Traverse both a and b simultaneously, a will reach to the end (lenB - lenA) steps before
  //Switch a to point the head of B, now a is (lenB - lenA) away from b.
  //Do the same until b = null, switch b to point head of A, now that a is lenB - (lenB - lenA) = lenA steps away from B’s end, so a and b are both lenA away from the end.
  public static ListNode getIntersectionNode(ListNode headA, ListNode headB){
    if(headA == null || headB == null){
      return null;
    }
    ListNode a = headA;
    ListNode b = headB;
    while(a != b){
      a = a == null ? headB : a.next;
      b = b == null ? headA : b.next;
    }
    return a;
  }

}
