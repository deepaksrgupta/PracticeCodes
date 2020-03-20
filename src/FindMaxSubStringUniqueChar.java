import java.util.HashMap;

public class FindMaxSubStringUniqueChar {
    public static int findMaxSubStringUniqueChar(String s){

        if(s.length() == 0) {
            return 0;
        }

        //base case
        int maxLength = 1;
        HashMap<Character,Integer> map = new HashMap<>();


        for(int i = 0, j = 0; j < s.length(); j++) {

            //if current char is already existing then change i; sliding window will decrease
            if(map.containsKey(s.charAt(j))){
                i  = Math.max(i,map.get(s.charAt(j)));
            }

            //check if current sliding window is maximum
            maxLength = Math.max(maxLength,j-i+1);

            //add updated index for each character mapping to utilise to reduce the sliding window
            map.put(s.charAt(j),j+1);
        }

        return maxLength;

    }
    public static void main(String[] args) {
        System.out.println(findMaxSubStringUniqueChar("abcabcbb"));
    }
}
