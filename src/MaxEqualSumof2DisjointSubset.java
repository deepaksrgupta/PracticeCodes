public class MaxEqualSumof2DisjointSubset {
    public int solve(int[] a){
        int[][][] cache = new int[a.length][10000][10000];
        return helper(a, 0,0,0,cache);
    }

    public int helper(int[] a, int leftSum, int rightSum, int i, int[][][] cache){

        if(i == a.length){
            return  leftSum == rightSum ? leftSum : 0;
        }

        if(cache[i][leftSum][rightSum] != 0){
            return cache[i][leftSum][rightSum];
        }



        int leftIncluded  = helper(a, leftSum + a[i], rightSum, i+1, cache);
        int rightIncluded  = helper(a, leftSum, rightSum + a[i], i+1, cache);
        int notIncluded  = helper(a, leftSum, rightSum, i+1, cache);

        cache[i][leftSum][rightSum] = Math.max(leftIncluded, Math.max(rightIncluded, notIncluded));

        return cache[i][leftSum][rightSum];
    }

    public static void main(String args[]){

        int[] arr = new int[]{1,2,4,6,8,10};

        int x = new MaxEqualSumof2DisjointSubset().solve(arr);
        System.out.println(x);
    }
}
