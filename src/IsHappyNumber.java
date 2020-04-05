import java.util.HashSet;

public class IsHappyNumber {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<Integer>();


        while(!set.contains(n)){
            set.add(n);
            n  = getNewNumber(n);
            if(n == 1){
                return true;
            }
        }

        return false;
    }

    public int getNewNumber(int n){

        int result = 0;

        while(n != 0) {
            int t = n/10;
            result += t*t;

            n = n/10;
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(new IsHappyNumber().isHappy(1));
    }
}
