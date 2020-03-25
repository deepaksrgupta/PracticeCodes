import java.util.*;

public class GenerateAllNumberLessThanN {


    //https://leetcode.com/problems/numbers-at-most-n-given-digit-set/
    public int atMostNGivenDigitSet(String[] D, int N) {

        if(D.length == 0) {
            return D.length;
        }

        LinkedHashSet<String> previousSet = new LinkedHashSet<>();
        Long currentMax = Long.MIN_VALUE;

        for(String s : D){
            int digit = Integer.parseInt(s);
            if(digit <= N){
                previousSet.add(s);
            }

            currentMax = Math.max(currentMax,digit);
        }

        LinkedHashSet<String> currentSet = new LinkedHashSet<>();


        while (currentMax <= N){

            for(String currentDigit : D){

                for(String previousNumber : previousSet) {

                    String newNumber = currentDigit + previousNumber;
                    Long temp = Long.parseLong(newNumber);
                    if(temp <= N){
                        currentSet.add(newNumber);
                    }

                    currentMax = Math.max(currentMax,temp);

                }

                previousSet.addAll(currentSet);
                currentSet.clear();
            }

        }

        previousSet.addAll(currentSet);
        return previousSet.size();
    }

    public static void main(String[] args) {
        String[] s = {"1","4","9"};
        int n = 1000;

        System.out.println(new GenerateAllNumberLessThanN().atMostNGivenDigitSet(s,n));
    }
}