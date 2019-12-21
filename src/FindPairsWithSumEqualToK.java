import java.util.*;

public class FindPairsWithSumEqualToK {

    public static void solvePair(int[] arr,int k){

        if(arr.length%2 != 0)
        {
            System.out.println("Array length is not even");
            return;
        }

        int modArr[] = new int[arr.length];


        for (int i=0;i<arr.length;i++)
            modArr[i] = arr[i]%k;

        HashMap<Integer,Integer> hm = new HashMap<>();


        for(int i=0;i<modArr.length;i++)
        {
            if(hm.containsKey(k-modArr[i]))
            {
                Helper.display(arr[i]+ " "+ arr[hm.get(k-modArr[i])]);

                hm.remove(k-modArr[i]);
            }
            else if(modArr[i] == 0  && hm.containsKey(modArr[i]))
            {
                Helper.display(arr[i]+ " "+ arr[hm.get(modArr[i])]);
                hm.remove(0);
            }
            else hm.put(modArr[i],i);
        }

        if(hm.size() == 0)
            Helper.display("Given array contains pair of element whose sum is k");
        else Helper.display("Some issue with the Algorithm");

    }

    public static void main(String[] args) {

        int arr[] = {92,75,34,65,48,45,35,46,20,20};

        int k = 10;


        solvePair(arr,k);

    }
}
