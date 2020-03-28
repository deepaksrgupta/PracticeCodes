import java.util.*;

class WordBreak1 {

    private String s;
    private HashSet<String> words;
    private int subProblems[];

    public boolean wordBreak(String s, List<String> wordDict) {
        this.s = s;
        subProblems = new int[s.length()+1];
        Arrays.fill(subProblems,-1);

        words = new HashSet<>();
        words.addAll(wordDict);


        return helper(0);
    }

    //https://leetcode.com/problems/word-break/
    private boolean helper(int start){

        if(start == s.length()){
            return true;
        }

        if(subProblems[start] != -1){
            return subProblems[start] == 1;
        }


        boolean result = false;
        for(int i = start+1; i <= s.length(); i++){

            String currentWord = s.substring(start,i);

            if(words.contains(currentWord)){
                result = result || helper(i);
            }

        }

        subProblems[start] = result ? 1 :0;

        return result;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {

        boolean[] subProblems = new boolean[s.length()+1];

        HashSet<String> words = new HashSet<>();
        words.addAll(wordDict);

        if(words.contains(s)){
            return true;
        }

        boolean result = false;

        //fill up base case
        subProblems[s.length()-1] = words.contains(s.substring(s.length()));


        for(int i = s.length()-1; i >= 0 ; i--){

            for(int j = i-1; j < s.length()-1; j++){

                boolean isCurrentValid = subProblems[j] && words.contains(s.substring(j,i));
                subProblems[i] = subProblems[i] || isCurrentValid;
            }
            subProblems[i] = subProblems[i] || words.contains(s.substring(i));
        }

        return result;
    }

    public static void main(String[] args) {

        String s  = "catsandog";
        String[] wordsString  = {"cats", "dog", "sand", "and", "cat","og"};
        List<String> words = Arrays.asList(wordsString);

        WordBreak1 wordBreak = new WordBreak1();

        System.out.println(wordBreak.wordBreak(s,words));
        System.out.println(wordBreak.wordBreak2(s,words));
        System.out.println(s.substring(s.length()-1));
    }
}