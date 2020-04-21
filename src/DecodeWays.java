import java.util.*;

public class DecodeWays {
    static int decodeVariations(String s) {
        // your code goes here

        int[] cache = new int[s.length()];
        cache[0] = s.charAt(0) != '0' ? 1 : 0;

        for(int i = 1; i< s.length() ; i++){

            if(s.charAt(i) != '0'){
                cache[i] = cache[i-1];
            }

            if(isValid(s.charAt(i-1),s.charAt(i))){

                if(i-2 >= 0) {
                    cache[i] += cache[i-2];
                }else{
                    cache[i] += 1;
                }
            }
        }

        return cache[s.length()-1];

    }

    //returns all strings which are decoded asked in amazon interview
    //time complexity is 2^N because we iterate for each decode which is same as branching off in fibonacci recursive code
    static List<String> getDecodedVariations(String s) {


        List<List<String>> output = new ArrayList<>();

        if(s.charAt(0) != '0'){

            char decodedChar = getDecoded('0',s.charAt(0));
            List<String> initialProblem = new ArrayList<>();
            initialProblem.add(String.valueOf(decodedChar));
            output.add(initialProblem);

        }else{
            output.add(new ArrayList<>());
        }


        for(int i = 1; i< s.length() ; i++){

            if(s.charAt(i) != '0'){

                List<String> previousSubProblem = output.get(i-1);

                List<String> currentSubProblem = new ArrayList<>();

                char decodedChar  = getDecoded('0',s.charAt(i));

                for(String decode : previousSubProblem){
                    currentSubProblem.add(decode+decodedChar);
                }

                output.add(currentSubProblem);

            }else{

                output.add(new ArrayList<>());
            }

            if(isValid(s.charAt(i-1),s.charAt(i))){

                char decodedChar  = getDecoded(s.charAt(i-1),s.charAt(i));

                if(i-2 >= 0) {

                    List<String> previousSubProblem  = output.get(i-2);
                    List<String> currentSubProblem = output.get(i);

                    for(String decode : previousSubProblem){
                        currentSubProblem.add(decode+decodedChar);
                    }

                }else{

                    List<String> currentSubProblem = output.get(i);
                    currentSubProblem.add(String.valueOf(decodedChar));

                    //output.add(i,currentSubProblem);
                }
            }
        }



        return output.get(output.size()-1);
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

    static char getDecoded(char first, char second){
        char result = '0';

        second = (char)(second - 49);


        if(first == '0'){
            result = (char) ((char) 'a' + second);
            return result;
        }else{
            if(first == '1'){
                result = 'j';
            }else{
                result = 't';
            }

            result = (char) ((char)result + second+1);
            return result;
        }
    }

    /*
    public static void main(String[] args) {
        String input  = "1211211";
        List<String> res = DecodeWays.getDecodedVariations(input);

        System.out.println("Size of output is "+ res.size());


        for (String r:
             res) {
            System.out.println(r);
        }
    }
     */

}
