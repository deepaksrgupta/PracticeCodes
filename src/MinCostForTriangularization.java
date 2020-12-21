import java.util.*;

public class MinCostForTriangularization {

    int cache[][];

    public int minScoreTriangulation(int[] a) {
        if(a.length < 3)    return 0;

        if(a.length == 3) return a[0]*a[1]*a[2];

        cache = new int[a.length][a.length];

        return helper(a,0,a.length-1);

    }

    public int helper(int a[],int start,int end){

        if(end-start+1 < 3) return 0;

        if(cache[start][end] != 0) return cache[start][end];

        int result = Integer.MAX_VALUE;


        for(int k = start + 1; k < end; k++) {

            result  = Math.min(result, helper(a,start,k) + (a[start] * a[end] * a[k]) + helper(a,k,end) );

        }


        cache[start][end] = result;

        return result;
    }

    /*
    https://leetcode.com/problems/minimum-score-triangulation-of-polygon/submissions/
    Runtime: 3 ms, faster than 56.98% of Java online submissions for Minimum Score Triangulation of Polygon.
    Memory Usage: 36.6 MB, less than 56.23% of Java online submissions for Minimum Score Triangulation of Polygon.
     */
    private int inf = 1_000_000_009;
    public int minScoreTriangulationBU(int[] arr) {

        if(arr == null || arr.length <= 1){
            return 0;
        }

        int [][]dp = new int[arr.length][arr.length];

        for(int subArrayLength = 2; subArrayLength < arr.length; subArrayLength++){

            for(int start = 0; start < arr.length - subArrayLength; start++){


                int end = start + subArrayLength;
                dp[start][end] = inf;

                for(int midCrossing = start+1; midCrossing < end; midCrossing++){

                    dp[start][end] = Math.min(dp[start][end],
                            dp[start][midCrossing] + dp[midCrossing][end] + arr[start] * arr[midCrossing] * arr[end]);
                }

            }

        }


        return dp[0][arr.length-1];
    }

    public static void main(String[] args) {

        int a[] = {3,7,4,5};

        System.out.println(new MinCostForTriangularization().minScoreTriangulation(a));
    }
}