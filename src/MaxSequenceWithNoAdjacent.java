import java.io.*;
import java.util.*;

class MaxSequenceWithNoAdjacent {

    static HashMap<Integer,Integer> hashMap = new HashMap<>();

    public static int findMaxWithNoAdjacentSubSequence(int[] arr, int index){

        if(hashMap.containsKey(index)){
            return hashMap.get(index);
        }

        if(index >= arr.length){
            return 0;
        }


        //get the next n+2 sub problem with addition to current element
        int withElement = findMaxWithNoAdjacentSubSequence(arr, index + 2);

        //below statement will handle negative elements? why you ask?
        //because bluntly adding negative number in sum will decrease the sum so its better to ignore it
        withElement = Math.max(withElement, arr[index] + withElement);

        //get n+1 sub problem with ignoring currrent element
        int withNeighbour  = findMaxWithNoAdjacentSubSequence(arr, index +1);


        int finalSum =  Math.max(withElement, withNeighbour);
        hashMap.put(index,finalSum);
        return finalSum;
    }

    public static void main(String args[]){

        //int[] arr = {3,8,2,9,4,1,5};
        int[] arr = {-2,3,4,-5,8,9};

        System.out.println(findMaxWithNoAdjacentSubSequence(arr,0));

        System.out.println("below are sub problems results");
        for(Integer e : hashMap.keySet()){
            System.out.println(e+ " "+ hashMap.get(e));
        }
    }
}
