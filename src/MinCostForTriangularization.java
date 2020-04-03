import java.util.*;

public class MinCostForTriangularization {

    int cache[][];

    public int minScoreTriangulation(int[] a) {
        if(a.length < 3)    return 0;

        if(a.length == 3) return a[0]*a[1]*a[2];

        cache = new int[a.length][a.length];

        return helper(a,0,a.length-1);

    }

    public int helper(int a[],int start,int end){

        if(end-start+1 < 3) return 0;

        if(cache[start][end] != 0) return cache[start][end];

        int result = Integer.MAX_VALUE;


        for(int k = start + 1; k < end; k++) {

            result  = Math.min(result, helper(a,start,k) + (a[start] * a[end] * a[k]) + helper(a,k,end) );

        }


        cache[start][end] = result;

        return result;
    }

    public static void main(String[] args) {

        int a[] = {3,7,4,5};

        System.out.println(new MinCostForTriangularization().minScoreTriangulation(a));
    }
}