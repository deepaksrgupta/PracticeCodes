import java.util.Arrays;

public class SortByPowValue {
    class Pair implements Comparable<Pair>{
        int number;
        int pow;

        Pair(int n, int p){
            number = n;
            pow = p;
        }

        @Override
        public int compareTo(Pair that) {
            int t = this.pow - that.pow;

            if(t != 0){
                return t;
            }

            return this.number - that.number;
        }
    }

    //https://leetcode.com/problems/sort-integers-by-the-power-value/
    public int getKth(int lo, int hi, int k) {

        if(k==0){
            return k;
        }

        Pair[] pairs = new Pair[hi-lo+1];


        for(int i= lo; i<= hi; i++){
            int j = getPow(i);
            pairs[i-lo] = new Pair(i,j);
        }


        Arrays.sort(pairs);


        return pairs[k-1].number;
    }

    private int getPow(int n){
        int c = 0;

        while(n != 1){

            if(n%2 == 1){
                n = 3 * n +1;
            }else{
                n = n / 2;
            }

            c++;
        }

        return c;
    }

    public static void main(String[] args) {

        System.out.println(new SortByPowValue().getKth(7,11,4));
    }
}
