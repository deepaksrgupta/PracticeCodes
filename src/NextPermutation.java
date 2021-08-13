import java.util.Arrays;

//https://leetcode.com/submissions/detail/537928082/
class NextPermutation {
    public void nextPermutation(int[] nums) {
        //find the rirst number from right which is smaller than the left one
        //after finding replace it with next max
        //sort the remaning array

        int numToReplace = -1;


        for(int i = nums.length - 2 ; i >= 0; i--){

            if(nums[i+1] > nums[i]) {
                numToReplace = i;
                break;
            }
        }

        if(numToReplace == -1){
            reverse(nums);
            return;
        }



        int nextMaxIndex = numToReplace;
        //finding any max for below for loop not behave in unexpected manner
        for(int i = nextMaxIndex + 1; i < nums.length; i++)
            if(nums[i] >= nums[nextMaxIndex])
                nextMaxIndex = i;



        for(int i = numToReplace + 1; i < nums.length; i++ ){

            if(nums[i] > nums[numToReplace] && nums[i] <= nums[nextMaxIndex]){

                nextMaxIndex = i;
            }

        }

        swap(nums, nextMaxIndex, numToReplace);

        Arrays.sort(nums, numToReplace + 1, nums.length);

    }

    public void reverse(int[] nums){

        int start = 0;
        int end = nums.length - 1;


        while(start < end){
            swap(nums, start, end);
            start++;
            end--;
        }

    }

    public void swap(int[] nums, int i, int j){
        int x = nums[i];
        nums[i] = nums[j];
        nums[j] = x;
    }
}