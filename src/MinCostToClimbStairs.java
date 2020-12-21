public class MinCostToClimbStairs {

    /*
    https://leetcode.com/problems/min-cost-climbing-stairs/submissions/
    Runtime: 1 ms, faster than 72.61% of Java online submissions for Min Cost Climbing Stairs.
    Memory Usage: 38.7 MB, less than 52.57% of Java online submissions for Min Cost Climbing Stairs.
     */

    public int minCostClimbingStairs(int[] cost) {

        if(cost == null || cost.length <= 1){
            return 0;
        }

        int prev = 0, prevToPrev = 0;

        for(int i = 2; i < cost.length + 1; i++){

            int current = Math.min(prev == 0 ? cost[i-1] : prev,
                    prevToPrev == 0 ? cost[i-2] : prevToPrev);

            current += (i >= cost.length ? 0 : cost[i]);

            prevToPrev = prev;
            prev = current;
        }

        return prev;
    }
}
