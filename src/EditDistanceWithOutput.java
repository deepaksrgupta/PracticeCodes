import java.io.*;
import java.util.*;

class EditDistanceWithOutput {

    static String[] diffBetweenTwoStrings(String source, String target) {

        // your code goes here
        int[][] subProblems = new int[source.length()+1][target.length()+1];

        for (int i = 0; i <= source.length(); i++)
            subProblems[0][i] = i;

        for (int i = 0; i <= target.length(); i++)
            subProblems[i][0] = i;


        for(int i = 1; i <= source.length(); i++){

            for(int j = 1; j <= target.length(); j++){

                if(source.charAt(i) == target.charAt(j)){
                    subProblems[i][j] = subProblems[i-1][j-1];
                }else{
                    subProblems[i][j] = Math.min(subProblems[i-1][j],subProblems[i][j-1]) + 1;
                }

            }

        }


    /*
      S    A,B,C
      T  0 1,2,3
      A  1,0,1,2
      B  2,1,0,1


      output: A,
    */

        List<String> output = new ArrayList<String>();
        int row = 1;
        int col = 1;

        while(row <= target.length() && col <= source.length()) {

            if(subProblems[row][col] == subProblems[row-1][col-1]){

                // String.valueOf(c)
                output.add(String.valueOf(target.charAt(row-1)));
                row++;
                col++;

            }else if(subProblems[row-1][col] == subProblems[row][col-1]
                    || subProblems[row-1][col] < subProblems[row][col-1]){

                output.add("-"+source.charAt(col-1));
                col++;

            }else{

                output.add("+"+target.charAt(row-1));
                row++;
            }

        }


        while(row <= target.length()){
            output.add("+"+target.charAt(row-1));
            row++;
        }

        while(col <= source.length()){
            output.add("-"+source.charAt(col-1));
            col++;
        }

        String[] result = new String[output.size()];
        output.toArray(result);
        return result;
    }


    // source: A,B,C,D,E,F,G
    // destin: A,B,D,F,F,G,H

  /*




    1. if i,j == i-1,j-1 then add the current in output list
  2. else if i-1,j == i,j-1, then add i,j+1 to output list as -char
  3. else whatever is the min accordinhg to i need to add it in output - or +

  output list: A,B,-C,D,-E,F,+F,G,+H
              "A", "B", "-C", "D", "-E", "F", "+F", "G", "+H"
     S A,B,C,D,E,F,G
  T  0 1,2,3,4,5,6,7
  A  1,0,1,2,3,4,5,6
  B  2,1,0,1,2,3,4,5
  D  3,2,1,2,1,2,3,4
  F  4,3,2,3,2,3,2,3
  F  5,4,3,4,3,4,3,4
  G  6,5,4,5,4,5,4,3
  H  7,6,5,6,5,6,5,4





  */



  /*

  t(i,j) =  0 + t(i-1,j-1) s[i] == d[j]
           min(t(i-1,j),t(,j+1))                 s[i] != d[j]



  */

    public static void main(String[] args) {

    }
}
