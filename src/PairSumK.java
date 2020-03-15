import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PairSumK {
    public static void main(String[] args) {
        int [] arr = {0, -1, -2, 2, 1};

        int k = 1;

        int[][] result = findPairsWithGivenDifference(arr,k);

        for(var t : result){
            System.out.println(t[0]+" "+t[1]);
        }
    }


    static int[][] findPairsWithGivenDifference(int[] arr, int k) {
        // your code goes here

        Set<Integer> hashSet = new HashSet<>();
        List<int[]> result = new ArrayList<>();

        //this to add all elements
        for( int element : arr){
            hashSet.add(element);
        }


        //to perform computations
        for (int value : arr) {

            int toFind = k + value;

            if (hashSet.contains(toFind)) {
                result.add(new int[]{toFind, value});
            }

        }


        int [][]  finalResult = new int[result.size()][2];

        //to build output array to be sent
        int i = 0;
        for(int[] res : result){
            finalResult[i] = res;
            i++;
        }


        return finalResult;

    }
}
