
import java.util.HashMap;

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

public class LC138_CopyListwithRandomPointer{

  static class RandomListNode {
      int label;
      RandomListNode next, random;
      RandomListNode(int x) {
        this.label = x;
      }
  };

  public static void main(String[] args) {
    RandomListNode head = new RandomListNode(-1);
//    head.next = new RandomListNode(1);
//    head.next.next = new RandomListNode(2);
//    head.next.random = new RandomListNode(3);
//    head.random = head.next.random;
//    head.next.next.random = head.next;
    RandomListNode  res1 = copyRandomList(head);
    RandomListNode  res2 = copyRandomListCopy(head);

    System.out.println(res1.label + " / " + res2.label );
  }

  //Assumtion what will happen when it is null  we need to return inplace or not

  //Solution 1:BF Time: O(N)  Space: O(N)
  //first time only go throgth the main node without the random node
  //Use hashset save all the node
  public static RandomListNode copyRandomList(RandomListNode head) {
    //sanity check
    if(head == null){
      return head;
    }

    HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
    RandomListNode dummy = new RandomListNode(0);
    RandomListNode node = dummy;
    RandomListNode cur, random, curRandom;
    while (head != null){
      if (!map.containsKey(head)){
        cur = new RandomListNode(head.label);
        map.put(head, cur);
      }
      else{
        cur = map.get(head);
      }
      node.next = cur;
      node = node.next;
      random = head.random;
      if (random != null){
        if (!map.containsKey(random)){
          curRandom = new RandomListNode(random.label);
          map.put(random, curRandom);
        }
        else{
          curRandom = map.get(random);
        }
        cur.random = curRandom;
      }
      head = head.next;
    }
    return dummy.next;
  }


  //Solution 1:BF Time: O(n)  Space: O(1)
  //first create one more node of each node and remove the old one
  public static RandomListNode copyRandomListCopy(RandomListNode head){
    //sanity check
    if(head == null){
      return head;
    }
    RandomListNode copyHead = head;
    RandomListNode lastHead = head;
    while (head != null){
      RandomListNode cur = new RandomListNode(head.label);
      RandomListNode tempNext = head.next;
      head.next = cur;
      cur.next = tempNext;
      head = tempNext;
    }
    //add the copy list
    while (copyHead!= null){
      if(copyHead.random != null){
        copyHead.next.random = copyHead.random.next;
      }
      copyHead = copyHead.next.next;
    }
    RandomListNode dammyHead = new RandomListNode(0);
    RandomListNode res = dammyHead;
    RandomListNode copy, copyIter = dammyHead;
    while (lastHead != null){
      RandomListNode next = lastHead.next.next;

      copy = lastHead.next;
      copyIter.next = copy;
      copyIter = copy;

      lastHead.next = next;
      lastHead = next;
    }
    return res.next;
  }
}
