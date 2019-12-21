import java.util.HashMap;
import java.util.Random;

public class Multiply2Strings
{
    public static String multiplyByDigit(String firstNumber,int digit){
        String result = "";
        int carry = 0;


        for(int i=firstNumber.length()-1;i>=0;i--){
            int multiple = (Character.getNumericValue(firstNumber.charAt(i)) * digit) + carry;
            if(multiple <= 9){

                result =  multiple + result;
                carry = 0;
            }else{

                result =  (multiple%10) + result;
                carry = (multiple - (multiple%10)) /10;
            }
        }

        if(carry != 0)
            result = carry + result;

        return result;
    }

    public static boolean checkZeroString(String s){
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)!='0'){
                return false;
            }
        }

        return true;
    }

    public static String  addStrings(String firstNumber,String secondNumber,int placePadding){

        if(checkZeroString(secondNumber)){
            return firstNumber + "0";
        }


        String result = "";

        for(int i= firstNumber.length() - placePadding;i < firstNumber.length();i++) {
            result = result + firstNumber.charAt(i);
        }

        int firstNumberIndex = firstNumber.length() - placePadding - 1;
        int carry = 0;

        for(int i = secondNumber.length()-1;i>=0;i--){

            if(firstNumberIndex >= 0){
                int sum = carry+Character.getNumericValue(secondNumber.charAt(i))+Character.getNumericValue(firstNumber.charAt(firstNumberIndex));
                result  = (sum%10) + result;
                carry = (sum - (sum%10))/10;

                firstNumberIndex--;
            }else{
                int sum = (carry+Character.getNumericValue(secondNumber.charAt(i)));
                result  = (sum%10) + result;
                carry = (sum - (sum%10))/10;
            }
        }

        while (firstNumberIndex >= 0){
            result  = (carry + firstNumber.charAt(firstNumberIndex)) + result;
            firstNumberIndex--;
            carry = 0;
        }

        if(carry != 0){
            result  = carry + result;
        }


        return result;
    }

    public static String multiplyString(String firstNumber,String secondNumber){

        String result = "";
        HashMap<Character,String> cache = new HashMap<>();

        for(int i=secondNumber.length()-1;i>=0;i--){

            String mulResult;

            if(i != secondNumber.length()-1 && secondNumber.charAt(i)== '0'){
                continue;
            }

            if(cache.containsKey(secondNumber.charAt(i))){
                mulResult = cache.get(secondNumber.charAt(i));
            }else{
                mulResult = multiplyByDigit(firstNumber,Character.getNumericValue(secondNumber.charAt(i)));
                cache.put(secondNumber.charAt(i),mulResult);
            }

            if(i == secondNumber.length()-1){
                result = mulResult;
            }else{
                result = addStrings(result,mulResult,secondNumber.length() - i-1);
            }
        }

        return  result;
    }

    public static void main(String args[]) {

        Random r = new Random();

        int i = 0;
        while(i < 1000){
            int a = r.nextInt(10000);
            int b = r.nextInt(10000);
            String answer = multiplyString(Integer.toString(a),Integer.toString(b));
            int c = 0;
            try{
                c = Integer.parseInt(answer);
                if(c != a*b){
                    System.out.println("error for "+a+" "+b+" "+answer);
                }else{
                    System.out.println("Success for "+a+" "+b);
                }
            }catch (Exception e){
                System.out.println("Number too big--> a:"+a+" b:"+b+" Ans:"+answer);
            }

            i++;
        }
    }
}
