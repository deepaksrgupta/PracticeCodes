public class Main
{
    public static int solveLCS(char[] firstArr, char[] secondArr,int first,int second){
        if(first >= firstArr.length || second >= secondArr.length){
            return 0;
        }

        if(firstArr[first] == secondArr[second]){
            return 1 + solveLCS(firstArr,secondArr,first+1,second+1);
        }else {
            return Math.max(solveLCS(firstArr,secondArr,first+1,second),solveLCS(firstArr,secondArr,first,second+1));
        }
    }

    public static void main(String args[]) {
        String first  = "BDCABA";
        String second =  "ABCBDAB";

        System.out.println(solveLCS(first.toCharArray(),second.toCharArray(),0,0));
        System.out.print("ss");
    }
}