import java.util.Arrays;

public class MinCostForTicketsWithPass {

    private int[] days;
    private int[] costs;
    private int[] allDays;
    // top down approach to solve the problem
    // https://leetcode.com/problems/minimum-cost-for-tickets/
    public int mincostTickets(int[] days, int[] costs) {

        this.days = days;
        this.costs = costs;
        this.allDays = new int[366];
        Arrays.fill(this.allDays,-1);

        return Helper(1,0);
    }

    private int Helper(int currentDay,int currentIndex){

        if(currentDay < allDays.length && allDays[currentDay] != -1){
            return allDays[currentDay];
        }

        if(currentDay > this.days[this.days.length-1]){
            return 0;
        }

        int val = Integer.MAX_VALUE;
        int notTaken = Integer.MAX_VALUE;


        if(currentDay == this.days[currentIndex]){
            currentIndex++;
        }else{
            notTaken = Helper(currentDay+1,currentIndex);
        }

        int oneDayPass = Helper(currentDay+1,currentIndex) + costs[0];
        int sevenDayPass = Helper(currentDay+7,currentIndex) + costs[1];
        int thirtyDayPass = Helper(currentDay+30,currentIndex) + costs[2];

        val = Math.min(Math.min(Math.min(Math.min(notTaken,oneDayPass),val),sevenDayPass),thirtyDayPass);

        allDays[currentDay] = val;
        return val;
    }

    public int mincostTicketsBottomUp(int[] days, int[] costs) {

        int lastDay = days[days.length-1];
        boolean []hash = new boolean[lastDay+1];
        int []dp = new int[lastDay+1];

        // to check if day is present in the array
        for(int day : days){
            hash[day] = true;
        }

        //base case
        dp[0] = 0;

        for(int i = 1; i <= lastDay; i++){

            if(hash[i]){

                dp[i] = Math.min(dp[i-1]+costs[0],
                        Math.min(dp[Math.max(i-7,0)]+costs[1],
                                dp[Math.max(i-30,0)]+costs[2]));

            }else{

                // if day is not present then get result from previous day
                dp[i] = dp[i-1];
            }
        }

        return dp[lastDay];
    }

    public static void main(String[] args) {
        MinCostForTicketsWithPass m = new MinCostForTicketsWithPass();
        int[] days = new int[]{1,2,3,4,5,6,7,8,9,10,30,31};
        int[] costs = new int[]{2,7,15};
        System.out.println(m.mincostTickets(days,costs));
        System.out.println(m.mincostTicketsBottomUp(days,costs));
    }
}
