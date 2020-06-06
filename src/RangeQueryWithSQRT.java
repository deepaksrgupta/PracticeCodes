public class RangeQueryWithSQRT {

    private int[] input;
    private int[] sqrtBlocks;
    private int sqrt;


    RangeQueryWithSQRT(int []input){

        if(input == null || input.length == 0){
            this.input = new int[0];
            this.sqrtBlocks = new int[1];
            this.sqrt = 1;
        }else{
            this.input = input;

            sqrt = (int) Math.sqrt(input.length);

            sqrtBlocks = new int[sqrt+2];

            int sum = 0;
            int sqrtIndex = 0;


            sum += input[0];

            for(int i=1;i < input.length; i++){

                if(i%sqrt == 0) {
                    sqrtBlocks[sqrtIndex++] = sum;
                    sum =0;
                }

                sum += input[i];
            }

            if(sum != 0){
                sqrtBlocks[sqrtIndex++] = sum;
            }
        }

    }

    //works in O(1)
    public void updateValueAtIndex(int index, int value){
        int sqrtBlock  = index/sqrt;

        //remove previous value
        int previousValue  = input[index];
        sqrtBlocks[sqrtBlock] -= previousValue;

        //add current value
        input[index] = value;
        sqrtBlocks[sqrtBlock] += value;

    }

    //works in O(sqrt(n))
    public int getSumFromRange(int start, int end){
        int sum = 0;

        for(int i = start; i <= end; ){

            if(i % sqrt == 0 && i + sqrt <= end){
                sum += sqrtBlocks[i/sqrt];
                i += sqrt;
            }else{
                sum += input[i];
                i++;
            }

        }

        return sum;
    }

    /*
    static public void main( String args[] ) {

        int input[] = new int[] {5,8,6,3,2,5,2,6,7,1,7,5,6,2,3,2};
        RangeQueryWithSQRT rangeQueryWithSQRT = new RangeQueryWithSQRT(input);

        int sum = rangeQueryWithSQRT.getSumFromRange(4,11);
        System.out.println(sum);

        rangeQueryWithSQRT.updateValueAtIndex(9,10);
        sum = rangeQueryWithSQRT.getSumFromRange(4,11);
        System.out.println(sum);
    }
     */
}
