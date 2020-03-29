public class MinFromSortedRotatedArray {
    public int findMin(int[] nums) {

        if(nums.length == 0) return 0;

        if(nums.length == 1 || nums[0] < nums[nums.length-1]) return nums[0];

        return helper(nums,0,nums.length-1);
    }

    public int helper(int []a, int start, int end){

        if(start == end) return a[start];

        if(start+1 == end) return Math.min(a[start],a[end]);

        int mid = (start + end) / 2;

        if(a[start] > a[mid]){
            return helper(a,start,mid);
        }else{
            return helper(a,mid,end);
        }

    }

    public static void main(String[] args) {

        MinFromSortedRotatedArray m = new MinFromSortedRotatedArray();

        int[] a = {6,7,0,1,2,3,4,5};

        System.out.println(m.findMin(a));

    }
}
