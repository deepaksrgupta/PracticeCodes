public class ChampagneTower {
    //https://leetcode.com/problems/champagne-tower/

    public static double champagneTower(int poured, int query_row, int query_glass) {
        double[][] glasses = new double[102][102];
        glasses[0][0] = (double)poured;

        for(int row = 0; row <= query_row; row++ ) {

            for(int col = 0 ;col <= query_glass; col++){

                double excess = ( glasses[row][col] - 1 ) / 2;


                if(excess > 0) {
                    glasses[row+1][col] += excess;
                    glasses[row+1][col+1] += excess;
                }

            }
        }

        return Math.min(1,glasses[query_row][query_glass]);

    }

    public static void main(String[] args) {

        System.out.println(champagneTower(2,0,0));
    }
}
