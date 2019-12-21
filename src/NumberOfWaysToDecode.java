import java.util.*;

public class NumberOfWaysToDecode {

    static int countDecodingDP(int digits[], int n)
    {
        int a =1,b=1;
        int c= 0;

        for (int i = 2; i <= n; i++)
        {
            c = 0;

            if (digits[i - 1] > 0)
                c = b;

            if (digits[i - 2] == 1 ||
                    (digits[i - 2] == 2 &&
                            digits[i - 1] < 7))
                c += a;

            a = b;
            b = c;
        }
        return c;
    }


    public static HashMap<Integer,Character> hashMap = new HashMap();

    public static int printAndCountNumDecodings(int[] digits, int index, String output) {

        if (index >= digits.length) {
            Helper.display(output);
            return 1;
        }

        if (digits[index] != 0) {
            int first = printAndCountNumDecodings(digits, index + 1, output + hashMap.get(digits[index]));

            int withoutFirst = 0;

            if(!(index+2 <= digits.length && !isValid(digits[index],digits[index+1])))
            {
                if(index+1 !=digits.length)
                    withoutFirst = printAndCountNumDecodings(digits, index + 2,
                            output + hashMap.get( Integer.parseInt(Integer.toString(digits[index]) + Integer.toString(digits[index+1]) )));
            }

            return first+withoutFirst;

        } else return 0;
    }

    public static boolean isValid(int firstDigit, int secondDigit) {
        if (firstDigit== 0|| (firstDigit > 2 && secondDigit > 6) || firstDigit > 2) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        int[] inputDigits = {1,1,0,2,1,3,1};


        for(int i=0;i<26;i++)
            hashMap.put(i+1,(char)('A'+i));

        //solution to count and print number ways to decode
        Helper.display(Integer.toString(printAndCountNumDecodings(inputDigits, 0, "")));

        //dp solution to count number of ways to decode
        Helper.display(Integer.toString(countDecodingDP(inputDigits,inputDigits.length)));

    }
}
