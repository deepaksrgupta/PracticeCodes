import java.util.ArrayList;

public class ZigZagString
{
    public static String zigzagString(String inputs,int rows){

        if(rows ==1){
            return inputs;
        }

        if(rows <= 0){
            return "";
        }

        ArrayList<StringBuilder> list = new ArrayList<>();

        //fill up array list
        for(int i=1;i<=rows;i++){
            list.add(new StringBuilder());
        }

        boolean isDownMode = true;
        int currentRow = 0;

        char[] input = inputs.toCharArray();
        for(int i=0;i<input.length;i++){

            if(isDownMode){

                StringBuilder currentList = list.get(currentRow);
                currentList.append(input[i]);

                currentRow++;

                if(currentRow % rows == 0){
                    isDownMode = false;
                    currentRow-= 2;
                }
            }else{

                StringBuilder currentList = list.get(currentRow);
                currentList.append(input[i]);

                currentRow--;

                if(currentRow == -1){
                    isDownMode = true;
                    currentRow+= 2;
                }
            }
        }

        //fill up the output
        StringBuilder output = new StringBuilder();
        for(StringBuilder current: list) {
            output.append(current);
        }
        return output.toString();
    }

    public static void main(String args[]) {
        System.out.println(zigzagString("PAYPALISHIRING",3));
    }
}

//for--->12345678901234567890123 and row=3

//1, ,5, ,9, ,3, ,7, ,1
//2,4,6,8,0,2,4,6,8,0,2
//3, ,7, ,1, ,5, ,9, ,3

//output 1,5,9,3,7,1,2,4,6,8,0,2,3,7,1,5,9,3