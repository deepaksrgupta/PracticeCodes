import java.util.ArrayList;
import java.util.List;

public class SieveOfEratosthenes {
    //works in O(nloglogn)
    public List<Number> getPrimes(int n){
        List<Number> list = new ArrayList<>();

        if(n <= 1){
            return list;
        }

        boolean[] marks = new boolean[n+1];

        for(int i = 2; i*i <= n; i++){

            if(marks[i])    continue;

            for(int multiple = i * 2; multiple <= n; multiple = multiple + i){
                marks[multiple] = true;
            }

        }

        for(int i=2;i<marks.length;i++){
            if(!marks[i]){
                list.add(i);
            }
        }

        return list;
    }

    public static void main(String args[]){
        int n = 100;
        List<Number> primes = new SieveOfEratosthenes().getPrimes(n);

        for(Number i : primes){
            System.out.println(i);
        }
    }
}
