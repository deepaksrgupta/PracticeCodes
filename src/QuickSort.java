public class QuickSort {
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
}
