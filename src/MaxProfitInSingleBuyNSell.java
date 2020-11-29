public class MaxProfitInSingleBuyNSell {

    //the best -> O(n)
    //keep track of a min element
    public int findMaxProfitFast(int[] arr){
        int maxProfit = 0;
        int minElement  = arr[0];

        for(int i= 1; i < arr.length; i++) {

            minElement = Math.min(minElement, arr[i]);

            maxProfit = Math.max(maxProfit, arr[i] - minElement);

        }

        return maxProfit;
    }


    //the good -> O(n * logn)
    //uses divide and conquer
    //get profit from left and right sub array; get the profit from full sub array
    public int findMaxProfitAvg(int[] arr){
        return findMaxProfitAvg(arr, 0, arr.length - 1);
    }

    private int findMaxProfitAvg(int[] arr, int low, int high){

        if(low == high){
            return 0;
        }

        int mid = low + ((high-low) / 2);

        int leftProfit  = findMaxProfitAvg(arr, low, mid);
        int rightProfit  = findMaxProfitAvg(arr, mid+1, high);

        //get max from left side and min from right side for max profit
        int crossProfit  = minOrMax(arr, low, high, false) - minOrMax(arr, low, high, true);


        return Math.max(leftProfit, Math.max(rightProfit, crossProfit));
    }

    private int minOrMax(int[] arr, int low, int high, boolean isMin){

        int minOrMax = arr[low];

        while (low <= high){

            minOrMax = isMin ? Math.min(minOrMax, arr[low]) : Math.max(minOrMax, arr[low]);
            low++;
        }

        return minOrMax;
    }


    //the bad -> O(n*n)
    //brute force compares each pair
    public int findMaxProfitSlow(int[] arr){
        int maxProfit = 0;

        for(int i = 0 ; i < arr.length; i++){

            for(int j = i + 1; j < arr.length; j++){

                maxProfit = Math.max(maxProfit,arr[j] - arr[i]);
            }

        }

        return maxProfit;
    }


    public static void main(String []args){
        int[] arr = new int[]{1,2,4,5,6,8,10,123};

        MaxProfitInSingleBuyNSell  maxProfitInSingleBuyNSell =  new MaxProfitInSingleBuyNSell();

        System.out.println(maxProfitInSingleBuyNSell.findMaxProfitSlow(arr));
        System.out.println(maxProfitInSingleBuyNSell.findMaxProfitAvg(arr));
        System.out.println(maxProfitInSingleBuyNSell.findMaxProfitFast(arr));
    }
}
