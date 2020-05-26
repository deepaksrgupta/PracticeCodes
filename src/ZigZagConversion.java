import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
    public static String convert(String s, int numRows) {

        if(numRows <= 1)    return s;



        List<List<Character>> rows = new ArrayList<>(numRows);

        for(int i = 0; i< numRows; i++)
            rows.add(new ArrayList<Character>());


        boolean goingDown = true;
        /*
                              1 2 3
        0 1 2 3 4 5 6 7 8 9 0
        P A Y P A L I S H I R I N G
        i

        2,6,10
        4,8,12
        numRows = 4
        goingDown = false
        index = 1

        0-> P
        1-> A
        2-> Y

        6 -> true
        3, 9 -> false
        */

        rows.get(0).add(s.charAt(0));
        int index = 1;
        for(int i = 1; i < s.length(); i++){

            rows.get(index).add(s.charAt(i));

            if(index == 0 || index == numRows-1){
                goingDown = !goingDown;
            }

            if(goingDown){
                index++;
            }else{
                index--;
            }
        }


        int i = 0;
        char []output = new char[s.length()];
        for(List<Character> ll : rows){
            for(Character c : ll){
                output[i++] = c;
            }
        }


        return String.valueOf(output);
    }

    static public void main( String args[] ) {

        System.out.println(convert("PAYPALISHIRING",3));
    }
}
