public class IsStringInterLeave {
    //https://leetcode.com/problems/interleaving-string/
    private short [][][] cache;
    public boolean isInterleave(String s1, String s2, String s3) {

        if(s3.length() != s1.length()+s2.length()){
            return false;
        }


        cache  = new short[s1.length()+1][s2.length()+1][s3.length()+1];

        return helper(s1,0,s2,0,s3,0);
    }

    public boolean helper(String s1,int i,String s2,int j, String s3, int k){

        if(i == s1.length() && j == s2.length() && k == s3.length()){
            return true;
        }


        if(cache[i][j][k] != 0){
            return cache[i][j][k] == 1;
        }



        boolean result = false;

        if(i < s1.length() && s3.charAt(k) == s1.charAt(i)){
            result |= helper(s1,i+1,s2,j,s3,k+1);
        }

        if(j < s2.length() && s3.charAt(k) == s2.charAt(j)){
            result |= helper(s1,i,s2,j+1,s3,k+1);
        }

        cache[i][j][k] = (short) (result ? 1 : -1);

        return result;
    }
}
