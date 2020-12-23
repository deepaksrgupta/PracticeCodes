public class JumpGame {
    /*
    https://leetcode.com/problems/jump-game/submissions/
    Runtime: 737 ms, faster than 11.74% of Java online submissions for Jump Game.
    Memory Usage: 40.7 MB, less than 92.11% of Java online submissions for Jump Game.
     */
    public boolean canJump(int[] nums) {

        if(nums == null || nums.length == 0){
            return false;
        }


        boolean[]dp = new boolean[nums.length];
        dp[dp.length-1] = true;

        for(int i = nums.length - 2; i >=0; i--){

            for(int j = 1; j <= nums[i]; j++){

                if(i+j < nums.length){

                    dp[i] = dp[i] || dp[i+j];

                }

            }

        }


        return dp[0];
    }
}
