
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 *
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 *
 * @author Liu.3502
 * @created 2018-03-24 下午12:08
 */
public class LC23_MergekSortedLists{

  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  public static void main(String[] args) {
    ListNode[] lists = null;
    //moveZeroesBF(n);
    ListNode ans = mergeKLists(lists);
    System.out.println(ans.val);
  }

  //Start from back because we need
  public static ListNode mergeKLists(ListNode[] lists) {
    ListNode dammyHead = new ListNode(0);
    ListNode resHead = dammyHead;
    //sanity check
    if(lists == null || lists.length == 0){
      return null;
    }
    PriorityQueue<ListNode> map = new PriorityQueue<ListNode>(
        lists.length, new Comparator<ListNode>() {
        @Override
        public int compare(ListNode o1, ListNode o2) {
          return o1.val == o2.val ? 0: o1.val > o2.val ? 1:-1;
      }
    }
    );

    //Time O(nlogn) put all the node in the map
    for(ListNode list : lists){
      map.add(list);
    }
    while(map.size() > 0) {
        ListNode cur = map.poll();
        if(cur!= null && cur.next != null){
          map.add(cur.next);
        }
        dammyHead.next = new ListNode(cur.val);
    }
    return resHead.next;
  }


  public static ListNode mergeKListsBS(ListNode[] lists) {
    if(lists == null || lists.length == 0){
      return null;
    }
    return margeList(lists,0,lists.length - 1);
  }

  public static ListNode margeList(ListNode[] lists,int left,int right){
    if(left == right){
      return lists[left];
    }
    int mid = left+(right - left)/2;
    return combine(margeList(lists,left,mid),margeList(lists,mid+1,right));
  }

  public static ListNode combine(ListNode lists1, ListNode lists2){
    ListNode dammyHead = new ListNode(0);
    ListNode newHead = dammyHead;
    while(lists1 != null && lists2 != null){
        if(lists1.val < lists2.val){
          dammyHead.next = lists1;
          lists1 = lists1.next;
        }else {
          dammyHead.next = lists2;
          lists2 = lists2.next;
        }
      dammyHead = dammyHead.next;
    }
    dammyHead.next = lists2 == null ? lists1:lists2;
    return newHead.next;
  }
}