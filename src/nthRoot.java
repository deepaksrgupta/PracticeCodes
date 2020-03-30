public class nthRoot {
    static double nthroot(double x, int n) {

        double result = 0;


        double start = 0;
        double end  = x/n;

        while(start <= end){

            double mid = start + (end - start)/2;

            result = Math.pow(mid,n);


            if(result == x || Math.abs(result-x) < 0.0001){
                break;
            }


            if(result > x) {
                end = mid;
            }else{
                start = mid;
            }

        }


        return result;
    }
}
