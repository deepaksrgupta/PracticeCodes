import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    private boolean isRandomized  = false;
    private final Random random = new Random();

    public void setRandomized(boolean isRandomized){
        this.isRandomized = isRandomized;
    }

    public void quickSort(int a[], int low, int high){

        if(high>low){

            int pivot  = partition(a,low,high);
            quickSort(a,low,pivot-1);
            quickSort(a,pivot+1,high);

        }
    }

    private int partition(int[] a,int start, int end){

        //keep tracking of all small elements in left side
        int lowIndex = start;

        //choose last element as pivot
        int pivot  = a[end];

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

    private void swap(int[] a,int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private int getRandomInteger(int range){
        return random.nextInt(range);
    }

    public static void main(String args[]){

        QuickSort quickSort = new QuickSort();
        quickSort.setRandomized(true);

        int[] arr = new int[quickSort.getRandomInteger(100)];

        for(int i=0;i< arr.length; i++){
            arr[i] = quickSort.getRandomInteger(100);
        }

        quickSort.quickSort(arr,0 , arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
