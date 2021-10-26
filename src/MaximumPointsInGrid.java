import java.util.Arrays;

//https://leetcode.com/submissions/detail/577287850/
public class MaximumPointsInGrid {

    public long maxPoints(int[][] points) {

        long[][] cache = new long[points.length+1][points[0].length+1];
        for(long[] c: cache){
            Arrays.fill(c, -1);
        }

        return getMaximumPoints(points, -1, -1, cache);
    }


    public long getMaximumPoints(int[][] points, int row, int col, long[][] cache){

        if(row == points.length){
            return 0;
        }

        if(row == points.length -1){
            return points[row][col];
        }

        if(row != -1 && col != -1 && cache[row][col] != -1){
            return cache[row][col];
        }


        long max = Long.MIN_VALUE;
        for(int i = 0; i < points[0].length; i++){

            max = Math.max(max,
                    getMaximumPoints(points, row + 1, i, cache) +
                            (row == -1 ? 0 : points[row][col]) -
                            (col == -1 ? 0 : Math.abs(col - i)));
        }

        if(row != -1 && col != -1 ){
            cache[row][col] = max;
        }


        return max;
    }
}
