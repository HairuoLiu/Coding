import java.util.*;

public class Amazon{

	public static void main(String[] args) {
		int res = insertNumber(7);
		System.out.println(res);
		int res2 = insertNumber2(7);
		
	}

	//permutation 内部for对应每个能够添加的值, 然后可以优化每次计算并且添加的值
	//
	public static int insertNumber(int N){
	    if(N == 0){
	        return 0;
	    }
	    int[] memo = new int[N];
	    // List<Integer> list = new ArrayList<>();	优化减少重复计算
	    // while(num <= N - 1){
	    // 	list.add(num);
	    // 	num = num * 2;
	    // }
	    memo[0] = 1;
	    for(int j = 0; j < N; ++j){
	        for(int i = 0; (int)Math.pow(2,i) < N; ++i){
	        	int num = (int) Math.pow(2,i);
	            if(j + num >= N){
	               break; 
	            }
	            memo[j + num] += memo[j];
	        }
	    }
	    for(int i : memo){
			System.out.print(i+ " ");
	    }
	    return memo[N - 1];
	}

}