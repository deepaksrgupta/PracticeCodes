import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class sortFrequencyByChar {

    /*
    https://leetcode.com/problems/sort-characters-by-frequency/submissions/
     */

    public String frequencySort(String s) {

        /*
        get count of each character anc sort it then populate the string and return it

        n*logn
        */

        HashMap<Character,Integer> counts = new HashMap<>();


        for(char c: s.toCharArray()){
            int newCount = counts.getOrDefault(c, 0);

            counts.put(c, newCount+1);
        }


        List<Character> sortedChar = new ArrayList<>(counts.keySet());
        Collections.sort(sortedChar, (a, b) ->  counts.get(b) - counts.get(a));


        StringBuilder sb = new StringBuilder();

        for(Character c : sortedChar){

            for(int i = 0; i < counts.get(c); i++){

                sb.append(c);
            }
        }


        return sb.toString();
    }
}
