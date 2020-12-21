import java.util.Arrays;

public class MinCoinChange {

    private int infinity  = 10000009;
    public int helperTD(int[] coins, int amount, Integer []cache){

        if(amount == 0){
            return 0;
        }

        if(cache[amount] != null){
            return cache[amount];
        }

        int minDenominations = infinity;

        for(int i = 0; i < coins.length; i++){

            if(amount - coins[i] >= 0 ){
                minDenominations = Math.min(minDenominations, helperTD(coins, amount - coins[i], cache) + 1);
            }
        }

        cache[amount] =  minDenominations;

        return minDenominations;
    }

    /*
    Runtime: 25 ms, faster than 26.83% of Java online submissions for Coin Change.
    Memory Usage: 39.3 MB, less than 16.74% of Java online submissions for Coin Change.
    https://leetcode.com/problems/coin-change/submissions/
     */
    public int coinChangeTD(int[] coins, int amount) {
        if(amount <= 0 || coins == null || coins.length == 0){
            return 0;
        }

        Integer []cache = new Integer[amount+1];

        int result  = helperTD(coins, amount, cache);

        return result == infinity ? -1 : result;
    }


    /*
    Runtime: 12 ms, faster than 56.38% of Java online submissions for Coin Change.
    Memory Usage: 38.6 MB, less than 51.08% of Java online submissions for Coin Change.
    https://leetcode.com/problems/coin-change/submissions/
     */
    public  int coinChange(int[] coins, int amount) {

        if (amount <= 0 || coins == null || coins.length == 0) {
            return 0;
        }

        int[] subProblems = new int[amount+1];

        for(int i = 1; i < subProblems.length; i++){

            subProblems[i] = infinity;

            for(int j = 0; j < coins.length; j ++){

                int previousCoinMin = i - coins[j];

                if(previousCoinMin >= 0 && subProblems[previousCoinMin] != infinity){

                    subProblems[i] = Math.min(subProblems[previousCoinMin] + 1, subProblems[i]);

                }

            }

        }

        return subProblems[amount] == infinity ? -1 : subProblems[amount];
    }

    public static void main(String[] args){
        System.out.println(new MinCoinChange().coinChange(new int[]{1,2, 5}, 11));
    }

}
