import java.util.Arrays;

public class KnightDialer {
    public static int knightDialer(int N) {

        //1 --> 18, 16
        //2 --> 27, 29
        //3 --> 38, 34
        //4 --> 43, 49, 40,
        //5 --> 0
        //6 --> 61, 67, 60
        //7 --> 72, 76
        //8 --> 81, 83
        //9 --> 92, 94
        //0 --> 04, 06
        //google coding interview question
        //https://leetcode.com/problems/knight-dialer/
        int[][] moves = {{4,6},{6,8},{7,9},{4,8},{0,3,9},{},{0,1,7},{2,6},{1,3},{2,4}};
        int[] [] dp = new int[N+1][10];
        int mod = 1000000000 + 7;

        Arrays.fill(dp[0],1);

        for(int i = 1 ; i<= N; i++ ) {

            for(int j = 0; j < moves.length; j++){

                for(int a: moves[j]){
                    dp[i][j] += dp[i-1][a];
                    dp[i][j] %= mod;
                }

            }
        }

        //return sum of last row
        int sum = 0;
        for(int i=0; i< dp[N-1].length;i++){
            sum += dp[N-1][i];

            sum %= mod;
        }
        return sum;
    }

    public static void main(String[] args) {

        System.out.println(knightDialer(10));
    }
}
