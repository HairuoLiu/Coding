import java.util.*;

public class Quora{
	
	public static void main(String[] args) {
		// String input = "abannnnwbwebbbneeas";
		// String res =  rearrangeString(input);
		// System.out.print(res);
	}

	public static List<String> breakString(String s){
		List<String> res = new ArrayList<>();
		StringBuilder  sb = new StringBuilder();
		for(int i = 0 ; i < s.length();){
			char c = s.charAt(i);
			if(c == '"'){
				++i;
				while(i < s.length() && s.charAt(i) != '"'){
					sb.append(s.charAt(i++));
				}
				if(sb.length() != 0){
					res.add(sb.toString());
				}
				sb = new StringBuilder();
			}else if(c == ' '){
				//while()
				sb = new StringBuilder();
			}else{

			}
		}
		return res;
	}

	// 2019 - 4 - 7 / 2019 - 2 - 21
	// 求所有的数字组合的乘
	// prime numbers， 比如[2,3,5] 
	// So this is a All subsets problem
	// BF
	public static List<Integer> getAllPrime(int[] arr){
		List<Integer> res = new ArrayList<>();
		if(sanityCheck(arr)) return res;
		combinations(arr,0,1,res);
		res.remove(1);
		return res;
	}

	public static void combinations(int[] arr, int index,int  cur, List<Integer> res){
		res.add(cur);
		if(index == arr.length){
			return;
		}
		combinations(arr, index + 1, cur * arr[index], res);
		combinations(arr, index + 1, cur,res);
	}

	//follow up
	//每层只考虑一个
	//本质控制每一次选择后 之后不会再选到当前的这个值
	public static void combinationsFollowSort(int[] arr, int index,int cur, List<Integer> res){
		res.add(cur);
		if(index == arr.length){
			return;
		}
		combinationsFollowSort(arr,index+1, cur * arr[index], res);					//add the first arr[index]
		while(index < arr.length - 1 && arr[index] == arr[index + 1]){			//skio all the same value
			++index;
		}
		combinationsFollowSort(arr,index + 1, cur * arr[index], res);
	}

	private static boolean sanityCheck(int[] arr){
		return arr == null || arr.length == 0;
	}

	// 2019 - 4 - 6 /
	// maxSubsetSum
	// 
	// public static void main(String[] args) {
	// 	int[] n = {3, 2, 1 ,2, 2, 1 , 1,  1};
	// 	System.out.print(getMax(n));
	// }

	public static int getMax(int[] arr){
		int res = 0;
		//sanity check
		if( arr == null || arr.length == 0) {
			return res;
		}
		//count and store by order
		TreeMap<Integer,Integer> count = new TreeMap<>();			//key: number, value: last index for the number;
		buildMemo(count,arr);
		List<Integer> orderlist = new ArrayList(count.keySet());
		return getResult(count,orderlist,0,new HashMap<>());
	}
	
	//DP - 用小空间
	public static int getResult(TreeMap<Integer,Integer> count, List<Integer> orderlist, int index,HashMap<Integer,Integer> memo){
		if( index >= orderlist.size()){
			return 0;
		}
		//memo
		if(memo.containsKey(index)) {
			return memo.get(index);
		}
		int curNum = orderlist.get(index);
		int countNum = count.get(curNum);
		int res = curNum * countNum;
		if(index + 1 < orderlist.size() && orderlist.get(index + 1) == curNum + 1) {
			// curNum + 1 == nextNum;
			res = Math.max(res + getResult(count,orderlist,index + 2,memo), getResult(count,orderlist,index + 1,memo));
		}else {
			// curNum + 1 != nextNum;
			res += getResult(count,orderlist,index + 1,memo);
		}
		//memo
		memo.put(index,res);
		return res;	
	}

	public static int getResult(int[]memo, int index){
		if(index >= memo.length){
			return 0;
		}
		int res = memo[index] * index + getResult(memo,index + 2);
 		return Math.max(res,getResult(memo,index + 1));
	}
	
	//用定长arr
	private static void buildMemo(int[]memo, int[] arr){
		for(int i : arr){
			memo[i]++;
		}
	}

