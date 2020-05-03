import java.util.HashMap;
public class JumpGame2DP {

    //https://leetcode.com/problems/jump-game-ii/
    //O(n*n)
    public HashMap<Integer,Integer> hm;

    public int jump(int[] nums) {
        if(nums.length == 0)    return -1;

        if(nums[0] == 0)  return -1;

        hm = new HashMap<>();

        return helper(nums,0);
    }


    public int helper(int []a,int start){
        if(start == a.length-1)   return 0;

        if(start >= a.length || a[start] == 0) return Integer.MAX_VALUE;

        if(hm.containsKey(start))
            return hm.get(start);

        int result = Integer.MAX_VALUE;


        for(int i = 1; i <= a[start]; i++) {
            result = Math.min(result,helper(a,start+i)+1);
        }

        hm.put(start,result);

        return result;
    }

    public static void main(String[] args) {
        int []a = {2,3,1,1,4};
        System.out.println(new JumpGame2DP().jump(a));
    }
}
