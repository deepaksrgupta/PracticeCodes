import java.util.HashSet;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubStringWithoutRepeatingChar {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()){
            return 0;
        }

        if(s.length() == 1){
            return 1;
        }

        int maxLength = 1;

        int i = 0, j = 1;
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(i));

        while(j < s.length()){

            if(set.contains(s.charAt(j))){

                maxLength = Math.max(maxLength, j - i);

                while(s.charAt(i) != s.charAt(j)){
                    set.remove(s.charAt(i));
                    i++;
                }
                i++;
            }else{
                set.add(s.charAt(j));
            }

            j++;

        }

        maxLength = Math.max(maxLength, j - i );

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubStringWithoutRepeatingChar().lengthOfLongestSubstring("bbbb"));
    }
}
