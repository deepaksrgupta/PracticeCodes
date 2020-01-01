import java.util.HashMap;

public class MaxRodCut {

    //same as hash map method but used proper array to cache the sub problems and utilise it
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

    //faster than exponential time
    //cache all the sub problems output in hashmap and return it if sub problem is already computed
    //not much difference in time but each recursion stack take activation record memory which makes it little slower compared to array based dp method
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
