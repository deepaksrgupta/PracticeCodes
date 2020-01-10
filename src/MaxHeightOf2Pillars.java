import java.util.*;


public class MaxHeightOf2Pillars {
    static int countR  = 0;
    public static int solve2PillarsR(int[] arr, int lh, int rh, int currentMax, int currentIndex){

        countR++;
        if(lh == rh){
            currentMax = lh;
        }

        if(currentIndex == arr.length){
            return currentMax;
        }

        int leftTaken   = solve2PillarsR(arr,lh+arr[currentIndex],rh,currentMax,currentIndex+1);

        int rightTaken   = solve2PillarsR(arr,lh,rh+ arr[currentIndex],currentMax,currentIndex+1);

        int noneTaken   = solve2PillarsR(arr,lh,rh,currentMax,currentIndex+1);

        return Math.max(Math.max(leftTaken,rightTaken),noneTaken);
    }

    static int countTD  = 0;
    static HashMap<String,Integer> hashMap = new HashMap<>();
    public static int solve2PillarsTD(int[] arr, int lh, int rh, int currentMax, int currentIndex){

        countTD++;

        if(lh == rh && lh > currentMax){
            currentMax = lh;
        }

        if(currentIndex == arr.length){
            return currentMax;
        }

        int leftTaken = 0;
        if(!hashMap.containsKey((lh+arr[currentIndex]) + " " + rh + " " + (currentIndex+1))){
            leftTaken   = solve2PillarsTD(arr,lh+arr[currentIndex],rh,currentMax,currentIndex+1);
            hashMap.put((lh+arr[currentIndex]) + " " + rh + " " + (currentIndex+1),leftTaken);
        }

        leftTaken = hashMap.get((lh+arr[currentIndex]) + " " + rh + " " + (currentIndex+1));

        int rightTaken = 0;
        if(!hashMap.containsKey(lh+ " " + (rh+ arr[currentIndex]) + " " + (currentIndex+1))){
            rightTaken   = solve2PillarsTD(arr,lh,rh+ arr[currentIndex],currentMax,currentIndex+1);
            hashMap.put(lh+ " " + (rh+ arr[currentIndex]) + " " + (currentIndex+1),rightTaken);
        }

        rightTaken = hashMap.get(lh+ " " + (rh+ arr[currentIndex]) + " " + (currentIndex+1));

        int noneTaken = 0;
        if(!hashMap.containsKey(lh+ " " + rh + " " + (currentIndex+1))){
            noneTaken   = solve2PillarsTD(arr,lh,rh,currentMax,currentIndex+1);
            hashMap.put(lh+ " " + rh + " " + (currentIndex+1),noneTaken);
        }
        noneTaken = hashMap.get(lh+ " " + rh + " " + (currentIndex+1));


        return Math.max(Math.max(leftTaken,rightTaken),noneTaken);
    }

    public static int solve2PillarsBU(int[] arr){
        int sum = 0;
        for (int a : arr){
            sum+= a;
        }
        int [][][] table = new int[arr.length][sum][sum];

        for(int i = 0 ;i < arr.length; i++){

        }

        return -1;
    }

    public static void main(String args[]) {

        //int arr[] = {0,2,1,3,5,4,6,2,3};
        int arr[] = {4,2,1,3};

        System.out.println(solve2PillarsTD(arr,0,0,0,0));
        System.out.println(countTD);

        System.out.println(solve2PillarsR(arr,0,0,0,0));
        System.out.println(countR);

        HashMap<String, ArrayList<String>> newHashMap = new HashMap<>();
        for (Map.Entry<String,Integer>  e: hashMap.entrySet()) {
            //System.out.println(e.getKey()+ " ----> "+e.getValue());
            String [] s = e.getKey().split(" ");

            String lh = s[0];
            String rh = s[1];
            String index = s[2];
            Integer maxHeight = e.getValue();

            if(newHashMap.containsKey(index)){

                ArrayList<String> val = newHashMap.get(index);
                val.add(String.format("(l:%1$s r:%1$s m:%2$s)",lh,rh,maxHeight));
                newHashMap.put(index,val);

            }else{
                ArrayList<String> val = new ArrayList<>();
                val.add(String.format("(l:%1$s r:%1$s m:%2$s)",lh,rh,maxHeight));
                newHashMap.put(index,val);
            }
        }

        for (Map.Entry<String,ArrayList<String>>  e: newHashMap.entrySet()) {
            System.out.print(e.getKey()+" ");
            for (String s: e.getValue()){
                System.out.print(s+ " ");
            }

            System.out.println();
        }
    }
}
