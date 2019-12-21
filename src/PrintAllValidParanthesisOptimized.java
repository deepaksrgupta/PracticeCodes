import java.util.*;
public class PrintAllValidParanthesisOptimized {


    static ArrayList<String> set = new ArrayList<>();

    static void generatePermutation(int left,int right,char[] workingArray,int index)
    {
        if(left < 0 || right < left)
        {
            return;
        }

        if(left==0 && right==0)
        {
            set.add(String.copyValueOf(workingArray));
            return;
        }

        workingArray[index] = '(';
        generatePermutation(left-1,right,workingArray,index+1);

        workingArray[index] = ')';
        generatePermutation(left,right-1,workingArray,index+1);
    }

    public static void main(String[] args) {

        int n = 5;
        char[] workingArray = new char[n*2];
        generatePermutation(n,n,workingArray,0);

        for(String s:set)
            System.out.println(s);
    }
}
