public class SmallestPositiveNumber {
    static int getDifferentNumber(int[] arr) {
        // your code goes here

        //marking elements visited
        for(int i=0;i < arr.length; i++){

            // 1, -2, 3, 4
            int currentIndex = Math.abs(arr[i]);

            if(currentIndex >= arr.length) continue;



            if(arr[currentIndex] == 0) {
                arr[currentIndex] = Integer.MIN_VALUE;
            } if(arr[currentIndex] == Integer.MIN_VALUE) {
                arr[0] *=  -1;
            }else{
                arr[currentIndex] *= -1;
            }
        }


        int  i = 0;
        for( ; i <arr.length; i++){
            if(arr[i] > 0){
                return i;
            }
        }

        return i;
    }

    public static void main(String[] args) {

        int a[] = {0,1,3,4};

        System.out.println(getDifferentNumber(a));

    }
}
