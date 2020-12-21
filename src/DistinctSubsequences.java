class DistinctSubsequences {

    /*
    Runtime: 1 ms, faster than 99.93% of Java online submissions for Distinct Subsequences.
    Memory Usage: 37.7 MB, less than 35.20% of Java online submissions for Distinct Subsequences.
    https://leetcode.com/problems/distinct-subsequences/submissions/
     */
    public int numDistinct(String s, String t) {
        return helper(s,t,0,0,new Integer[s.length()+1][t.length()+1]);
    }

    public int helper(String s, String t, int si, int ti, Integer[][] cache){

        if(ti >= t.length() || si >= s.length()){
            return 0;
        }

        if(cache[si][ti] != null){
            return cache[si][ti];
        }

        int num = 0;

        if(t.charAt(ti) == s.charAt(si)){
            num += helper(s,t, si+1, ti+1, cache);

            if(t.length() == ti+1){
                num++;
            }
        }

        num += helper(s,t, si+1, ti, cache);

        cache[si][ti] = num;

        return num;
    }
}
