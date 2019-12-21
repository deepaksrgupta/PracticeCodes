import java.util.*;


public class MaxHeightOf2Pillars {

    public static HashMap<String,Integer> hashMap = new HashMap<>();

    public static int count = 0;

    public static int maxHeightOfPillarsDP(int leftHeight,int rightHeight,int maxHeightDP ,int[] a,int index)
    {
        count = count+1;

        String key = String.valueOf(leftHeight)+ " " + String.valueOf(rightHeight) + " " + String.valueOf(maxHeightDP) + " " + String.valueOf(index);

        if(hashMap.containsKey(key))
        {
            int val = hashMap.get(key);
            hashMap.put(key,val+1);
            return val;
        }
        else hashMap.put(key,1);

        if(leftHeight == rightHeight && leftHeight > maxHeightDP)
        {
            return leftHeight;
        }

        if(index == a.length) {
            return 0;
        }

        int third = maxHeightOfPillarsDP(leftHeight+a[index],rightHeight,maxHeightDP,a,index+1);
        int second = maxHeightOfPillarsDP(leftHeight,rightHeight+a[index],maxHeightDP,a,index+1);
        int first = maxHeightOfPillarsDP(leftHeight,rightHeight,maxHeightDP,a,index+1);

        //System.out.println(first+" "+second+" "+third);

        return Math.max(Math.max(first,second),third);
    }


    public static int maxHeightRecursive = 0;

    public static void maxHeightOfPillarRecursive(int leftHeight,int rightHeight,int[] arr,int index){

        count = count+1;

        if(leftHeight == rightHeight && leftHeight >maxHeightRecursive)
        {
            maxHeightRecursive = leftHeight;
        }

        if(index == arr.length)
            return;

        maxHeightOfPillarRecursive(leftHeight+arr[index],rightHeight,arr,index+1);
        maxHeightOfPillarRecursive(leftHeight,rightHeight+arr[index],arr,index+1);
        maxHeightOfPillarRecursive(leftHeight+arr[index],rightHeight,arr,index+1);

    }

    public static void main(String[] args) throws Exception {

        //364 function calls without dp

        int[] array = {1,2,3,4,6,5,3,7,5};

        //int[] array = {1,2,3,4,6};

        //int[] array = {2,4,6};

        System.out.println(maxHeightOfPillarsDP(0,0,0,array,0));

        //maxHeightOfPillarRecursive(0,0,array,0);
        //System.out.println(maxHeightRecursive);

        for(Map.Entry<String,Integer> entry: hashMap.entrySet())
        {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        System.out.println("Total function calls "+count);
    }
}
