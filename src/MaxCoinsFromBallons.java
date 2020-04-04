public class MaxCoinsFromBallons {
    public int maxCoinsBU(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        for (int k = 2; k < n; ++k)
            for (int left = 0; left < n - k; ++left) {
                int right = left + k;
                for (int i = left + 1; i < right; ++i){
                    dp[left][right] = Math.max(dp[left][right],
                            nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);
                    //System.out.println("k="+k+" left="+left+" i="+i+" right="+right +" dp="+dp[left][right]+" nums[left]="+nums[left]+" nums[i]="+nums[i]+" nums[right]="+nums[right]);
                }

            }

        return dp[0][n - 1];
    }

    public int burstBallonsDP(int[] a){

        int dp[][] = new int[a.length+2][a.length+2];

        int nums[] = new int[a.length+2];

        for(int i=1;i <= a.length; i++)  nums[i] = a[i-1];
        nums[0] = nums[nums.length-1] = 1;


        for(int length  = 2; length < nums.length; length++){       //checks for all subarray length

            for(int left = 0; left < nums.length - length; left++) { //starting of sub array

                int right  = left + length;     // max sub array can go


                for(int k = left +1; k < right; k++){   // tries all possible combination of subarrays to build up dp

                    dp[left][right] = Math.max(dp[left][right], dp[left][k] + (nums[left]*nums[k]*nums[right]) +dp[k][right]);

                }

            }

        }


        return dp[0][nums.length-1];
    }

    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;


        int[][] memo = new int[n][n];
        return burst(memo, nums, 0, n - 1);
    }

    public int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int ans = 0;
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]
                    + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        memo[left][right] = ans;
        return ans;
    }

    public static void main(String[] args) {

        //int[] a = {3,2,5,8};
        int[] a = {1, 2, 3, 4, 3};

        int result = new MaxCoinsFromBallons().burstBallonsDP(a);

        System.out.println(result);
    }
}
