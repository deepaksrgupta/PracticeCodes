import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class minArrayToBurstBalloons {
    /*
       arr contains balloons at a[i] in the air
       we can fire arrows at integer height x if pops a balloon then arrow will continue to go from left to right at x-1 till then end
       we need to find minimum number of arrows to fired such that all balloons are popped
    */
    static int minArrowsRequiredToPopBalloons(int [] arr){

        //problem is about finding total number of sequentially  decreasing subsequence
        //we can fire arrow at index and keep blowing next balloons
        //this solution will build up to O(n*n) at worst for for sorted increasing sequence


        // alternatively to bring down the complexity for a current we just need to know whether it can extend a new existing or create a new
        // as good as saying whether for x does x-1 existing in hashmap<Integer,List<Intger> if it exists then the value list and update the key
        // else we x in hashmap for a new sequence
        // for each index we are just checking in hashmap 2 times leading to O(n) complexity


        HashMap<Integer, List<Integer>> sequence = new HashMap<>();



        for(int ele: arr){

            List<Integer> currentSequence = sequence.getOrDefault(ele+1,new ArrayList<Integer>());

            sequence.remove(ele+1);

            currentSequence.add(ele);

            sequence.put(ele,currentSequence);
        }


        return sequence.size();
    }

    static public void main( String args[] ) {

        int a[] = new int[]{5,4,6,5,3,2,4};

        System.out.println(minArrowsRequiredToPopBalloons(a));
    }
}
