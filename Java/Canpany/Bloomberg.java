import java.util.*;
//
public class Bloomberg{

	class TreeNode{
		int val;
		TreeNode left,right;
		public TreeNode(int val){
			this.val = val;
		}
	}

	// public static void main(String[] args) {
	// 	//System.out.print();
	// }

	//4-20
	//第一轮：ABC + 三哥。上来问why BB，然后ABC出题。给一串数字，照着手机键盘，给出所有对应字母串的组合。比如给23，输出AD，AE，AF，BD。。等等的，edge case约定好怎么办，先说了下思路，秒掉。
	//三哥出题，UDP那道高频，又说了下思路，我说用hashset，三哥问为啥不用heap，解释了下。秒掉。
	
	private final char[][] KEY = {{},{'a','b','c'},{'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
	public List<String> letterCombinations(String digits) {
		List<String> res = new ArrayList<>();
		//sanity check
        if(digits == null || digits.length() == 0){
        	return res;
        }
        buildHelper(digits,0,new StringBuilder(), res);
        return res;
    }

    public void buildHelper(String str, int index,StringBuilder temp,List<String> res){
    	//base case
    	if(index == str.length()){
    		res.add(temp.toString());
    		return;
    	}
    	for(char c : KEY[str.charAt(index) - '0' - 1]){
    		temp.append(c);
    		buildHelper(str,index + 1,temp,res);
    		temp.deleteCharAt(temp.length() - 1);
    	}
    }


	//第二轮：小白哥 + 国人。上来问why software engineer，然后国人出题。从左到右从上到下print tree，蠡口原题，题号忘了。秒掉。搞完又让我设计了几个test case，国人表示满意。小白哥出题，LRU变种。搞完又问考虑frequency怎么办，我说了下LFU的思路。问他要写下code吗，他说不啦。

	public List<TreeNode> printTree(TreeNode root){
		List<TreeNode> res = new ArrayList<>();
		if(root == null){
			return res;
		}
		printHelper(root,res);
		return res;
	}

	public void printHelper(TreeNode root, List<TreeNode> res){
		if(root == null){
			return; 
		}
		res.add(root);
		printHelper(root.left,res);
		printHelper(root.right,res);
	}

	//经理说假设没有BB，某家公司的股票在A地以x元成交，在B地以y元成交，让设计数据结构。
	//我说用hashmap吧，key就是公司名，values就是list of pairs，比如A--x, B--y。他说太慢了，我又解释了一遍。
	//他问你听懂没有，我说我来重述下问题吧。剩下几分钟一直在想从数据结构上优化，他一直不满意。。后来他提了下，要看看整个系统啊。
	//我说嗯，可以一步一步来分析，找bottleneck。网络的问题？数据更新慢？数据读取慢？都有可能的。他表示满意。。心想大哥您不是让我优化数据结构嘛。。
	//问了很多情形，基本上就是设计数据结构，trade-off between读写速度吧。不知不觉跟他扯了一个半小时，双方都表示尽兴
	
	//LRU
	class LRUNode{
		LRUNode next,prev;
		int key;
		int val;
		LRUNode(int key, int val){
			this.key = key;
			this.val = val;
		}
	}
	class LRUCache{
		//LRUNode head,tail;
        LinkedHashMap<Integer,Integer> memo;
		public LRUCache(int capacity){
			this.memo = new LinkedHashMap<Integer, Integer>(){ 
				protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest){ 
					return size() > capacity; 
				} 
			};
		}

		public int get(int key) {
            Integer res = memo.remove(key);
			if(res != null){
                memo.put(key,res);
				return res;
			}
			return -1;
		}

		public void put(int key, int value) {
            memo.remove(key);
			memo.put(key,value);			
		}
	}

	//持续抛硬币，要是末尾三个结果是HHT则A赢，如果是HTH则B赢，都不不是的话就⼀一直抛
	//问谁获 胜⼏几率⼤大为什什么，并写code来模拟这个游戏
	//
	public String winA(char f, char s, char t){
		if(f == 'H' &&  s == 'H' && t == 'T') return "A";
		if(f == 'H' &&  s == 'T' && t == 'H') return "B";
		return winA(s,t,new Random().nextInt(2) == 1 ? 'H' : 'T');
	}

	//114
	public void flatten(TreeNode root) {
		if (root == null) return;
		TreeNode left = root.left;
		TreeNode right = root.right;
		flatten(root.right);
		flatten(root.left);
		root.right = left;
		root.left = null;
		while(root.right != null){
			root = root.right;
		}
		root.right = right;
	}

	//387
	//public 

	//searchin rotated sorted array
    public static int search(int[] arr, int target) {
    	//sanity check
  		if(arr == null || arr.length == 0){
			return -1;
		}
		int left = 0, right = arr.length - 1; 
		
		while(left + 1 < right){
			int mid = left + (right - left) / 2;
			if(arr[mid] == target){
				return mid;
			}else if(arr[mid] < arr[right]){
				if(arr[mid] < target && target <= arr[right]){
					left = mid;
				}else{
					right = mid;
				}
			}else if(arr[mid] > arr[right]){
				if(arr[mid] > target && target >= arr[left]){
					right = mid;
				}else{
					left = mid;
				}
			}else{
				right--;
			} 
        }
        if(arr[left] == target){
            return left;
        }else if(arr[right] == target){
            return right;
        }
        return -1;
    }

	// Lotterysystem design
	// O(1)add(), remove(), getWinner()
	// 
	
	//UDP那个题前人写过。就是写一个函数，输入是一个数据包，比如1 -> A, 2 -> B, 4->C, 3->D
	//数字是UDP数据包的标号，字母是内容。如果新接收到的数据包是下个期待的包，就打印出来，如果不是就存下来，等期待的包到之后一起打印。上面例子打印输出应该是1 -> A, 2 -> B, 3 -> DC 
	static class UDP{
		int num;
		String detail;
		public UDP(int num , String detail){
			this.num = num;
			this.detail = detail;
		}
	}
	public static void getUDP(List<UDP> list){
		//sanity check
		if(list == null || list.size() == 0){
			return;
		}	
		int nextPrint = 1;
		Map<Integer,UDP> map = new HashMap<>();
		for(UDP i : list){
			while(nextPrint == i.num || map.containsKey(nextPrint)){
				System.out.print( nextPrint == i.num ? i.detail : map.remove(nextPrint).detail);
				nextPrint++;
			}
			if(nextPrint < i.num){
				map.put(i.num,i);
			}
		}
	}

	// public static void main(String[] args) {
	// 	List<UDP> l = new ArrayList<>();
	// 	l.add(new UDP(1,"A"));
	// 	l.add(new UDP(2,"B"));
	// 	l.add(new UDP(4,"D"));
	// 	l.add(new UDP(3,"C"));
	// 	getUDP(l);
	// }	

	class Node{
		Node left,right,next;
		public Node(){
		}
	}

	//117. Populating Next Right Pointers in Each Node II
	public Node connect(Node root) {
        //sanity check
        if(root == null){
            return null;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0 ; i < size; ++i){
                Node cur = q.poll();
                if( i + 1 != size){
                    cur.next = q.peek();
                }
                if(cur.left != null){
                    q.offer(cur.left);
                }
                if(cur.right != null){
                    q.offer(cur.right);
                } 
            }
        }
        return root;
    }