	//用动态map
	private static void buildMemo(TreeMap<Integer,Integer> memo, int[] arr){
		for(int i : arr){
			memo.put(i,memo.getOrDefault(i, 0) + 1);
		}
	}

	// 2019 - 4 - 2
	// Given a string, rearrange it so that no same characters are adjacent to each other.
	public static String rearrangeString(String str){
		//sanity check
		if(str == null || str.length() == 0){
			return str;
		} 
		//不如最后sort一遍
		TreeMap<Character,Integer> map = new TreeMap<>();
		for(Character c : str.toCharArray()){
			map.put(c,map.getOrDefault(c,0)+1);
		}
		StringBuilder res = new StringBuilder();
		
		//
		while(map.size() > 0){
			Character largest = map.firstKey();
			Character secLarget = map.higherKey(largest);
			if(largest != null){
				res.append(largest);
				int count = map.remove(largest);
				if( count > 1){
					map.put(largest,count - 1);
				}
			}
			if(secLarget != null){
				res.append(secLarget);
				int count = map.remove(secLarget);
				if( count > 1){
					map.put(secLarget,count - 1);
				}
			}
		}
		return res.toString();
	}
	
	//BF: BackTrack
	public static String rearrangeStringBF(String str){
		char[] c = str.toCharArray();
		return backTrack(c,0) ? new String(c) : null;
	}

	public static boolean backTrack(char[] c,int index){
		if(index == c.length){
			return checkVaild(c);
		}
		//优化:相同的不用换2遍
		Set<Character> visit = new HashSet<>();
		for(int i = index; i < c.length; ++i){
			if(!visit.add(c[i])) continue;

			swap(c,index,i);
			if(backTrack(c,index + 1)){
				return true;
			}
			swap(c,index,i);
		}
		return false;
	}
	
	public static boolean checkVaild(char[] c){
		for(int i = 0; i < c.length - 1; ++i){
			if(c[i] == c[i+1]) return false;
		}
		return true;
	}

	public static void swap(char[] c, int i, int j){
		char temp = c[i];
		c[i] = c[j];
		c[j] = temp;
	}
	
	//Greedy + 
	public static Character[] rearrangeStringGreedy(String str){
		if(str == null || str.length() == 0){
			return null;
		} 
		HashMap<Character,Integer> map = new HashMap<>();
		for(Character c : str.toCharArray()){
			map.put(c,map.getOrDefault(c,0)+1);
		}
		List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
		Collections.sort(list, (o1,o2) -> o1.getValue() - o2.getValue());
		Character[] res = new Character[str.length()];
		int start = 0;
		for(Map.Entry<Character, Integer> entry : list){
			start = buildword(res,start,entry.getKey(),entry.getValue());
		}
		return res;
	}
	
	private static int buildword(Character[] arr, int start,Character c, int number){
		int nextStart = start - 1;
		for(int i = start; i < arr.length; ++i){
			if(nextStart == i - 1){
				nextStart = i;
			}
			if(arr[i] != null){
				continue;
			}
			arr[i++] = c;
			number--;
			if(number == 0){
				break;
			}
		}
		return nextStart;
	}

	// public static int minStepRearrange(){

	// }

	// 2019 - 4 - 1
	// Given an array of integers which are increasing first then descreasing pattern, find the max number of the array
	// input = [1, 3, 5, 7, 6, 3], output = 7

