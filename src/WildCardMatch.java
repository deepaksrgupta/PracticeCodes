import java.util.HashMap;

public class WildCardMatch {
    public static int countR = 0;
    public static boolean findWildCard(String s, int si, String p, int pi) {
        countR++;
        if(si >= s.length() || pi >= p.length()){
            return false;
        }

        if(s.charAt(si) == p.charAt(pi)){
            if(si== s.length()-1 && pi == p.length()-1){
                return true;
            } else {
                return findWildCard(s,si+1,p,pi+1);
            }
        }else {
            if(p.charAt(pi) == '?') {
                return findWildCard(s,si+1,p,pi+1);
            }else if(p.charAt(pi) == '*'){
                return findWildCard(s,si+1,p,pi) || findWildCard(s,si+1,p,pi+1) || findWildCard(s,si,p,pi+1);
            }
        }
        return false;
    }

    public static int countTD = 0;
    public static HashMap<String,Boolean> hashMap = new HashMap<>();
    public static boolean findWildCardTD(String s, int si, String p, int pi) {
        countTD++;
        if(si >= s.length() || pi >= p.length()){
            return false;
        }

        if(hashMap.containsKey(si+" "+pi)){
            return hashMap.get(si+" "+pi);
        }

        if(s.charAt(si) == p.charAt(pi)){
            if(si== s.length()-1 && pi == p.length()-1){
                return true;
            } else {

                if(!hashMap.containsKey((si+1)+" "+(pi+1))){
                    boolean r  = findWildCardTD(s,si+1,p,pi+1);
                    hashMap.put((si+1)+" "+(pi+1),r);
                }
                return hashMap.get((si+1)+" "+(pi+1));
            }
        }else {
            if(p.charAt(pi) == '?') {
                if(!hashMap.containsKey((si+1)+" "+(pi+1))){
                    boolean r  = findWildCardTD(s,si+1,p,pi+1);
                    hashMap.put((si+1)+" "+(pi+1),r);
                }
                return hashMap.get((si+1)+" "+(pi+1));
            }else if(p.charAt(pi) == '*'){
                if(!hashMap.containsKey((si+1)+" "+(pi))){
                    boolean r  = findWildCardTD(s,si+1,p,pi);
                    hashMap.put((si+1)+" "+(pi),r);
                }
                boolean first =  hashMap.get((si+1)+" "+(pi));

                if(!hashMap.containsKey((si+1)+" "+(pi+1))){
                    boolean r  = findWildCardTD(s,si+1,p,pi+1);
                    hashMap.put((si+1)+" "+(pi+1),r);
                }
                boolean second = hashMap.get((si+1)+" "+(pi+1));

                if(!hashMap.containsKey((si)+" "+(pi+1))){
                    boolean r  = findWildCardTD(s,si,p,pi+1);
                    hashMap.put((si)+" "+(pi+1),r);
                }
                boolean third = hashMap.get((si)+" "+(pi+1));

                return first || second || third;
            }
        }
        return false;
    }

    public static void main(String args[]) {

        String s = "adceeeeeeebbbb";
        String p = "*a*b*b";

        System.out.println(findWildCard(s,0,p,0));
        System.out.println(countR);

        System.out.println(findWildCardTD(s,0,p,0));
        System.out.println(countTD);
    }
}
