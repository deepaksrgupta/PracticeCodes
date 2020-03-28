public class ProductExceptSelf {

    //https://leetcode.com/problems/product-of-array-except-self/
    static int[] productExceptSelf(int[] arr) {

        if(arr.length == 0){
            return arr;
        }

        if(arr.length == 1){
            return new int[0];
        }


        int[] prefixMul = new int[arr.length];
        int[] suffixMul = new int[arr.length];
        int[] result  = new int[arr.length];

        int runningMultiple = 1;
        for(int i=0;i< prefixMul.length;i++)
        {
            runningMultiple = arr[i] * runningMultiple;
            prefixMul[i] = runningMultiple;
        }

        runningMultiple = 1;
        for(int i= suffixMul.length-1; i >= 0; i--){
            runningMultiple = arr[i] * runningMultiple;
            suffixMul[i] = runningMultiple;
        }

        for(int i = 0; i< result.length ; i++){

            if(i==0){
                result[i] = suffixMul[i+1];
            }else if(i == result.length-1){
                result[i] = prefixMul[i-1];
            }else{
                result[i] = prefixMul[i-1] * suffixMul[i+1];
            }
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
