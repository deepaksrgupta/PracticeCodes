public class MinMax {

    class Pair {
        int min;
        int max;

        Pair(int min, int max){
            this.min = min;
            this.max = max;
        }

        public int getMax() {
            return max;
        }

        public int getMin() {
            return min;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public void setMin(int min) {
            this.min = min;
        }
    }

    public Pair findMinMax(int[] arr){

        if(arr == null || arr.length == 0){
            return null;
        }

        return recursiveMinMax(arr, 0, arr.length - 1);
    }

    /*
    T(n)=2.T(n2)+2=2.(2.T(n4)+2)+2.....= 3n2−2 --> total comparison
     */
    private Pair recursiveMinMax(int[] arr, int low, int high){

        if(low == high){
            return new Pair(arr[low], arr[low]);
        }

        int mid = low + ((high - low) / 2);

        //get left pair
        Pair leftSubProblem = recursiveMinMax(arr,low, mid);

        //get right pair
        Pair rightSubProblem = recursiveMinMax(arr, mid + 1, high);


        //return final pair
        leftSubProblem.setMin(Math.min(leftSubProblem.getMin(),rightSubProblem.getMin()));
        leftSubProblem.setMax(Math.max(leftSubProblem.getMax(),rightSubProblem.getMax()));

        return leftSubProblem;
    }

    public static void main(String args[]){
        MinMax m = new MinMax();

        Pair minMax = m.findMinMax(new int[]{1,2,3,100,43,-100,4,5});

        System.out.println(minMax.getMin()+" "+ minMax.getMax());
    }
}
