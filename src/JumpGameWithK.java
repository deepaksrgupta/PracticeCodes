import java.util.*;
import java.lang.*;
//https://binarysearch.com/problems/Bunnyhopping/editorials/3333535
class JumpGameWithK {

    static class KeyWithValue implements Comparable<KeyWithValue>{
        int key;
        int value;

        KeyWithValue(int key, int value){
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(KeyWithValue that){
            return this.value - that.value;
        }

        @Override
        public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KeyWithValue that = (KeyWithValue) o;
            return key == that.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    //O(n*logk) accepted. here dequeue can be used to find minimum from sliding window in constant time
    public int solve(int[] a, int k) {

        if(a.length == 0){return 0;}

        if(a.length == 1){return a[0];}

        PriorityQueue<KeyWithValue> pq = new PriorityQueue<>();

        pq.add(new KeyWithValue(a.length-1, a[a.length-1]));
        for(int i = a.length-2; i >= a.length - k; i--){

            if(i >= 0){
                pq.add(new KeyWithValue(i, a[i] + pq.peek().value));
            }
        }

        for(int i = a.length - k - 1; i >= 0 ; i--){

            while(pq.peek().key > i + k){
                pq.poll();
            }

            KeyWithValue min = pq.peek();

            pq.add(new KeyWithValue(i, a[i] + min.value));

            //pq.remove(new KeyWithValue(i + k, -1));
        }

        while (pq.peek().key != 0) {
            pq.poll();
        }
        return pq.poll().value;
    }

    //O(n*k) gives tle
    public int solve2(int[] a, int k) {

        int dp[] = new int[a.length];
        Arrays.fill(dp,10000000);
        dp[dp.length-1] = a[dp.length-1];

        for(int i = dp.length-2; i >= 0; i--){

            for(int j = 1; j <= k; j++){

                int sub = i + j;
                if(sub < dp.length){
                    dp[i] = Math.min(dp[i], dp[sub] + a[i]);
                }

            }

        }

        return dp[0];
    }
}
