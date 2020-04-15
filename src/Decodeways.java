public class Decodeways {
    static int decodeVariations(String s) {
        // your code goes here

        int[] cache = new int[s.length()];
        cache[0] = s.charAt(0) != '0' ? 1 : 0;

        for(int i = 1; i< s.length() ; i++){

            if(s.charAt(i) != '0'){
                cache[i] = cache[i-1];
            }

            if(i-1 >= 0 && isValid(s.charAt(i-1),s.charAt(i))){

                if(i-2 >= 0) {
                    cache[i] += cache[i-2];
                }else{
                    cache[i] += 1;
                }
            }
        }

        return cache[s.length()-1];

    }

    static boolean isValid(char first,char second){

        if(first == '0' && second == '0'){
            return false;
        }

        if(first > '2')
            return false;

        if(second > '6'){
            return false;
        }


        return true;
    }
}
