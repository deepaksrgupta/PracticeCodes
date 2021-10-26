import java.util.HashMap;

public class GoodWaysToSplitString {
    //https://leetcode.com/submissions/detail/577301746/
    //O(n)

    public int numSplits(String s) {

        if (s == null || s.length() == 1) {
            return 0;
        }


        int validSplitCount = 0;

        HashMap<Character, Integer> leftCount = new HashMap<>();
        HashMap<Character, Integer> rightCount = new HashMap<>();

        for (char c : s.toCharArray()) {
            rightCount.put(c, rightCount.getOrDefault(c, 0) + 1);
        }


        for (int i = 0; i < s.length() - 1; i++) {

            char c = s.charAt(i);

            leftCount.put(c, leftCount.getOrDefault(c, 0) + 1);

            int charCount = rightCount.getOrDefault(c, 0);
            if (charCount > 0) {
                if (charCount == 1) {
                    rightCount.remove(c);
                } else {
                    rightCount.put(c, rightCount.get(c) - 1);
                }
            }

            if (leftCount.size() == rightCount.size()) {
                validSplitCount++;
            }
        }

        return validSplitCount;
    }

}
