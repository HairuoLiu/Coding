
/**
 * Given a list of sorted characters letters containing only lowercase letters,
 * and given a target letter target, find the smallest element in the list that is larger than the given target.
 *
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 *
 * Examples:
 * Input:
 * letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 *
 * @authorLiu.3502
 * @created2018-01-31 下午6:45
 */

public class LC35_SearchInsertPosition{

  public static void main(String[] args) {
    char[] letter = {'c', 'f', 'j'};
    char target = 'k';
    char ans = nextGreatestLetter(letter,target);
    System.out.println(ans);
  }

  public static char nextGreatestLetter(char[] letters, char target) {
      int left = 0, right = letters.length - 1;
      //sanity cheak
      if(right <= 0){
        return ' ';
      }
      while (left <= right){
        int mid = left + (right - left) / 2;
        if(letters[mid]  <= target){
          left = mid + 1;
        }else if(letters[mid] > target  ){
          right = mid-1;
        }
      }
      if(left == letters.length  ){
        return letters[0];
      }
      return letters[left];
  }

}
