import java.util.*;
public class CombinationsOfPhoneNumber {

    /*
    Runtime: 2 ms, faster than 55.68% of Java online submissions for Letter Combinations of a Phone Number.
    Memory Usage: 39.1 MB, less than 6.16% of Java online submissions for Letter Combinations of a Phone Number.
     */
    public List<String> letterCombinations(String digits) {

        HashMap<Character,List<Character>> mappings = new HashMap<>();
        mappings.put('2', Arrays.asList('a','b','c'));
        mappings.put('3', Arrays.asList('d','e','f'));
        mappings.put('4', Arrays.asList('g','h','i'));
        mappings.put('5', Arrays.asList('j','k','l'));
        mappings.put('6', Arrays.asList('m','n','o'));
        mappings.put('7', Arrays.asList('p','q','r','s'));
        mappings.put('8', Arrays.asList('t','u','v'));
        mappings.put('9', Arrays.asList('w','x','y','z'));


        Set<String> set = new HashSet<>();
        set.add("");

        Set<String> currentSet = new HashSet<>();

        for(int i = 0 ; i < digits.length(); i++){

            List<Character> characters  = mappings.get(digits.charAt(i));

            for(Character s : characters){

                for(String prefix : set){
                    currentSet.add(prefix+s);
                }
            }


            set.clear();
            set.addAll(currentSet);
            currentSet.clear();
        }

        List<String> mainList = new ArrayList<String>();

        if(set.size() > 1) {
            mainList.addAll(set);
        }


        return mainList;
    }
}
