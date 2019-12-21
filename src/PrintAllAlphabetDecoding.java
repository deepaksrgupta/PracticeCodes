public class PrintAllAlphabetDecoding
{
    public static char getValidChar(String s){

        int temp = Integer.parseInt(s);

        if(temp <=0 || temp >  26 || s.charAt(0) == '0'){
            return '0';
        }

        return (char)('A'+temp-1);
    }

    public static void PrintEncoding(String input,String output,int index){

        if(index >= input.length())
        {
            System.out.println(output);
            return;
        }

        char current =  getValidChar(input.substring(index,index+1));
        if(current != '0'){
            PrintEncoding(input,output + Character.toString(current),index+1);
        }

        if(index+2<=input.length()) {
            current = getValidChar(input.substring(index, index + 2));
            if (current != '0') {
                PrintEncoding(input, output+ Character.toString(current), index + 2);
            }
        }
    }

    public static void main(String args[]){
        PrintAllAlphabetDecoding t = new PrintAllAlphabetDecoding();
        t.PrintEncoding("12312321212","",0);
    }

}