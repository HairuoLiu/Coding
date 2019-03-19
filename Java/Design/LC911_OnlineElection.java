
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * In an election, the i-th vote was cast for persons[i] at time times[i].
 *
 * Now, we would like to implement the following query function: TopVotedCandidate.q(int t) will return the number of the person that was leading the election at time t.
 *
 * Votes cast at time t will count towards our query.  In the case of a tie, the most recent vote (among tied candidates) wins.
 *
 *
 *
 * Example 1:
 *
 * Input: ["TopVotedCandidate","q","q","q","q","q","q"], [[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]
 * Output: [null,0,1,1,0,0,1]
 * Explanation:
 * At time 3, the votes are [0], and 0 is leading.
 * At time 12, the votes are [0,1,1], and 1 is leading.
 * At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
 * This continues for 3 more queries at time 15, 24, and 8.
 *
 * @author Liu.3502
 * @created 2018-09-10 下午1:17
 */
public class LC911_OnlineElection{

  public static void main(String[] args) {
    OnlineElection_911 n = new OnlineElection_911();
    int[] persons  = {0,1,1,0,0,1,0};
    int[] times = {0,5,10,15,20,25,30};
    TopVotedCandidate newV = n.new TopVotedCandidate(persons,times);

    int [] test = {3,12,25,15,24,8};
    for(int i : test){
      System.out.println(newV.q(i));
    }

  }

  class TopVotedCandidate {

    class Candidate {

      int name;
      int vote;
      int lastVoteTime;

      public Candidate(int name, int time) {
        this.name = name;
        this.vote = 1;
        this.lastVoteTime = time;
      }

      public void updateVote(int time) {
        this.vote++;
        this.lastVoteTime = time;
      }
    }

    TreeSet<Candidate> pq;     //
    Map<Integer, Candidate> nameMap;  //key is Candidate, value is time;
    TreeMap<Integer, Integer> map;

    public TopVotedCandidate(int[] persons, int[] times) {
      nameMap = new HashMap<>();

      pq = new TreeSet<>((a, b) -> a.vote == b.vote ? b.lastVoteTime - a.lastVoteTime  :   b.vote  -  a.vote);

      map = new TreeMap<>();
      for (int i = 0; i < persons.length; ++i) {
        int curP = persons[i];

        if (nameMap.containsKey(curP)) {
          Candidate curC = nameMap.get(curP);
          pq.remove(curC);
          curC.updateVote(times[i]);
        } else {
          nameMap.put(curP, new Candidate(curP, times[i]));
        }
        pq.add(nameMap.get(curP));
        map.put(times[i], pq.first().name);
      }
    }


    public int q(int t) {
      Integer key = map.floorKey(t);
      if (key != null) {
        return map.get(key);
      }
      return -1;
    }
  }

}
