import java.util.HashMap;

public class LCS {
    /*
    recursive LCS
     */
    static int countR = 0;
    public static int solveLCS(char[] firstArr, char[] secondArr,int first,int second){
        countR++;

        if(first >= firstArr.length || second >= secondArr.length){
            return 0;
        }

        if(firstArr[first] == secondArr[second]){
            return 1 + solveLCS(firstArr,secondArr,first+1,second+1);
        }else {
            return Math.max(solveLCS(firstArr,secondArr,first+1,second),solveLCS(firstArr,secondArr,first,second+1));
        }
    }

    /*
    top down DP LCS with hashmap ds as a memoization
     */
    static int countTD = 0;
    static HashMap<String,Integer> hashMap = new HashMap<>();
    public static int solveLCSTD(char[] firstArr, char[] secondArr,int first,int second){
        countTD++;

        if(first >= firstArr.length || second >= secondArr.length){
            return 0;
        }

        if(firstArr[first] == secondArr[second]){
            if(hashMap.containsKey(first+","+second)){
                return hashMap.get(first+","+second);
            }else{
                int result = solveLCSTD(firstArr,secondArr,first+1,second+1);
                hashMap.put(first+","+second,result+1);
                return 1 + result;
            }
        }else {
            //return Math.max(solveLCSTD(firstArr,secondArr,first+1,second),solveLCSTD(firstArr,secondArr,first,second+1));

            int firstResult,secondResult;
            if(hashMap.containsKey((first+1) + "," + second)){
                firstResult = hashMap.get((first+1) + "," + second);
            }else{
                firstResult = solveLCSTD(firstArr,secondArr,first+1,second);
            }

            if(hashMap.containsKey(first + "," + (second+1))){
                secondResult = hashMap.get(first + "," + (second+1));
            }else{
                secondResult = solveLCSTD(firstArr,secondArr,first,second+1);
            }

            return Math.max(firstResult,secondResult);
        }
    }

    /*
    bottom up LCS matrix as tabular
     */
    public static void solveLCSBU(char[] first, char[] second){
        int[][] table = new int[first.length+1][second.length+1];
        int[][] direction = new int[first.length+1][second.length+1];

        //fill up table
        for(int i = 0; i <= first.length; i++) {
            for (int j = 0; j <= second.length; j++){
                if(i == 0 || j == 0){
                    //base case for empty string comparison
                    table[i][j] = 0;

                }else if(first[i-1] == second[j-1]){
                    //case where characters are equal so take previous equal
                    table[i][j] = table[i-1][j-1] + 1;
                    direction[i][j] = 1;

                }else if(table[i-1][j] > table[i][j-1]){
                    //take the winner sub problem
                    table[i][j] = table[i-1][j];
                    direction[i][j] = 2;

                } else{
                    table[i][j] = table[i][j-1];
                    direction[i][j] = 3;
                }
            }
        }

        System.out.println("Botoom up -> LCS is " + getLCSString(direction,first,first.length,second.length) + " of length "+table[first.length][second.length]);
    }

    public static String getLCSString(int[][] direction,char[] first,int i,int j){
        if (i == 0 || j == 0) {
            return "";
        }

        if(direction[i][j] == 1){
            return  getLCSString(direction,first,i-1,j-1)+ first[i-1] ;
        }else if(direction[i][j] == 2){
            return getLCSString(direction,first,i-1,j);
        }else{
            return getLCSString(direction,first,i,j-1);
        }
    }

    public static void findLCSHelper(String first, String second){
        System.out.println("LCS from recursion is " + solveLCS(first.toCharArray(),second.toCharArray(),0,0));
        System.out.println("Total Recursive function calls is " + countR);

        System.out.println("LCS from Top down is " + solveLCSTD(first.toCharArray(),second.toCharArray(),0,0));
        System.out.println("Total Top down recursive function calls is " + countTD);

        solveLCSBU(first.toCharArray(),second.toCharArray());
    }
    /*
        String first  = "BDCAABCDBA";
        String second =  "ABCBDABCBDACB";
        LCS lcs = new LCS();
        lcs.findLCSHelper(first,second);
     */
}
