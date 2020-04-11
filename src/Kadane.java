public class Kadane {
    public int maxSubArray(int[] nums) {

        int finalMax = Integer.MIN_VALUE;
        int currentMax = 0;

        for(int i=0;i< nums.length;i++){
            currentMax = Math.max(nums[i],currentMax+nums[i]);
            finalMax = Math.max(currentMax,finalMax);
        }

        return finalMax;
    }
}
