import java.util.*;
public class FindShortestSuperSequence  {
    public static String findShortestSuperSequence(char seq[],char text[]){

        if(text.length < seq.length){
            return "";
        }

        String shortestSuperSequence  = String.valueOf(text);

        HashSet<Character>  cache = new HashSet<>();
        for(char c : seq){
            cache.add(c);
        }

        int start  = 0;
        HashMap<Character,Integer> hashMap = new HashMap<>();

        int end  = seq.length-1;
        for(int i = start;i < end; i++){
            if(cache.contains(text[i])){

                if(hashMap.containsKey(text[i])){
                    hashMap.put(text[i],hashMap.get(text[i])+1);
                }else{
                    hashMap.put(text[i],1);
                }


            }
        }

        boolean isFound = false;

        while (end < text.length){

            if(cache.contains(text[end])){
                if(hashMap.containsKey(text[end])){
                    hashMap.put(text[end],hashMap.get(text[end])+1);
                }else{
                    hashMap.put(text[end],1);
                }
            }


            while (hashMap.size() == seq.length){

                isFound = true;

                //start
                //end
                //has a sub string length
                if(shortestSuperSequence.length() > end-start+1){
                    shortestSuperSequence = String.valueOf(text).substring(start,end+1);
                }

                //reduce start from here an check for remaining math count then update the shortestSuperSequence
                if(cache.contains(text[start])){
                    int val = hashMap.get(text[start]);
                    if(val == 1){
                        hashMap.remove(text[start]);
                    }else{
                        hashMap.put(text[start],val-1);
                    }

                }

                start++;
            }

            end++;
        }

        return isFound ? shortestSuperSequence : "";
    }

    public static void main(String[] args) {

        String seq  = "159";
        String text = "7590213579115";

        System.out.println(findShortestSuperSequence(seq.toCharArray(),text.toCharArray()));

    }
}
