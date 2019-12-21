public class minSubArrayWithSumGreaterThanK
{

    static int getMinSubArrayLength(int[] a,int k){

        int start = 0;
        int end = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while (end < a.length){

            //keep on adding elements
            while (sum <= k && end < a.length){
                sum+= a[end++];
            }


            while (sum > k && start < a.length){
                if(end-start < minLength)
                    minLength = end -start;

                sum-= a[start++];
            }
        }

        return minLength;
    }

    public static void main(String args[]){
        int arr[] = {1, 4, 45, 6, 0, 19};
        int x = 51;

        System.out.println(getMinSubArrayLength(arr,x));

    }
}
