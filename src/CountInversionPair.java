import java.util.Arrays;

public class CountInversionPair {

    public static int mergeNCount(int[] arr,int left, int mid,int right){
        int count = 0;

        int[] lSubArray = Arrays.copyOfRange(arr,left,mid+1);
        int[] rSubArray = Arrays.copyOfRange(arr,mid+1,right+1);


        int i = 0;
        int j = 0;
        int k = left;


        while (i < lSubArray.length && j < rSubArray.length) {

            if(lSubArray[i] > rSubArray[j]){
                count += j + left - i;
                arr[k++] = rSubArray[j++];
            }else{
                arr[k++] = lSubArray[i++];
            }
        }


        while (i < lSubArray.length){
            arr[k++] = lSubArray[i++];
        }

        while (j < rSubArray.length){
            arr[k++] = rSubArray[j++];
        }

        return count;
    }

    public static int countInversionViaMergeSort(int[] arr,int left,int right) {
        int count  = 0;

        if (right > left) {
            int mid = (right+left) / 2;

            count +=  countInversionViaMergeSort(arr,left,mid);
            count += countInversionViaMergeSort(arr,mid+1,right);

            count += mergeNCount(arr,left,mid,right);
        }
        return count;
    }

    public static void main(String args[]) {
        int[] arr = {8,7,6,5,4,3,2,1};
        //Arrays.sort(arr);
        System.out.println(countInversionViaMergeSort(arr,0,arr.length-1));
    }
}
