import java.util.Arrays;
import java.util.Random;

public class QuickSelect {
    private boolean isRandomized  = false;
    private final Random random = new Random();

    public void setRandomized(boolean isRandomized){
        this.isRandomized = isRandomized;
    }

    private void swap(int[] a,int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private int getRandomInteger(int range){
        return random.nextInt(range);
    }

    /*
    avg case works in O(n)
    worse case works in O(n * n)
     */
    public int kthSmallestElement(int[] arr, int k){

        if(k <= 0 || arr == null || arr.length == 0 || arr.length < k){
            return -1;
        }

        k--;

        int kSmallestElement  = -1;
        int start = 0;
        int end = arr.length - 1;

        while (true){

            if(start == end && start == k){
                kSmallestElement = arr[k];
                break;
            }

            int pivotIndex = partition(arr, start, end);

            if(pivotIndex == k){
                kSmallestElement = arr[pivotIndex];
                break;
            }

            if(pivotIndex > k){
                end  = pivotIndex - 1;
            }else {
                start = pivotIndex + 1;
            }

        }

        return kSmallestElement;
    }

    private int partition(int[] a,int start, int end){

        //keep tracking of all small elements in left side
        int lowIndex = start;

        //choose last element as pivot
        int pivot  = a[end];

        //if randomization is enabled then get random index and swap it with last element
        if(isRandomized){
            int randomIndex  = getRandomInteger(end - start) + start;
            pivot  = a[randomIndex];
            swap(a, randomIndex, end);
        }

        //keep putting all the less elements in start
        for(int i = start; i < end; i++){

            if(a[i] < pivot) {

                swap(a,i,lowIndex);
                lowIndex++;
            }
        }

        swap(a,lowIndex,end);

        return lowIndex;
    }

    public static void main(String args[]){

        QuickSelect quickSelect = new QuickSelect();
        quickSelect.setRandomized(true);

        for(int j = 0; j < 1000000; j++){

            int[] arr = new int[quickSelect.getRandomInteger(100)];

            for(int i=0;i< arr.length; i++){
                arr[i] = quickSelect.getRandomInteger(100);
            }

            if(arr.length == 0){
                continue;
            }

            int k = quickSelect.getRandomInteger(arr.length);
            if(k == 0){
                continue;
            }

            int kSmallestElement = quickSelect.kthSmallestElement(arr,k);

            Arrays.sort(arr);
            int kSmallestBySort = k-1 <= arr.length -1 ? arr[k-1] : -1;

            if (kSmallestElement != kSmallestBySort) {
                System.out.println(k + " " + kSmallestElement + " " + kSmallestBySort + " " + Arrays.toString(arr));
            }
            else {
                System.out.println("success");
            }

        }


    }
}
