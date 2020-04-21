public class DivisibleString {

    //asked in vmware
    public static String stringDivisible(String start,String end){

        String result  = "-1";

        if(end.length() > start.length()) {
            return result;
        }

        boolean isDivisible  = true;

        if(!start.equals(end)) {

            while (!start.equals(end) && start.length() >= end.length()){
                end  = end + end;
            }

            isDivisible = start.equals(end);
        }


        if(isDivisible) {

            //cut down the input string
            result = cutDown(end);

        }


        return result;
    }

    public static String cutDown(String source){

        if(source.length() % 2 != 0){
            return source;
        }


        int mid = source.length()/2;

        String first  = source.substring(0,mid);
        String second = source.substring(mid);

        if(first.equals(second)){
            return cutDown(first);
        }else{
            return source;
        }
    }

    public static void main(String[] args) {
        System.out.print(stringDivisible("abcabcabc","abcabc"));
    }
}
