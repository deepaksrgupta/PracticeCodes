public class ArrangeCoinsInPyramid {

    /*https://leetcode.com/problems/arranging-coins/
    Details
Runtime: 14 ms, faster than 10.50% of Java online submissions for Arranging Coins.
Memory Usage: 36.8 MB, less than 61.08% of Java online submissions for Arranging Coins.
     */

    //works in O(sqrt(n))
    public int arrangeCoins(int n) {

        if(n==0)    return 0;
        if(n==1 || n==2)    return 1;

        long sum = 0;
        int count  = 0;
        for(int i = 1; i < n; i++){
            sum += i;
            count++;


            if(sum == n){
                break;
            }

            if(sum > n){
                count--;
                break;
            }

        }


        return count;
    }
}
