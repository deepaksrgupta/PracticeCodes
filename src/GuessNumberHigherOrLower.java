public class GuessNumberHigherOrLower {

    /*
    https://leetcode.com/submissions/detail/580179505/
     */

    public int getMoneyAmount(int n) {

        return getMoneyAmountTopDown(1, n, new int[n+1][n+1]);
    }

    public int getMoneyAmountTopDown(int start, int end, int[][] cache){

        if(start == end){
            return 0;
        }

        if(start+1 == end) {
            return start;
        }

        if (cache[start][end] != 0){
            return cache[start][end];
        }


        int min = Integer.MAX_VALUE;


        for(int i = start + 1; i < end; i++){
            int left = getMoneyAmountTopDown(start, i-1,cache);
            int right = getMoneyAmountTopDown(i+1,end, cache);

            int newMin = Math.max(left, right);

            min = Math.min(min, newMin + i);
        }


        cache[start][end] = min;
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new GuessNumberHigherOrLower().getMoneyAmount(2));
    }
}
