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

    static int MatrixChainOrder(int p[], int n)
    {
        /* For simplicity of the program, one extra row and one
        extra column are allocated in m[][].  0th row and 0th
        column of m[][] are not used */
        int m[][] = new int[n][n];

        int i, j, k, L, q;

        /* m[i,j] = Minimum number of scalar multiplications needed
        to compute the matrix A[i]A[i+1]...A[j] = A[i..j] where
        dimension of A[i] is p[i-1] x p[i] */

        // cost is zero when multiplying one matrix.
        for (i = 1; i < n; i++)
            m[i][i] = 0;

        // L is chain length.
        for (L=2; L<n; L++)
        {
            for (i=1; i<n-L+1; i++)
            {
                j = i+L-1;
                if(j == n) continue;
                m[i][j] = Integer.MAX_VALUE;
                for (k=i; k<=j-1; k++)
                {
                    // q = cost/scalar multiplications
                    q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }

        return m[1][n-1];
    }

    static void findMatrixHelper(){
        //int dimension[] = {10,30,5,60};
        int dimension[] = {30,35,15,5,10,20,25};
        System.out.println(solveMChainMul(dimension,0,dimension.length-1));
        System.out.println(countRecusive);

        System.out.println(solveMChainMulTopDown(dimension,0,dimension.length-1));
        System.out.println(countTD);

        System.out.println(MatrixChainOrder(dimension,dimension.length));
    }

    /*
        MatrixChainMultiplication multiplication = new MatrixChainMultiplication();
        multiplication.findMatrixHelper();
     */
}
