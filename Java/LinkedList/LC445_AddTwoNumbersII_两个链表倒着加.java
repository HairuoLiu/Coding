
import java.util.ArrayDeque;
import java.util.Deque;

/**
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes first and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC445_AddTwoNumbersII_两个链表倒着加{

  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  public static void main(String[] args) {
    ListNode head1 = new ListNode(7);
    head1.next = new ListNode(2);
    head1.next.next= new ListNode(4);
    head1.next.next.next= new ListNode(3);
    ListNode head2 = new ListNode(5);
    head2.next = new ListNode(6);
    ListNode ans1 = addTwoNumbersBF(head1,head2);
    ListNode ans2 = addTwoNumbersBFplus(head1,head2);
    ListNode ans3 = addTwoNumbersReverse(head1,head2);
    System.out.println(ans1 + " " + ans2);
  }

  //Assumtion what will happen when it is null  we need to return inplace or not

  //Solution 1: get length + two Stack;
  public static ListNode addTwoNumbersBF(ListNode l1, ListNode l2) {
    //sanity check
    if(l1 == null || l2 == null){
      return l1 == null ? l2:l1;
    }
    Deque<Integer> s1 = new ArrayDeque<>();
    Deque<Integer> s2 = new ArrayDeque<>();

    while (l1 != null){
      s1.offerFirst(l1.val);
      l1 = l1.next;
    }
    while (l2 != null){
      s2.offerFirst(l2.val);
      l2 = l2.next;
    }
    int sum = 0;
    ListNode list = null;
    while (!s2.isEmpty() || !s1.isEmpty()){
      if (!s1.isEmpty()){
        sum += s1.pop();
      }
      if (!s2.isEmpty()){
        sum += s2.pop();
      }
      ListNode temp = new ListNode(sum % 10);
      temp.next = list;
      list = temp;
      sum /= 10;
    }
    return list.val == 0 ? list.next : list;
  }

  //Solution 2: get length + one Stack;
  public static ListNode addTwoNumbersBFplus(ListNode l1, ListNode l2) {
    //sanity check
    if(l1 == null || l2 == null){
      return l1 == null ? l2:l1;
    }
    Deque<Integer> list = new ArrayDeque<>();

    //O(长)
    int lengthA = 0, lengthB = 0;

    while (l1 != null){
      list.offerFirst(l1.val);
      l1 = l1.next;
      lengthA++;
    }
    while (l2 != null){
      list.offerLast(l2.val);
      l2 = l2.next;
      lengthB++;
    }

    int sum = 0;
    ListNode pre = null;
    while (!list.isEmpty()){
      if (lengthA > 0){
        sum += list.pollLast();
        lengthA--;
      }
      if(lengthB > 0){
        sum += list.pollFirst();
        lengthB--;
      }
      ListNode temp = new ListNode(sum % 10);
      temp.next = pre;
      pre = temp;
      sum /= 10;
    }
    return pre.val == 0 ? pre.next : pre;
  }


  //Solution 3: get length +
  public static ListNode addTwoNumbersDivideAndConquer(ListNode l1, ListNode l2) {
    //sanity check
    if(l1 == null || l2 == null){
      return l1 == null ? l2:l1;
    }
    int lengthA = getLength(l1);
    int lengthB = getLength(l2);

    int diff = Math.abs(lengthA - lengthB);
    if (diff != 0){
      return lengthA - lengthB > 0 ? addToOneList(l1,l2,diff) : addToOneList(l2,l1,diff);
    }
    return addToOneList(l1,l2,0);
  }

  // Get the length of the listNode
  // Revece 2 list and create new list
  public static int getLength(ListNode n){

    return 0;
  }




  public static ListNode addToOneList(ListNode longOne,ListNode ShortOne,int diff){
    ListNode dammyHead = new ListNode(1);
    ListNode newHead = dammyHead;
    dammyHead.next = longOne;
    while (diff > 0){
     dammyHead = dammyHead.next;
    }

    return newHead;
  }

  //Solution 3: recursive Time O(N) + O(N)
  public static ListNode addTwoNumbersReverse(ListNode l1, ListNode l2) {
    ListNode newl1 =reverseList(l1);
    ListNode newl2 =reverseList(l2);
    int add = 0;
    ListNode preNode = null;
    while (newl1 != null || newl2 != null || add == 1){

      int num1 = newl1==null ? 0:newl1.val;
      int num2 = newl2==null ? 0:newl2.val;
      if( newl1 != null ){
        newl1 = newl1.next;
      }
      if( newl2 != null ){
        newl2 = newl2.next;
      }
      int sum = num1 + num2 + add;
      ListNode newRoot = new ListNode(sum%10);
      add = sum /10;
      newRoot.next = preNode;
      preNode = newRoot;
    }
    return preNode;
  }


  // 0 -> 1 -> 2 -> 3   0 <- 1
  public static ListNode reverseList(ListNode l){
    if(l.next == null  || l == null){
      return l;
    }
    ListNode newRoot = reverseList(l.next);
    l.next.next = l;
    l.next = null;
    return newRoot;
  }
}
