import java.util.HashMap;

public class MatrixChainMultiplication {
    public static int countRecusive = 0;
    public static int solveMChainMul(int dimension[],int i,int j){
        countRecusive++;
        if(j <= i+ 1){
            return 0;
        }

        int minVal = Integer.MAX_VALUE;

        for(int k = i+1 ; k < j; k++){
            int cost = solveMChainMul(dimension,i,k);
            int secondCost = solveMChainMul(dimension,k,j);

            int finalCost = cost + secondCost + (dimension[i] * dimension[k] * dimension[j]);
            if(finalCost < minVal){
                minVal = finalCost;
            }
        }

        return minVal;
    }

    public static int  countTD = 0;
    public static HashMap<String, Integer> hashMap = new HashMap<>();
    public static int solveMChainMulTopDown(int dimension[],int i,int j){
        countTD++;
        if(j <= i+ 1){
            return 0;
        }

        if(!hashMap.containsKey(i+ "," + j)) {

            int minVal = Integer.MAX_VALUE;

            for (int k = i + 1; k < j; k++) {
                int cost = solveMChainMulTopDown(dimension, i, k);
                int secondCost = solveMChainMulTopDown(dimension, k, j);

                int finalCost = cost + secondCost + (dimension[i] * dimension[k] * dimension[j]);
                if (finalCost < minVal) {
                    minVal = finalCost;
                }
            }

            hashMap.put(i+","+j,minVal);
        }

        return hashMap.get(i+","+j);
    }

    public static int matrixChainMultiplicationBU(int[] a){

        int dp[][] = new int[a.length][a.length];

        for(int subArrayLength = 2; subArrayLength < a.length; subArrayLength++) { //chain length corresponds to chain+1 elements

            for(int left = 0; left < a.length - subArrayLength; left++) { //starting index

                int right  = left +  subArrayLength;    // end index for each sub array


                dp[left][right] = Integer.MAX_VALUE;    //default max value

                for(int k = left + 1 ; k < right; k++){ //consider is value inside in the range of subArray


                    dp[left][right] = Math.min(dp[left][right], dp[left][k] + (a[left]* a[k]*a[right]) +dp[k][right]);

                }


            }


        }


        return dp[0][a.length-1];
    }

    static void findMatrixHelper(){
        //int dimension[] = {10,30,5,60};
        int dimension[] = {30,35,15,5,10,20,25};
        System.out.println(solveMChainMul(dimension,0,dimension.length-1));
        System.out.println(countRecusive);

        System.out.println(solveMChainMulTopDown(dimension,0,dimension.length-1));
        System.out.println(countTD);

        System.out.println(matrixChainMultiplicationBU(dimension));
    }

    public static void main(String[] args) {
        MatrixChainMultiplication multiplication = new MatrixChainMultiplication();
        multiplication.findMatrixHelper();
    }
}
