import java.util.Arrays;

public class JumpGame2 {
    /*
    https://leetcode.com/problems/jump-game-ii/submissions/
    Runtime: 588 ms, faster than 5.20% of Java online submissions for Jump Game II.
    Memory Usage: 41.1 MB, less than 51.80% of Java online submissions for Jump Game II.
     */
    public int jump(int[] nums) {

        if(nums == null || nums.length <= 1){
            return 0;
        }


        int[]dp = new int[nums.length];
        Arrays.fill(dp, 1000000);
        dp[dp.length-1] = 0;

        for(int i = nums.length - 2; i >=0; i--){

            for(int j = 1; j <= nums[i]; j++){

                if(i+j < nums.length){

                    dp[i] = Math.min(dp[i], dp[i+j] + 1);


                }

            }

        }


        return dp[0];

    }
}
