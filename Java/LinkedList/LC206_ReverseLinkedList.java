
import java.util.Deque;
import java.util.LinkedList;

/**
 * Reverse a singly-linked list.
 *
 * Examples
 *
 * L = null, return null
 * L = 1 -> null, return 1 -> null
 * L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
 *
 * @authorLiu.3502
 * @created2018-01-29 下午7:56
 */
public class LC206_ReverseLinkedList{
    static class ListNode {
        public int value;
        public ListNode next;
        public ListNode(int value) {
            this.value = value;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode list= new ListNode(0);
        list.next=new ListNode(2);
        list.next.next=new ListNode(3);
        list.next.next.next=new ListNode(4);
        ListNode newList=reverseIt(list);
        while (newList!=null){
            System.out.println("newList的值是：" + newList.value);
            newList=newList.next;
        }

    }

    public static ListNode reverseRec(ListNode head) {
        if(head.next==null||head==null){
            return head;
        }
        ListNode newhead=reverseRec(head.next);
        head.next.next=head;
        head.next=null;
        return newhead;
    }


    public static ListNode reverseIt(ListNode head){
        if(head==null){
            return null;
        }
        Deque <ListNode> stack=new LinkedList<ListNode>();
        stack.offerFirst(head);
        ListNode pre=null;
        while(!stack.isEmpty()){
            ListNode curr = stack.pollFirst();
            if(curr!=null){
                stack.offerFirst(curr.next);
                ListNode temp=curr;
                curr.next=pre;
                pre=temp;
            }

        }
        return pre;
    }

}
