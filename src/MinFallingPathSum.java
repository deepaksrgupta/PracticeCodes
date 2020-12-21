public class MinFallingPathSum {
    /*
    https://leetcode.com/problems/minimum-falling-path-sum/submissions/
    Runtime: 3 ms, faster than 82.59% of Java online submissions for Minimum Falling Path Sum.
    Memory Usage: 39.9 MB, less than 32.22% of Java online submissions for Minimum Falling Path Sum.
     */
    public int minFallingPathSum(int[][] a) {

        for(int i = 1; i < a.length; i++){

            for(int j = 0 ; j < a[i].length; j++){

                int minFall = 1000009;

                //upper left
                if(j-1 >= 0){
                    minFall = Math.min(minFall, a[i-1][j-1]);
                }

                //upper
                minFall = Math.min(minFall, a[i-1][j]);

                //upper right
                if(j+1 < a[i].length){
                    minFall = Math.min(minFall, a[i-1][j+1]);
                }

                a[i][j] += minFall;
            }

        }

        int max = Integer.MAX_VALUE;
        for(int ele : a[a.length - 1]){
            max = Math.min(max, ele);
        }

        return max;
    }

    public static void main(String []args){
        System.out.println(new MinFallingPathSum().minFallingPathSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }
}
