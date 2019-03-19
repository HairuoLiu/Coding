
import java.util.HashSet;
import java.util.Set;

/**
 *
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
 *
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 *
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
 *
 * Note:
 *
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 * Example 1:
 *
 * [0,1,3,5,6,8,12,17]
 *
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 *
 * Return true. The frog can jump to the last stone by jumping
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 * 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 *
 * Example 2:
 *
 * [0,1,2,3,4,8,9,11]
 *
 * Return false. There is no way to jump to the last stone as
 * the gap between the 5th and 6th stone is too large.
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC403_FrogJump{

  public static void main(String[] args) {
    int [] nums = {0,1,2,3,4,8,9,11};
    System.out.println(canCrossRec(nums));
  }

  // use a set or a busket to get the place is vaid or not
  public static boolean canCrossRec(int[] stones) {
    if(stones ==null || stones.length == 0){
      return false;
    }
    int len = stones.length;
    if(len == 1){
      return true;
    }
    Set<Integer> set = new HashSet<>();
    for(int i : stones){
      set.add(i);
    }
    return jumphelper(set,0,1,stones[stones.length-1]);
  }

  public static boolean jumphelper(Set<Integer> set,int now,int jumpNum,int end){
    if(end < jumpNum || jumpNum < 1){
      return false;
    }
    if(now == end){
      return true;
    }
    if (set.contains(now)) {
      return jumphelper(set,now+jumpNum,jumpNum - 1,end)||
          jumphelper(set,now+jumpNum,jumpNum,end)||
          jumphelper(set,now+jumpNum,jumpNum + 1,end);
    }
    return false;
  }


  public static boolean canCrossMem(int[] stones) {
    if(stones ==null || stones.length == 0){
      return false;
    }
    int len = stones.length;
    if(len == 1){
      return true;
    }
    Set<Integer> set = new HashSet<>();
    for(int i : stones){
      set.add(i);
    }
    return jumphelper(set,0,1,stones[stones.length-1]);
  }

  public static boolean[] m;
  public static boolean jumphelper2(Set<Integer> set,int now,int jumpNum,int end){
    if(end < jumpNum || jumpNum < 1){
      return false;
    }
    if(now == end){
      return true;
    }
    if(m[now]){
      return m[now];
    }
    if(set.contains(now)){
      return
          jumphelper2(set,now+jumpNum,jumpNum - 1,end)||
          jumphelper2(set,now+jumpNum,jumpNum,end)||
          jumphelper2(set,now+jumpNum,jumpNum + 1,end);
    }
    return false;
  }

  public static boolean canCross(int[] stones) {
    //sanity check
    if(stones ==null || stones.length == 0){
      return false;
    }
    int len = stones.length;
    boolean[] m = new boolean[len + 1];

    Set<Integer> set = new HashSet<>();
    for(int i : stones){
      set.add(i);
    }
    int end = stones[len-1];
    for(int i = 1; i <= end; i++){
      if(set.contains(i)){

      }
    }
    return false;
  }

}