    //二轮是设计地铁系统，每张地铁卡有一个ID，然后有很多不同的站
    //设计一个class求两站之间平均travel的时间，问了一些类似于万一刷卡进站了但是没有刷卡出站的话怎么办之类的问题
    class CARD{
    	int money;
 		String startState, endState; 
    	long startTime, endTime;
    	public CARD(){

    	}
    }



	//一个日程表，有一些events，只能给别人显示空余时间的block，求这些空余时间段   
	static class Event{
    	int start, end; 
    	public Event(int start,int end){
    		this.start = start;
    		this.end  = end;
    	}
    	private void printE(){
    		System.out.print(start + "," + end);
    	}
    }
	// public static void main(String[] args) {
	// 	List<Event> in = new ArrayList<>();
	// 	in.add(new Event(1,2));in.add(new Event(3,4));in.add(new Event(5,7));in.add(new Event(8,9));
	// 	List<Event> res = getValidTime(in);
	// 	for(Event e: res){
	// 		e.printE();
	// 	}
	// }	 
	public static List<Event> getValidTime( List<Event> in ){
	    List<Event> res = new ArrayList<>();
	    //sanity  check
	    if(in == null ||in.size() == 0){
	        return  res;
	    }
	    int size = in.size();
	    int[] startT = new int[size];
	    int[] endT = new int[size];
	    for(int i = 0; i < size; ++i){
	        startT[i] = in.get(i).start;
	        endT[i] = in.get(i).end;
	    }
	    Arrays.sort(startT);
	    Arrays.sort(endT);
	    int diff = 0;
	    for(int i = 0, j = 0; i < size && j < size;){
	        if(startT[i] <= endT[j]){
	            diff++;
	            i++;
	        }else{
	            diff--;
	            if(diff == 0) res.add( new Event(endT[j],startT[i]) );
	            j++;
	        }
	    }
	    return res;
	}

