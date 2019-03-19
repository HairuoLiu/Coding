
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant
 * time.
 *
 * push(x) -- Push element x onto stack. pop() -- Removes the element on top of the stack. top() --
 * Get the top element. getMin() -- Retrieve the minimum element in the stack.
 *
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC155_MinStack{


  public static void main(String[] args) {

  }

  //Wapper
  class MinStackNode {
    public Node ans;
    public void push(int x) {
      ans = ans == null ? new Node(x, x) : new Node(x, Math.min(x, ans.min), ans);
    }
    public void pop() {
      ans = ans.next;
    }

    public int top() {
      return ans.val;
    }

    public int getMin() {
      return ans.min;
    }

    private class Node {
      int val;
      int min;
      Node next;
      private Node(int val, int min) {
        this(val, min, null);
      }
      private Node(int val, int min, Node next) {
        this.val = val;
        this.min = min;
        this.next = next;
      }
    }
  }

  //linklist
  class MinStackList {
    ArrayList<Integer> list;
    public MinStackList() {
      list = new ArrayList<Integer>();
    }

    public void push(int x) {
      list.add(x);
    }

    public void pop() {
      if (list.size() != 0) {
        list.remove(list.size() - 1);
      }
    }

    public int top() {
      return list.get(list.size() - 1);
    }

    //Get Min element from stack
    public int getMin() {
      return list.stream().mapToInt(v -> v).min().orElseThrow(NoSuchElementException::new);
    }
  }
}

