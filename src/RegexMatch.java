import java.util.*;

public class RegexMatch {

    //https://leetcode.com/problems/regular-expression-matching/
    public boolean isMatch(String s, String p) {
        if(s.length() == 0 && p.length() == 0){
            return true;
        }

        if (p.isBlank()) return false;


        if(s.equals(p)) return true;

        p = getCleanedPattern(p);


        return helper(s,p,0,0);

    }


    public boolean helper(String s, String p, int si, int pi){

        if(pi == p.length() && si == s.length()){
            return true;
        }

        if(pi == p.length() && si < s.length() ) {
            return false;
        }


        if(pi+1 < p.length() && isWildCard(p.charAt(pi+1))){

            boolean dontConsider = helper(s,p,si,pi+2);

            boolean consider = false;

            if(si < s.length() && (p.charAt(pi) == '.' || p.charAt(pi) == s.charAt(si))){
                consider = helper(s,p,si+1,pi);
            }

            return consider || dontConsider;
        }

        if(si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.')){
            return helper(s,p,si+1,pi+1);
        }

        return false;
    }

    public boolean isWildCard(char c){
        return c == '*';
    }


    // removes multiple wildcards
    public String getCleanedPattern(String p){
        StringBuilder cleaned = new StringBuilder(p.length());

        char previous = p.charAt(0);

        cleaned.append(previous);


        for(int i =1; i< p.length(); i++){
            if(previous == p.charAt(i) && previous == '*'){
                continue;
            }

            previous = p.charAt(i);
            cleaned.append(previous);
        }

        return cleaned.toString();
    }

    public static void main(String[] args) {

        String s = "ab";
        String p = ".*c";

        System.out.println(new RegexMatch().isMatch(s,p));
    }
}