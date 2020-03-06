public class FindMissingEleOccursTwiceFromThriceArray {
    public static int  findMissing(int[] A){

        //count number of 1s
        int[] counts = new int[32];
        for(int x : A){
            for (int i = 0 ;i < 32;i++){

                if((x & 1<<i) != 0 ){
                    ++counts[i];
                }
            }
        }


        //cancel out elements from binary rep using mod
        int result  = 0;
        for(int i=0;i<counts.length;i++){
            int temp = counts[i] % 3;

            //this check ensure that element which comes twice will ne considered only once
            if(temp == 2){
                temp = 1;
            }

            result |= temp * (1 << i);
        }

        return result;
    }

    public static void main(String args[]) {
        System.out.println(findMissing(new int[]{2,2,3,3,3,4,4,4}));
    }


}
