public class LongestPalindromeSubstring {

    /*
    https://leetcode.com/problems/longest-palindromic-substring/submissions/
    Runtime: 24 ms, faster than 80.97% of Java online submissions for Longest Palindromic Substring.
    Memory Usage: 39.3 MB, less than 63.21% of Java online submissions for Longest Palindromic Substring.
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {

            int len1 = expandAroundCenter(s, i, i);

            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
