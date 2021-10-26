public class NumOfBST {
    /*
    https://leetcode.com/problems/unique-binary-search-trees/submissions/
    Runtime: 0 ms, faster than 100.00% of Java online submissions for Unique Binary Search Trees.
    Memory Usage: 35.9 MB, less than 34.04% of Java online submissions for Unique Binary Search Trees.

    catalans number -> 2nCn / n + 1 -> can use direct formula for linear time solution
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }
}


