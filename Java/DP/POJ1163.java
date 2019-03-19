
/**
 *
 *     7
 *    3 8
 *   8 1 0
 *  2 7 4 4
 * 4 5 2 6 5
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:49
 */


public class POJ1163 {

  public static void main(String[] args) {
    int arr[][] = { {7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
    int ans1 = MaxSumBF(arr,0,0);
    int ans3 = MaxSumDP(arr);
    int ans4 = MaxSumDPplus(arr);

    System.out.println(ans1 + " / " + ans3 + " / " + ans4 );
  }

  //典型的递归问题。
  //D(r, j)出发，下一步只能走D(r+1,j)或者D(r+1, j+1)。故对于N行的三角形:
  public static int MaxSumBF(int[][]arr, int i, int j ) {
    if (i == arr.length - 1) {
      return arr[i][j];
    }else{
      return Math.max(MaxSumBF(arr,i+1,j), MaxSumBF(arr,i+1,j+1)) + arr[i][j];
    }
  }

  static Integer table[][];
  public static int MaxSumMemory(int[][]arr, int i, int j ) {
    if (i == arr.length - 1) {
      return arr[i][j];
    }else if(table[i][j] != null){
      return table[i][j];
    }else{
      return Math.max(MaxSumMemory(arr,i+1,j), MaxSumMemory(arr,i+1,j+1)) + arr[i][j];
    }
  }

  //i表示再第几层, j表示当前层的长度
  public static int MaxSumDP(int[][]arr) {
    int m[][] = new int[arr.length][arr[arr.length - 1].length];
    for (int i = arr.length - 1; i >= 0; i--) {
      for (int j = 0; j <= i; j++){
        if (i == arr.length - 1) {
          m[i][j] = arr[i][j];
        }else{
          m[i][j] = Math.max(m[i+1][j],m[i+1][j+1]) + arr[i][j];
        }
      }
    }
    return m[0][0];
  }

  //i表示再第几层, j表示当前层的长度
  public static int MaxSumDPplus(int[][]arr) {
    int m[] = new int[arr[arr.length - 1].length];
    for (int i = arr.length - 1; i >= 0; i--) {
      for (int j = 0; j <= i; j++){
        if (i == arr.length - 1) {
          m[j] = arr[i][j];
        }else{
          m[j] = Math.max(m[j],m[j+1]) + arr[i][j];
        }
      }
    }
    return m[0];
  }


}
