import java.util.HashMap;

public class MaxRodCut {

    //bottom up technique to solve rod cut uses tabular method to store result of sub problems in bottom up manner
    //usually bottom up is faster than top down by constant factor
    static int cutRodDP(int price[],int rodSize) {

        int subProblems[] = new int[rodSize+1];
        subProblems[0] = 0;

        for(int i=1; i <= rodSize; i++) {

            int ans = Integer.MIN_VALUE;
            for(int j = 0 ; j < i ; j++){
                ans = Integer.max(ans,price[j] + subProblems[i-j-1]);
            }

            subProblems[i] = ans;
        }
        return subProblems[rodSize];
    }

    //top down approach to solve rod cut problem uses memoization to store result in top down manner
    //instead of hashmap we can use array to store result of sub problems
    static HashMap<Integer,Integer> hm = new HashMap<>();
    public static int cutRod(int arr[],int size){

        if(size <= 0){
            return 0;
        }
        //count++;

        if(hm.containsKey(size)){
            return hm.get(size);
        }

        int currentMax = Integer.MIN_VALUE;
        for(int i=0;i<size;i++){
            currentMax = Integer.max(currentMax,arr[i]+ cutRod(arr,size-i-1));
        }

        hm.put(size,currentMax);
        return currentMax;
    }

    public static void MemicRodCut() {
        int arr[] = new int[] {1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22
                ,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22
                ,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22
                ,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22
                ,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,
                1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,
                1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,
                1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22,1, 5, 8, 9, 10, 17, 17, 20,12,1,4,64,47,3,3,73,67,25,78,22,66,33,67,4,99,22};
        int size = arr.length;
        long l1 = System.currentTimeMillis();
        //System.out.println("Max value you can get is " + cutRod(arr,130));
        System.out.println(cutRod(arr,size));
        long l2 = System.currentTimeMillis();

        System.out.println("hm size is "+hm.size());

        System.out.println(l2 - l1);
        //System.out.println("Total fn calls "+count);
        l1 = System.currentTimeMillis();
        System.out.println(cutRodDP(arr,size));
        l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
    }
}
