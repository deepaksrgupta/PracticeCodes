import java.util.*;

public class WordLadderLength {
    private HashMap<String,Integer> hm;
    private HashMap<String,Boolean> visited;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        hm = new HashMap<>();
        visited = new HashMap<>();

        for(String s : wordList)
            visited.put(s,false);


        if(!visited.containsKey(endWord)){
            return 0;
        }

        int result = 1+ladderLengthHelper(beginWord.toCharArray(),endWord.toCharArray());

        if(result == Integer.MAX_VALUE){
            result = 0;
        }

        return result;

    }

    public int ladderLengthHelper(char[] start,char[] end){

        String begin = new String(start);

        if(begin.equals(new String(end))){
            return 0;
        }

        if(hm.containsKey(begin)){
            return hm.get(begin);
        }

        int minLength = Integer.MAX_VALUE;


        for(int i=0;i < start.length; i++){

            for(char c = 'a'; c <= 'z'; c++){
                char backUp = start[i];

                if(c == start[i]){
                    continue;
                }

                start[i] = c;
                String currentString  = new String(start);
                String endString = new String(end);
                if(currentString.equals(endString)){
                    return 1;
                }

                if(visited.containsKey(currentString) && !visited.get(currentString)){
                    visited.put(begin,true);

                    System.out.println(currentString+" -> "+endString);

                    minLength = Math.min(minLength,1 + ladderLengthHelper(start,end));
                }

                start[i] = backUp;
            }
        }

        hm.put(begin,minLength);

        return minLength;

    }

    //https://leetcode.com/problems/word-ladder/
    public static void main(String[] args) {

        String start  = "qa";
        String  end = "sq";

        String[] words = new String[]{"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
        List<String> ll = Arrays.asList(words);



        System.out.println(new WordLadderLength().ladderLength(start,end,ll));
    }
}
