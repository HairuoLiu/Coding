
/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check. Since each version is developed based
 * on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which
 * causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to
 * the API.
 *
 * Example:
 *
 * Given n = 5
 *
 * call isBadVersion(3) -> false call isBadVersion(5) -> true call isBadVersion(4) -> true
 *
 * Then 4 is the first bad version.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC278_FirstBadVersion{

  public static void main(String[] args) {

    int target = 'k';
    int ans = firstBadVersion(target);
    System.out.println(ans);
  }

  public static int firstBadVersion(int n) {
    //sanity check
    if (n == 0) {
      return 0;
    }

    int left = 0,right = n;
    while (left <= right){
      int mid = left + (right - left)/2;
      if(isBadVersion(mid)){
        right = mid - 1;
      }else if(!isBadVersion(mid)){
        left = mid + 1;
      }
    }
    return left;
  }

  //not really
  public static boolean isBadVersion(int version){
    return true;
  }

}
