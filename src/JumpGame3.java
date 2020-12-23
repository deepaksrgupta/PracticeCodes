import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class JumpGame3 {
    /*
    https://leetcode.com/problems/jump-game-iii/submissions/
    Runtime: 11 ms, faster than 5.13% of Java online submissions for Jump Game III.
    Memory Usage: 46.9 MB, less than 25.12% of Java online submissions for Jump Game III.
     */
    public boolean canReach(int[] arr, int start) {

        if(arr == null || arr.length == 0 || start < 0 || start >= arr.length){
            return false;
        }


        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> queue = new LinkedList<>();

        queue.addLast(start);


        while(!queue.isEmpty()){

            int currentIndex = queue.removeFirst();

            if(visited.contains(currentIndex)) {
                continue;
            }


            visited.add(currentIndex);

            if(arr[currentIndex] == 0){
                return true;
            }

            int rightSide = currentIndex + arr[currentIndex];
            if(rightSide < arr.length){
                queue.addLast(rightSide);
            }

            int leftSide = currentIndex - arr[currentIndex];
            if(leftSide >= 0){
                queue.addLast(leftSide);
            }



        }

        return false;
    }
}
