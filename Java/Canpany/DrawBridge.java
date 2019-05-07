import java.util.*;
public class DrawBridge{

	// public static void main(String[] args) {
	// }
	
	// 印度小哥
	// LC 273.Integer to English Words 给数字返回英文单词
    public String[] lessTen = {"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    public String[] lessTwenty = {"Ten","Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    public String[] lessHundred = {"","Ten","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};   
    
    public String numberToWords(int num) {
        if(num == 0) return "Zero";
        return getStr(num);
    }
    
    public String getStr(int num){
        if(num < 10){
            return lessTen[num];
        }else if(num < 20){
            return lessTwenty[num - 10];
        }else if(num < 100){
            String nRes = getStr(num%10);
            return lessHundred[num/10] + (nRes.length() == 0 ? "":" " + nRes) ;
        }else if(num < 1000){
            String nRes = getStr(num%100);
            return lessTen[num/100] + " Hundred" + (nRes.length() == 0 ? "":" " + nRes);
        }else if(num < 1000000){
            String nRes = getStr(num%1000);
            return getStr(num/1000) + " Thousand"+ (nRes.length() == 0 ? "":" " + nRes);
        }else if(num < 1000000000){
            String nRes = getStr(num%1000000);
            return getStr(num/1000000) + " Million" + (nRes.length() == 0 ? "":" " + nRes);
        }else{
            String nRes = getStr(num%1000000000);
            return getStr(num/1000000000)+ " Billion" + (nRes.length() == 0 ? "":" " + nRes);
        }
    }

	//Reverse word break 2
	public int StringToInteger (String str){
		Map<Character,Integer> map = new HashMap<>();
		String[] input = str.split(" ");
		int res = 0 , tmp = 0;
		for (int i = 0; i < input.length; i ++) {
			Integer t = map.get(input[i]);
			if ( t < 100 ){
				tmp += t;
			}else if( t < 1000 ){
				tmp *= t;
			}else{				//>1000
				res += tmp * t;
				tmp = 0;
			}	
		}
		return res + tmp;
	}

	//Binary tree 给root, node, 要求打印出从root 到给定node的path
	// target 不嫩为null
	List<TreeNode> printPath(TreeNode root ,TreeNode target){
		if(root == null){
			return null;
		}
		if(root == target){
			List<TreeNode> res = new LinkedList<>();
			res.add(target);
			return res;
		}
		List<TreeNode> pl = printPath(root.left,target);
		List<TreeNode> pr = printPath(root.right,target);
		if(pl != null){
			pl.add(0,root);
			return pl;
		}else if(pr != null){
			pr.add(0,root);
			return pr;
		}
		return null;
	}

	static class TreeNode{
		int val;
		TreeNode left,right;
		public TreeNode(int val){
			this.val = val;
		}
	}

	//1. BST two sum 
	//简单版
	public boolean findTarget(TreeNode root, int k) {
        return find(root,k,new HashSet<>());
    }
    public boolean find(TreeNode root, int k, Set<Integer> set){
        if(root == null){
            return false;
        }
        if(find(root.left,k,set) || find(root.right,k,set)){
            return true;
        }
        if(set.contains(k - root.val)){
            return true;
        }
        set.add(root.val);
        return false;
    }

    //困难版
	public static int bstTwoSum(TreeNode root, int target){
		return inorder(root,target,new HashSet<>());
	}

	public static int inorder(TreeNode root,int target,Set<Integer> set){
		if(root == null){
			return 0;
		}
		set.add(root.val);
		int res = inorder(root.left,target,set) + inorder(root.right,target,set);
		int t = target - root.val;
		if( t != root.val && set.contains(t)){
			res++;
		}
		return res;
	}

	//2. Remove k digits 
	//
	
	//3.find longest parlindorme subseq
	// for each range we try to find the longest parlindorme
	public static void main(String[] args) {
		String str = "BBABCBCAB";
		int[][] memo = new int[str.length()][str.length()];
		for(int[] arr :memo){
			Arrays.fill(arr,-1);
		}
		int res = longestParlindorme(str,0,str.length()-1,memo);
		// String res = longestParlindormeDP(str);
		System.out.println(res);
		String ans = buildParlindorme(memo,0,str.length()-1,str);
		System.out.print(ans);
	}

	public static int longestParlindorme(String str, int l, int r, int[][] memo){
		if(l == r || l + 1 == r){
			return str.charAt(l) == str.charAt(r) ?  r - l + 1 : 0;
		}
		if(memo[l][r] != -1){
			return memo[l][r];
		}
		int res = 0;
		if(str.charAt(l) == str.charAt(r)){
			res = longestParlindorme(str,l+1,r-1,memo) + 2;
		}else{
			res = Math.max(longestParlindorme(str,l+1,r,memo),longestParlindorme(str,l,r-1,memo));
		}
		memo[l][r] = res;
		return res;
	}

	public static String buildParlindorme(int[][] memo,int l,int r,String str){
		if(l == r || l + 1 == r){
			return str.substring(l,r+1);
		}
		if(str.charAt(l) == str.charAt(r)){
			return str.charAt(l) + buildParlindorme(memo,l+1,r-1,str) + str.charAt(r);
		}else {
			return memo[l+1][r] > memo[l][r-1] ? buildParlindorme(memo,l+1,r,str):buildParlindorme(memo,l,r-1,str);
		}
	}

	public static String longestParlindormeDP(String str){	
		int slen = str.length();
		int[][] memo = new int[slen][slen];
		for(int[] arr :memo){
			Arrays.fill(arr,-1);
		}
		for(int len = 0; len < str.length(); ++len){
			for(int i = 0; i + len < str.length(); ++i){
				if(len == 0 || len == 1){
					memo[i][i + len] = str.charAt(i) == str.charAt(i+len) ? len + 1 : 0;
				}else if(str.charAt(i) == str.charAt(i+len)){
					memo[i][i + len] = memo[i+1][i + len - 1] + 2;
				}else{
					memo[i][i + len] = Math.max(memo[i + 1][i + len], memo[i][i + len - 1]);
				}
			}
		}
		char[] res = new char[memo[0][slen-1]];
		int i = 0, j = slen - 1;
		int l = 0, r = memo[0][slen-1] - 1;
		while(i < slen && j >= 0 && l <= r){
			if(memo[i][j-1] == memo[i+1][j] && memo[i][j-1] != memo[i][j] ){
				res[l++] = str.charAt(i++);
				res[r--] = str.charAt(j--);
			}else if(memo[i+1][j] == memo[i][j]){
				i++;
			}else{
				j--;
			}
		}
		return new String(res);
	}

	// k nearly sort
	// public static void main(String[] args) {
	// 	int[] A = {6, 5, 3, 2, 7,5,8,10,9};
	// 	//bfNearlySorted(A,3);
	// 	insertionSortFollow(A,3);
	// 	for(int i : A){
	// 		System.out.print(i + " ");
	// 	}
	// }

	//O(N*K)
 	public static void bfNearlySorted(int A[], int size){
 		//sanity check skip
 		for(int i = 0; i < A.length; ++i){
 			int j = i + 1;
 			while( j < A.length && i + size >= j){
 				if(A[i] > A[j]){
 					swap(A,i,j);
 				}
 				++j;
 			}
 		}
 	}

 	public static void swap(int[] A, int i, int j){
 		int temp = A[i];
 		A[i] = A[j];
 		A[j] = temp;
 	}

 	//O(k) + O((n-k)*logK)

	// n * (logK) 实现有点问题
	public static void insertionSortFollow(int A[], int size){
		TreeMap<Integer,TreeSet<Integer> > tMap = new TreeMap<>();	//store the element n to n + k		key is value, value	is index key
		Map<Integer,Integer> indexMap = new HashMap<>();			//key is index, value is value

		for(int i = 0; i < A.length + size - 1 ; ++i){
			if( i < A.length){
				if( !tMap.containsKey(A[i]) ) {
					tMap.put(A[i],new TreeSet<>());
				}
				tMap.get(A[i]).add(i);
				indexMap.put(i,A[i]);
			}
			
			if(i >= size){
				// Get smallest value and index first value
				Integer smallestValue = tMap.firstKey(), firstKey = i - size;	//O(logk)
				int firstValue = indexMap.remove(firstKey);						//O(1)							
				tMap.get(firstValue).remove(firstKey);							//remove first index from valueMap time: o(log(size)) worst case k
				
				if(firstValue > smallestValue){
					//get All
					Integer smallestKey = tMap.get(smallestValue).first();  	//o(log(size)) worst case k
					tMap.get(smallestValue).remove(smallestKey);				//o(log(size)) worst case k

					swap(A,smallestKey,firstKey);

					if(tMap.get(smallestValue).size() == 0){								
						tMap.remove(smallestValue);
					}
					tMap.get(firstValue).add(smallestKey);						//o(log(size)) worst case k
					indexMap.put(smallestKey,firstValue);
				}
				//if firstValue is empty
				if(tMap.get(firstValue).size() == 0){
					tMap.remove(firstValue);
				}
			}
		}
	}

	//LCA of Deepest Nodes in Binary Tree
	static class Node{
		int highet;
		TreeNode node;
		public Node(){
			this.highet = 0;
		}
		public void addHighet(){
			this.highet++;
		}
	}

	public static TreeNode LCAdeepest(TreeNode root){
		if(root == null){
			return null;
		}
		return getDeepest(root).node;
	}

	public static Node getDeepest(TreeNode root){
		if(root == null) return new Node();

		Node left = getDeepest(root.left);
		Node right = getDeepest(root.right);
		left.addHighet();
		right.addHighet();
		if(left.highet == right.highet){
			left.node = root;
			return left;
		}else{
			return left.highet > right.highet ? left : right;
		}
	}

	//Intersection of Two Arrays + Intersection of Two Sorted Arrays
	//no sort
	public static List<Integer> intersection(int[] arr1 , int[] arr2){
		//sanity check
		List<Integer> res = new ArrayList<>();
		if(arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0){
			return res;
		}
		Map<Integer,Integer> map = new HashMap<>();
		for(int i : arr1){
			map.put(i,map.getOrDefault(i,0) + 1);
		}
		for(int i : arr2){
			if(map.containsKey(i)){
				res.add(i);
				if(map.get(i) == 1){
					map.remove(i);
				}
				map.put(i,map.get(i) - 1);
			}
		}
		return res;
	}

	//sort
	public static List<Integer> intersectionSort(int[] arr1 , int[] arr2){
		List<Integer> res = new ArrayList<>();
		if(arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0){
			return res;
		}
		for(int i = 0, j = 0 ; i < arr1.length && j < arr2.length;){
			if(arr1[i] == arr2[j]){
				res.add(arr1[i]);
				++i;++j;
			}else if(arr1[i] < arr2[j]){
				++i;
			}else{
				++j;
			}
		}
		return res;
	}

	// LC11 Container With Most Water
	public int maxArea(int[] height) {
		//sanity check
        if(height.length < 2){
        	return 0;
        }
        int max = 0;
        for(int left = 0 , right = height.length - 1; left < right;){
        	max = Math.max(max, (right - left) * Math.min(height[left],height[right]) );
        	if(height[left] > height[right]) right--;
        	else left++;
        }
        return max;
    }

    // 输入一个array，要求将其切分成两个subarray，使两个subarray中的unique number的个数相等
    // public static void main(String[] args) {
    // 	int [] arr = {10,3,1,2};
    // 	int res = getBlock(arr);
    // 	System.out.print(res);
    // }
    public static int getBlock(int[] arr){
    	int res = -1;
    	Map<Integer,Integer> memo = new HashMap<>();
    	int diff = 0;
    	for(int i : arr){
    		Integer cur = memo.get(i);
    		if(cur == null){
				memo.put(i,1);
				diff++;
    		}else{
				memo.put(i,cur + 1);
				diff = cur == 1 ? diff - 1 : diff;
    		}
    	}	
    	int dif = 0;
    	Map<Integer,Integer> m = new HashMap<>();
    	for(int i = 0 ; i < arr.length; ++i){
    		Integer cur = m.get(i);
    		if(cur == null){
				m.put(arr[i],1);
				dif++;
    		}else{
				m.put(arr[i],cur + 1);
				dif = cur == 1 ? dif - 1 : dif;
    		}
    		Integer r = memo.remove(arr[i]);
    		if(r >= 2){
    			memo.put(arr[i],r - 1);
    			diff = r == 2 ? diff+1 : diff;
    		}else if(r == 1){
    			diff--;
    		}
    		if(diff == dif) return i;
    	}
    	return res;
    }

    // public static void main(String[] args) {
    // 	List<List<Integer>> res = getAllFactor(8);
    // 	for(List<Integer> l : res){
    // 		for(Integer j : l){
    // 			System.out.print(j +" ");
    // 		}
    // 		System.out.println();
    // 	}
    // }

    //列出一个数字的所有factor组合
    public static List<List<Integer>> getAllFactor(int num){
    	 List<List<Integer>> res = new ArrayList<>();
    	 List<Integer> factorList = new ArrayList<>();
    	 for(int i = 2; i <= num/2; ++i){
    	 	if(num % i == 0){
    	 		factorList.add(i);
    	 	}
    	 }
    	 find(num,num,new ArrayList<>(),res);
    	 return res;
    }

    public static void find(int num, int pre, List<Integer> cur, List<List<Integer>> res){
    	if(num == 1){
    		if(cur.size() == 1){
				cur.add(1);
				res.add(new ArrayList<>(cur));
				cur.remove(cur.size()-1);
    		}else{
    			res.add(new ArrayList<>(cur));
    		}
    		return;
    	}
    	for(int i = pre; i >= 2; --i){		//we can find all factor first
    		if(num % i == 0){
    			cur.add(i);
    			find(num/i,i,cur,res);
    			cur.remove(cur.size()-1);
    		}
    	}
    }

    //[0,0,0,0,0,0,0,0] -> [0,1,1,1,0,0,0,0]
   	
}