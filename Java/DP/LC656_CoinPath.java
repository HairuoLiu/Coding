
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer Bdenotes that from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to the place indexed i in the array.
 *
 * Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum coins.
 *
 * If there are multiple paths with the same cost, return the lexicographically smallest such path.
 *
 * If it's not possible to reach the place indexed N then you need to return an empty array.
 *
 * Example 1:
 *
 * Input: [1,2,4,-1,2], 2
 * Output: [1,3,5]
 *
 *
 * Example 2:
 *
 * Input: [1,2,4,-1,2], 1
 * Output: []
 *
 *
 * Note:
 *
 * Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ, Pai < Pbi; when no such i exists, then n < m.
 * A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
 * Length of A is in the range of [1, 1000].
 * B is in the range of [1, 100].
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC656_CoinPath {

  public static void main(String[] args) {
    int[] array = {1,2,4,-1,2};
    int k = 2;
    List<Integer> res = cheapestJumpBF(array,k);

    System.out.println(res + " / ");
  }

  //When we think about the problem we try
  public static List<Integer> cheapestJumpBF(int [] A, int B) {
    int max = Integer.MAX_VALUE;
    List<Integer> res = new LinkedList<>();
    int[] min = {Integer.MAX_VALUE};
    DFS(res,new LinkedList<>(),min,0,A,B,0);
    return res;
  }

  //为啥list不回传
  public static void DFS(List<Integer> res, List<Integer> temp, int[] min,int cur, int[] A,int B,int level){
    if(A[level] < 0){
      return;
    }
    if( level >= A.length - 1 ){
      if(min[0] > cur){
        res = new ArrayList<>(temp);
        min[0] = cur;
      }
      return;
    }
    for(int i = 1; i <= B && i +level < A.length; i++){
      temp.add(level);
      DFS(res,temp,min,cur + A[level],A,B,level+i);
      temp.remove(temp.size()-1);
    }
  }


  //m[] set<max,list<Integer>> 到当前点的最小值
  //
  public static int [] cheapestJumpDP(int [] A, int B){
    int[] m = new int[A.length];
    Arrays.fill(m,Integer.MAX_VALUE);
    int Alen = A.length;
    // for each digit
    for(int i = Alen - 1;  i >= 0; i--){
      if(A[i] > 0){
        for(int j = Math.max(0, (i - B)); j < i ;j++){
          if (m[j] == Integer.MAX_VALUE){
            continue;
          }
          if(m[j] > 0){
            m[i] = Math.min(A[i] + m[j],m[i]);
          }
        }
      }
    }
    int [] res = new int[0];
    return res;
  }

}
