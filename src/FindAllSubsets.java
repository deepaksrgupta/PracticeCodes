public class FindAllSubsets {
    static public void printAllCombinations(String ip){

        if(ip == null || ip.isEmpty()) {
            return;
        }

        helper(ip,0,new StringBuilder());
    }


    static public void helper(String ip, int start, StringBuilder output){

        if(start >= ip.length()){
            System.out.println(output.toString());
            return;
        }

        char currentChar = ip.charAt(start);


        output.append(currentChar);
        helper(ip,start+1,output);

        output.deleteCharAt(output.length()-1);
        helper(ip,start+1,output);
    }


    static public void main( String args[] ) {
        // Find All Possible Combinations Of String In Java

    /* All combination of string in java  is the companion problem to find permutation of the string .
    The combination  generated from the algorithm has range in length from one to the  length of the string.
    Two combinations that differ only in ordering of their characters are the same combination.
    In other words, "12" and "31" are different combinations from the input string "123", but "21" is the same as "12". */

//    If the input is "wxyz"  then the output of the string is

        //  " w wx wxy wxyz wxz wy wyz wz x xy xyz xz y yz z "



        // input  = abc

        // a , b, c, ab, bc, ac, abc


        // input = wxyz

        // w, x, y, z, wx, xy, yz, wy, xz, wz, wxy, xyz, wyz, wxz, wxyz


    /*
      abc




    */
        String s = "wxyz";
        printAllCombinations(s);
    }
}
