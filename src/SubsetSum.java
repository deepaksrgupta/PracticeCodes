import java.util.HashMap;

public class SubsetSum {
    public static int countRecursive  = 0;
    public static boolean SubsetSumRecurive(int[] arr, int start, int len, int sum){
        countRecursive++;
        if(sum==0){
            return true;
        }

        if(start >= len){
            return false;
        }

        boolean first = false;

        if(arr[start] <= sum){
            first = SubsetSumRecurive(arr,start+1,len,sum-arr[start]);
        }

        return first || SubsetSumRecurive(arr,start+1,len,sum);
    }

    public static int countTD  = 0;
    public static HashMap<String, Boolean> hashMap = new HashMap<String, Boolean>();
    public static boolean SubsetSumTD(int[] arr, int start, int len, int sum){
        countTD++;
        if(sum==0){
            return true;
        }

        if(start >= len){
            return false;
        }

        boolean first = false;

        if(arr[start] <= sum){
            if(hashMap.containsKey((start+1)+ ","+(sum-arr[start]))){
                return hashMap.get((start+1)+ ","+(sum-arr[start]));
            }
            first = SubsetSumTD(arr,start+1,len,sum-arr[start]);
            hashMap.put((start+1)+ ","+(sum-arr[start]),first);
        }

        boolean second  = false;
        if(hashMap.containsKey((start+1)+","+sum)){
            second =  hashMap.get((start+1)+","+sum);
        }else{
            second = SubsetSumTD(arr,start+1,len,sum);
        }

        return first || second;
    }

    /*
     int[] arr = {3,34,4,12,5,2,4,2,7,3,9,3,12,32,99,12};
        int sum = 1000;
        System.out.println("Recursive solution is " + SubsetSumRecurive(arr,0,arr.length,sum));
        System.out.println("Recurisve function call is " + countRecursive);

        System.out.println("Top down DP solution is " + SubsetSumTD(arr,0,arr.length,sum));
        System.out.println("Top down DP function call is " + countTD);
     */
}
