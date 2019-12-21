import java.util.*;
public class PrintAllValidParanthesis {

    static public HashSet<String> generateAllParanthesis(int n)
    {
        HashSet<String> currentSet = new HashSet<>();

        if(n==1)
        {
            currentSet.add("()");
            return currentSet;
        }
        else
        {
            HashSet<String> previousSet = generateAllParanthesis(n-1);

            for(String s:previousSet)
            {
                for(int i=0;i<s.length();i++)
                {
                    if(s.charAt(i)=='(')
                    {
                        currentSet.add(getProcessedString(s,i));
                    }
                }
                currentSet.add(s+"()");
            }
        }

        return currentSet;
    }


    static public String getProcessedString(String s,int l){

        String left = s.substring(0,l+1);
        String right = s.substring(l+1);
        return  left + "()" +right;
    }

    public static void main(String[] args) {

        int n = 3;
        HashSet<String> set = generateAllParanthesis(n);
        for(String s:set)
            System.out.println(s);
    }
}
