import java.util.*;
//
public class Bloomberg{
	public static void main(String[] args) {
		String str = "abbaa";
		String res = xxl(str.toCharArray());

		System.out.print(permutation(str));
	}

	//LC463 Island Perimeter + 138 Copy List with Random Pointer
	
	//LC88
	//Input:	nums1 = [1,2,3,0,0,0], m = 3
	//			nums2 = [2,5,6],       n = 3
	//Output: [1,2,2,3,5,6]

	//LC 3 Longest Substring Without Repeating Characters + 如何检验正确性

	//数组找出现次数最多的数字
	//方法一 BF: 对每个index扫一遍有几何相同的 
	//方法二 BF + memo: 因为有重复扫描可以用一种方式记录所有扫描过的值 - space增加
	//方法二 Sort: 通过保持数字的连续,可以直接知道结果
	
	// input [1,2,3,4]
	// 返回 [24,12,8,6]
	// 本质从右边记录[24,12.4.1]
	
	// public static int[] getProduct(int[] arr){
	// 	return 
	// }

	//String判断是否存在permutation是palindrome
	public static boolean permutation(String str){
		Map<Character,Integer> map = new HashMap<>();
		for(Character c : str.toCharArray()){
			map.put(c,map.getOrDefault(c,0) + 1);
		}
		boolean onPair = false;
		for( Map.Entry<Character,Integer> m : map.entrySet()){
			if( !onPair && (m.getValue()%2 == 1) ){
				onPair = !onPair;
			}else if(onPair && (m.getValue()%2 == 1)){
				return false;
			}
		}
		return true;
	}

	//abbbaac -> 消消乐
	public static String xxl(char[] array){
		//sanity check
		if(array == null || array.length == 0) {
			return "";
		}
		int i = 0;
		for (int j = 1; j < array.length; j++) {
			if (i == -1 || array[i] != array[j]) {
				array[++i] = array[j];
			}else {
				i--;
				while (j + 1 < array.length && array[j] == array[j + 1]) {
					j++;
				}
			}
		}
		return new String(array, 0, i + 1);
	}
	
	//LC33 Search in Rotated Sorted Array

	//LRU LC146
	
	//LC 139 Word Break    
	// Input: s = "leetcode", wordDict = ["leet", "code"] Output: true
	
	//LC56 Merge Intervals
	
	//LC394 Decode String
	//s = "3[a]2[bc]", return "aaabcbc".
	
	//LC177
	
}