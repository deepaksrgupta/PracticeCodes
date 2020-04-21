public class MeetingPlanner {
    static int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
        // your code goes here

        int startA = 0;
        int startB = 0;

        while(startA < slotsA.length && startB < slotsB.length) {


            int minSec = Math.min(slotsA[startA][1],slotsB[startB][1]);

            int maxSec = Math.max(slotsA[startA][0],slotsB[startB][0]);

            if(minSec - maxSec >= dur){

                return new int[]{maxSec,maxSec+dur};
            }


            if(minSec == slotsA[startA][1]){
                startA++;
            }else{
                startB++;
            }

        }


        return new int[]{};
    }
}
