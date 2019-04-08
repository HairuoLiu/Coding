import java.util.*;
public class DrawBridge{

	public static void main(String[] args) {
		
	}
	
	//LC 273. Integer to English Words 给数字返回英文单词
	private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

	public String numberToWords(int num) {
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

	private String helper(int num) {
	    if (num == 0)
	        return "";
	    else if (num < 20)
	        return LESS_THAN_20[num] + " ";
	    else if (num < 100)
	        return TENS[num / 10] + " " + helper(num % 10);
	    else
	        return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
	}
	//1. BST two sum 2. Remove k digits 3.find longest parlindorme subseq

	// k nearly sort
	public static void insertionSort(int A[], int size) {  
		int i, key, j;  
		for (i = 1; i < size; i++) {  
    		key = A[i];  
    		j = i-1;  
  	
    		/* Move elements of A[0..i-1], that are greater than key, to one  
        	position ahead of their current position.  
        	This loop will run at most k times */
    		while (j >= 0 && A[j] > key){  
        		A[j+1] = A[j];  
        		j = j-1;  
    		}  
    		A[j+1] = key;  
		}  
	}  

	//LCA of Deepest Nodes in Binary Tree

	//Intersection of Two Arrays + Intersection of Two Sorted Arrays
	//
	//
	//
}