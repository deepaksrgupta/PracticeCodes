public class LongestPalindromeSubstring {
    public static int solveLPS(char[] q,int start,int end){
        if(start == end){
            return 1;
        }

        if(q[start] == q[end] && start+1 == end) {
            return 2;
        }

        if(q[start] == q[end]){
            return 2 + solveLPS(q,start+1,end-1);
        }

        return Math.max(solveLPS(q,start+1,end),solveLPS(q,start,end-1));
    }
}
