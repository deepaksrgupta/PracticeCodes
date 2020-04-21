public class GetStringEditDistance {
    private String first;
    private String second;
    private int[][] cache;

    public int minDistance(String word1, String word2) {

        first = word1;
        second = word2;
        cache = new int[word1.length()][word2.length()];

        return helper(0,0);
    }

    private int helper(int i, int j){

        //done because both sub problems are finished
        if(first.length() == i && second.length() == j) {
            return 0;
        }


        //perform deletion if 1 string reaches end
        if(first.length() == i){
            return 1 + helper(i,j+1);
        }

        if(second.length() == j){
            return 1 + helper(i+1,j);
        }

        //check for cache hit
        if(cache[i][j] != 0){
            return cache[i][j];
        }



        //if character matches then don't need to perform any ops
        if(first.charAt(i) == second.charAt(j)){
            return helper(i+1,j+1);
        }


        // perform operation
        int insert = helper(i,j+1);
        int delete = helper(i+1,j);
        int replace = helper(i+1,j+1);

        int result =  Math.min(insert,Math.min(delete,replace)) + 1;

        cache[i][j] = result;

        return result;
    }

    //https://leetcode.com/problems/edit-distance/
    public static void main(String[] args) {
        Long l1 = System.currentTimeMillis();
        System.out.println(new GetStringEditDistance().minDistance("intention","execution"));
        Long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
    }
}