	//sort string by frequency
	//
	class DoubleLinkedList{
		int num;
		Set<String> set;
		DoubleLinkedList next, prev;
		public DoubleLinkedList(int num){
			this.num = num;
			this.set = new HashSet<>();
		}
		public void PrinbSet(){
			for(String i : set){
				System.out.print(i);
			}
		}
		public boolean add(String s){
			return set.add(s);
		}
		public boolean remove(String s){
			return set.remove(s);
		}
	}

	class FreqList{
		DoubleLinkedList head, tail;
		Map<Integer,DoubleLinkedList> listMap;
		Map<String, Integer> freqMap;
		public FreqList(){
			this.head = new DoubleLinkedList(Integer.MAX_VALUE);
			this.tail = new DoubleLinkedList(Integer.MIN_VALUE);
			head.next = tail;
			tail.prev = head;
			this.listMap = new HashMap<>();
			this.freqMap = new HashMap<>();
		}

		public void insertWords(List<String> list){
			if(list == null || list.size() == 0){
				return;
			}
		}

		private void removeWord(String s){
			//sanity check
			if(!freqMap.containsKey(s)) return;
			Integer curNum = freqMap.get(s);
			DoubleLinkedList node = listMap.get(curNum);
			int curFreq = curNum + 1;
			if(node.set.size() == 1 && !listMap.containsKey(curFreq)){
				node.num = curFreq;
				listMap.put(curFreq,listMap.remove(curNum));
			}else if(node.set.size() == 1){
				removeNode(node,curNum);
			}else{
				if(node.remove(s)){
					if(!listMap.containsKey(curFreq)){
						addNode(curNum);	
					}
					listMap.get(curFreq).add(s);
				}
			}
		}

		private void addNode(int prevFreq){
			int curFreq = prevFreq+1;
			DoubleLinkedList newNode = new DoubleLinkedList(curFreq);
			DoubleLinkedList tempNode =  prevFreq == 0 ? head : listMap.get(prevFreq);
			newNode.next = tempNode.next;
			tempNode.next.prev = newNode;
			tempNode.next = newNode;
			newNode.prev = tempNode;
			listMap.put(curFreq,newNode);
		}	

		private void removeNode(DoubleLinkedList node,int curNum){
			node.prev.next = node.next;
			node.next.prev = node.prev;
			listMap.remove(curNum);
		}

		public void printWord(){
			DoubleLinkedList temp = head.next;
			while(head != tail){
				head.PrinbSet();
				head = head.next;
			}
		}
	}

}