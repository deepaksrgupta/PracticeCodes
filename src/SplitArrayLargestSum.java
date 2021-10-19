public class SplitArrayLargestSum {
    /*
    https://leetcode.com/submissions/detail/573843537/
    https://leetcode.com/problems/split-array-largest-sum/
    works in O(n * log(sum))
     */
    public int splitArray(int[] nums, int m) {

        if(m == 0){
            return 0;
        }

        int sum = 0;
        int max = -1;

        for(int num : nums){
            sum += num;
            max = Math.max(max, num);
        }

        if(m == 1){
            return  sum;
        }

        int low = max;
        int high = sum;

        while(low < high){

            int mid =  low + (high - low)/2;

            int limitSubArrayCount = getMaxSubArraySum(nums, mid);

            if(limitSubArrayCount > m){
                low = mid + 1;
            }else{
                high = mid;
            }
        }


        /*
        [7,2,5,10,8]

        m = 2;

        low = 10
        high = 32

        mid = 21

        limitSubArrayCount = 2;

        low = 10
        high = 21
        mid = 15

        limitSubArrayCount = 3;

        low = 15
        high = 21
        mid = 18


        limitSubArrayCount = 2;

        low = 18
        high = 21
        mid = 19

        limitSubArrayCount = 2;

        low = 18
        high = 19
        mid = 18

        limitSubArrayCount = 2;

        low = 18
        high = 18


        return low



        */


        return low;
    }

    private int getMaxSubArraySum(int[] nums, int runningSumMax){
        int count = 1;

        int runningSum = 0;

        for(int num: nums){

            if(runningSum + num > runningSumMax){
                runningSum = num;
                count++;
            }else{
                runningSum += num;
            }

        }


        return count;
    }

    public static void main(String[] args) {

        System.out.println(new SplitArrayLargestSum().splitArray(new int[]{7,2,5,10,8},2));
    }
}
