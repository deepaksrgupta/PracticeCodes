import java.util.ArrayList;

public class KMP {

        public static int[] getLPS(char []str){

            int lps[] = new int[str.length];

            int index = 0;

            for(int i = 1;i < str.length; ) {
                if(str[i] == str[index]){
                    lps[i] = index + 1;
                    i++;
                    index++;
                }else{

                    if(index!=0){
                        index = lps[index-1];
                    }else{
                        lps[i] = 0;
                        i++;
                    }
                }
            }

            return lps;
        }

        public static ArrayList<Integer> KMPSearch(char []str,char []text) {
            ArrayList<Integer> ll = new ArrayList<>();

            int[] lps = getLPS(str);

            int index = 0;
            for (int i=0; i < text.length ; ) {

                if(index == str.length) {
                    ll.add(i-index);
                    index = 0;
                }

                if(str[index] == text[i]) {
                    index++;
                    i++;
                }else{
                    if (index != 0) {
                        index = lps[index-1];
                    }else{
                        i++;
                    }
                }
            }

            if( index == str.length) {
                ll.add(text.length - index -1);
            }

            return ll;
        }

        public static void main(String args[]) {
            char[] pattern = "ABABAC".toCharArray();
            char[] text = "ABABABCABABABACABABABCABABAC".toCharArray();
            ArrayList<Integer> ListOfIndex = KMPSearch(pattern,text);

            if(ListOfIndex.size() == 0) {
                System.out.println("No pattern found");
            }else{
                System.out.println("Pattern found at ");

                for (Integer i: ListOfIndex) {
                    System.out.print(i+" ");
                }
            }
        }
}
