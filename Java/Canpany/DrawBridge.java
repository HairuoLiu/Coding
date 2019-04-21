import java.util.*;
public class DrawBridge{

	public static void main(String[] args) {
		
	}
	
	//LC 273. Integer to English Words 给数字返回英文单词
	private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

	public static String numberToWords(int num) {
	    if (num == 0) return "Zero";

	    int i = 0;
	    String words = "";
	    
	    while (num > 0) {
	        if (num % 1000 != 0)
	    	    words = helper(num % 1000) +THOUSANDS[i] + " " + words;
	    	num /= 1000;
	    	i++;
	    }
	    
	    return words.trim();
	}

	private static String helper(int num) {
	    if (num == 0)
	        return "";
	    else if (num < 20)
	        return LESS_THAN_20[num] + " ";
	    else if (num < 100)
	        return TENS[num / 10] + " " + helper(num % 10);
	    else
	        return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
	}

	class TreeNode{
		int val;
		TreeNode left,right;
		public TreeNode(int val){
			this.val = val;
		}
	}
	//1. BST two sum 
	public static int BSTTwoSum(TreeNode root, int target){
		return inorder(root,target,new HashSet<>());
	}

	public static int inorder(TreeNode root,int target,Set<Integer> set){
		if(root == null){
			return 0;
		}
		set.add(root.val);
		int res = inorder(root.left,target,set) + inorder(root.right,target,set);
		int t = target - root.val;
		if( t != root.val && set.contains(t) ){
			res++;
		}
		return res;
	}

	//2. Remove k digits 
	
	//3.find longest parlindorme subseq
	// for each range we try to find the longest parlindorme
	// if 
	public int longestParlindorme(String str){
		//int[] memo = new 
		int[][] memo = new int[str.length()][str.length()];
		// for(int i = 0; i < str.length; ++i){
		// 	for(int j = 0; i < str.length/2; ++j){
		// 		if(){

		// 		}
		// 	}
		// }
		return 0;
	}

	// k nearly sort
	// 
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
		Node right =  getDeepest(root.right);

		if(left.highet > right.highet){
			left.addHighet();
			return left;
		}else if(left.highet < right.highet){
			right.addHighet();
			return right;
		}else{
			left.addHighet();
			left.node = root;
			return left;
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


}