public class LargestSumWithoutNeighbour {
    //https://binarysearch.com/problems/Largest-Sum-of-Non-Adjacent-Numbers
    public int solve(int[] nums) {
        int [][] cache = new int[nums.length + 1][2];
        return getSum(nums,0, 1, cache);
    }

    public int getSum(int[] nums, int i, int take, int [][] cache){

        if(i >= nums.length){
            return 0;
        }

        if(cache[i][take] != 0){
            return cache[i][take];
        }

        int sum = 0;

        if(take == 1 && nums[i] > 0){
            sum  = Math.max(sum, getSum(nums, i +1, 0, cache) + nums[i] );
        }

        sum = Math.max(sum, getSum(nums, i+1, 1, cache));

        cache[i][take] = sum;
        return sum;
    }
}
