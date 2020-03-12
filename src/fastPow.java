public class fastPow {
    public static double myPow(double x, int n) {

        if (n < 0){
            if(n == -2147483648){
                return 1/(helper(x,2147483647) * x);
            }else{
                return 1/helper(x,-n);
            }
        }else{
            return helper(x,n);
        }
    }

    public static double helper(double x, int n){
        if(x == 1){
            return x;
        }

        if(n == 0){
            return 1;
        }

        if(n == 1) {
            return x;
        }

        double val = helper(x,n/2);
        if (n % 2 == 0) {
            return val * val;
        }else{
            return val * val * x;
        }
    }


    public static double iterative(double x,int n){

        if (x == 1 || x == 0){
            return 1;
        }

        if(n < 0 ) {

            if(n == -2147483648) {
                return 1 / (itrHelper(x,2147483647) * x);
            }
            return 1 / itrHelper(x,-n);
        }

        return itrHelper(x,n);
    }

    public static double itrHelper(double x,int n){
        double result = 1;

        while (n > 0){

            if (n % 2 == 1){
                result = result * x;
            }

            x = x * x;
            n = n/2;
        }
        return result;
    }

    public static void main(String args[]) {
        int a = 2;
        int n = 11;

        /* Both solution gives stats on leetcode
        Runtime: 0 ms, faster than 100.00% of Java online submissions for Pow(x, n).
        Memory Usage: 37 MB, less than 5.88% of Java online submissions for Pow(x, n).
         */
        System.out.println(myPow(a,n));
        System.out.println(iterative(a,n));
    }
}