	public static int findMont(int[] arr){
		//sanity check
		if( arr == null || arr.length == 0 ){
			return  -1;
		}
		int left = 0 , right = arr.length - 1;
		while(left <= right){
			int mid = left + (right - left) / 2;
			if( arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid]){
				return mid;
			}else{
				if(arr[mid - 1] > arr[mid]){
					right = mid - 1;
				}else if( arr[mid + 1] > arr[mid]){
					left = mid + 1;
				}
			}
		}	
		return -1;
	}

	// String 分行
	// public static void main(String[] args) {
	// 	List<String[]> curList = new ArrayList<>();
	// 	List<Integer[]> curInt = new ArrayList<>();
	// 	curList.add(new String[]{"abcdefg","abc","abcde"});
	// 	curList.add(new String[]{"ab","abcde","abcd"});
	// 	curInt.add(new Integer[]{3,3,3});
	// 	curInt.add(new Integer[]{3,3,3});
	// 	List<List<String>> res = getRow(curList,curInt);
	// 	for(List<String> s : res){
	// 		for(String str : s){
	// 			System.out.print(str + " | ");
	// 		}
	// 		System.out.println();
	// 	}
	// }
	public static List<List<String>> getRow(List<String[]>in, List<Integer[]> eachNumRol){
		List<List<String>> res = new ArrayList<>();
		//sanity check skip
		for(int i = 0 ; i < in.size() ; ++i){
			Integer[] curList = eachNumRol.get(i);
			String[] curStr = in.get(i);
			
			int time = 0, count = 0;
			while(true){
				List<String> tempres = new ArrayList<>();
				count = 0;
				for( int j = 0; j < curStr.length; ++j ){
					StringBuilder sb = new StringBuilder();
					for(int k = 0; k < curList[j]; k++){
						if( time * curList[j] + k < curStr[j].length()){
							sb.append(curStr[j].charAt( time * curList[j] + k ));
							count++;
						}else{
							sb.append(" ");
						}
					}
					tempres.add(sb.toString());
				}
				if(count == 0){
					break;
				}
				time++;
				res.add(new ArrayList<>(tempres));
			}
		}
		return res;
	}

	// Last Permutation Next Permutation
	// 
	// public static void main(String[] args) {
	// 	int[] n  = {0,0};
	// 	nextPermutation(n);
	// 	//lastPermutation(n);
	// 	System.out.print("");
	// }

    public static void nextPermutation(int[] nums) {
        //sanity check
         if(nums == null || nums.length == 0 ){
             return;
         }
         TreeMap<Integer,Integer> memo = new TreeMap<>();
         for(int i = nums.length -1; i >= 0 ;--i){
             Integer larger = memo.higherKey(nums[i]);
             if(larger != null){
                 swap(nums,i, memo.get(larger));
                 Arrays.sort(nums,i+1,nums.length);
                 return;
             }
             memo.put(nums[i],i);
             if(i == 0){
                Arrays.sort(nums); 
             }
         } 
     }
     
    public static void lastPermutation(Integer[] nums) {	//不能是
        //sanity check
         if(nums == null || nums.length == 0 ){
             return;
         }
         TreeMap<Integer,Integer> memo = new TreeMap<>();
         for(int i = nums.length -1; i >= 0 ;--i){
             Integer larger = memo.lowerKey(nums[i]);
             if(larger != null){
                 swap(nums,i, memo.get(larger));
                 Arrays.sort(nums,i+1,nums.length,Collections.reverseOrder());
                 return;
             }
             memo.put(nums[i],i);
             if(i == 0){
                Arrays.sort(nums); 
             }
         } 
     }
    
    public static void swap(Integer[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
	
     public static void swap(int[] nums, int i, int j){
         int temp = nums[i];
         nums[i] = nums[j];
         nums[j] = temp;
     }

	// 2019 - 3 - 28
	// we need some way to record the data in last 5 minutes for any current timestamp, because we only care about last 5 minutes, 
	// so we can use a queue to record each data with its timestamp. we need to calculate the median of last 5 minutes’ data, so we use treeset.
	// 
	//  一堆数据get max, 但是数据是有timestamp的 也就是说要定期清除过期的数据 -- 用deque解决就行
	//  follow up是改成求median, 我假设的是数据能全部in memory 用quick select优化到o(n)的复杂度
	class Data{
		int val;
		long timestamp;
		public Data(long timestamp,int val){
			this.val = val;
			this.timestamp = timestamp;
		}
	}

	//trade off 看Get操作决定是单次操作还是
	//如果操作少用quick select做 median 能到 logn(平均) * call的次数
	class DataSteam{
		//order datastruct
		private Deque<Data> store;
		private PriorityQueue<Data> pq;
		private TreeSet<Data> tMap;
		private Data mid;

		private Long breakTime;
		private int leftSize;
		private int rightSize;

		public DataSteam(Long breakTime){
			this.store = new ArrayDeque<>();
			this.pq = new PriorityQueue<>((o1,o2) -> o1.val - o2.val);
			this.tMap = new TreeSet<>((o1,o2) -> o1.val - o2.val);
			this.breakTime = breakTime;
			this.leftSize = 0;
			this.rightSize = 0;
		}

		public void add(long time, int val){
			remove(time + breakTime);
			Data nData = new Data(time,val);
			store.offerFirst(nData);
			//max
			pq.offer(nData);
			//median
			if(mid.val < val){
				if(leftSize < rightSize){
					
				}
			}else{

			}
			
		}

		private void remove(long time){
			while( !store.isEmpty() && store.peek().timestamp < time){
				Data data = store.pollLast();
				pq.remove(data);
				tMap.remove(data);
			}
			
		}

		public Integer getMax(){
			return pq.isEmpty() ? null : pq.peek().val;
		}

		public Integer getMedian(){
			return pq.isEmpty() ? null : pq.peek().val;
		}
	}
	
	//给了一堆feed story，每个story有score & length两种特性
	//要求选出story subset -- 要求是所有story的长度加起来不能超过限制 然后story个数越多越好 以及subset里面的story‘score大于所有其他不在subset里面的. Fr
	public static List<Integer> storyScore(int[] story, int[] score){
		List<Integer> res = new ArrayList<>();
		// if(){

		// }
		return res;
	}

	//2019 - 3 - 1
	//给一组数字和一个target number，从这组数字种选若干个使得加和为target。问有多少种方法。是一道利口原题，类似于knapsack
	public static int get(int[] arr, int target){
		//sanity check
		if(arr == null || arr.length == 0){
			return 0;
		}
		int[] memo = new int[target + 1]; 	//store 
		memo[0] = 1;
		for( int i = 0; i < arr.length; ++i){
			for(int j = target; j >= 0 ; --j){
				if(memo[j] != 0 && j + arr[i] < target){
					memo[j + arr[i]] += memo[j];
				}
			}
		}
		return memo[target];
	}

	//dfs
	public static int dfs(int[] arr, int index, int target){
		if(index == arr.length){
			return target == 0 ? 1 : 0;
		}
		return dfs(arr,index + 1, target - arr[index]) +  dfs(arr, index + 1, target);
	}


	//2019 - 2 - 28
	//一个list含meeting interval. let time = 5, query出几个正在进行的meeting
	class Interval{
		int start,end;
		public Interval(int start,int end){
			this.start = start;
			this.end = end; 
		}
	}

	public static int getMeetingNumber(List<Interval> meetingList,int time){
		//sanity check
		if(meetingList == null || meetingList.size() == 0){
			return 0;
		}
		int res = 0;
		for(Interval i : meetingList){
			if(i.start <= time && i.end >= time) res++;
		}
		return res;
	}	

	//max of window size K
	public static int maxWindow(int[] arr,int k){
		//
		int max = 0, sum = 0;
		int l = 0;
		for(int r = 0; r < arr.length; ++r){
			sum += arr[r];
			if( r >= k ){
				sum -= arr[l++];
			}
			max = Math.max(max,sum);
		}
		return max;
	}

	//2019 - 2 - 23
	//path sum: count number of paths from the root summing to the target
	//root to leaf ? root  to any ? any to any? any to leaf?
	class TreeNode{
		int val;
		TreeNode left, right;
		public TreeNode(int val){
			this.val = val;
		}
	}
	
	public static int countNum(TreeNode root, int target){
		//sanity check
		if(root == null){
			return 0;
		}
		return countNumRootToLeaf(root, target);
		//return countNumRootToAny(root, target);		
	} 

	//root to leaf
	public static int countNumRootToLeaf(TreeNode root, int target){
		if(root == null){
			return target == 0 ? 1 : 0;
		}
		return countNumRootToLeaf(root.left,target - root.val) + countNumRootToLeaf(root.right,target - root.val);
	}

	//root to any
	public static int countNumRootToAny(TreeNode root, int target){
		if(root == null){
			return target == 0 ? 1 : 0;
		}
		return countNumRootToAny(root.left,target - root.val) + countNumRootToAny(root.right,target - root.val) + target == 0 ? 1 : 0;
	}

	//any to leat 
	public static void countNum(TreeNode root, int target, int o, int[] res){
		if(root == null){
			return;
		}
		res[0] += target == 0 ? 1 : 0;
		countNum(root.left,target - root.val, o,res);
		countNum(root.right,target - root.val,o,res);
		countNum(root.left,o,o,res);
		countNum(root.right,o,o,res);
	}
	
	//LC297
	//Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //base case
        if(root == null) return "#";
        String res =  root.val + " " + serialize(root.left) + serialize(root.right);
        return res;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //sanity check
        if(data == null || data.length() == 0){
        	return null;
        }
        return desHelper(data,new int[]{0});
    }

    public TreeNode desHelper(String data, int[] index){
    	if(index[0] >= data.length()){
    		return null;
    	}
        if(data.charAt(index[0]) == '#'){
            index[0]++;
    		return null;   
        }
        int neg = 1;										//get pos or nag
        if(data.charAt(index[0]) == '-'){
            index[0]++;
            neg = -1;
        }
    	int val = 0;										//get val
    	while(index[0] < data.length() && Character.isDigit(data.charAt(index[0]))){
    		val *= 10;
			val += data.charAt(index[0]++) - '0';
    	}
    	index[0]++;											//skip space
    	TreeNode root = new TreeNode(val * neg);
    	root.left = desHelper(data,index);
    	root.right = desHelper(data,index);
    	return root;
    }

    //LC 113. Path Sum II
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        pathS(root,res,new ArrayList<>(),sum);
        return res;
    }
    
    public static void pathS(TreeNode root, List<List<Integer>> res, List<Integer> cur, int sum){
        if(root == null){
            return;
        }
        cur.add(root.val);
        if(root.left == null && root.right == null){
            if(sum == root.val){   
                res.add(new ArrayList<>(cur));
            }
            cur.remove(cur.size() - 1);
            return;
        }
        
        pathS(root.left,res,cur,sum - root.val);
        pathS(root.right,res,cur,sum - root.val);
        
        cur.remove(cur.size() - 1);
    }

    //LC 437. Path Sum III
    //BF
    public static int pathSumIII(TreeNode root, int sum) {
        if(root == null) return 0;
        return  pathS(root,sum) + pathSumIII(root.left,sum) + pathSumIII(root.right,sum);
    }
    
    public static  int pathS(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        int res = (root.val == sum ? 1 : 0);
        res += pathS(root.left,sum - root.val) + pathS(root.right,sum - root.val);
        return res;
    }

    //prefix sum
    public static int pathSumPrefix(TreeNode root, int sum) {
        if(root == null) return 0;
        List<Integer> list = new LinkedList<>();
        list.add(0);
        return pathS(root,sum,list);
    }
    
    public static int pathS(TreeNode root, int sum, List<Integer> sumList){
        if(root == null){
            return 0;
        }
        sumList.add(sumList.get(sumList.size()-1) + root.val);
        int res = pathS(root.left,sum,sumList) + pathS(root.right,sum,sumList);
        int prev = sumList.remove(sumList.size()-1);
        for(int last : sumList){
            if(prev - last == sum){
                res++;
            }
        }
        return res;
    }

    //2019 - 2 - 8
    /* f(0)="";			0	2^0
	 * f(1)="0";	 	1	2^1
	 * f(2)="1";		10
	 * f(3)="00";		11	2^2
	 * f(4)="01";		100
	 * f(5)="10";		101
	 * f(6)="11";		110
	 * f(7)="000";		111
	 * 求给任意一个n 打印出string
	 */
 	//public static void main(String[] args) {
	// 	int n  = Integer.MAX_VALUE - 1;
	// 	System.out.print(getNum(n));
	// }

	public static String getNum(int n) {
		return Integer.toBinaryString(n + 1).substring(1);
	}


	//LC 168. Excel Sheet Column Title
	public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while(n > 0){
            int cur = --n % 26;
            sb.insert(0,(char)('A'+ cur));
            n /= 26;
        }
        return sb.toString();
    }



}